package site.ncov.www.ncov.vaccination.respository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.domain.Vaccinations;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;
import site.ncov.www.ncov.vaccination.mapper.VaccinationMapper;
import site.ncov.www.ncov.vaccination.respository.VaccinationRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.vaccination.respository.VaccinesRespository;

import java.util.List;

/**
 * <p>
 * 疫苗接种信息 服务实现类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
@Service
public class VaccinationRespositoryImpl extends ServiceImpl<VaccinationMapper, VaccinationVo> implements VaccinationRespository {

    @Autowired
    private VaccinesRespository vaccinesRespository;

    @Override
    public Vaccinations queryVaccinations(Integer id) {
        return Vaccinations.transEntity(
                Vaccination.transEntityList(
                        this.lambdaQuery().eq(
                                VaccinationVo::getUserId, id
                        ).list(), vaccinesRespository::getById
                )
        );
    }
}
