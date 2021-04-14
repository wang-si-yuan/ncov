package site.ncov.www.ncov.common.service.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.common.model.vo.UserVo;
import site.ncov.www.ncov.common.service.UserService;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserVo userVo = userService.getOne(userService.lambdaQuery().eq(UserVo::getUserPhone,username));
        if (userVo == null) {
            throw new UsernameNotFoundException("用户无法找到");
        }
        return new User(
                username,userVo.getUserPwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(
                        userVo.getUserRole().name()
        ));
    }
}
