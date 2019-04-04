package io.easyspring.message.core.subset.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamSource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 流类型的附件对象
 *
 * @author summer
 * DateTime 2019-03-20 14:35
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputStreamAttachmentBuilder implements Serializable {

    private static final long serialVersionUID = -694547856392279773L;

    /**
     * 附件的名称
     */
    @NotBlank(message = "附件名称不能为空")
    private String name;
    /**
     * 附件的流对象
     */
    @NotNull(message = "附件的流对象不能为空")
    private InputStreamSource inputStreamSource;
}
