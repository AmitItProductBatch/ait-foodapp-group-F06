package com.ait.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.app.dto.RoleRequestDto;
import com.ait.app.dto.RoleResponseDto;
import com.ait.app.model.Role;
import com.ait.app.service.RoleService;

@RestController
@RequestMapping("api/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping("addRole")
	public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto request) {
		RoleResponseDto response = roleService.createRole(request);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

}
