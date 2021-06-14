package site.ncov.www.ncov.place.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.place.domain.vo.RiskVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class AddRiskDto {
    @ApiModelProperty(value = "风险地区（区）")
    private String riskPlace;

    @ApiModelProperty(value = "风险等级")
    private Integer riskLevel;

    public RiskVo transVo() {
        return BeanConvertUtils.copyProperties(this, RiskVo.class);
    }

    public static List<RiskVo> transVoList(List<AddRiskDto> addRiskDtoList) {
        List<RiskVo> riskVoList = new ArrayList<>();
        addRiskDtoList.forEach(addRiskDto -> riskVoList.add(addRiskDto.transVo()));
        return riskVoList;
    }
}
