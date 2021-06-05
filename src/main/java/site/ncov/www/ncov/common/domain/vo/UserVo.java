package site.ncov.www.ncov.common.domain.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.ncov.www.ncov.common.domain.entity.Gender;
import site.ncov.www.ncov.common.domain.entity.Role;
import site.ncov.www.ncov.common.domain.entity.UserStatus;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_user")
@ApiModel(value="UserVo对象", description="用户表")
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

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
    private String userCardPhoto;

    @ApiModelProperty(value = "健康码：0绿1黄2红")
    private UserStatus userStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
