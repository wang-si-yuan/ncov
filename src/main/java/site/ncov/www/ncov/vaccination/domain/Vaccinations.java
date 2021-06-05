package site.ncov.www.ncov.vaccination.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class Vaccinations {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    private List<Vaccination> vaccinationList;

    private Integer num;

    private Integer total;


    public static Vaccinations transEntity(List<Vaccination> vaccinationList) {
        Vaccinations vaccinations = new Vaccinations();
        if (vaccinationList.isEmpty()) {
            return vaccinations;
        } else {
            vaccinations.setVaccinationList(vaccinationList);
            vaccinations.setUserId(vaccinationList.get(0).getUserId());
            vaccinations.setNum(vaccinationList.size());
            vaccinations.setTotal(vaccinationList.get(0).getVaccines().getVaccinesNum());
            return vaccinations;
        }
    }

}
