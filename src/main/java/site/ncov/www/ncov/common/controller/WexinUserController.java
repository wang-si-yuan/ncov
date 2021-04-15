package site.ncov.www.ncov.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.*;
import site.ncov.www.ncov.common.service.UserService;

import javax.annotation.security.PermitAll;
import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */
@Api(tags="微信用户模块")
@RestController
@RequestMapping("/weixin")
public class WexinUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("注册用户")
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public HttpResult Register(String phone, String pwd, @RequestPart("file") MultipartFile pic) throws FileNotFoundException, WebException {

        User user = userService.getUserByDev(pic);
        user.setUserPwd(new Password(pwd,passwordEncoder));
        user.setUserPhone(new Phone(phone));
        user.setUserRole(Role.NORMAL);

        userService.save(user.transVo());
        return HttpResult.ok(user);

    }
}
