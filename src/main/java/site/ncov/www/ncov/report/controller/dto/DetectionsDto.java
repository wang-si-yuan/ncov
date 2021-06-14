package site.ncov.www.ncov.report.controller.dto;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import site.ncov.www.ncov.common.domain.entity.Phone;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import site.ncov.www.ncov.report.domain.vo.DetectionVo;

import java.time.LocalDateTime;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class DetectionsDto {

    private Integer detectionId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "检测时间")
    private LocalDateTime detectionTime;

    @ApiModelProperty(value = "检测结果")
    private String detectionResult;

    @ApiModelProperty(value = "工作人员")
    private String workingUser;

    public void transDto(SFunction<Integer, UserVo> userRe, DetectionVo detectionVo) {
        detectionId = detectionVo.getDetectionId();
        detectionTime = detectionVo.getDetectionTime();
        if (detectionVo.getDetectionResult()) {
            detectionResult = "阳性";
        } else {
            detectionResult = "阴性";
        }

        workingUser = detectionVo.getWorkingUser();
        UserVo userVo = userRe.apply(detectionVo.getUserId());
        userName = userVo.getUserName();
        userPhone = userVo.getUserPhone();
        userIdcard = userVo.getUserIdcard();
    }
}
