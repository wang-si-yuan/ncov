package site.ncov.www.ncov.common.domain.dto;

import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.entity.Gender;
import site.ncov.www.ncov.common.domain.entity.Role;
import site.ncov.www.ncov.common.utils.ReadExcelUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

public class ExcelMapping {
    public static List<AddUserDto> transAddUser(MultipartFile multipartFile) throws IOException {
        List<Map<String, Object>> mapList = ReadExcelUtils.getMaps(multipartFile);
        List<AddUserDto> addUserDtoList = new ArrayList<>();
        mapList.forEach(map ->  {
            AddUserDto addUserDto = new AddUserDto();
            addUserDto.setUserName((String) map.get("姓名"));
            addUserDto.setUserPhone((String) map.get("手机号"));
            addUserDto.setUserGender(Gender.getGenderByDesc((String) map.get("性别")));
            addUserDto.setUserPwd((String) map.get("密码"));
            addUserDto.setUserAddress((String) map.get("地址"));
            addUserDto.setUserBirth(LocalDate.parse((String) map.get("生日"), DateTimeFormatter.ISO_DATE));
            addUserDto.setUserIdcard((String) map.get("身份证号"));
            addUserDto.setUserNation((String) map.get("民族"));
            addUserDto.setUserRole(Role.getRoleByDesc((String) map.get("角色")));
            addUserDtoList.add(addUserDto);
        });
        return addUserDtoList;
    }
}
