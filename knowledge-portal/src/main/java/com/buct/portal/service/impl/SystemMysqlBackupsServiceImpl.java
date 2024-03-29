package com.buct.portal.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buct.common.exception.Asserts;
import com.buct.portal.mapper.SystemMysqlBackupsMapper;
import com.buct.portal.model.SystemMysqlBackups;
import com.buct.portal.service.SystemMysqlBackupsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Software：IntelliJ IDEA 2021.2 x64
 * Date: 2021/9/16 15:19
 * ClassName:SystemMysqlBackupsService
 * 类描述： MySQL备份实现
 */
@Slf4j
@Service
public class SystemMysqlBackupsServiceImpl extends ServiceImpl<SystemMysqlBackupsMapper, SystemMysqlBackups> implements SystemMysqlBackupsService {

    @Resource
    private SystemMysqlBackupsMapper systemMysqlBackupsMapper;

    @Override
    public List<SystemMysqlBackups> selectBackupsList() {
        return systemMysqlBackupsMapper.selectBackupsList();
    }

    @Override
    public Object mysqlBackups(String filePath, String url, String userName, String password) {
        // 获取ip
        final String ip = url.substring(13, 27);
        // 获取端口号
        final String port = url.substring(28, 32);
        // 获取数据库名称
        final String database_name = url.substring(33, 42);
        // 数据库文件名称
        StringBuilder mysqlFileName = new StringBuilder()
                .append("knowledge")
                .append("_")
                .append(DateUtil.format(new Date(), "yyyy-MM-dd-HH-mm-ss"))
                .append(".sql");
        // 备份命令
        StringBuilder cmd = new StringBuilder()
                .append("mysqldump ")
                .append("--column-statistics=0 ")
                .append("--no-tablespaces ")
                .append("-h ")
                .append(ip)
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                // 排除MySQL备份表
                .append(" --ignore-table ")
                .append(database_name)
                .append(".mysql_backups ")
                .append(database_name)
                .append(" > ")
                .append(filePath)
                .append("/")
                .append(mysqlFileName);
        // 判断文件是否保存成功
        if (!FileUtil.exist(filePath)) {
            FileUtil.mkdir(filePath);
            Asserts.fail("备份失败，文件保存异常，请查看文件内容后重新尝试！");
        }
        // 获取操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();
        String[] command = new String[]{"/bin/sh", "-c", String.valueOf(cmd)};
        SystemMysqlBackups smb = new SystemMysqlBackups();
        // 备份信息存放到数据库
        smb.setMysqlIp(ip);
        smb.setMysqlPort(port);
        smb.setBackupsName(String.valueOf(mysqlFileName));
        smb.setDatabaseName(database_name);
        smb.setMysqlCmd(String.valueOf(cmd));
        smb.setBackupsPath(filePath);
        smb.setCreateTime(DateTime.now());
        smb.setStatus(1);
        smb.setOperation(0);
        systemMysqlBackupsMapper.insert(smb);
        log.info("数据库备份命令为：{}", cmd);
        // 获取Runtime实例
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            if (process.waitFor() == 0) {
                log.info("Mysql 数据库备份成功,备份文件名：{}", mysqlFileName);
            } else {
                Asserts.fail("网络异常，数据库备份失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail("网络异常，数据库备份失败");
        }
        return smb;
    }

    @Override
    public SystemMysqlBackups selectListId(Long id) {
        return systemMysqlBackupsMapper.selectListId(id);
    }

    @Override
    public Object rollback(SystemMysqlBackups smb, String userName, String password) {
        // 备份路径和文件名
        StringBuilder realFilePath = new StringBuilder().append(smb.getBackupsPath()).append("/").append(smb.getBackupsName());
        if (!FileUtil.exist(String.valueOf(realFilePath))) {
            Asserts.fail(HttpStatus.NOT_FOUND.value() + "文件不存在，恢复失败，请查看目录内文件是否存在后重新尝试！");
        }
        StringBuilder cmd = new StringBuilder()
                .append("mysql -h")
                .append(smb.getMysqlIp())
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                .append(" ")
                .append(smb.getDatabaseName())
                .append(" < ")
                .append(realFilePath);
        String[] command = new String[0];
        log.info("数据库恢复命令为：{}", cmd);
        // 获取操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();
        command = new String[]{"/bin/sh", "-c", String.valueOf(cmd)};
        // 恢复指令写入到数据库
        smb.setMysqlBackCmd(String.valueOf(cmd));
        // 更新操作次数
        smb.setRecoveryTime(DateTime.now());
        smb.setOperation(smb.getOperation() + 1);
        // 获取Runtime实例
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            if (process.waitFor() == 0) {
                log.info("Mysql 数据库恢复成功,恢复文件名：{}", realFilePath);
            } else {
                Asserts.fail(HttpStatus.GATEWAY_TIMEOUT.value() + "网络异常，恢复失败，请稍后重新尝试！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Asserts.fail(HttpStatus.GATEWAY_TIMEOUT.value() + "网络异常，恢复失败，请稍后重新尝试！");
        }
        return smb;
    }
}