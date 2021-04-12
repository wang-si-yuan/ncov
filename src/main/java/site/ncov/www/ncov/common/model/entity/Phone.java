package site.ncov.www.ncov.common.model.entity;

import lombok.Getter;
import site.ncov.www.ncov.common.exception.WebException;

/**
 * @author 王思源
 * @version 0.0.0
 */
@Getter
public class Phone {

    private final String phone;

    public Phone(String phone) {
        this.phone = phone;
    }

    private void checkPhone(String phone) throws WebException {
        if (phone.length() == 11) {
            return;
        }

        throw new WebException(HttpResult.error("电话号码格式错误"));
    }
}
