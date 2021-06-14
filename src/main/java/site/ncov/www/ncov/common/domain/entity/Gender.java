package site.ncov.www.ncov.common.domain.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 王思源
 * @version 0.0.0
 */

public enum Gender implements IEnum<Integer> {

    MAN(0,"男"),WOMAN(1,"女");
    @EnumValue
    private final int value;

    @JsonValue
    private final String desc;

    Gender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }


    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static Gender getGenderByDesc(String desc) {
        Gender[] values = Gender.values();
        for (Gender gender : values) {
            if (gender.desc.equals(desc)) {
                return gender;
            }
        }
        return null;
    }
}
