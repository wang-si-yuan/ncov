package site.ncov.www.ncov.report.respository.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import site.ncov.www.ncov.common.respository.UserService;
import site.ncov.www.ncov.report.controller.dto.DetectionCurrDto;
import site.ncov.www.ncov.report.controller.dto.DetectionsDto;
import site.ncov.www.ncov.report.domain.Detection;
import site.ncov.www.ncov.report.domain.vo.DetectionVo;
import site.ncov.www.ncov.report.mapper.DetectionMapper;
import site.ncov.www.ncov.report.respository.DetectionRespository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @Override
    public Page<DetectionsDto> queryDetections(Detection detection, Long curr) {
        Page<DetectionVo> page = this.lambdaQuery().page(new Page<>(curr, 10));
        Page<DetectionsDto> detectionsDtoPage = new Page<>(curr, 10, page.getTotal());
        detectionsDtoPage.setRecords(new ArrayList<>());
        page.getRecords().forEach(detectionVo -> {
            DetectionsDto detectionsDto = new DetectionsDto();
            detectionsDto.transDto(userService::getById, detectionVo);
            detectionsDtoPage.getRecords().add(detectionsDto);
        });
        return detectionsDtoPage;
    }

    @Override
    public void addDetections(List<Detection> detectionList) {
        this.saveBatch(Detection.detectionVoList(detectionList));
    }

    @Override
    public LocalDate detectionDate(Integer id) {
        List<DetectionVo> list = this.lambdaQuery().eq(DetectionVo::getUserId, id).orderByDesc(DetectionVo::getDetectionTime).list();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0).getDetectionTime().toLocalDate();
    }

    @Override
    public DetectionCurrDto getCurr(Integer userId) {
        List<DetectionVo> detectionVoList = this.lambdaQuery().eq(DetectionVo::getUserId, userId).orderByDesc(DetectionVo::getDetectionTime).list();

        return DetectionCurrDto.transDto(detectionVoList.get(0));
    }
}
