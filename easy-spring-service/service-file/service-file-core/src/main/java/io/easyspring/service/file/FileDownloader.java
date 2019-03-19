package io.easyspring.service.file;

import io.easyspring.service.file.support.SimpleUserFile;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 文件下载的抽象接口
 *
 * @author summer
 * @date 2019-02-13 10:06
 * @version V1.0.0-RELEASE
 */
@Validated
public interface FileDownloader {

    /**
     * 获取文件地址
     *
     * @author summer
     * @date 2019-02-13 11:26
     * @param simpleUserFile 简单文件对象
     * @param expiration 过期时间
     * @param style 图片样式 (具体样式请产考 https://help.aliyun.com/document_detail/47505.html?spm=a2c4g.11186623.2.22.3a686b29hqgmhu#concept-agt-jgc-kfb)
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    String getUrl(@NotNull(message = "简单文件对象不能为空") @Valid SimpleUserFile simpleUserFile,
                  Long expiration, String style);

}
