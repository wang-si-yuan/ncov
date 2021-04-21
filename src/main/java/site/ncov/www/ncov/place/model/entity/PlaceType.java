package site.ncov.www.ncov.place.model.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author 王思源
 * @version 0.0.0
 */

public enum PlaceType implements IEnum<Integer> {
    COMMUNITY(0,"居民社区"),
    OFFICE(1,"办公社区"),
    RESTAURANT(2,"饭店"),
    HOTEL(3,"宾馆"),
    ENTERTAINMENT(4,"娱乐场所"),
    GOVERNMENT(5,"政府机构"),
    HOSPITAL(6,"医院"),
    SCHOOL(7,"学校"),
    OTHEROPEN(8,"其他公共场所"),
    OTHERPRIVATE(9,"其他非公共场所");

    @EnumValue
    private final int value;
    private final String desc;

    public String getDesc() {
        return desc;
    }

    PlaceType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
