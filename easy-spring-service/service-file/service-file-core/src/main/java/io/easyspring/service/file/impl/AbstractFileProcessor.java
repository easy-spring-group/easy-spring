package io.easyspring.service.file.impl;

import io.easyspring.service.file.FilePathGenerator;
import io.easyspring.service.file.FileProcessor;
import io.easyspring.service.file.support.SimpleUserFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;

/**
 * 抽象的文件上传处理器
 *
 * @author summer
 * DateTime 2019-02-12 14:10
 * @version V1.0.0-RELEASE
 */
@Slf4j
@Validated
public abstract class AbstractFileProcessor implements FileProcessor {

    /**
     * 注入文件路径的生成器
     */
    @Autowired
    private FilePathGenerator filePathGenerator;

    /**
     * 文件上传的抽象方法
     *
     * Author summer
     * DateTime 2019-02-12 14:11
     * @param fileTypeCode 文件类型识别码(用于生成文件地址)
     * @param uploadFile 需要上传的文件对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Version V1.0.0-RELEASE
     */
    @Override
    public SimpleUserFile upload(String fileTypeCode,
                                 File uploadFile) {

        // 获取文件存储区
        String bucketName = filePathGenerator.getBucketName(fileTypeCode);
        // 获取文件名称
        String objectName = filePathGenerator.generatorNewObjectName(fileTypeCode, uploadFile.getName());

        // 执行上传操作
        return putObject(bucketName, objectName, uploadFile);
    }

    /**
     * 文件上传的抽象方法
     *
     * Author summer
     * DateTime 2019-02-12 20:05
     * @param fileTypeCode 文件类型识别码(用于生成文件地址)
     * @param fileName 文件名称
     * @param fileInputStream 需要上传的文件流
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Version V1.0.0-RELEASE
     */
    @Override
    public SimpleUserFile upload(String fileTypeCode,
                                 String fileName,
                                 InputStream fileInputStream) {
        // 获取文件存储区
        String bucketName = filePathGenerator.getBucketName(fileTypeCode);
        // 获取文件名称
        String objectName = filePathGenerator.generatorNewObjectName(fileTypeCode, fileName);

        // 执行上传操作
        return putObject(bucketName, objectName, fileInputStream);
    }

    /**
     * 文件上传的方法
     *
     * @param bucketName 存储区名称
     * @param objectName 文件名称
     * @param uploadFile 上传的文件对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Author summer
     * DateTime 2019-03-19 15:43
     * Version V1.0.0-RELEASE
     */
    protected abstract SimpleUserFile putObject(@NotBlank(message = "文件存储区的名称不能为空") String bucketName,
                                                @NotBlank(message = "文件名称不能为空") String objectName,
                                                @NotNull(message = "上传的文件对象不能为空") File uploadFile);

    /**
     * 文件上传的方法
     *
     * @param bucketName 存储区名称
     * @param objectName 文件名称
     * @param fileInputStream 文件流对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Author summer
     * DateTime 2019-03-19 15:44
     * Version V1.0.0-RELEASE
     */
    protected abstract SimpleUserFile putObject(@NotBlank(message = "文件存储区的名称不能为空") String bucketName,
                                                @NotBlank(message = "文件名称不能为空") String objectName,
                                                @NotNull(message = "上传的文件流不能为空") InputStream fileInputStream);

    /**
     * 获取文件地址的方法
     *
     * Author summer
     * DateTime 2019-02-13 12:27
     * @param simpleUserFile 简单文件对象
     * @param expiration 过期时间
     * @param style 图片样式 (具体样式请产考 https://help.aliyun.com/document_detail/47505.html?spm=a2c4g.11186623.2.22.3a686b29hqgmhu#concept-agt-jgc-kfb)
     * @return java.lang.String
     * Version V1.0.0-RELEASE
     */
    @Override
    public String generatorUrl(SimpleUserFile simpleUserFile,
                         Long expiration,
                         String style) {
        // 获取文件地址并返回
        return getUrl(simpleUserFile, expiration, style);
    }

    /**
     * 获取文件地址的方法
     *
     * Author summer
     * DateTime 2019-02-13 12:31
     * @param bucketName oss 存储区的名称
     * @param objectName 文件名称
     * @param expiration 过期时间
     * @param style 图片样式 (具体样式请产考 https://help.aliyun.com/document_detail/47505.html?spm=a2c4g.11186623.2.22.3a686b29hqgmhu#concept-agt-jgc-kfb)
     * @return java.lang.String
     * Version V1.0.0-RELEASE
     */
    @Override
    public String generatorUrl(String bucketName,
                               String objectName,
                               Long expiration,
                               String style) {

        // 创建简单文件对象, 并设置对应的参数
        SimpleUserFile simpleUserFile = new SimpleUserFile();
        // 设置存储区的名称
        simpleUserFile.setBucketName(bucketName);
        // 设置文件的名称
        simpleUserFile.setObjectName(objectName);

        // 执行获取地址的方法
        return getUrl(simpleUserFile, expiration, style);
    }

    /**
     * 获取文件地址的方法
     *
     * @param simpleUserFile 简单文件对象
     * @param expiration 超时时间
     * @param style 图片样式
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 15:45
     * Version V1.0.0-RELEASE
     */
    protected abstract String getUrl(@NotNull(message = "简单用户文件对象不能为空") @Valid SimpleUserFile simpleUserFile,
                                     Long expiration, String style);
}
