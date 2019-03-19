package io.easyspring.service.file;

import io.easyspring.service.file.impl.DefaultFilePathGeneratorImpl;
import io.easyspring.service.file.oss.OssFileDownloader;
import io.easyspring.service.file.oss.OssFileUploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传器的 Bean 配置器
 *
 * @author summer
 * @date 2019-02-12 16:24
 * @version V1.0.0-RELEASE
 */
@Configuration
public class FileBeanConfig {

    /**
     * 文件路径的生成器
     *
     * @return io.easyspring.service.file.FilePathGenerator
     * @author summer
     * @date 2019-03-19 15:06
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(FilePathGenerator.class)
    public FilePathGenerator filePathGenerator(){
        // 默认的文件路径生成器
        return new DefaultFilePathGeneratorImpl();
    }

    /**
     * 阿里云文件上传器的 Bean 配置
     *
     * @author summer
     * @date 2019-02-12 16:26
     * @return io.easyspring.service.file.FileUploader
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "ossFileUploader")
    public FileUploader ossFileUploader(){
        // 创建阿里云的上传器
        return new OssFileUploader();
    }

    /**
     * 阿里云文件下载器的 Bean 配置
     *
     * @author summer
     * @date 2019-02-13 12:17
     * @return io.easyspring.service.file.FileDownloader
     * @version V1.0.0-RELEASE
     */
    @Bean
    @ConditionalOnMissingBean(name = "ossFileDownloader")
    public FileDownloader ossFileDownloader() {
        // 创建阿里云的下载器
        return new OssFileDownloader();
    }

}
