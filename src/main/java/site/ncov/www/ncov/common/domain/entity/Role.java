package site.ncov.www.ncov.common.domain.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 王思源
 * @version 0.0.0
 */

public enum Role implements IEnum<Integer> {
    NORMAL(0,"公民"),
    ADMIN(1,"系统管理员"),
    GOVERNMENT(2,"政府"),
    HOSPITAL(3,"疫苗接种"),
    INSTITUTION(4,"核酸检测"),
    GOVERNMENT_ADMIN(5,"政府管理员"),
    HOSPITAL_ADMIN(6,"疫苗接种管理员"),
    INSTITUTION_ADMIN(7,"核酸检测管理员");

    @EnumValue
    private final int value;

    @JsonValue
    private final String desc;

    Role(int value, String desc) {
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
}
