package site.ncov.www.ncov.common.domain.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * @author 王思源
 * @version 0.0.0
 */

public enum UserStatus implements IEnum<Integer> {
    GREEN(0,"绿码"),
    YELLOW(1,"黄码"),
    RED(2,"红码");

    @EnumValue
    private final int value;

    public String getDesc() {
        return desc;
    }

    @JsonValue
    private final String desc;

    UserStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
