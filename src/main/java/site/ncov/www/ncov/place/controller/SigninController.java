package site.ncov.www.ncov.place.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.HttpResult;
import site.ncov.www.ncov.common.model.entity.User;
import site.ncov.www.ncov.common.service.UserService;
import site.ncov.www.ncov.place.model.entity.Signin;
import site.ncov.www.ncov.place.model.param.AutoSigninParam;
import site.ncov.www.ncov.place.service.SigninService;

import java.io.FileNotFoundException;

/**
 * <p>
 * 签到表 前端控制器
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-21
 */
@Controller
@RequestMapping("/place/signinVo")
public class SigninController {

    @Autowired
    private SigninService signinService;

    @Autowired
    private UserService userService;

    public HttpResult autoSignin(AutoSigninParam autoSigninParam) throws FileNotFoundException, WebException {
        User curr = userService.getCurr(autoSigninParam.getHttpSession());
        Signin signin = autoSigninParam.transEntity();
        signin.setSigninUser(curr);

        if (signinService.save(signin.transVo())){
            return HttpResult.ok(signin);
        } else {
            return HttpResult.error();
        }

    }

}

