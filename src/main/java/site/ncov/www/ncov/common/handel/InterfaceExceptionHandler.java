package site.ncov.www.ncov.common.handel;

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

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public HttpResult errorHandler(Exception e) {
        return HttpResult.error(e.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = WebException.class)
    public HttpResult myFrrorHandler(WebException e) {
        return e.getHttpResult();
    }

}
