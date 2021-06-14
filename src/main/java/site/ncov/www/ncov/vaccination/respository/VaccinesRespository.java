package site.ncov.www.ncov.vaccination.respository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 疫苗信息表 服务类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
public interface VaccinesRespository extends IService<VaccinesVo> {

    public Vaccines queryVaccines(Integer id);

    public void addVaccines(List<Vaccines> vaccinesList);

    Page<VaccinesVo> queryVaccinesList(Vaccines vaccines, Long curr);

    Vaccines queryVaccinesByCode(String code);
}
