package site.ncov.www.ncov.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.ncov.www.ncov.common.domain.entity.*;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
@Slf4j
public class AddUserDto {
    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "地址")
    private String userAddress;

    @ApiModelProperty(value = "性别")
    private Gender userGender;

    @ApiModelProperty(value = "民族")
    private String userNation;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    @ApiModelProperty(value = "角色")
    private Role userRole;

    @ApiModelProperty(value = "生日")
    private LocalDate userBirth;

    @ApiModelProperty(value = "用户证件照片")
    private Picture userCardPhoto;

    @ApiModelProperty(value = "健康码：0绿1黄2红")
    private UserStatus userStatus;

    public User transEntity() throws WebException {
        User user = BeanConvertUtils.copyProperties(this, User.class);

        Phone phone = new Phone();
        phone.setPhone(this.userPhone);
        user.setUserPhone(phone);

        Password password = new Password();
        password.setPwd(this.userPwd, new BCryptPasswordEncoder());
        user.setUserPwd(password);

        return user;
    }

    public static List<User> transEntityList(List<AddUserDto> addUserDtoList) {
        List<User> userList = new ArrayList<>();
        addUserDtoList.forEach(addUserDto -> {
            try {
                userList.add(addUserDto.transEntity());
            } catch (WebException e) {
                log.error(e.toString());
            }
        });
        return userList;
    }
}
