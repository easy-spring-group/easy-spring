package io.easyspring.service.message.subset.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.Serializable;

/**
 * 文件类型的附件对象
 *
 * @author summer
 * DateTime 2019-03-20 14:35
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileAttachmentBuilder implements Serializable {

    private static final long serialVersionUID = 3041552956303861659L;

    /**
     * 附件的名称
     */
    @NotBlank(message = "附件名称不能为空")
    private String name;
    /**
     * 附件对象
     */
    @NotNull(message = "附件不能为空")
    private File file;
}
