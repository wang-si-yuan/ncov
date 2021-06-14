package site.ncov.www.ncov.common.utils;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

public class ReadExcelUtils {

    public static List<Map<String, Object>> getMaps(MultipartFile multipartFile) throws IOException {
        ExcelReader excelReader = ExcelUtil.getReader(multipartFile.getInputStream());
        return excelReader.readAll();
    }
}
