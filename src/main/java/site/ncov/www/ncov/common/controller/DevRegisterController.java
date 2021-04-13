package site.ncov.www.ncov.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.model.dto.RegisterDto;
import site.ncov.www.ncov.common.model.entity.HttpResult;
import site.ncov.www.ncov.common.model.entity.Phone;
import site.ncov.www.ncov.common.model.entity.Picture;
import site.ncov.www.ncov.common.model.entity.User;
import site.ncov.www.ncov.common.service.IDCardOCRService;

import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */

@RestController
@RequestMapping("/dev/register")
public class DevRegisterController {

    @Autowired
    private IDCardOCRService idCardOCRService;

    @PostMapping
    public HttpResult setUser(String phone, String pwd, MultipartFile picture) throws FileNotFoundException {
        User user = idCardOCRService.getUserByOCR(new Picture(picture));
        if (user!=null) {
            user.setUserPhone(new Phone(phone));
            user.setUserPwd(pwd);
        }
        return HttpResult.ok(user);
    }
}
