package site.ncov.www.ncov.place.model.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 王思源
 * @version 0.0.0
 */

public enum SigninType implements IEnum<Integer> {

    //value = "0:自主签到；1：小程序自动签到；2：进店签到"

    AUTONOMY(0,"自主签到"),
    AUTO(1,"小程序自动签到"),
    SIGNIN(2,"进店签到");

    @EnumValue
    private final int value;

    @JsonValue
    private final String desc;

    SigninType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
