package site.ncov.www.ncov.place.model.param;

import io.swagger.annotations.ApiModelProperty;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.place.model.entity.SigninType;
import site.ncov.www.ncov.place.model.vo.SigninVo;

/**
 * @author 王思源
 * @version 0.0.0
 */

public class SigninParam {
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

    public SigninVo transVo() {
        SigninVo signinVo = BeanConvertUtils.copyProperties(this, SigninVo.class);
        signinVo.setSigninType(SigninType.SIGNIN);
        return signinVo;
    }


}
