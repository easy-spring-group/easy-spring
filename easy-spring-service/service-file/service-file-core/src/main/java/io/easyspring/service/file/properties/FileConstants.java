package io.easyspring.service.file.properties;

/**
 * 文件的常量配置类
 *
 * @author summer
 * DateTime 2019-03-19 21:36
 * @version V1.0.0-RELEASE
 */
public interface FileConstants {

    /**
     * 阿里 oss 的常量配置
     *
     * @author summer
     * DateTime 2019-03-19 21:36
     * @version V1.0.0-RELEASE
     */
    interface Oss{

        /**
         * 文件预览的最长过期时间
         */
        long MAX_EXPIRATION = 64800000L;
        /**
         * 文件预览的默认过期时间
         */
        long DEFAULT_EXPIRATION = 7200000L;
        /**
         * 文件预览的最端过期时间
         */
        long SHORT_EXPIRATION = 600000L;

        /**
         * 上传器的 Bean 的名字
         */
        String UPLOADER_BEAN_NAME = "ossFileUploader";
        /**
         * 载器的的 Bean 的名字
         */
        String DOWNLOADER_BEAN_NAME = "ossFileDownloader";
        /**
         * 消息处理器的 Bean 的名字
         */
        String PROCESSOR_BEAN_NAME = "ossFileProcessor";
    }
}
