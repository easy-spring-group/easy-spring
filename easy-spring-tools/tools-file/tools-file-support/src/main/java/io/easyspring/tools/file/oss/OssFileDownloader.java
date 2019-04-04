package io.easyspring.tools.file.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import io.easyspring.tools.file.FileDownloader;
import io.easyspring.tools.file.properties.FileProperties;
import io.easyspring.tools.file.properties.OssProperties;
import io.easyspring.tools.file.support.SimpleUserFile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.Date;

/**
 * 阿里云文件下载器
 *
 * @author summer
 * DateTime 2019-02-13 11:19
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Data
@Slf4j
public class OssFileDownloader implements FileDownloader {

    /**
     * 文件的配置对象
     */
    @Autowired
    private FileProperties fileProperties;
    /**
     * 注入 oss 客户端的工具类
     */
    @Autowired
    private OssClientUtils ossClientUtils;

    /**
     * 获取文件地址
     *
     * Author summer
     * DateTime 2019-02-13 11:26
     * @param simpleUserFile 简单文件对象
     * @param expiration 过期时间
     * @param style 图片样式 (具体样式请产考 https://help.aliyun.com/document_detail/47505.html?spm=a2c4g.11186623.2.22.3a686b29hqgmhu#concept-agt-jgc-kfb)
     * @return java.lang.String
     * Version V1.0.0-RELEASE
     */
    @Override
    public String getUrl(SimpleUserFile simpleUserFile, Long expiration, String style) {

        OssProperties ossProperties = fileProperties.getOss();

        OSSClient ossClient = ossClientUtils.getOssClient();

        // 处理过期时间(如果没有传入, 则设置成默认的过期时间 -- 8 小时)
        expiration = (expiration == null || expiration < 1) ? ossProperties.getDefaultExpiration() : expiration;
        // 指定过期时间
        Date expirationTime = new Date(System.currentTimeMillis() + expiration);

        // 创建地址请求对象
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(simpleUserFile.getBucketName(),
                simpleUserFile.getObjectName(), HttpMethod.GET);
        req.setExpiration(expirationTime);
        // 如果样式不为空, 则说明是想要图片的连接地址
        if (!StringUtils.isEmpty(style)) {
            req.setProcess(style);
        }
        URL signedUrl = ossClient.generatePresignedUrl(req);

        // 关闭OSSClient。
        ossClient.shutdown();

        return signedUrl.toString();
    }
}
