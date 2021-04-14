package site.ncov.www.ncov.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.*;
import site.ncov.www.ncov.common.service.UserService;

import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */
@Api(tags="开发者模块")
@RestController("/dev")
public class DevController {

    @Autowired
    private UserService userService;

    @ApiOperation("测试连接")
    @GetMapping("/ping")
    public HttpResult PingPang() {
        return HttpResult.ok("pang");
    }

    @ApiOperation("添加用户信息")
    @PostMapping("/addUserByDev")
    public HttpResult addUserByDev(@RequestBody Role role, @RequestBody String phone, @RequestBody String pwd, @RequestBody @RequestPart("file") MultipartFile pic) throws FileNotFoundException, WebException {
        User user = userService.getUserByDev(pic);
        user.setUserPwd(new Password(pwd));
        user.setUserPhone(new Phone(phone));
        user.setUserRole(role);
        userService.save(user.transVo());
        return HttpResult.ok(user);
    }
}
