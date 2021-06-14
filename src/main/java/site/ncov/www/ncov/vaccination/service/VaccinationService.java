package site.ncov.www.ncov.vaccination.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinationsQuery;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;
import site.ncov.www.ncov.vaccination.controller.dto.VaccinationsDto;
import site.ncov.www.ncov.vaccination.domain.Vaccination;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

public interface VaccinationService {
    CurrVaccinationsDto getCurr() throws FileNotFoundException, WebException;

    Page<VaccinationsDto> queryVaccination(Vaccination vaccination, Long curr);

    void addVaccinations(List<Vaccination> vaccinationList);

    void removeVaccination(Integer id);
}
