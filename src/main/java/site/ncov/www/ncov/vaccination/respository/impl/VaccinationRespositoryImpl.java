package site.ncov.www.ncov.vaccination.respository.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.controller.cqe.VaccinationsQuery;
import site.ncov.www.ncov.vaccination.controller.dto.VaccinationsDto;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.domain.Vaccinations;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;
import site.ncov.www.ncov.vaccination.mapper.VaccinationMapper;
import site.ncov.www.ncov.vaccination.respository.VaccinationRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.vaccination.respository.VaccinesRespository;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

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

    @Override
    public Page<VaccinationsDto> queryVaccination(Vaccination vaccination, Long curr) {
        Page<VaccinationVo> page = this.lambdaQuery().page(new Page<>(curr, 10));
        Page<VaccinationsDto> vaccinationsDtoPage
                = new Page<>(curr, 10, page.getTotal());
        vaccinationsDtoPage.setRecords(new ArrayList<>());
        page.getRecords().forEach(vaccinationVo -> {
            VaccinationsDto vaccinationsDto = new VaccinationsDto();
            vaccinationsDto.transDto(userService::getById, vaccinesRespository::getById, vaccinationVo);
            vaccinationsDtoPage.getRecords().add(vaccinationsDto);
        });
        return vaccinationsDtoPage;
    }

    @Override
    public void addVaccinations(List<Vaccination> vaccinationList) {
        this.saveBatch(Vaccination.transVaccinationVoList(vaccinationList));
    }
}
