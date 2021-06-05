package site.ncov.www.ncov.vaccination.respository.impl;

import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;
import site.ncov.www.ncov.vaccination.mapper.VaccinesMapper;
import site.ncov.www.ncov.vaccination.respository.VaccinesRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public void addVaccines(Vaccines vaccines) {
        this.save(vaccines.transVaccinesVo());
    }
}
