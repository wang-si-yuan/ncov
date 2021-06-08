package site.ncov.www.ncov.vaccination.controller.dto;

import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccinations;

import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class CurrVaccinationsDto {

    private List<CurrVaccinationDto> currVaccinationDtoList;

    private Integer num;

    private Integer total;

    public static CurrVaccinationsDto transCurrVaccinationsDto(Vaccinations vaccinations) {
        CurrVaccinationsDto currVaccinationsDto = BeanConvertUtils.copyProperties(vaccinations, CurrVaccinationsDto.class);
        currVaccinationsDto.setCurrVaccinationDtoList(
                CurrVaccinationDto.transCurrVaccinationDtoList(vaccinations.getVaccinationList()));
        return currVaccinationsDto;
    }
}
