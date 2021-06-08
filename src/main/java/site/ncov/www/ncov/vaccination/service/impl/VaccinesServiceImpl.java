package site.ncov.www.ncov.vaccination.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.vaccination.controller.dto.CurrVaccinationsDto;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.respository.VaccinesRespository;
import site.ncov.www.ncov.vaccination.service.VaccinesService;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class VaccinesServiceImpl implements VaccinesService {

    @Autowired
    private VaccinesRespository vaccinesRespository;

    @Override
    public void addVaccines(Vaccines transEntity) {
        vaccinesRespository.addVaccines(transEntity);
    }
}
