package io.easyspring.service.file.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 简单的文件对象
 *
 * @author summer
 * DateTime 2019-02-12 15:50
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SimpleUserFile implements Serializable {

    private static final long serialVersionUID = 5656036835416174462L;

    /**
     * 文件存储的区域名称
     */
    @NotBlank(message = "文件存储的区域名称不能为空")
    private String bucketName;
    /**
     * 文件名称(文件存储的路径)
     */
    @NotBlank(message = "文件名称不能为空")
    private String objectName;
}
