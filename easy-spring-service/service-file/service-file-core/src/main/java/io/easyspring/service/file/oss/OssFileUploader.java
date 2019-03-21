package io.easyspring.service.file.oss;

import com.aliyun.oss.OSSClient;
import io.easyspring.service.file.EasyFileException;
import io.easyspring.service.file.FileUploader;
import io.easyspring.service.file.support.SimpleUserFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStream;

/**
 * 阿里云文件上传器
 *
 * @author summer
 * DateTime 2019-02-12 15:20
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@Slf4j
public class OssFileUploader implements FileUploader {

    /**
     * 文件的配置对象
     */
    @Autowired
    private OssClientUtils ossClientUtils;

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
    @Override
    public SimpleUserFile putObject(String bucketName, String objectName, File uploadFile) {
        // 创建 oss 客户端连接对象
        OSSClient ossClient = ossClientUtils.getOssClient();

        // 执行上传
        ossClient.putObject(bucketName, objectName, uploadFile);
        ossClient.shutdown();

        // 执行封装并返回
        return buildSimpleUserFile(bucketName, objectName);
    }

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
    @Override
    public SimpleUserFile putObject(String bucketName, String objectName, InputStream fileInputStream) {
        // 创建 oss 客户端连接对象
        OSSClient ossClient = ossClientUtils.getOssClient();

        // 执行上传
        ossClient.putObject(bucketName, objectName, fileInputStream);
        ossClient.shutdown();

        // 执行封装并返回
        return buildSimpleUserFile(bucketName, objectName);
    }

//    /**
//     * 封装阿里云客户端对象
//     *
//     * @return com.aliyun.oss.OSSClient
//     * Author summer
//     * DateTime 2019-03-19 16:39
//     * Version V1.0.0-RELEASE
//     */
//    private OSSClient buildOssClient(){
//        /*
//         * 获取配置信息
//         */
//        // 获取 oss 的配置信息
//        OssProperties ossProperties = fileProperties.getOss();
//        // 创建OSSClient实例。
//        return OssClientUtils.getOssClient(ossProperties);
//    }

    /**
     * 封装简单文件对象
     *
     * @param bucketName 存储区名称
     * @param objectName 文件对象名称
     * @return io.easyspring.service.file.support.SimpleUserFile
     * Author summer
     * DateTime 2019-03-19 16:34
     * Version V1.0.0-RELEASE
     */
    private SimpleUserFile buildSimpleUserFile(String bucketName, String objectName){
        // 参数校验
        if (StringUtils.isEmpty(bucketName)) {
            throw new EasyFileException("文件存储区的名称不能为空");
        }
        if (StringUtils.isEmpty(objectName)) {
            throw new EasyFileException("文件名称不能为空");
        }

        /*
         * 创建简单的文件返回对象
         */
        SimpleUserFile simpleUserFile = new SimpleUserFile();
        // 设置存储区名称
        simpleUserFile.setBucketName(bucketName);
        // 设置文件对象名称
        simpleUserFile.setObjectName(objectName);

        return simpleUserFile;
    }
}
