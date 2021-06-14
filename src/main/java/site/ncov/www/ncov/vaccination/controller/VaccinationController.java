package site.ncov.www.ncov.vaccination.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinationsCommand;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinationsQuery;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;
import site.ncov.www.ncov.vaccination.controller.dto.VaccinationsDto;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.service.VaccinationService;
import site.ncov.www.ncov.vaccination.service.VaccinesService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @Autowired
    private VaccinesService vaccinesService;

    @ApiOperation("查询接种信息")
    @RequestMapping(value = "/getCurrVaccination", method = {RequestMethod.GET})
    public HttpResult getCurrVaccination() throws FileNotFoundException, WebException {
        CurrVaccinationsDto curr = vaccinationService.getCurr();
        return HttpResult.ok(curr);
    }

    @ApiOperation("查询接种信息")
    @RequestMapping(value = "/queryVaccination", method = {RequestMethod.GET})
    public HttpResult queryVaccination(VaccinationsQuery vaccinationsQuery) {
        Page<VaccinationsDto> vaccinationsDtoPage
                = vaccinationService.queryVaccination(
                        vaccinationsQuery.transEntity(userService::getUserByIdCard, vaccinesService::queryVaccinesByCode),
                vaccinationsQuery.getCurr());
        return HttpResult.ok(vaccinationsDtoPage);
    }

    @ApiOperation("添加接种信息")
    @RequestMapping(value = "/addVaccinations", method = {RequestMethod.POST})
    public HttpResult addVaccinations(MultipartFile vaccinations) throws IOException {
        VaccinationsCommand vaccinationsCommand = new VaccinationsCommand();
        List<Vaccination> vaccinationList = vaccinationsCommand.transEntityList(userService::getUserByIdCard, vaccinesService::queryVaccinesByCode, vaccinations);
        vaccinationService.addVaccinations(vaccinationList);
        return HttpResult.ok();
    }

    @ApiOperation("删除接种信息")
    @RequestMapping(value = "/removeVaccination", method = {RequestMethod.POST})
    public HttpResult removeVaccination(Integer id) {
        vaccinationService.removeVaccination(id);
        return HttpResult.ok();
    }



}

