package site.ncov.www.ncov.common.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.*;
import site.ncov.www.ncov.common.model.vo.UserVo;
import site.ncov.www.ncov.common.mapper.UserMapper;
import site.ncov.www.ncov.common.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserVo> implements UserService {


    @Override
    public User getUserByDev(MultipartFile pic) throws FileNotFoundException, WebException {
        Picture picture = new Picture(pic);
        User user = picture.queryUserByOCR();
        if (user == null){
            picture.clearPic();
            throw new WebException(HttpResult.error("ocr校验失败"));
        }
        return user;
    }

    @Override
    public User getCurr() throws FileNotFoundException, WebException {
//        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = User.transEntity(this.lambdaQuery().eq(UserVo::getUserPhone, userDetails.getUsername()).one());
        return user;
    }
}
