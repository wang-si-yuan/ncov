package site.ncov.www.ncov.vaccination.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class Vaccines {

    private Integer vaccinesId;

    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "疫苗类型")
    private String vaccinesType;

    @ApiModelProperty(value = "次数")
    private Integer vaccinesNum;

    public VaccinesVo transVaccinesVo() {
        VaccinesVo vaccinesVo = BeanConvertUtils.copyProperties(this, VaccinesVo.class);

        return vaccinesVo;
    }

    public static Vaccines transEntity(VaccinesVo vaccinesVo) {
        Vaccines vaccines = BeanConvertUtils.copyProperties(vaccinesVo, Vaccines.class);
        return vaccines;
    }
}
