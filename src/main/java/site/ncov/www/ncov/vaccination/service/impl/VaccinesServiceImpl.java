package site.ncov.www.ncov.vaccination.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;
import site.ncov.www.ncov.vaccination.respository.VaccinesRespository;
import site.ncov.www.ncov.vaccination.service.VaccinesService;

import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class VaccinesServiceImpl implements VaccinesService {

    @Autowired
    private VaccinesRespository vaccinesRespository;

    @Override
    public void addVaccines(List<Vaccines> vaccinesList) {
        vaccinesRespository.addVaccines(vaccinesList);
    }

    @Override
    public Page<VaccinesVo> queryVaccines(Vaccines vaccines, Long curr) {
        return vaccinesRespository.queryVaccinesList(vaccines, curr);
    }

    @Override
    public Vaccines queryVaccinesByCode(String code) {
        return vaccinesRespository.queryVaccinesByCode(code);
    }
}
