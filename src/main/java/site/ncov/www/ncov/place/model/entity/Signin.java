package site.ncov.www.ncov.place.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.place.model.vo.SigninVo;

import java.time.LocalDateTime;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
@Builder
public class Signin {

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
    private Place signinPlace;

    @ApiModelProperty(value = "签到时间")
    private LocalDateTime signinTime;

    @ApiModelProperty(value = "签到人员")
    private User signinUser;

    @ApiModelProperty(value = "0:自主签到；1：小程序自动签到；2：进店签到")
    private SigninType signinType;

    public SigninVo transVo(){
        SigninVo signinVo = BeanConvertUtils.copyProperties(this, SigninVo.class);
        if (this.getSigninPlace()!=null&&this.getSigninPlace().getPlaceId()!=null){
            signinVo.setSigninPlace(this.getSigninPlace().getPlaceId());
        }

        if (this.getSigninUser()!=null&&this.getSigninUser().getUserId()!=null){
            signinVo.setSigninUser(this.getSigninUser().getUserId());
        }

        return signinVo;
    }

    public static Signin transEntity(SigninVo signinVo) {
        Signin signin = BeanConvertUtils.copyProperties(signinVo,Signin.class);
        if (signinVo.getSigninPlace()!=null) {
            signin.setSigninPlace(Place.builder().placeId(signinVo.getSigninPlace()).build());
        }

        if (signinVo.getSigninUser()!=null) {
            signin.setSigninUser(User.builder().userId(signinVo.getSigninId()).build());
        }

        return signin;
    }

    @Tolerate
    public Signin() {
    }
}