package com.ait.app.service;

import com.ait.app.dto.UserDto;
import com.ait.app.model.Address;

public interface UserService {
  UserDto saveUser(UserDto userDto);
  
}
