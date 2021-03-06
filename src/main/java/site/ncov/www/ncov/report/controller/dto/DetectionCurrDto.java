package site.ncov.www.ncov.report.controller.dto;

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
public class DetectionCurrDto {
    @ApiModelProperty(value = "检测时间")
    private LocalDateTime detectionTime;

    @ApiModelProperty(value = "检测结果")
    private String detectionResult;

    @ApiModelProperty(value = "工作人员")
    private String workingUser;

    public static DetectionCurrDto transDto(DetectionVo detectionVo) {
        DetectionCurrDto detectionCurrDto = BeanConvertUtils.copyProperties(detectionVo, DetectionCurrDto.class);
        if (detectionVo.getDetectionResult()) {
            detectionCurrDto.setDetectionResult("阳性") ;
        } else {
            detectionCurrDto.setDetectionResult("阴性") ;
        }
        return detectionCurrDto;
    }

    public static List<DetectionCurrDto> transDtoList(List<DetectionVo>  detectionVoList) {
        List<DetectionCurrDto> detectionCurrDtoList = new ArrayList<>();
        detectionVoList.forEach(detectionVo -> detectionCurrDtoList.add(DetectionCurrDto.transDto(detectionVo)));
        return detectionCurrDtoList;
    }
}
