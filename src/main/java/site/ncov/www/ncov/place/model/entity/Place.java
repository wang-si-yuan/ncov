package site.ncov.www.ncov.place.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
@Builder
public class Place {

    private Integer placeId;

    @ApiModelProperty(value = "维度")
    private BigDecimal placeLat;

    @ApiModelProperty(value = "经度")
    private BigDecimal placeLng;

    @ApiModelProperty(value = "省")
    private String placeProvince;

    @ApiModelProperty(value = "市")
    private String placeCity;

    @ApiModelProperty(value = "区")
    private String placeDistrict;

    @ApiModelProperty(value = "街道")
    private String placeStreet;

    @ApiModelProperty(value = "门牌号")
    private String placeStreetNumber;

    @ApiModelProperty(value = "类型：0，居民社区；1，办公社区；2，饭店；3，宾馆；4，娱乐场所；5，政府机构；6，医院；7，学校；8，其他公共场所；9，其他非公共场所")
    private PlaceType placeType;

    @ApiModelProperty(value = "名称")
    private String placeTitle;

    @ApiModelProperty(value = "营业资质：0有1无")
    private Boolean placeQualifications;

    @Tolerate
    public Place() {
    }
}
