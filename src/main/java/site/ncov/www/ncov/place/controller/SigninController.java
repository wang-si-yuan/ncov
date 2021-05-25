package site.ncov.www.ncov.place.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.HttpResult;
import site.ncov.www.ncov.common.model.entity.User;
import site.ncov.www.ncov.common.service.UserService;
import site.ncov.www.ncov.place.model.entity.Signin;
import site.ncov.www.ncov.place.model.param.AutoSigninParam;
import site.ncov.www.ncov.place.model.vo.SigninVo;
import site.ncov.www.ncov.place.service.SigninService;

import java.io.FileNotFoundException;
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

    @ApiOperation("自动更新地点")
    @RequestMapping(value = "/autoSignin",method = {RequestMethod.POST})
    public HttpResult autoSignin(AutoSigninParam autoSigninParam) throws FileNotFoundException, WebException {
        User curr = userService.getCurr();
        Signin signin = autoSigninParam.transEntity();
        signin.setSigninUser(curr);

        if (signinService.save(signin.transVo())){
            return HttpResult.ok(signin);
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

}

