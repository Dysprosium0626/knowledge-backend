package com.buct.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String userId;

    private String comment;

    private Date createdTime;

    private Date updatedTime;

    private Integer isDeleted;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String COMMENT = "comment";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String IS_DELETED = "is_deleted";

}
