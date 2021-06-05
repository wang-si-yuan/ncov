package site.ncov.www.ncov.common.respository.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import site.ncov.www.ncov.common.respository.UserService;

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

        org.springframework.security.core.userdetails.User user = User.transEntity(userService.lambdaQuery().eq(UserVo::getUserPhone,username).one()).transDetails();
        if (user == null) {
            throw new UsernameNotFoundException("用户无法找到");
        }
        return user;
    }
}
