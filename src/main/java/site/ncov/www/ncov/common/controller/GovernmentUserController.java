package site.ncov.www.ncov.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.dto.UserDto;
import site.ncov.www.ncov.common.model.entity.HttpResult;
import site.ncov.www.ncov.common.service.UserService;

import java.io.FileNotFoundException;

/**
 * @author 王思源
 * @version 0.0.0
 */
@Api(tags="政府用户模块")
@RestController
@RequestMapping("/government")
public class GovernmentUserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取当前用户")
    @RequestMapping(value = "/getCurr",method = {RequestMethod.GET})
    public HttpResult getCurr() throws FileNotFoundException, WebException {
        return HttpResult.ok(userService.getCurr());
    }

    @ApiOperation("管理员创建账户")
    @PreAuthorize("hasAuthority('GOVERNMENT_ADMIN')")
    @RequestMapping(value = "/addUser",method = {RequestMethod.POST})
    public HttpResult addUser(UserDto userDto) {
        Integer id = userService.addUserBackId(userDto.transEntity());
        if (id != null) {
            return HttpResult.ok(id);
        }

        return HttpResult.error("插入失败");
    }
}
