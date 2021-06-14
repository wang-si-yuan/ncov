package site.ncov.www.ncov.report.respository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import site.ncov.www.ncov.report.controller.dto.DetectionCurrDto;
import site.ncov.www.ncov.report.controller.dto.DetectionsDto;
import site.ncov.www.ncov.report.domain.Detection;
import site.ncov.www.ncov.report.domain.vo.DetectionVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangsiyuan
 * @since 2021-06-08
 */
public interface DetectionRespository extends IService<DetectionVo> {

    Page<DetectionsDto> queryDetections(Detection detection, Long curr);

    void addDetections(List<Detection> detectionList);

    LocalDate detectionDate(Integer id);

    List<DetectionCurrDto> getCurr(Integer userId);
}
