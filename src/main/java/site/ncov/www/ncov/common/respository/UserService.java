package site.ncov.www.ncov.common.respository;

import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.domain.entity.User;
import site.ncov.www.ncov.common.domain.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

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
    Integer addUserBackId(User user);
    Integer getUserByIdCard(String card);

}
