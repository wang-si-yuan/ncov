package site.ncov.www.ncov.place.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 风险
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_risk")
@ApiModel(value="RiskVo对象", description="风险")
public class RiskVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "risk_id", type = IdType.AUTO)
    private Integer riskId;

    @ApiModelProperty(value = "风险地区（区）")
    private String riskPlace;

    @ApiModelProperty(value = "风险等级")
    private Integer riskLevel;

    private LocalDateTime startTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
