package site.ncov.www.ncov.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author 王思源
 * @version 0.0.0
 */

public class BeanConvertUtil {
    static Logger logger = LoggerFactory.getLogger(BeanConvertUtil.class);

    private BeanConvertUtil(){}

    /**
     * 类之间相同属性的copy
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T>T copyProperties(Object source,Class<T> targetClass) {
        try {
            if (source == null || targetClass == null) {
                return null;
            }
            T doInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source, doInstance);
            return doInstance;
        } catch (InstantiationException | IllegalAccessException e) {

            logger.debug(e.toString());
        }
        return null;
    }
}
