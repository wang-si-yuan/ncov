package site.ncov.www.ncov.common.domain.entity;

import lombok.Getter;
import site.ncov.www.ncov.common.exception.WebException;

/**
 * @author 王思源
 * @version 0.0.0
 */
@Getter
public class Phone {

    private String phone;

    public Phone(String phone) {
        this.phone = phone;
    }

    public Phone(){}

    public void setPhone(String phone) throws WebException {
        checkPhone(phone);
        this.phone = phone;
    }

    private void checkPhone(String phone) throws WebException {
        if (phone.length() == 11) {
            return;
        }

        throw new WebException(HttpResult.error("电话号码格式错误"));
    }
}
