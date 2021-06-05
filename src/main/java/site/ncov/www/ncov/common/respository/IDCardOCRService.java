package site.ncov.www.ncov.common.respository;

import site.ncov.www.ncov.common.domain.entity.Picture;
import site.ncov.www.ncov.common.domain.entity.User;

/**
 * @author 王思源
 * @version 0.0.0
 */

public interface IDCardOCRService {
    User getUserByOCR(Picture picture);
}
