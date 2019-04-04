package io.easyspring.tools.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 文件处理器的管理器
 *
 * @author summer
 * DateTime 2019-03-13 13:53
 * @version V1.0.0-RELEASE
 */
@Component
public class FileProcessorHolder {

    /**
     * 注入文件处理器的集合
     */
    @Autowired
    private Map<String, FileProcessor> fileProcessorMap;

    /**
     * 根据文件存储平台, 获取对应的文件处理器
     *
     * @param filePlatform 文件存储平台类型
     * @return io.easyspring.service.message.FileProcessor
     * Author summer
     * DateTime 2019-03-13 14:00
     * Version V1.0.0-RELEASE
     */
    public FileProcessor findFileProcessor(FilePlatformEnum filePlatform){
        // 参数校验
        if (filePlatform == null) {
            throw new EasyFileException("文件存储平台类型不能为空");
        }

        // 根据消息通道类型的字符串, 获取对应的文件处理器
        return findFileProcessor(filePlatform.toString().toLowerCase());
    }

    /**
     * 根据文件存储平台, 获取对应的文件处理器
     *
     * @param filePlatform 文件存储平台类型的字符串
     * @return io.easyspring.service.message.FileProcessor
     * Author summer
     * DateTime 2019-03-13 14:01
     * Version V1.0.0-RELEASE
     */
    public FileProcessor findFileProcessor(String filePlatform) {
        // 参数校验
        if (StringUtils.isEmpty(filePlatform)) {
            throw new EasyFileException("文件存储平台类型不能为空");
        }

        // 获取文件处理器的名称
        String fileProcessorName = filePlatform.toLowerCase() + FileProcessor.class.getSimpleName();

        // 获取对应的文件处理器
        FileProcessor fileProcessor = fileProcessorMap.get(fileProcessorName);

        // 如果最终获取到的文件处理器为空
        if (fileProcessor == null) {
            throw new EasyFileException("文件处理器" + fileProcessorName + "不存在");
        }

        return fileProcessor;
    }

}
