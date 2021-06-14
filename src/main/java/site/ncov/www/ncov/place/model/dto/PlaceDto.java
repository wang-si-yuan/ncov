package site.ncov.www.ncov.place.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import site.ncov.www.ncov.place.model.entity.PlaceType;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class PlaceDto {
   @ApiModelProperty(value = "名称")
    private String placeTitle;

    private Long curr;
}
