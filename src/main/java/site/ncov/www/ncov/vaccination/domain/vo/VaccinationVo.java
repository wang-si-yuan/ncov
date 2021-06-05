package site.ncov.www.ncov.vaccination.domain.vo;

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
 * 疫苗接种信息
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_vaccination")
@ApiModel(value="VaccinationVo对象", description="疫苗接种信息")
public class VaccinationVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vaccination_id", type = IdType.AUTO)
    private Integer vaccinationId;

    @ApiModelProperty(value = "疫苗id")
    private Integer vaccinesId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "接种时间")
    private LocalDateTime vaccinationTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
