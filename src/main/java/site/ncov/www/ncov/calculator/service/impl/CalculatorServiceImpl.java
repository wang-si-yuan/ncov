package site.ncov.www.ncov.calculator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.calculator.service.CalculatorService;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.report.service.DetectionService;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;
import site.ncov.www.ncov.vaccination.service.VaccinationService;

import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private UserService userService;

    @Autowired
    private DetectionService detectionService;

    @Autowired
    private VaccinationService vaccinationService;

    @Override
    public Double queryCode() throws FileNotFoundException, WebException {
        User curr = userService.getCurr();
        Integer L = 3 - curr.getUserStatus().getValue();
        LocalDate detectionDate = detectionService.detectionDate(curr.getUserId());
        CurrVaccinationsDto c = vaccinationService.getCurr();
        Double H = (double)c.getNum()/c.getTotal();
        Double code = 0d;
        code += L*20d;
        if (detectionDate!=null) {
            double D = (LocalDate.now().toEpochDay() - detectionDate.toEpochDay());
            code += 30d / (Math.floor(D / 14d) + 1d);
        }
        code += 10d*H;

        return code;
    }
}