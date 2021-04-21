package site.ncov.www.ncov.place.model.vo;

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
 * 签到表
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_signin")
@ApiModel(value="SigninVo对象", description="签到表")
public class SigninVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "signin_id", type = IdType.AUTO)
    private Integer signinId;

    @ApiModelProperty(value = "维度")
    private Double signinLat;

    @ApiModelProperty(value = "经度")
    private Double signinLng;

    @ApiModelProperty(value = "省")
    private String signinProvince;

    @ApiModelProperty(value = "市")
    private String signninCity;

    @ApiModelProperty(value = "区")
    private String signinDistrict;

    @ApiModelProperty(value = "街道")
    private String signinStreet;

    @ApiModelProperty(value = "门牌号")
    private String signinStreetNumber;

    @ApiModelProperty(value = "地点")
    private Integer signinPlace;

    @ApiModelProperty(value = "签到时间")
    private LocalDateTime signinTime;

    @ApiModelProperty(value = "签到人员")
    private Integer signinUser;

    @ApiModelProperty(value = "0:自主签到；1：小程序自动签到；2：进店签到")
    private Integer signinType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
