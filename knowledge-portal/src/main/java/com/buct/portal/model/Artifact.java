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
@TableName("artifact")
public class Artifact implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String title;

    private String dated;

    private String artist;

    private String role;

    private String department;

    private String medium;

    private String country;

    private String description;

    private String comments;

    private String webUrl;

    private String imgUrl;

    private Date submitTime;

    private Date createdTime;

    private Date updatedTime;

    private Integer isDeleted;


    public static final String ID = "id";

    public static final String DATED = "dated";

    public static final String ARTIST = "artist";

    public static final String ROLE = "role";

    public static final String DEPARTMENT = "department";

    public static final String MEDIUM = "medium";

    public static final String DESCRIPTION = "description";

    public static final String COMMENTS = "comments";

    public static final String WEB_URL = "web_url";

    public static final String IMG_URL = "img_url";

    public static final String SUBMIT_TIME = "submit_time";

    public static final String CREATED_TIME = "created_time";

    public static final String UPDATED_TIME = "updated_time";

    public static final String IS_DELETED = "is_deleted";

}
