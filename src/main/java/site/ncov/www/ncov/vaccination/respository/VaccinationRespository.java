package site.ncov.www.ncov.vaccination.respository;

import site.ncov.www.ncov.vaccination.domain.Vaccinations;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
