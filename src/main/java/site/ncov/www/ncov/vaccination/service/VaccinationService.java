package site.ncov.www.ncov.vaccination.service;

import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;

import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */

public interface VaccinationService {
    CurrVaccinationsDto getCurr() throws FileNotFoundException, WebException;
}
