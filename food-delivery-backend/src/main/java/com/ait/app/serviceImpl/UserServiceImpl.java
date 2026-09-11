package com.ait.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleInfoNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.app.dto.UserDto;
import com.ait.app.model.Role;
import com.ait.app.model.User;
import com.ait.app.repository.RoleRepository;
import com.ait.app.repository.UserRepository;
import com.ait.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = new User();
		Role role = roleRepository.findById(userDto.getRoleId()).get();

		user.setName(userDto.getName());
		user.setAddress(userDto.getAddress());
		user.setEmail(userDto.getEmail());
		List<Role>roles=new ArrayList();
		roles.add(role);
		User savedUser = userRepository.save(user);
		UserDto dto = new UserDto();
		dto.setName(savedUser.getName());
		dto.setAddress(savedUser.getAddress());
		dto.setEmail(savedUser.getEmail());

		return dto;
	}

}
