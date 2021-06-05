package site.ncov.www.ncov.common.domain.entity;

import lombok.Data;
import site.ncov.www.ncov.common.utils.HttpStatus;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class HttpResult {
    private int code;
    private String msg;
    private Object data;

    public static HttpResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static HttpResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        r.code = 200;
        return r;
    }

    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        r.code = 200;
        return r;
    }

    public static HttpResult ok() {
        HttpResult httpResult = new HttpResult();
        httpResult.code=200;
        return httpResult;
    }


}
