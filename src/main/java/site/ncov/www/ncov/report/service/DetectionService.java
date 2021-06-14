package site.ncov.www.ncov.report.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.ncov.www.ncov.report.controller.dto.DetectionsDto;
import site.ncov.www.ncov.report.domain.Detection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王思源
 * @version 0.0.0
 */

public interface DetectionService {

    Page<DetectionsDto> queryDetections(Detection detection, Long curr);

    void addDetections(List<Detection> detectionList);

    void removeDetections(Integer id);

    LocalDate detectionDate(Integer id);
}
