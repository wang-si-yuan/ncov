package site.ncov.www.ncov.report.controller.cqe;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.report.domain.Detection;

import java.time.LocalDateTime;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class DetectionQuery {
    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "检测时间")
    private LocalDateTime detectionTime;

    @ApiModelProperty(value = "检测结果")
    private Boolean detectionResult;

    @ApiModelProperty(value = "工作人员")
    private String workingUser;

    private Long curr;

    public Detection transEntity(SFunction<String, Integer> userRe) {
        Detection detection = BeanConvertUtils.copyProperties(this, Detection.class);
        if (!StringUtils.isEmpty(userIdcard)) {
            detection.setUserId(userRe.apply(userIdcard));
        }

        return detection;
    }
}
