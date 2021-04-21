package site.ncov.www.ncov.place.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.place.model.entity.Signin;
import site.ncov.www.ncov.place.model.entity.SigninType;

import javax.servlet.http.HttpSession;

/**
 * @author 王思源
 * @version 0.0.0
 */
@Data
public class AutoSigninParam {

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

    public Signin transEntity(){
        Signin signin = BeanConvertUtils.copyProperties(this,Signin.class);
        signin.setSigninType(SigninType.AUTO);
        return signin;
    }

}
