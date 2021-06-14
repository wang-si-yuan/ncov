package site.ncov.www.ncov.vaccination.controller.dto;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.domain.entity.Phone;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import site.ncov.www.ncov.vaccination.domain.Vaccines;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinationVo;
import site.ncov.www.ncov.vaccination.domain.vo.VaccinesVo;

import java.time.LocalDateTime;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class VaccinationsDto {

    private Integer vaccinationId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "疫苗类型")
    private String vaccinesType;

    @ApiModelProperty(value = "接种时间")
    private LocalDateTime vaccinationTime;

    public void transDto(SFunction<Integer, UserVo> userRe, SFunction<Integer, VaccinesVo> vaccinesRe, VaccinationVo vaccinationVo) {
        this.setVaccinationTime(vaccinationVo.getVaccinationTime());
        this.setVaccinationId(vaccinationVo.getVaccinationId());
        UserVo userVo = userRe.apply(vaccinationVo.getUserId());
        this.setUserName(userVo.getUserName());
        this.setUserPhone(userVo.getUserPhone());
        this.setUserIdcard(userVo.getUserIdcard());

        VaccinesVo vaccinesVo = vaccinesRe.apply(vaccinationVo.getVaccinesId());
        this.setVaccinesCode(vaccinesVo.getVaccinesCode());
        this.setVaccinesType(vaccinesVo.getVaccinesType());
    }
}
