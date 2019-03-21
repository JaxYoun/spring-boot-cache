package com.yang.springbootcache.service;

import com.yang.springbootcache.model.dto.UserDTO;
import com.yang.springbootcache.model.vo.UserVO;

/**
 * @author: Yang
 * @date: 2019/3/22 00:13
 * @description:
 */
public interface SimpleUserService {

    UserVO getUserById(UserDTO userDTO);

}
