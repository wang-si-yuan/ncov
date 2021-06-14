package site.ncov.www.ncov.common.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.domain.dto.AddUserDto;
import site.ncov.www.ncov.common.domain.dto.ExcelMapping;
import site.ncov.www.ncov.common.domain.entity.Role;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.domain.entity.UserStatus;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.domain.dto.UserDto;
import site.ncov.www.ncov.common.domain.entity.HttpResult;
import site.ncov.www.ncov.common.respository.UserService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/addUser",method = {RequestMethod.POST})
    public HttpResult addUser(UserDto userDto) {
        Integer id = userService.addUserBackId(userDto.transEntity());
        if (id != null) {
            return HttpResult.ok(id);
        }

        return HttpResult.error("插入失败");
    }

    @ApiOperation("管理员查询账户列表")
    @RequestMapping(value = "/queryUserList",method = {RequestMethod.GET})
    public HttpResult queryUserList(UserDto userDto) {
        Page<UserVo> page = userService.lambdaQuery()
                .like(!StringUtils.isEmpty(userDto.getUserIdcard()), UserVo::getUserIdcard, userDto.getUserIdcard())
                .like(!StringUtils.isEmpty(userDto.getUserName()), UserVo::getUserName, userDto.getUserName())
                .like(!StringUtils.isEmpty(userDto.getUserPhone()),UserVo::getUserPhone, userDto.getUserPhone())
                .eq(userDto.getUserGender()!=null,UserVo::getUserGender, userDto.getUserGender())
                .eq(userDto.getUserStatus()!=null,UserVo::getUserStatus, userDto.getUserStatus())
                .eq(userDto.getUserRole()!=null,UserVo::getUserRole, userDto.getUserRole()).page(new Page<UserVo>(userDto.getCurr(),10));
        return HttpResult.ok(page);
    }

    @ApiOperation("管理员修改用户")
    @RequestMapping(value = "/modifyUser",method = {RequestMethod.POST})
    public HttpResult modifyUser(String id, Role role) {
        userService.lambdaUpdate().eq(UserVo::getUserId, id).set(UserVo::getUserRole, role).update();
        return HttpResult.ok();
    }

    @ApiOperation("管理员添加用户")
    @RequestMapping(value = "/addUsers",method = {RequestMethod.POST})
    public HttpResult addUsers(MultipartFile users) throws IOException {
        List<AddUserDto> addUserDtoList = ExcelMapping.transAddUser(users);
        List<User> userList = AddUserDto.transEntityList(addUserDtoList);

        List<UserVo> userVoList = User.transVos(userList);
        userService.saveBatch(userVoList);
        return HttpResult.ok();
    }
}
