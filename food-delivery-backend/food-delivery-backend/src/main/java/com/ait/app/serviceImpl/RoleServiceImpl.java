package com.ait.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ait.app.dto.RoleRequestDto;
import com.ait.app.dto.RoleResponseDto;
import com.ait.app.exception.DuplicateRoleException;
import com.ait.app.exception.RoleValidationException;
import com.ait.app.model.Role;
import com.ait.app.repository.RoleRepository;
import com.ait.app.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public RoleResponseDto createRole(RoleRequestDto request) {

		if (request.getName() == null || request.getName().trim().isEmpty()) {

			throw new RoleValidationException("Name is required", HttpStatus.BAD_REQUEST);
		}

		if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {

			throw new RoleValidationException("Description is required", HttpStatus.BAD_REQUEST);
		}

		String roleName = request.getName();
		if (roleRepository.existsByName(roleName)) {
			throw new DuplicateRoleException("Role already exists", HttpStatus.CONFLICT);
		}

		Role role = new Role();
		role.setName(roleName);
		role.setDescription(request.getDescription());

		Role savedRole = roleRepository.save(role);

		RoleResponseDto response = new RoleResponseDto();

		response.setId(savedRole.getId());
		response.setName(savedRole.getName());
		response.setDescription(savedRole.getDescription());

		return response;

	}

}
