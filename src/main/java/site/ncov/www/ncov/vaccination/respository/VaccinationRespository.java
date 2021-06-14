package site.ncov.www.ncov.vaccination.respository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinationsQuery;
import site.ncov.www.ncov.vaccination.controller.dto.VaccinationsDto;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.domain.Vaccinations;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 疫苗接种信息 服务类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
public interface VaccinationRespository extends IService<VaccinationVo> {

    Vaccinations queryVaccinations(Integer id);

    Page<VaccinationsDto> queryVaccination(Vaccination vaccination, Long curr);

    void addVaccinations(List<Vaccination> vaccinationList);
}
