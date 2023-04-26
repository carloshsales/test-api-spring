package com.api.authentication.domain.enums;

public enum Category {
    TECNCATEGORY(1),
    TEST(2),
    HEALTH(3);

    private final Integer value;

    Category(Integer i) {
        this.value = i;
    }

    public Integer getValue(){
        return this.value;
    }
}
