package site.ncov.www.ncov.vaccination.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;

import java.util.List;

public interface VaccinesService {

    void addVaccines(List<Vaccines> vaccinesList);

    Page<VaccinesVo> queryVaccines(Vaccines vaccines, Long curr);

    Vaccines queryVaccinesByCode(String code);
}
