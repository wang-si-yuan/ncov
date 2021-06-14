package site.ncov.www.ncov.report.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.report.controller.dto.DetectionCurrDto;
import site.ncov.www.ncov.report.controller.dto.DetectionsDto;
import site.ncov.www.ncov.report.domain.Detection;
import site.ncov.www.ncov.report.respository.DetectionRespository;
import site.ncov.www.ncov.report.service.DetectionService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class DetectionImpl implements DetectionService {

    @Autowired
    private DetectionRespository detectionRespository;

    @Override
    public Page<DetectionsDto> queryDetections(Detection detection, Long curr) {
        return detectionRespository.queryDetections(detection, curr);
    }

    @Override
    public void addDetections(List<Detection> detectionList) {
        detectionRespository.addDetections(detectionList);
    }

    @Override
    public void removeDetections(Integer id) {
        detectionRespository.removeById(id);
    }

    @Override
    public LocalDate detectionDate(Integer id) {
        return detectionRespository.detectionDate(id);
    }

    @Override
    public List<DetectionCurrDto> getCurr(Integer userId) {
        return detectionRespository.getCurr(userId);
    }
}
