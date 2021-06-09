package site.ncov.www.ncov.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.domain.entity.*;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;

import java.time.LocalDate;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class UserDto {

    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "性别")
    private Gender userGender;

    @ApiModelProperty(value = "角色")
    private Role userRole;

    @ApiModelProperty(value = "健康码：0绿1黄2红")
    private UserStatus userStatus;

    private Long curr;

    public User transEntity() {
       return BeanConvertUtils.copyProperties(this,User.class);
    }
}
