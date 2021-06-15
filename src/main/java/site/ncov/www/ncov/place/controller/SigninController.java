package site.ncov.www.ncov.place.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.place.domain.vo.RiskVo;
import site.ncov.www.ncov.place.model.dto.AddRiskDto;
import site.ncov.www.ncov.place.model.dto.ExcelMapping;
import site.ncov.www.ncov.place.model.entity.Signin;
import site.ncov.www.ncov.place.model.entity.SigninType;
import site.ncov.www.ncov.place.model.param.AutoSigninParam;
import site.ncov.www.ncov.place.model.param.SigninParam;
import site.ncov.www.ncov.place.model.vo.PlaceVo;
import site.ncov.www.ncov.place.model.vo.SigninVo;
import site.ncov.www.ncov.place.respository.RiskRespository;
import site.ncov.www.ncov.place.service.PlaceService;
import site.ncov.www.ncov.place.service.SigninService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-21
 */

@Api(tags="地点签到模块")
@RestController
@RequestMapping("/place")
public class SigninController {

    @Autowired
    private SigninService signinService;

    @Autowired
    private UserService userService;

    @Autowired
    private RiskRespository riskRespository;

    @Autowired
    private PlaceService placeService;

    @ApiOperation("自动更新地点")
    @RequestMapping(value = "/autoSignin",method = {RequestMethod.POST})
    public HttpResult autoSignin(AutoSigninParam autoSigninParam) throws FileNotFoundException, WebException {
        User curr = userService.getCurr();
        Signin signin = autoSigninParam.transEntity();
        curr.setUserStatus(riskRespository.USER_STATUS(autoSigninParam.getSigninDistrict()));
        signin.setSigninUser(curr);
        userService.saveOrUpdate(curr.transVo());
        if (signinService.save(signin.transVo())){
            return HttpResult.ok(signin);
        } else {
            return HttpResult.error();
        }

    }

    @ApiOperation("更新地点")
    @RequestMapping(value = "/signin",method = {RequestMethod.POST})
    public HttpResult signin(SigninParam signinParam) throws FileNotFoundException, WebException {
        User curr = userService.getCurr();
        SigninVo signinVo = signinParam.transVo();
        curr.setUserStatus(riskRespository.USER_STATUS(signinVo.getSigninDistrict()));
        signinVo.setSigninUser(curr.getUserId());
        userService.saveOrUpdate(curr.transVo());
        if (signinService.save(signinVo)){
            return HttpResult.ok(signinVo);
        } else {
            return HttpResult.error();
        }

    }

    @ApiOperation("获取7天内到访的城市")
    @RequestMapping(value = "/weekPlace",method = {RequestMethod.GET})
    public HttpResult weekPlace() throws FileNotFoundException, WebException {
        User user = userService.getCurr();
        List<SigninVo> signinVoList = signinService.lambdaQuery().select(SigninVo::getSignninCity)
                .eq(SigninVo::getSigninUser, user.getUserId()).between(SigninVo::getCreateTime, LocalDateTime.now().minusWeeks(1), LocalDateTime.now()).list()
                .stream().distinct().collect(Collectors.toList());

        List<String> citys = new ArrayList<>();
        signinVoList.forEach(signinVo -> citys.add(signinVo.getSignninCity()));

        return HttpResult.ok(citys);
    }

    @ApiOperation("获取7天内到访的地点")
    @RequestMapping(value = "/weekPlaces",method = {RequestMethod.GET})
    public HttpResult weekPlaces() throws FileNotFoundException, WebException {
        User user = userService.getCurr();
        List<SigninVo> signinVoList = signinService.lambdaQuery().select(SigninVo::getSigninPlace).eq(SigninVo::getSigninType, SigninType.SIGNIN)
                .eq(SigninVo::getSigninUser, user.getUserId()).between(SigninVo::getCreateTime, LocalDateTime.now().minusWeeks(1), LocalDateTime.now()).list()
                .stream().distinct().collect(Collectors.toList());
        if (signinVoList.isEmpty()) {
            return HttpResult.ok();
        } else {
            List<String> places = new ArrayList<>();
            signinVoList.forEach(signinVo -> {
                String x = placeService.lambdaQuery().select(PlaceVo::getPlaceTitle).eq(PlaceVo::getPlaceId, signinVo.getSigninPlace()).one().getPlaceTitle();
                places.add(x);
            });
            return HttpResult.ok(places);
        }
    }

    @ApiOperation("设置风险城市")
    @RequestMapping(value = "/addRisk",method = {RequestMethod.POST})
    public HttpResult addRisk(MultipartFile risks) throws IOException, WebException {
        List<AddRiskDto> addRiskDtoList = ExcelMapping.transAddRiskDto(risks);
        List<RiskVo> riskVoList = AddRiskDto.transVoList(addRiskDtoList);
        riskVoList.forEach(riskVo -> {
            RiskVo one = riskRespository.lambdaQuery().eq(RiskVo::getRiskPlace, riskVo.getRiskPlace()).one();
            if (one != null) {
                riskRespository.lambdaUpdate().eq(RiskVo::getRiskPlace, riskVo.getRiskPlace()).set(RiskVo::getRiskLevel, riskVo.getRiskLevel()).update();
            } else {
                riskRespository.save(riskVo);
            }
            List<SigninVo> list = signinService.lambdaQuery().eq(SigninVo::getSigninDistrict, riskVo.getRiskPlace()).list();
            list.forEach(x -> {
                userService.lambdaUpdate().eq(UserVo::getUserId, x.getSigninUser()).set(UserVo::getUserStatus, riskVo.getRiskLevel()-1).update();
            });

        });

        return HttpResult.ok();
    }

    @ApiOperation("设置风险城市")
    @RequestMapping(value = "/queryRisk",method = {RequestMethod.GET})
    public HttpResult queryRisk(Long curr) {
        Page<RiskVo> page = riskRespository.lambdaQuery().page(new Page<>(curr, 10));
        return HttpResult.ok(page);
    }

}

