package site.ncov.www.ncov.vaccination.controller.cqe;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.utils.BeanConvertUtils;
import site.ncov.www.ncov.common.utils.ReadExcelUtils;
import site.ncov.www.ncov.vaccination.domain.Vaccination;
import site.ncov.www.ncov.vaccination.domain.Vaccines;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class VaccinationsCommand {
    @ApiModelProperty(value = "身份证号")
    private String userIdcard;

    @ApiModelProperty(value = "疫苗批号")
    private String vaccinesCode;

    @ApiModelProperty(value = "接种时间")
    private LocalDateTime vaccinationTime;

    public Vaccination transEntity(SFunction<String, Integer> userRespository, SFunction<String, Vaccines> vaccinesRespository) {
        Vaccination vaccination = BeanConvertUtils.copyProperties(this, Vaccination.class);
        if (!StringUtils.isEmpty(userIdcard)) {
            vaccination.setUserId(userRespository.apply(userIdcard));
        }
        if (!StringUtils.isEmpty(vaccinesCode)) {
            vaccination.setVaccines(vaccinesRespository.apply(vaccinesCode));
        }
        return vaccination;
    }

    public List<Vaccination> transEntityList(SFunction<String, Integer> userRespository, SFunction<String, Vaccines> vaccinesRespository, List<VaccinationsCommand> vaccinationsCommandList) {
        List<Vaccination>  vaccinationList = new ArrayList<>();
        vaccinationsCommandList.forEach(vaccinationsCommand -> vaccinationList.add(vaccinationsCommand.transEntity(userRespository, vaccinesRespository)));
        return vaccinationList;
    }

    public List<Vaccination> transEntityList(SFunction<String, Integer> userRespository, SFunction<String, Vaccines> vaccinesRespository, MultipartFile vaccinations) throws IOException {
        List<Map<String, Object>> maps = ReadExcelUtils.getMaps(vaccinations);
        List<VaccinationsCommand> vaccinationsCommandList = new ArrayList<>();
        maps.forEach(map -> {
            VaccinationsCommand vaccinationsCommand = new VaccinationsCommand();
            vaccinationsCommand.setUserIdcard((String) map.get("身份证号"));
            vaccinationsCommand.setVaccinesCode((String) map.get("疫苗批号"));
            vaccinationsCommand.setVaccinationTime(LocalDateTime.parse((String) map.get("接种时间"), DateTimeFormatter.ISO_DATE_TIME));
            vaccinationsCommandList.add(vaccinationsCommand);
        });
        return transEntityList(userRespository, vaccinesRespository, vaccinationsCommandList);
    }
}
