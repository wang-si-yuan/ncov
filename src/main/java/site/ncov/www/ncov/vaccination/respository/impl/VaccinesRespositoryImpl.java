package site.ncov.www.ncov.vaccination.respository.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;
import site.ncov.www.ncov.vaccination.mapper.VaccinesMapper;
import site.ncov.www.ncov.vaccination.respository.VaccinesRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 疫苗信息表 服务实现类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
@Service
public class VaccinesRespositoryImpl extends ServiceImpl<VaccinesMapper, VaccinesVo> implements VaccinesRespository {

    @Override
    public Vaccines queryVaccines(Integer id) {
        VaccinesVo vaccinesVo = this.getById(id);
        return Vaccines.transEntity(vaccinesVo);
    }

    @Override
    public void addVaccines(List<Vaccines> vaccinesList) {
        this.saveBatch(Vaccines.transVaccinesVos(vaccinesList));
    }

    @Override
    public Page<VaccinesVo> queryVaccinesList(Vaccines vaccines, Long curr) {
        return this.lambdaQuery()
                .like(!StringUtils.isEmpty(vaccines.getVaccinesCode()), VaccinesVo::getVaccinesCode, vaccines.getVaccinesCode())
                .like(!StringUtils.isEmpty(vaccines.getVaccinesType()), VaccinesVo::getVaccinesType, vaccines.getVaccinesType())
                .eq(vaccines.getVaccinesNum() != null, VaccinesVo::getVaccinesNum, vaccines.getVaccinesNum())
                .page(new Page<>(curr, 10));
    }

    @Override
    public Vaccines queryVaccinesByCode(String code) {
        return Vaccines.transEntity(this.lambdaQuery().eq(VaccinesVo::getVaccinesCode, code).one());
    }
}
