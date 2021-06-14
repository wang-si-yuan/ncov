package site.ncov.www.ncov.vaccination.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinationsQuery;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;
import site.ncov.www.ncov.vaccination.controller.dto.VaccinationsDto;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.domain.Vaccinations;
import site.ncov.www.ncov.vaccination.respository.VaccinationRespository;
import site.ncov.www.ncov.vaccination.service.VaccinationService;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class VaccinationServiceImpl implements VaccinationService {

    @Autowired
    private VaccinationRespository vaccinationRespository;

    @Autowired
    private UserService userService;

    @Override
    public CurrVaccinationsDto getCurr() throws FileNotFoundException, WebException {
        Vaccinations vaccinations = vaccinationRespository.queryVaccinations(userService.getCurr().getUserId());
        return CurrVaccinationsDto.transCurrVaccinationsDto(vaccinations);
    }

    @Override
    public Page<VaccinationsDto> queryVaccination(Vaccination vaccination, Long curr) {
        return vaccinationRespository.queryVaccination(vaccination, curr);
    }

    @Override
    public void addVaccinations(List<Vaccination> vaccinationList) {
        vaccinationRespository.addVaccinations(vaccinationList);
    }

    @Override
    public void removeVaccination(Integer id) {
        vaccinationRespository.removeById(id);
    }
}
