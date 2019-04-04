package io.easyspring.tools.file;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * 文件路径的生成器
 *
 * @author summer
 * DateTime 2019-03-19 14:13
 * @version V1.0.0-RELEASE
 */
@Validated
public interface FilePathGenerator {

    /**
     * 获取存储区的名称
     *
     * @param fileTypeCode 文件类型识别码
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:23
     * Version V1.0.0-RELEASE
     */
    String getBucketName(@NotBlank(message = "文件类型识别码不能为空")
                         @Length(min = 2, message = "文件类型识别码不能小于 2 位") String fileTypeCode);

    /**
     * 生成文件的名称
     *
     * @param fileTypeCode 文件类型识别码
     * @param fileName 文件的原名
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:23
     * Version V1.0.0-RELEASE
     */
    String generatorObjectName(@NotBlank(message = "文件类型识别码不能为空")
                               @Length(min = 2, message = "文件类型识别码不能小于 2 位") String fileTypeCode,
                               @NotBlank(message = "文件的原名不能为空") String fileName);

    /**
     * 生成新的文件名称
     *
     * @param fileTypeCode 文件类型识别码
     * @param fileName 文件的原名
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:24
     * Version V1.0.0-RELEASE
     */
    String generatorNewObjectName(@NotBlank(message = "文件类型识别码不能为空")
                                  @Length(min = 2, message = "文件类型识别码不能小于 2 位") String fileTypeCode,
                                  @NotBlank(message = "文件的原名不能为空") String fileName);

    /**
     * 生成新的文件名称
     *
     * @param sourceFileName 原始的文件的名称
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:55
     * Version V1.0.0-RELEASE
     */
    String generatorNewFileName(String sourceFileName);

    /**
     * 生成文件名称中间的路径
     *
     * @return java.lang.String
     * Author summer
     * DateTime 2019-03-19 14:25
     * Version V1.0.0-RELEASE
     */
    String generatorObjectIntermediatePath();
}
