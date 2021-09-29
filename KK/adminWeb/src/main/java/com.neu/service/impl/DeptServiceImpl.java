package com.neu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.domain.Dept;
import com.neu.dto.DeptDTO;
import com.neu.exception.DMLException;
import com.neu.exception.ParamException;
import com.neu.mapper.DeptMapper;
import com.neu.service.DeptService;
import com.neu.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	DeptMapper deptMapper;

	@Override
	public PageInfo<DeptVO> find(DeptDTO deptDTO) {

		PageInfo<DeptVO> pageInfo=null;
		if(deptDTO.isPaging()){
			//开始分页
			PageHelper.startPage(deptDTO.getPageNum(),deptDTO.getPageSize());

			Page<DeptVO> page = (Page<DeptVO>) deptMapper.find(deptDTO);
			pageInfo =page.toPageInfo();
		}else{

			pageInfo = new PageInfo<>();
			List<DeptVO> deptVOs =  deptMapper.find(deptDTO);

			pageInfo.setList(deptVOs);
			pageInfo.setTotal(deptVOs.size());
		}
		return pageInfo;
	}

	@Override
	public int add(DeptDTO deptDTO) {
		//转换 DTO-->domain
		Dept dept = new Dept();
		dept.setName(deptDTO.getName());
		dept.setNum(deptDTO.getNum());
		dept.setParentId(deptDTO.getParentId());
		return deptMapper.add(dept);
	}

	@Override
	public int deleteByIds(Integer [] id) {
		try{
			return deptMapper.deleteByIds(id);
		}catch (Exception ex){
			//记录日志 TODO
			throw new DMLException("删除部门失败");
		}
	}

	@Override
	public DeptVO findById(Integer id) {
		return deptMapper.findById(id);
	}

	@Override
	public int update(DeptDTO deptDTO) {

		DeptVO deptVO = findById(deptDTO.getId());
		if(deptVO==null){
			throw new ParamException("更新部门参数错误");
		}

		//转换
		Dept dept = new Dept();
		dept.setId(deptDTO.getId());
		dept.setName(deptDTO.getName());
		dept.setNum(deptDTO.getNum());
		dept.setParentId(deptDTO.getParentId());
		return deptMapper.update(dept);
	}
}
