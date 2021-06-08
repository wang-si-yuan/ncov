package site.ncov.www.ncov.vaccination.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccination;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class CurrVaccinationDto {

    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "疫苗类型")
    private String vaccinesType;

    @ApiModelProperty(value = "接种时间")
    private LocalDateTime vaccinationTime;

    public static CurrVaccinationDto transCurrVaccinationDto(Vaccination vaccination) {
        CurrVaccinationDto currVaccinationDto = BeanConvertUtils.copyProperties(vaccination, CurrVaccinationDto.class);
        currVaccinationDto.setVaccinesCode(vaccination.getVaccines().getVaccinesCode());
        currVaccinationDto.setVaccinesType(vaccination.getVaccines().getVaccinesType());

        return currVaccinationDto;
    }

    public static List<CurrVaccinationDto> transCurrVaccinationDtoList(List<Vaccination> vaccinationList) {
        List<CurrVaccinationDto> currVaccinationDtoList = new ArrayList<>();
        vaccinationList.forEach(vaccination -> currVaccinationDtoList.add(transCurrVaccinationDto(vaccination)));
        return currVaccinationDtoList;
    }

}
