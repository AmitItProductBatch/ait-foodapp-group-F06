package com.ait.app.service;

import com.ait.app.dto.RoleRequestDto;
import com.ait.app.dto.RoleResponseDto;
import com.ait.app.model.Role;

public interface RoleService {

	RoleResponseDto createRole(RoleRequestDto request);

}
