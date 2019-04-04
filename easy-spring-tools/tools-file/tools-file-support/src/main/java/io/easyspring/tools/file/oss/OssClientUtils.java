package io.easyspring.tools.file.oss;

import com.aliyun.oss.OSSClient;
import io.easyspring.tools.file.EasyFileException;
import io.easyspring.tools.file.properties.FileProperties;
import io.easyspring.tools.file.properties.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * oss 客户端的工具类
 *
 * @author summer
 * DateTime 2019-02-13 11:37
 * @version V1.0.0-RELEASE
 */
@Component
public class OssClientUtils {

    /**
     * 注入文件的配置对象
     */
    @Autowired
    private FileProperties fileProperties;

    /**
     * 创建OSSClient实例。
     *
     * Author summer
     * DateTime 2019-02-12 20:31
     * @return com.aliyun.oss.OSSClient
     * Version V1.0.0-RELEASE
     */
    public OSSClient getOssClient() {

        // 获取 oss 的配置对象
        OssProperties ossProperties = fileProperties.getOss();
        // 参数校验
        if (!ossClientPropertiesValid(ossProperties)) {
            throw new EasyFileException("创建OSSClient实例失败");
        }
        // 创建并返回对象
        return new OSSClient(ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret());
    }

    /**
     * 校验 oss 的配置参数的合法性
     *
     * Author summer
     * DateTime 2019-02-13 11:31
     * @param ossProperties oss 的配置参数
     * @return boolean
     * Version V1.0.0-RELEASE
     */
    private static boolean ossClientPropertiesValid(OssProperties ossProperties) {
        // 执行校验
        if (ossProperties == null) {
            throw new EasyFileException("oss 配置对象不能为空, 请查看配置 easy-spring.file.oss");
        }
        if (StringUtils.isEmpty(ossProperties.getEndpoint())) {
            throw new EasyFileException("oss 连接的服务器不能为空, 请查看配置 easy-spring.file.oss.endpoint");
        }
        if (StringUtils.isEmpty(ossProperties.getAccessKeyId())) {
            throw new EasyFileException("oss 识别码 id 不能为空, 请查看配置 easy-spring.file.oss.access-key-id");
        }
        if (StringUtils.isEmpty(ossProperties.getAccessKeySecret())) {
            throw new EasyFileException("oss 的秘钥不能为空, 请查看配置 easy-spring.file.oss.access-key-secret");
        }

        return true;
    }
}
