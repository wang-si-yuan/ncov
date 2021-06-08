package site.ncov.www.ncov.place.respository.impl;

import site.ncov.www.ncov.common.domain.entity.UserStatus;
import site.ncov.www.ncov.place.domain.vo.RiskVo;
import site.ncov.www.ncov.place.mapper.RiskMapper;
import site.ncov.www.ncov.place.respository.RiskRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 风险 服务实现类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
@Service
public class RiskRespositoryImpl extends ServiceImpl<RiskMapper, RiskVo> implements RiskRespository {

    @Override
    public UserStatus USER_STATUS(String place) {
        Integer riskLevel = this.lambdaQuery().eq(RiskVo::getRiskPlace, place).one().getRiskLevel();
        if (riskLevel.equals(3)) {
            return UserStatus.RED;
        } else if (riskLevel.equals(2)) {
            return UserStatus.YELLOW;
        }
        return UserStatus.GREEN;
    }
}
