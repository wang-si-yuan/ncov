package site.ncov.www.ncov.common.service;

import site.ncov.www.ncov.common.model.entity.Picture;
import site.ncov.www.ncov.common.model.entity.User;

/**
 * @author 王思源
 * @version 0.0.0
 */

public interface IDCardOCRService {
    User getUserByOCR(Picture picture);
}
