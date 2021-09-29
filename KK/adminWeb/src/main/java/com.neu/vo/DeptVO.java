package com.neu.vo;

import com.neu.his.domain.BaseDomain;

public class DeptVO extends BaseDomain {

    private String name;
    private String num;
    private int childCount;   //子节点数量

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
}
