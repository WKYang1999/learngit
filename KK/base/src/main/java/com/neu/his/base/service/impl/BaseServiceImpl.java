package com.neu.his.base.service.impl;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.his.base.dto.BaseDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.his.base.service.BaseService;
import com.neu.his.domain.BaseDomain;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import  java.lang.reflect.Type;

public class BaseServiceImpl<T extends BaseDomain,E extends BaseDTO,D extends BaseDomain> implements BaseService<T,E> {

	protected BaseMapper<T,E,D> baseMapper;

	//代表实体类的字节码对象
	private Class<D> clazz;

	//泛型
	    //含义:参数化类型

	public BaseServiceImpl(){
		//<T extends BaseDomain,E extends BaseDTO,D extends BaseDomain>
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type [] types = parameterizedType.getActualTypeArguments();
		clazz = (Class<D>) types[2];
	}

	@Override
	public T findById(Integer id) {
		return baseMapper.findById(id);
	}

	@Override
	public int add(E e) {
		//转换 DTO-->Domain TODO

		//方案
		    //自己写工具类（反射知识）
		    //第三方的库
		         //BeanUtil
		         //Dozer
		              //步骤
		                   //添加依赖
		                   //看文档

		Mapper mapper = DozerBeanMapperBuilder.create().build();
		//第一个参数的就是目标类型 D
		D d = mapper.map(e, clazz);
		return baseMapper.add(d);
	}

	@Override
	public int deleteByIds(Integer[] id) {
		return baseMapper.deleteByIds(id);
	}

	@Override
	public int update(E e) {
		Mapper mapper = DozerBeanMapperBuilder.create().build();
		//第一个参数的就是目标类型 D
		D d = mapper.map(e, clazz);
		return baseMapper.update(d);
	}

	@Override
	public PageInfo<T> find(E e) {
		PageInfo<T> pageInfo=null;
		if(e.isPaging()){
			PageHelper.startPage(e.getPageNum(),e.getPageSize());
			pageInfo =((Page<T>) baseMapper.find(e)).toPageInfo();
		}else{
			pageInfo = new PageInfo<>();
			List<T> vos =  baseMapper.find(e);
			pageInfo.setList(vos);
			pageInfo.setTotal(vos.size());
		}
		return pageInfo;
	}
}
