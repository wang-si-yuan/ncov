package site.ncov.www.ncov.vaccination.controller.cqe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.common.utils.ReadExcelUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class VaccinesCommand {
    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "疫苗类型")
    private String vaccinesType;

    @ApiModelProperty(value = "次数")
    private Integer vaccinesNum;

    public Vaccines transEntity() {
        return BeanConvertUtils.copyProperties(this, Vaccines.class);
    }

    public static List<Vaccines> transEntityList(List<VaccinesCommand> vaccinesCommandList) {
        List<Vaccines> vaccinesList = new ArrayList<>();
        vaccinesCommandList.forEach(vaccinesCommand -> vaccinesList.add(vaccinesCommand.transEntity()));
        return vaccinesList;
    }

    public static List<Vaccines> transEntityList(MultipartFile vaccines) throws IOException {
        List<Map<String, Object>> maps = ReadExcelUtils.getMaps(vaccines);
        List<VaccinesCommand> vaccinesCommandList = new ArrayList<>();
        maps.forEach(map -> {
            VaccinesCommand vaccinesCommand = new VaccinesCommand();
            vaccinesCommand.setVaccinesCode((String) map.get("疫苗批号"));
            vaccinesCommand.setVaccinesType((String) map.get("疫苗类型"));
            vaccinesCommand.setVaccinesNum(((Long) map.get("次数")).intValue());
            vaccinesCommandList.add(vaccinesCommand);
        });
        return transEntityList(vaccinesCommandList);
    }
}
