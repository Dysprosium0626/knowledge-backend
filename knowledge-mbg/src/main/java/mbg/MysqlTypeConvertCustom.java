package mbg;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @author Dysprosium
 * @title: MysqlTypeConvertCustom
 * @projectName insider
 * @description: TODO
 * @date 2022-12-103:48
 */
public class MysqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {

    private static final String TINYINT_TYPE = "tinyint(4)";

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String str = fieldType.toLowerCase();
        if (str.contains(TINYINT_TYPE)){
            return DbColumnType.INTEGER;
        }
        if (str.contains("bigint(20)")){
            return DbColumnType.STRING;
        }
        return super.processTypeConvert(globalConfig, fieldType);
    }
}