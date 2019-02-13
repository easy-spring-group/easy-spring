package com.bcdbook.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author summer
 * @date 2018-12-13 14:46
 * @version V1.0.0-RELEASE
 */
public class Generator {

    /** 生成文件的输出目录 */
    private static final String GLOBAL_CONFIG_OUTPUT_DIR = "/Users/summer/workspace/works/yinbao/risk-management-build/risk-management/src/main/java";
    /** mysql 的 xml 文件输出路径 */
    private static final String MAPPER_XML_OUTPUT_DIR = "/Users/summer/workspace/works/yinbao/risk-management-build/risk-management/src/main/resources/mapper";

    /** 数据源连接地址 */
    private static final String DATA_SOURCE_CONFIG_URL = "jdbc:mysql://47.96.126.38:3306/risk_management?useUnicode=true&characterEncoding=utf-8";
    /** 驱动器 */
    private static final String DATA_SOURCE_CONFIG_DRIVER_NAME = "com.mysql.jdbc.Driver";
    /** 用户名 */
    private static final String DATA_SOURCE_CONFIG_USERNAME = "root";
    /** 密码 */
    private static final String DATA_SOURCE_CONFIG_PASSWORD = "YinbaOWang";

    /** 表前缀 */
    private static final String[] STRATEGY_CONFIG_TABLE_PREFIX = {"cust", "sys", "tool"};
    /** 想要生成的表 */
    private static final String[] STRATEGY_CONFIG_INCLUDE =
//            {"sys_user", "sys_role", "sys_permission", "sys_user_role", "sys_role_permission"};
            {"tool_dictionary", "tool_pcc", "tool_user_file"};

    /** 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名 */
    private static final String PACKAGE_CONFIG_PARENT = "com.yinbaochina.management.risk";
    /** 父包模块名 */
    private static final String PACKAGE_CONFIG_MODULE_NAME = "tools";

    /**
     * 启动生成的 main 方法
     *
     * @author summer
     * @date 2018-12-13 16:14
     * @param args main 的参数
     * @return void
     * @version V1.0.0-RELEASE
     */
    public static void main(String[] args) {
        // 创建生成对象
        Generator generator = new Generator();
        // 执行生成
        generator.generator();
    }

    /**
     * 执行生成
     *
     * @author summer
     * @date 2018-12-13 14:48
     * @return void
     * @version V1.0.0-RELEASE
     */
    private void generator(){
        // 创建生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        autoGenerator.setGlobalConfig(generateGlobalConfig());
        // 数据源配置
        autoGenerator.setDataSource(generateDataSourceConfig());
        // 策略配置
        autoGenerator.setStrategy(generateStrategyConfig());
        // 包名配置
        autoGenerator.setPackageInfo(generatePackageConfig());
        // 模板配置
        autoGenerator.setTemplate(generateTemplateConfig());
        // 注入 injectionConfig 配置
        autoGenerator.setCfg(generateInjectionConfig());
        // 执行生成
        autoGenerator.execute();
    }

    /**
     * 全局配置设置
     *
     * @author summer
     * @date 2018-12-13 14:51
     * @return com.baomidou.mybatisplus.generator.config.GlobalConfig
     * @version V1.0.0-RELEASE
     */
    private GlobalConfig generateGlobalConfig(){

        /*
         * 创建全局策略配置对象
         */
        GlobalConfig globalConfig = new GlobalConfig();

        // 生成文件的输出目录
        globalConfig.setOutputDir(GLOBAL_CONFIG_OUTPUT_DIR);
        // 是否覆盖已有文件 [false]
        globalConfig.setFileOverride(true);
        // 是否打开输出目录 [true]
        globalConfig.setOpen(true);
        // 是否在 xml 中添加二级缓存配置 [false]
        globalConfig.setEnableCache(false);
        // 开发人员 [null]
        globalConfig.setAuthor("summer");
        // 开启 Kotlin 模式 [false]
        globalConfig.setKotlin(false);
        // 开启 swagger2 模式 [false]
        globalConfig.setSwagger2(true);
        // 开启 ActiveRecord 模式 [false]
        globalConfig.setActiveRecord(false);
        // 开启 BaseResultMap [false]
        globalConfig.setBaseResultMap(true);
        // 开启 baseColumnList(mapper.xml 的 BaseColumn) [false]
        globalConfig.setBaseColumnList(true);
        // 时间类型对应策略 [DateType.TIME_PACK]
        globalConfig.setDateType(DateType.ONLY_DATE);

        // 实体命名方式 [null]
        globalConfig.setEntityName(null);
        // mapper 命名方式 [null]
        globalConfig.setMapperName("%sMapper");
        // Mapper xml 命名方式 [null]
        globalConfig.setXmlName("%sMapper");
        // service 命名方式 [null]
        globalConfig.setServiceName("%sService");
        // service impl 命名方式 [null]
        globalConfig.setServiceImplName("%sServiceImpl");
        // controller 命名方式 [null]
        globalConfig.setControllerName("%sController");

        return globalConfig;
    }

    /**
     * 数据源配置
     *
     * @author summer
     * @date 2018-12-13 15:33
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @version V1.0.0-RELEASE
     */
    private DataSourceConfig generateDataSourceConfig(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        /*
         * 数据库信息查询类
         * 默认由 dbType 类型决定选择对应数据库内置实现
         * 实现 IDbQuery 接口自定义数据库查询 SQL 语句 定制化返回自己需要的内容
         */
        // dataSourceConfig.setDbQuery();
        // 数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        /*
         * 数据库 schema name
         * 例如 PostgreSQL 可指定为 public
         */
        // dataSourceConfig.setSchemaName(null);
        /*
         * 类型转换
         * 默认由 dbType 类型决定选择对应数据库内置实现
         * 实现 ITypeConvert 接口自定义数据库 字段类型 转换为自己需要的 java 类型，
         * 内置转换类型无法满足可实现 IColumnType 接口自定义
         */
//        dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//                return super.processTypeConvert(fieldType);
//            }
//        });
        // 驱动连接的URL
        dataSourceConfig.setUrl(DATA_SOURCE_CONFIG_URL);
        // 驱动名称
        dataSourceConfig.setDriverName(DATA_SOURCE_CONFIG_DRIVER_NAME);
        // 数据库连接用户名
        dataSourceConfig.setUsername(DATA_SOURCE_CONFIG_USERNAME);
        // 数据库连接密码
        dataSourceConfig.setPassword(DATA_SOURCE_CONFIG_PASSWORD);

        return dataSourceConfig;
    }

    /**
     * 策略配置
     *
     * @author summer
     * @date 2018-12-13 15:33
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @version V1.0.0-RELEASE
     */
    private StrategyConfig generateStrategyConfig(){
        // 创建策略配置对象
        StrategyConfig strategyConfig = new StrategyConfig();
        // 是否大写命名 ORACLE 注意
        // strategyConfig.setCapitalMode(true);
        // 是否跳过视图
        // strategyConfig.setSkipView(false);
        // 数据库表映射到实体的命名策略(驼峰)
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        // strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 表前缀
        strategyConfig.setTablePrefix(STRATEGY_CONFIG_TABLE_PREFIX);
        // 字段前缀
        // strategyConfig.setFieldPrefix("user", "role");

        // 自定义实体，公共字段
        strategyConfig.setSuperEntityColumns(new String[] { "id", "create_time", "update_time", "deleted" });

        // 自定义继承的 Entity 类全称，带包名
        strategyConfig.setSuperEntityClass("com.bcdbook.framework.base.model.BaseModel");
        // 自定义继承的 Mapper 类全称，带包名
        strategyConfig.setSuperMapperClass("com.bcdbook.framework.base.mapper.BaseMapper");
        // 自定义继承的 Service 类全称，带包名
        strategyConfig.setSuperServiceClass("com.bcdbook.framework.base.service.BaseService");
        // 自定义继承的 ServiceImpl 类全称，带包名
        strategyConfig.setSuperServiceImplClass("com.bcdbook.framework.base.service.impl.BaseServiceImpl");
        // 自定义继承的Controller类全称，带包名
        // strategyConfig.setSuperServiceImplClass("com.bcdbook.framework.base.web.BaseController");

        // 需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategyConfig.setInclude(STRATEGY_CONFIG_INCLUDE);
        // 需要排除的表名，允许正则表达式
        // strategyConfig.setExclude("sys_user", "sys_role");

        // 【实体】是否生成字段常量（默认 false）
         strategyConfig.setEntityColumnConstant(false);
        // 【实体】是否为构建者模型（默认 false）
        // strategyConfig.setEntityBuilderModel(false);
        // 【实体】是否为lombok模型（默认 false）
        strategyConfig.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀（默认 false）
        // strategyConfig.setEntityBooleanColumnRemoveIsPrefix(false);
        // 生成 @RestController 控制器
        strategyConfig.setRestControllerStyle(true);
        // 驼峰转连字符
        // strategyConfig.setControllerMappingHyphenStyle(false);
        // 是否生成实体时，生成字段注解
        strategyConfig.entityTableFieldAnnotationEnable(false);
        // 乐观锁属性名称
        // strategyConfig.setVersionFieldName("user_id");
        // 逻辑删除属性名称
        // strategyConfig.setLogicDeleteFieldName("deleted");
        // 表填充字段
        // strategyConfig.setTableFillList();

        return strategyConfig;
    }

    /**
     * 包名配置
     *
     * @author summer
     * @date 2018-12-13 15:33
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @version V1.0.0-RELEASE
     */
    private PackageConfig generatePackageConfig(){
        PackageConfig packageConfig = new PackageConfig();
        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        packageConfig.setParent(PACKAGE_CONFIG_PARENT);
        // 父包模块名
        packageConfig.setModuleName(PACKAGE_CONFIG_MODULE_NAME);

        // Entity 包名
        packageConfig.setEntity("model");
        // Service 包名
        packageConfig.setService("service");
        // Service Impl 包名
        packageConfig.setServiceImpl("service.impl");
        // Mapper 包名
        packageConfig.setMapper("mapper");
        // Mapper XML 包名
        packageConfig.setXml("mapper");
        // Controller 包名
        packageConfig.setController("controller");
        // 路径配置信息
        // packageConfig.setPathInfo();

        return packageConfig;
    }

    /**
     * 模板配置
     *
     * @author summer
     * @date 2018-12-13 15:33
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @version V1.0.0-RELEASE
     */
    private TemplateConfig generateTemplateConfig(){
        // 创建模板配置对象
        TemplateConfig templateConfig = new TemplateConfig();

        /*
         * 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
         * 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
         * 如下任何一个模块如果设置 空 OR Null 将不生成该模块。
         */
        // Java 实体类模板
        // templateConfig.setEntity(null);
        // Kotin 实体类模板
        // templateConfig.setEntityKt(null);
        // Service 类模板
        // templateConfig.setService(null);
        // Service impl 实现类模板
        // templateConfig.setServiceImpl(null);
        // controller 控制器模板
        // templateConfig.setController(null);
        // mapper 模板
        // templateConfig.setMapper(null);
        // mapper xml 模板
         templateConfig.setXml(null);

        return templateConfig;
    }

    /**
     * 注入 injectionConfig 配置
     *
     * @author summer
     * @date 2018-12-13 15:33
     * @return com.baomidou.mybatisplus.generator.config.DataSourceConfig
     * @version V1.0.0-RELEASE
     */
    private InjectionConfig generateInjectionConfig(){

        // 创建 InjectionConfig 对象
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                /*
                 * 自定义返回配置 Map 对象
                 * 该对象可以传递到模板引擎通过 cfg.map.xxx 引用
                 */
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        /*
         * 自定义输出文件
         * 配置 FileOutConfig 指定模板文件、输出文件达到自定义文件生成目的
         */
        // 定义自定义模板及输出文件的集合, 用于封装自定义输出文件
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        // 调整 xml 生成及输出目录
        fileOutConfigList.add(getMapperFileOutConfig());

        // 把自定义的输出文件加入到自定义配置中
        injectionConfig.setFileOutConfigList(fileOutConfigList);

        /*
         * 自定义判断是否创建文件
         * 实现 IFileCreate 接口
         * 该配置用于判断某个类是否需要覆盖创建，当然你可以自己实现差异算法 merge 文件
         */
        // injectionConfig.setFileCreate();


        return injectionConfig;
    }

   /**
    * 定义 mapper 文件的生成路径
    *
    * @author summer
    * @date 2018-12-13 17:09
    * @return com.baomidou.mybatisplus.generator.config.FileOutConfig
    * @version V1.0.0-RELEASE
    */
    private FileOutConfig getMapperFileOutConfig(){
        return new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return MAPPER_XML_OUTPUT_DIR + "/" + PACKAGE_CONFIG_MODULE_NAME + "/" + tableInfo.getEntityName() + "Mapper.xml";
//                return null;
            }
        };
    }
}
