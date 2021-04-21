package site.ncov.www.ncov.common.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.vo.UserVo;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
@Builder
public class User {

    private Integer userId;

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
        UserVo userVo = BeanConvertUtils.copyProperties(this, UserVo.class);
        if (this.getUserPhone()!=null&&this.getUserPhone().getPhone()!=null){
            userVo.setUserPhone(this.getUserPhone().getPhone());
        }

        if (this.getUserCardPhoto()!=null&&this.getUserCardPhoto().getUrl()!=null){
            userVo.setUserCardPhoto(this.getUserCardPhoto().getUrl());
        }

        if(this.getUserPwd()!=null&&this.getUserPwd().getPwd()!=null){
            userVo.setUserPwd(this.getUserPwd().getEpwd());
        }
        return userVo;
    }

    public static User transEntity(UserVo userVo) throws FileNotFoundException, WebException {
        User user = BeanConvertUtils.copyProperties(userVo,User.class);
        if (userVo.getUserPhone()!=null){
            user.setUserPhone(new Phone(userVo.getUserPhone()));
        }

        if (userVo.getUserCardPhoto()!=null){
            user.setUserCardPhoto(new Picture(userVo.getUserCardPhoto()));
        }

        if (userVo.getUserPwd()!=null){
            user.setUserPwd(new Password(userVo.getUserPwd()));
        }

        return user;
    }

    public org.springframework.security.core.userdetails.User transDetails() {
        return new org.springframework.security.core.userdetails.User(userPhone.getPhone(),userPwd.getEpwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(userRole.name()));
    }
}
