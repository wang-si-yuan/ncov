package site.ncov.www.ncov.report.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.report.domain.vo.DetectionVo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class Detection {

    @ApiModelProperty(value = "用户")
    private Integer userId;

    @ApiModelProperty(value = "检测时间")
    private LocalDateTime detectionTime;

    @ApiModelProperty(value = "检测结果")
    private Boolean detectionResult;

    @ApiModelProperty(value = "工作人员")
    private String workingUser;

    public DetectionVo transVo() {
        return BeanConvertUtils.copyProperties(this, DetectionVo.class);
    }

    public static Detection transEntity(DetectionVo detectionVo) {
        return BeanConvertUtils.copyProperties(detectionVo, Detection.class);
    }

    public static List<DetectionVo> detectionVoList(List<Detection> detectionList) {
        List<DetectionVo> detectionVoList = new ArrayList<>();
        detectionList.forEach(detection -> detectionVoList.add(detection.transVo()));
        return detectionVoList;
    }
}
