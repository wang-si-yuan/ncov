package site.ncov.www.ncov.common.model.entity;

import lombok.Data;
import site.ncov.www.ncov.common.utils.HttpStatus;
import site.ncov.www.ncov.common.exception.WebException;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Data
public class Password {
    private String pwd;

    public Password() {
    }

    public void setPwd(String pwd) throws WebException {
        checkPassword(pwd);
        this.pwd = pwd;
    }

    public Password(String pwd) throws WebException {
        checkPassword(pwd);
        this.pwd = pwd;
    }

    private void checkPassword(String password) throws WebException {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        if (password.matches(regex)) {
            return;
        }
        throw new WebException(HttpResult.error(HttpStatus.SC_BAD_REQUEST,"密码格式校验错误"));

    }


}
