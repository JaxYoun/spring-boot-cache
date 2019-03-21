package com.yang.springbootcache.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Yang
 * @date: 2019/3/21 23:54
 * @description:
 */
@Data
@Builder
public class UserVO implements Serializable {

    private Integer id;

    private String name;

    private String password;

}
