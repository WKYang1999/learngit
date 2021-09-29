package com.neu.service.impl;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.neu.domain.User;
import com.neu.dto.UserDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.UserMapper;
import com.neu.service.RoleService;
import com.neu.service.UserService;
import com.neu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserVO, UserDTO,User> implements UserService{

	@Value("${password}")
	private String password;

	@Autowired
	RoleService roleService;

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.baseMapper = userMapper;
	}

	@Override
	public UserVO login(String loginName, String password) {
		UserMapper userMapper = (UserMapper) baseMapper;
		return userMapper.login(loginName,password);
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		/*for(int id : ids){
			System.out.println();
			unbind(id);
		}*/
		return super.deleteByIds(ids);
	}

	@Override
	public int add(UserDTO userDTO) {
		if(userDTO.getPassword()==null){
			userDTO.setPassword(password);
		}
		if(userDTO.getHead()==null){
			userDTO.setHead("7.jpg");
		}
		//int result =  super.add(userDTO);
		Mapper mapper = DozerBeanMapperBuilder.create().build();
		//第一个参数的就是目标类型 D
		User user = mapper.map(userDTO, User.class);
		int result = baseMapper.add(user);
		//维护关系
		    //中间表插入数据
		Integer [] roleIds= userDTO.getRoleId();
		if(roleIds!=null){
			for(Integer roleId:roleIds){
				bind(user.getId(),roleId);
			}
		}if(roleIds==null){
				bind(user.getId(),2);
		}
		return result;
	}


	@Override
	public int update(UserDTO userDTO) {
		int result =  super.update(userDTO);
		if(userDTO.isRoles()){
			int userId = userDTO.getId();
			//解除之前绑定的
			unbind(userId);
			//重新绑定
			Integer [] roleIds= userDTO.getRoleId();
			if(roleIds!=null){
				for(Integer roleId:roleIds){
					bind(userId,roleId);
				}
			}
		}
		return result;
	}

	@Override
	public UserVO findById(Integer id) {
		UserVO userVO =  super.findById(id);

		int [] roleId = roleService.findByUserId(userVO.getId());

		userVO.setRoleId(roleId);
		return userVO;
	}

	@Override
	public int bind(int userId, int roleId) {
		UserMapper userMapper = (UserMapper) baseMapper;
		return userMapper.bind(userId,roleId);
	}

	@Override
	public int unbind(int userId) {
		UserMapper userMapper = (UserMapper) baseMapper;
		return userMapper.unbind(userId);
	}

	@Override
	public UserVO register(String loginName) {
		UserMapper userMapper = (UserMapper) baseMapper;
		return userMapper.register(loginName);
	}

	@Override
	public int updatePassword(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setPassword(userDTO.getPassword());
		UserMapper userMapper = (UserMapper) baseMapper;
		return userMapper.updatePassword(user);
	}

	@Override
	public int delete(Integer[] idArray) {
		UserMapper userMapper = (UserMapper) baseMapper;
		return userMapper.delete(idArray);
	}

}
