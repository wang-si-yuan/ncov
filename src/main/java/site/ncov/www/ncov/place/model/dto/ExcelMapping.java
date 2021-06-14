package site.ncov.www.ncov.place.model.dto;

import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.utils.ReadExcelUtils;
import site.ncov.www.ncov.place.model.entity.PlaceType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

public class ExcelMapping {
    public static List<AddPlaceDto> transAddPlaceDto(MultipartFile places) throws IOException {
        List<Map<String, Object>> mapList = ReadExcelUtils.getMaps(places);
        List<AddPlaceDto> addPlaceDtoList = new ArrayList<>();
        mapList.forEach(map  ->  {
            AddPlaceDto addPlaceDto = new AddPlaceDto();
            addPlaceDto.setPlaceProvince((String) map.get("省"));
            addPlaceDto.setPlaceCity((String) map.get("市"));
            addPlaceDto.setPlaceDistrict((String) map.get("区"));
            addPlaceDto.setPlaceStreet((String) map.get("街道"));
            addPlaceDto.setPlaceStreetNumber((String) map.get("门牌号"));
            addPlaceDto.setPlaceType(PlaceType.getPlaceByDesc((String) map.get("类型")));
            addPlaceDto.setPlaceTitle((String) map.get("名称"));
            addPlaceDtoList.add(addPlaceDto);
        });
        return addPlaceDtoList;
    }

    public static List<AddRiskDto> transAddRiskDto(MultipartFile risks) throws IOException {
        List<Map<String, Object>> mapList = ReadExcelUtils.getMaps(risks);
        List<AddRiskDto> addRiskDtoList = new ArrayList<>();
        mapList.forEach(map  ->  {
            AddRiskDto addRiskDto = new AddRiskDto();
            addRiskDto.setRiskPlace((String) map.get("区"));
            addRiskDto.setRiskLevel(((Long) map.get("风险等级")).intValue());
            addRiskDtoList.add(addRiskDto);
        });
        return addRiskDtoList;
    }
}
