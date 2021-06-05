package site.ncov.www.ncov.vaccination.domain;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class Vaccination {

    private Integer vaccinationId;

    @ApiModelProperty(value = "疫苗")
    private Vaccines vaccines;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "接种时间")
    private LocalDateTime vaccinationTime;

    public VaccinationVo transVaccinationVo() {
        VaccinationVo vaccinationVo = BeanConvertUtils.copyProperties(this, VaccinationVo.class);

        vaccinationVo.setVaccinesId(this.getVaccines().getVaccinesId());

        return vaccinationVo;
    }

    public static List<VaccinationVo> transVaccinationVoList(List<Vaccination> vaccinationList) {
        List<VaccinationVo> vaccinationVoList = new ArrayList<>();
        vaccinationList.forEach(vaccination -> vaccinationVoList.add(vaccination.transVaccinationVo()));
        return vaccinationVoList;
    }

    public static List<Vaccination> transEntityList(List<VaccinationVo> vaccinationVoList, SFunction<Integer, VaccinesVo> respository) {
        List<Vaccination> vaccinationList = new ArrayList<>();
        vaccinationVoList.forEach(vaccinationVo -> {
            VaccinesVo vaccinesVo = respository.apply(vaccinationVo.getVaccinesId());
            vaccinationList.add(Vaccination.transEntity(vaccinesVo, vaccinationVo));
        });
        return vaccinationList;
    }

    public static Vaccination transEntity(VaccinesVo vaccinesVo, VaccinationVo vaccinationVo) {
        Vaccination vaccination = BeanConvertUtils.copyProperties(vaccinationVo, Vaccination.class);

        vaccination.setVaccines(Vaccines.transEntity(vaccinesVo));

        return vaccination;
    }
}
