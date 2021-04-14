package site.ncov.www.ncov.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.vo.UserVo;
import site.ncov.www.ncov.common.utils.BeanConvertUtil;

import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
@Component
public class User {

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

    @ApiModelProperty(value = "用户证件照片")
    private Picture userCardPhoto;

    public UserVo transVo(){
        UserVo userVo = BeanConvertUtil.copyProperties(this, UserVo.class);
        userVo.setUserPhone(this.getUserPhone().getPhone());
        userVo.setUserCardPhoto(this.getUserCardPhoto().getUrl());
        userVo.setUserPwd(this.getUserPwd().getPwd());
        return userVo;
    }

    public static User transUser(UserVo userVo) throws FileNotFoundException, WebException {
        User user = BeanConvertUtil.copyProperties(userVo,User.class);
        user.setUserPhone(new Phone(userVo.getUserPhone()));
        user.setUserCardPhoto(new Picture(userVo.getUserCardPhoto()));
        user.setUserPwd(new Password(userVo.getUserPwd()));
        return user;
    }
}
