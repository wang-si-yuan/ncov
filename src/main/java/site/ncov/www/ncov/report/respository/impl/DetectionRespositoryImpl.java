package site.ncov.www.ncov.report.respository.impl;

import site.ncov.www.ncov.report.domain.vo.DetectionVo;
import site.ncov.www.ncov.report.mapper.DetectionMapper;
import site.ncov.www.ncov.report.respository.DetectionRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
@Service
public class DetectionRespositoryImpl extends ServiceImpl<DetectionMapper, DetectionVo> implements DetectionRespository {

}
