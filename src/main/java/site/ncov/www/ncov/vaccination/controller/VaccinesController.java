package site.ncov.www.ncov.vaccination.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinesCommand;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinesQuery;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;
import site.ncov.www.ncov.vaccination.service.VaccinesService;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 疫苗信息表 前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */

@Api(tags="疫苗模块")
@RestController
@RequestMapping("/vaccination")
public class VaccinesController {

    @Autowired
    private VaccinesService vaccinesService;

    @ApiOperation("添加疫苗")
    @RequestMapping(value = "/addVaccines", method = {RequestMethod.POST})
    public HttpResult addVaccines(MultipartFile vaccines) throws IOException {
        List<Vaccines> vaccinesList = VaccinesCommand.transEntityList(vaccines);
        vaccinesService.addVaccines(vaccinesList);
        return HttpResult.ok();
    }

    @ApiOperation("查询疫苗")
    @RequestMapping(value = "/queryVaccines", method = {RequestMethod.GET})
    public HttpResult queryVaccines(VaccinesQuery vaccinesQuery) {
        Page<VaccinesVo> vaccinesVoPage = vaccinesService.queryVaccines(vaccinesQuery.transEntity(), vaccinesQuery.getCurr());
        return HttpResult.ok(vaccinesVoPage);
    }
}

