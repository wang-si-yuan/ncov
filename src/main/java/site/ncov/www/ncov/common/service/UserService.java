package site.ncov.www.ncov.common.service;

import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.Phone;
import site.ncov.www.ncov.common.model.entity.Role;
import site.ncov.www.ncov.common.model.entity.User;
import site.ncov.www.ncov.common.model.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-04-12
 */
public interface UserService extends IService<UserVo> {

    User getUserByDev(MultipartFile pic) throws FileNotFoundException, WebException;
    User getCurr() throws FileNotFoundException, WebException;

}
