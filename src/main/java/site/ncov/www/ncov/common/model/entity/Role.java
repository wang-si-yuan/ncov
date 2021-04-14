package site.ncov.www.ncov.common.model.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author 王思源
 * @version 0.0.0
 */

public enum Role implements IEnum<Integer> {
    NORMAL(0,"公民"),
    ADMIN(1,"系统管理员"),
    GOVERNMENT(2,"政府"),
    HOSPITAL(3,"医院"),
    INSTITUTION(4,"体检机构");

    @EnumValue
    private final int value;
    private final String desc;

    Role(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
