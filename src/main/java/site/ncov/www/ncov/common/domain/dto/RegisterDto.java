package site.ncov.www.ncov.common.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.entity.Phone;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class RegisterDto {
    private Phone phone;
    private MultipartFile idCard;
    private String pwd;
}
