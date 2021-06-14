package site.ncov.www.ncov.calculator.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.ncov.www.ncov.calculator.service.CalculatorService;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.exception.WebException;

import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;


    @ApiOperation("查询呢code")
    @RequestMapping(value = "/queryCode",method = {RequestMethod.GET})
    public HttpResult queryCode() throws FileNotFoundException, WebException {
        Double code = calculatorService.queryCode();
        return HttpResult.ok(code);
    }
}
