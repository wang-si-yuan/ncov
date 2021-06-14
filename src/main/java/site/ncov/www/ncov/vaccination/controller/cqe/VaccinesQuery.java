package site.ncov.www.ncov.vaccination.controller.cqe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccines;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class VaccinesQuery {
    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "疫苗类型")
    private String vaccinesType;

    @ApiModelProperty(value = "次数")
    private Integer vaccinesNum;

    private Long curr;

    public Vaccines transEntity() {
        return BeanConvertUtils.copyProperties(this, Vaccines.class);
    }
}
