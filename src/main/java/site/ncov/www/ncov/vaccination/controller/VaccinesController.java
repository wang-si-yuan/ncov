package site.ncov.www.ncov.vaccination.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinesCommand;
import site.ncov.www.ncov.vaccination.service.VaccinesService;

/**
 * <p>
 * 疫苗信息表 前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/vaccination/")
public class VaccinesController {

    @Autowired
    private VaccinesService vaccinesService;

    @RequestMapping("/addVaccines")
    public HttpResult addVaccines(VaccinesCommand vaccinesCommand) {
        vaccinesService.addVaccines(vaccinesCommand.transEntity());
        return HttpResult.ok();
    }
}

