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
 * 疫苗信息表
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_vaccines")
@ApiModel(value="VaccinesVo对象", description="疫苗信息表")
public class VaccinesVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vaccines_id", type = IdType.AUTO)
    private Integer vaccinesId;

    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "疫苗类型")
    private String vaccinesType;

    @ApiModelProperty(value = "次数")
    private Integer vaccinesNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
