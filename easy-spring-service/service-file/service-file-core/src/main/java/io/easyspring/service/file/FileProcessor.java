package io.easyspring.service.file;

import io.easyspring.service.file.support.SimpleUserFile;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;

/**
 * 文件上传的控制器
 *
 * @author summer
 * @date 2019-02-12 13:34
 * @version V1.0.0-RELEASE
 */
public interface FileProcessor {

    /**
     * 文件上传的抽象方法
     *
     * @author summer
     * @date 2019-02-12 14:11
     * @param fileTypeCode 文件类型识别码(用于生成文件地址)
     * @param uploadFile 需要上传的文件对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * @version V1.0.0-RELEASE
     */
    SimpleUserFile upload(@NotBlank(message = "文件类型识别码不能为空")
                          @Length(min = 2, message = "文件类型识别码不能小于 2 位") String fileTypeCode,
                          @NotNull(message = "文件对象不能为空") File uploadFile);

    /**
     * 文件上传的抽象方法
     *
     * @author summer
     * @date 2019-02-12 20:05
     * @param fileTypeCode 文件类型识别码(用于生成文件地址)
     * @param fileName 文件名称
     * @param fileInputStream 需要上传的文件流
     * @return io.easyspring.service.file.support.SimpleUserFile
     * @version V1.0.0-RELEASE
     */
    SimpleUserFile upload(@NotBlank(message = "文件类型识别码不能为空")
                          @Length(min = 2, message = "文件类型识别码不能小于 2 位") String fileTypeCode,
                          @NotBlank(message = "文件的原名不能为空") String fileName,
                          @NotNull(message = "文件流不能为空") InputStream fileInputStream);

    /**
     * 获取文件地址的方法
     *
     * @author summer
     * @date 2019-02-13 12:27
     * @param simpleUserFile 简单文件对象
     * @param expiration 过期时间
     * @param style 图片样式 (具体样式请产考 https://help.aliyun.com/document_detail/47505.html?spm=a2c4g.11186623.2.22.3a686b29hqgmhu#concept-agt-jgc-kfb)
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    String generatorUrl(@NotNull(message = "简单用户文件对象不能为空") @Valid SimpleUserFile simpleUserFile,
                        Long expiration,
                        String style);

    /**
     * 获取文件地址的方法
     *
     * @author summer
     * @date 2019-02-13 12:31
     * @param bucketName 文件存储区的名称
     * @param objectName 文件名称
     * @param expiration 过期时间
     * @param style 图片样式 (具体样式请产考 https://help.aliyun.com/document_detail/47505.html?spm=a2c4g.11186623.2.22.3a686b29hqgmhu#concept-agt-jgc-kfb)
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    String generatorUrl(@NotBlank(message = "文件存储区的名称不能为空") String bucketName,
                        @NotBlank(message = "文件名称不能为空") String objectName,
                        Long expiration,
                        String style);
}
