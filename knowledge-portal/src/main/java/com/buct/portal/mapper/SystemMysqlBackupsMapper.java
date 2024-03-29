package com.buct.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buct.portal.model.SystemMysqlBackups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
 
import java.util.List;
 
/**
 * Software：IntelliJ IDEA 2021.2 x64
 * Date: 2021/9/16 15:01
 * InterfaceName:SystemMysqlBackupsMapper
 * 接口描述： MySQL备份接口
 */
@Mapper
public interface SystemMysqlBackupsMapper extends BaseMapper<SystemMysqlBackups> {
 
    /**
     * 查询所有备份数据
     */
    List<SystemMysqlBackups> selectBackupsList();
 
    /**
     * 根据ID查询
     */
    SystemMysqlBackups selectListId(@Param("id") Long id);
}