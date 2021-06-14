package site.ncov.www.ncov.report.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.report.controller.cqe.DetectionCommand;
import site.ncov.www.ncov.report.controller.cqe.DetectionQuery;
import site.ncov.www.ncov.report.controller.dto.DetectionCurrDto;
import site.ncov.www.ncov.report.controller.dto.DetectionsDto;
import site.ncov.www.ncov.report.domain.Detection;
import site.ncov.www.ncov.report.service.DetectionService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/report")
public class DetectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private DetectionService detectionService;

    @ApiOperation("查询报告信息")
    @RequestMapping(value = "/queryDetections", method = {RequestMethod.GET})
    public HttpResult queryDetections(DetectionQuery detectionQuery) {
        Page<DetectionsDto> detectionsDtoPage = detectionService.queryDetections(detectionQuery.transEntity(userService::getUserByIdCard), detectionQuery.getCurr());
        return HttpResult.ok(detectionsDtoPage);
    }

    @ApiOperation("添加报告信息")
    @RequestMapping(value = "/addDetections", method = {RequestMethod.POST})
    public HttpResult addDetections(MultipartFile detections) throws IOException {
        List<Detection> detectionList = new DetectionCommand().transEntityList(userService::getUserByIdCard, detections);
        detectionService.addDetections(detectionList);
        return HttpResult.ok();
    }

    @ApiOperation("删除报告信息")
    @RequestMapping(value = "/removeDetections", method = {RequestMethod.POST})
    public HttpResult removeDetections(Integer id) {
        detectionService.removeDetections(id);
        return HttpResult.ok();
    }

    @ApiOperation("查询报告信息")
    @RequestMapping(value = "/getCurr", method = {RequestMethod.GET})
    public HttpResult getCurr() throws FileNotFoundException, WebException {
        User curr = userService.getCurr();
        DetectionCurrDto detectionCurrDto = detectionService.getCurr(curr.getUserId());
        return HttpResult.ok(detectionCurrDto);
    }

}

