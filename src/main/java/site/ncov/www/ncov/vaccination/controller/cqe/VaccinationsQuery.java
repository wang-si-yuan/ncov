package site.ncov.www.ncov.vaccination.controller.cqe;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.domain.Vaccinations;
import site.ncov.www.ncov.vaccination.domain.Vaccines;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class VaccinationsQuery {

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "接种时间")
    private LocalDateTime vaccinationTime;

    private Long curr;

    public Vaccination transEntity(SFunction<String, Integer> userRespository, SFunction<String, Vaccines> vaccinesRespository) {
        Vaccination vaccination = BeanConvertUtils.copyProperties(this, Vaccination.class);
        if (!StringUtils.isEmpty(userIdcard)) {
            vaccination.setUserId(userRespository.apply(userIdcard));
        }
        if (!StringUtils.isEmpty(vaccinesCode)) {
            vaccination.setVaccines(vaccinesRespository.apply(vaccinesCode));
        }
        return vaccination;
    }

    public List<Vaccination> transEntityList(SFunction<String, Integer> userRespository, SFunction<String, Vaccines> vaccinesRespository, List<VaccinationsQuery> vaccinationsQueryList) {
        List<Vaccination>  vaccinationList = new ArrayList<>();
        vaccinationsQueryList.forEach(vaccinationsQuery -> vaccinationList.add(vaccinationsQuery.transEntity(userRespository, vaccinesRespository)));
        return vaccinationList;
    }
}
