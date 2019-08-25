package com.dp.homework.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dp
 * @create 2019-08-25 13:37
 */
@Data
public class AccountProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private Integer point;
    private String gender;
    private String avatar;
    private Integer postCount;
    private Integer commentCount;
    private Date lasted;

}
