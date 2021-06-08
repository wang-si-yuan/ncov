package site.ncov.www.ncov.place.respository;

import site.ncov.www.ncov.common.domain.entity.UserStatus;
import site.ncov.www.ncov.place.domain.vo.RiskVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 风险 服务类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
public interface RiskRespository extends IService<RiskVo> {
    UserStatus USER_STATUS(String place);
}
