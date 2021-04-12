package site.ncov.www.ncov.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ncov.www.ncov.common.model.dto.RegisterDto;
import site.ncov.www.ncov.common.model.entity.HttpResult;
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
    public HttpResult setUser(RegisterDto registerDto) throws FileNotFoundException {
        User user = idCardOCRService.getUserByOCR(new Picture(registerDto.getIdCard()));
        if (user!=null) {
            user.setUserPhone(registerDto.getPhone());
        }
        return HttpResult.ok(user);
    }
}
