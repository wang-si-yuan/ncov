package site.ncov.www.ncov.place.model.vo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.ncov.www.ncov.place.model.entity.PlaceType;

/**
 * <p>
 * 地点
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ncov_place")
@ApiModel(value="PlaceVo对象", description="地点")
public class PlaceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "place_id", type = IdType.AUTO)
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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Boolean isDelete;


}
