package com.neu.his.base.service;

import com.github.pagehelper.PageInfo;
import com.neu.his.base.dto.BaseDTO;
import com.neu.his.domain.BaseDomain;

public interface BaseService<T extends BaseDomain,E extends BaseDTO> {

    PageInfo<T> find(E e);

    int add(E e);

    int deleteByIds(Integer[] id);

    T findById(Integer id);

    int update(E e);
}
