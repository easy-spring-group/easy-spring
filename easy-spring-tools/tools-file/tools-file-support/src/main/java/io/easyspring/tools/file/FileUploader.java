package io.easyspring.tools.file;

import io.easyspring.tools.file.support.SimpleUserFile;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;

/**
 * 文件上传器的抽象类
 *
 * @author summer
 * DateTime 2019-02-12 15:21
 * @version V1.0.0-RELEASE
 */
@Validated
public interface FileUploader {

    /**
     * 阿里云平台文件的文件上传器
     *
     * Author summer
     * DateTime 2019-02-12 16:12
     * @param bucketName 文件存储区
     * @param objectName 文件名称
     * @param uploadFile 需要上传的文件对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Version V1.0.0-RELEASE
     */
    SimpleUserFile putObject(@NotBlank(message = "文件存储区的名称不能为空") String bucketName,
                             @NotBlank(message = "文件名称不能为空") String objectName,
                             @NotNull(message = "上传的文件对象不能为空") File uploadFile);

    /**
     * 阿里云平台文件的文件上传器
     *
     * Author summer
     * DateTime 2019-02-12 20:09
     * @param bucketName 文件存储区
     * @param objectName 文件名称
     * @param fileInputStream 上传的文件流
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Version V1.0.0-RELEASE
     */
    SimpleUserFile putObject(@NotBlank(message = "文件存储区的名称不能为空") String bucketName,
                             @NotBlank(message = "文件名称不能为空") String objectName,
                             @NotNull(message = "上传的文件流不能为空") InputStream fileInputStream);
}
