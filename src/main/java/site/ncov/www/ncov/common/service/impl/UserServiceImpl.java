package site.ncov.www.ncov.common.service.impl;

import site.ncov.www.ncov.common.model.vo.UserVo;
import site.ncov.www.ncov.common.mapper.UserMapper;
import site.ncov.www.ncov.common.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
