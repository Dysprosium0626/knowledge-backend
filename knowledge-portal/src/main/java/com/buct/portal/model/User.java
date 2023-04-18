package com.buct.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("是否为管理员")
    private Integer isAdmin;

    @ApiModelProperty("个人描述")
    private String description;

    @ApiModelProperty("性别")
    private Integer gender;

    private Date createdTime;

    private Date updatedTime;

    private Boolean isDeleted;


    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String EMAIL = "email";

    public static final String IS_ADMIN = "is_admin";

    public static final String DESCRIPTION = "description";

    public static final String GENDER = "gender";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String IS_DELETED = "is_deleted";

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
