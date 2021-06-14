package site.ncov.www.ncov.place.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.place.model.entity.PlaceType;
import site.ncov.www.ncov.place.model.vo.PlaceVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class AddPlaceDto {
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

    public PlaceVo transVo() {
        return BeanConvertUtils.copyProperties(this, PlaceVo.class);
    }
    public static List<PlaceVo> transVoList(List<AddPlaceDto> addPlaceDtoList) {
        List<PlaceVo> placeVoList =  new ArrayList<>();
        addPlaceDtoList.forEach(addPlaceDto -> placeVoList.add(addPlaceDto.transVo()));
        return placeVoList;
    }
}
