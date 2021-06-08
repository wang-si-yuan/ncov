package site.ncov.www.ncov.vaccination.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;
import site.ncov.www.ncov.vaccination.service.VaccinationService;
import site.ncov.www.ncov.vaccination.service.VaccinesService;

import java.io.FileNotFoundException;

/**
 * <p>
 * 疫苗接种信息 前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
@RestController
@Api(tags = "疫苗接种信息接口")
@RequestMapping("/vaccination/vaccinationVo")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @ApiOperation("查询接种信息")
    @RequestMapping(value = "/getCurrVaccination", method = {RequestMethod.GET})
    public HttpResult getCurrVaccination() throws FileNotFoundException, WebException {
        CurrVaccinationsDto curr = vaccinationService.getCurr();
        return HttpResult.ok(curr);
    }

}

