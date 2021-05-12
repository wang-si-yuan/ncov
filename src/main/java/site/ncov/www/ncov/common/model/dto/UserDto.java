package site.ncov.www.ncov.common.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.model.entity.*;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;

import java.time.LocalDate;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class UserDto {
    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private Phone userPhone;

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "地址")
    private String userAddress;

    @ApiModelProperty(value = "性别")
    private Gender userGender;

    @ApiModelProperty(value = "民族")
    private String userNation;

    @ApiModelProperty(value = "密码")
    private Password userPwd;

    @ApiModelProperty(value = "角色")
    private Role userRole;

    @ApiModelProperty(value = "生日")
    private LocalDate userBirth;

    @ApiModelProperty(value = "健康码：0绿1黄2红")
    private UserStatus userStatus;

    public User transEntity() {
       return BeanConvertUtils.copyProperties(this,User.class);
    }
}
