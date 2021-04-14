package site.ncov.www.ncov.common.handel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.ncov.www.ncov.common.exception.WebException;
import site.ncov.www.ncov.common.model.entity.HttpResult;

/**
 * @author 王思源
 * @version 0.0.0
 */

@RestControllerAdvice
public class InterfaceExceptionHandler {

    static Logger logger = LoggerFactory.getLogger(InterfaceExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public HttpResult errorHandler(Exception e) {
        logger.error(e.toString(),e);
        return HttpResult.error(e.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = WebException.class)
    public HttpResult myFrrorHandler(WebException e) {
        logger.error(e.toString(),e);
        return e.getHttpResult();
    }

}
