package com.neu.his.base.mapper;



import com.neu.his.base.dto.BaseDTO;
import com.neu.his.domain.BaseDomain;

import javax.management.relation.Role;
import java.util.List;

//T 一定是BaseDomain的子类
//E 一定是BaseDomain的子类
public interface BaseMapper<T  extends BaseDomain,E extends BaseDTO,D extends BaseDomain> {
    List<T> find(E e);

    int add(D d);

    int deleteByIds(Integer[] id);

    T findById(Integer id);

    int update(D e);

}
