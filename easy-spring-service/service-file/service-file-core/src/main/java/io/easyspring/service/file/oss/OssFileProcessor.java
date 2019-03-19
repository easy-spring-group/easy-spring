package io.easyspring.service.file.oss;

import io.easyspring.service.file.FileDownloader;
import io.easyspring.service.file.FileUploader;
import io.easyspring.service.file.impl.AbstractFileProcessor;
import io.easyspring.service.file.properties.FileConstants;
import io.easyspring.service.file.support.SimpleUserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * 阿里 oss 的文件处理器
 *
 * @author summer
 * @date 2019-03-19 15:44
 * @version V1.0.0-RELEASE
 */
@Component(FileConstants.Oss.PROCESSOR_BEAN_NAME)
public class OssFileProcessor extends AbstractFileProcessor {

    /**
     * 注入 oss 文件的上传器
     */
    @Autowired
    private FileUploader ossFileUploader;
    /**
     * 注入 oss 文件的下载器
     */
    @Autowired
    private FileDownloader ossFileDownloader;

    /**
     * 文件上传的方法
     *
     * @param bucketName 存储区名称
     * @param objectName 文件名称
     * @param uploadFile 上传的文件对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * @author summer
     * @date 2019-03-19 15:43
     * @version V1.0.0-RELEASE
     */
    @Override
    protected SimpleUserFile putObject(String bucketName, String objectName, File uploadFile) {
        // 执行上传
        return ossFileUploader.putObject(bucketName, objectName, uploadFile);
    }

    /**
     * 文件上传的方法
     *
     * @param bucketName 存储区名称
     * @param objectName 文件名称
     * @param fileInputStream 文件流对象
     * @return io.easyspring.service.file.support.SimpleUserFile
     * @author summer
     * @date 2019-03-19 15:44
     * @version V1.0.0-RELEASE
     */
    @Override
    protected SimpleUserFile putObject(String bucketName, String objectName, InputStream fileInputStream) {
        // 执行上传
        return ossFileUploader.putObject(bucketName, objectName, fileInputStream);
    }

    /**
     * 获取文件地址的方法
     *
     * @param simpleUserFile 简单文件对象
     * @param expiration 超时时间
     * @param style 图片样式
     * @return java.lang.String
     * @author summer
     * @date 2019-03-19 15:45
     * @version V1.0.0-RELEASE
     */
    @Override
    protected String getUrl(SimpleUserFile simpleUserFile, Long expiration, String style) {
        // 获取文件的连接地址
        return ossFileDownloader.getUrl(simpleUserFile, expiration, style);
    }
}
