package io.easyspring.service.file.impl;

import io.easyspring.framework.common.snowflake.SnowflakeHelp;
import io.easyspring.framework.common.utils.FileUtils;
import io.easyspring.service.file.EasyFileException;
import io.easyspring.service.file.FilePathGenerator;
import io.easyspring.service.file.properties.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 默认的文件路径生成器的实现类
 *
 * @author summer
 * DateTime 2019-03-19 14:22
 * @version V1.0.0-RELEASE
 */
public class DefaultFilePathGeneratorImpl implements FilePathGenerator {

    /**
     * 文件的配置对象
     */
    @Autowired
    private FileProperties fileProperties;
    /**
     * 全局唯一 id 生成器
     */
    @Autowired
    private SnowflakeHelp snowflakeHelp;

    /**
     * 获取存储区的名称
     *
     * @param fileTypeCode 文件类型识别码
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:23
     * Version V1.0.0-RELEASE
     */
    @Override
    public String getBucketName(String fileTypeCode) {
        // 获取文件存储区域的配置
        Map<String, String> bucketNameMap = fileProperties.getBucketName();
        // 检查文件的配置
        if (CollectionUtils.isEmpty(bucketNameMap)) {
            throw new EasyFileException("文件的存储区配置不能为空, 请配置 easy-spring.file.bucket-name");
        }

        // 获取存储区的名称
        String bucketName = bucketNameMap.get(fileTypeCode);
        // 如果获取到的名称为空
        if (StringUtils.isEmpty(bucketName)) {
            throw new EasyFileException("文件识别码 " + fileTypeCode
                    + "对应的存储区不存在, 请检查配置: easy-spring.file.bucket-name");
        }

        return bucketName;
    }

    /**
     * 生成文件的名称
     *
     * @param fileTypeCode 文件类型识别码
     * @param fileName 文件的原名
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:23
     * Version V1.0.0-RELEASE
     */
    @Override
    public String generatorObjectName(String fileTypeCode, String fileName) {
        // 获取文件名称前缀
        String objectNamePrefix = getObjectNamePrefix(fileTypeCode);
        // 获取文件名称的中间路径
        String intermediatePath = generatorObjectIntermediatePath();

        // 拼装 ObjectName 并返回
        return objectNamePrefix + intermediatePath + "/" + fileName;
    }

    /**
     * 生成新的文件名称
     *
     * @param fileTypeCode 文件类型识别码
     * @param sourceFileName 文件的原名
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:24
     * Version V1.0.0-RELEASE
     */
    @Override
    public String generatorNewObjectName(String fileTypeCode, String sourceFileName) {
        // 获取文件名称前缀
        String objectNamePrefix = getObjectNamePrefix(fileTypeCode);
        // 获取文件名称的中间路径
        String intermediatePath = generatorObjectIntermediatePath();
        // 获取新的文件名称
        String newFileName = generatorNewFileName(sourceFileName);

        return objectNamePrefix + intermediatePath + "/" + newFileName;
    }

    /**
     * 生成新的文件名称
     *
     * @param sourceFileName 原始的文件的名称
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:55
     * Version V1.0.0-RELEASE
     */
    @Override
    public String generatorNewFileName(String sourceFileName) {
        // 定义新的文件名称
        String newFileName = String.valueOf(snowflakeHelp.nextId());
        // 如果文件
        if (StringUtils.isEmpty(sourceFileName)) {
            return newFileName;
        }

        // 获取文件后缀
        String fileSuffix = FileUtils.getFileSuffix(sourceFileName);
        // 如果文件的后缀不为空, 则执行文件名称的拼装
        if (!StringUtils.isEmpty(fileSuffix)) {
            newFileName = newFileName + fileSuffix;
        }

        // 返回新的文件名称
        return newFileName;
    }

    /**
     * 生成文件名称中间的路径
     *
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:25
     * Version V1.0.0-RELEASE
     */
    @Override
    public String generatorObjectIntermediatePath() {
        return FileUtils.getDatePath();
    }

    /**
     * 根据文件类型识别码, 获取文件名称前缀
     *
     * @param fileTypeCode 文件类型识别码
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:41
     * Version V1.0.0-RELEASE
     */
    private String getObjectNamePrefix(String fileTypeCode){
        // 获取文件名称前缀的配置
        Map<String, String> objectNamePrefixMap = fileProperties.getObjectNamePrefix();
        // 检查文件的配置
        if (CollectionUtils.isEmpty(objectNamePrefixMap)) {
            throw new EasyFileException("文件的名称前缀配置不能为空, 请配置 easy-spring.file.object-name-prefix");
        }

        // 获取文件名称前缀
        String objectNamePrefix = objectNamePrefixMap.get(fileTypeCode);
        // 如果获取到的文件名称前缀为空
        if (StringUtils.isEmpty(objectNamePrefix)) {
            throw new EasyFileException("文件识别码 " + fileTypeCode
                    + "对应的文件名称前缀不存在, 请检查配置: easy-spring.file.object-name-prefix");
        }

        return objectNamePrefix;
    }
}
