package site.ncov.www.ncov.report.controller.cqe;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.common.utils.ReadExcelUtils;
import site.ncov.www.ncov.report.domain.Detection;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class DetectionCommand {
    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "检测时间")
    private LocalDateTime detectionTime;

    @ApiModelProperty(value = "检测结果")
    private Boolean detectionResult;

    @ApiModelProperty(value = "工作人员")
    private String workingUser;

    public Detection transEntity(SFunction<String, Integer> userRe) {
        Detection detection = BeanConvertUtils.copyProperties(this, Detection.class);
        if (!StringUtils.isEmpty(userIdcard)) {
            detection.setUserId(userRe.apply(userIdcard));
        }

        return detection;
    }

    public List<Detection> transEntityList(SFunction<String, Integer> userRe, List<DetectionCommand> detectionCommandList) {
        List<Detection> detectionList = new ArrayList<>();
        detectionCommandList.forEach(detectionCommand -> detectionList.add(detectionCommand.transEntity(userRe)));
        return detectionList;
    }

    public List<Detection> transEntityList(SFunction<String, Integer> userRe, MultipartFile detections) throws IOException {
        List<Map<String, Object>> maps = ReadExcelUtils.getMaps(detections);
        List<DetectionCommand> detectionCommandList = new ArrayList<>();
        maps.forEach(map -> {
            DetectionCommand detectionCommand = new DetectionCommand();
            detectionCommand.setUserIdcard((String)map.get("身份证号"));
            detectionCommand.setDetectionTime(LocalDateTime.parse((String) map.get("检测时间"), DateTimeFormatter.ISO_DATE_TIME));
            detectionCommand.setWorkingUser((String) map.get("工作人员"));
            if (map.get("检测结果").equals("阴性"))
                detectionCommand.setDetectionResult(false);
            else detectionCommand.setDetectionResult(true);
            detectionCommandList.add(detectionCommand);
        });
        return transEntityList(userRe, detectionCommandList);
    }
}
