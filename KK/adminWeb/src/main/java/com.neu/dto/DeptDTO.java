package com.neu.dto;

import com.neu.his.base.dto.BaseDTO;
import com.neu.his.domain.BaseDomain;

public class DeptDTO extends BaseDTO {

    private String name;
    private String num;
    private Integer parentId;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
