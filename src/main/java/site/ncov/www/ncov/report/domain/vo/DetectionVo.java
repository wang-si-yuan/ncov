package site.ncov.www.ncov.report.domain.vo;

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
 * 
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_detection")
@ApiModel(value="DetectionVo对象", description="")
public class DetectionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "detection_id", type = IdType.AUTO)
    private Integer detectionId;

    @ApiModelProperty(value = "用户")
    private Integer userId;

    @ApiModelProperty(value = "检测时间")
    private LocalDateTime detectionTime;

    @ApiModelProperty(value = "检测结果")
    private Boolean detectionResult;

    @ApiModelProperty(value = "工作人员")
    private String workingUser;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
