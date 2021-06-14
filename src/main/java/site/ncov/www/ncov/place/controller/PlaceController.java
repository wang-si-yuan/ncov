package site.ncov.www.ncov.place.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import site.ncov.www.ncov.place.model.dto.AddPlaceDto;
import site.ncov.www.ncov.place.model.dto.ExcelMapping;
import site.ncov.www.ncov.place.model.dto.PlaceDto;
import site.ncov.www.ncov.place.model.vo.PlaceVo;
import site.ncov.www.ncov.place.service.PlaceService;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 地点 前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @ApiOperation("管理员添加地点")
    @RequestMapping(value = "/addPlaces",method = {RequestMethod.POST})
    public HttpResult AddPlaces(MultipartFile places) throws IOException {
        List<AddPlaceDto> addPlaceDtoList = ExcelMapping.transAddPlaceDto(places);
        List<PlaceVo> placeVoList = AddPlaceDto.transVoList(addPlaceDtoList);
        placeService.saveBatch(placeVoList);
        return HttpResult.ok();
    }

    @ApiOperation("管理员查询账户列表")
    @RequestMapping(value = "/queryPlaceList",method = {RequestMethod.GET})
    public HttpResult  queryPlaceList(PlaceDto placeDto)  {
        Page<PlaceVo> page = placeService.lambdaQuery()
                .like(!StringUtils.isEmpty(placeDto.getPlaceTitle()), PlaceVo::getPlaceTitle, placeDto.getPlaceTitle())
                .page(new Page<PlaceVo>(placeDto.getCurr(), 10));
        return HttpResult.ok(page);
    }

    @ApiOperation("删除地点")
    @RequestMapping(value = "/removePlace", method = {RequestMethod.POST})
    public HttpResult removePlace(Integer id) {
        placeService.removeById(id);
        return HttpResult.ok();
    }



}

