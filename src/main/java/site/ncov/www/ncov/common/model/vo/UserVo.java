package site.ncov.www.ncov.common.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_user")
@ApiModel(value="UserVo对象", description="用户表")
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "姓氏")
    private String userLastname;

    @ApiModelProperty(value = "名")
    private String userFirstname;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    @ApiModelProperty(value = "角色")
    private Integer userRole;

    @ApiModelProperty(value = "用户证件照片")
    private String userCardPhoto;

    @ApiModelProperty(value = "用户照片")
    private String userPhoto;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
