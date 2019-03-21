package io.easyspring.service.message;

import io.easyspring.service.message.support.EasyMessage;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 消息延迟发送的缓存信息存储器
 *
 * @author summer
 * DateTime 2019-03-13 17:14
 * @version V1.0.0-RELEASE
 */
@Validated
public interface MessageDelayCacheRepository<T extends EasyMessage> {

    /**
     * 保存消息的方法
     *
     * @param message 消息对象
     * Author summer
     * DateTime 2019-03-13 17:26
     * Version V1.0.0-RELEASE
     */
    void save(@NotNull(message = "消息对象不能为空") @Valid T message);

    /**
     * 获取缓存的消息的方法
     *
     * @param messageChannelType 消息通道的类型
     * @param messageNo 消息编号
     * @return T
     * Author summer
     * DateTime 2019-03-13 17:30
     * Version V1.0.0-RELEASE
     */
    T get(@NotNull(message = "消息通道类型不能为空") MessageChannelType messageChannelType,
          @NotBlank(message = "消息编号不能为空") String messageNo);

    /**
     * 根据消息通道, 获取通道下所有的消息
     *
     * @param messageChannelType 消息通道
     * @return java.util.List<T>
     * Author summer
     * DateTime 2019-03-14 10:16
     * Version V1.0.0-RELEASE
     */
    List<T> listDelayMessageByMessageChannelType(
            @NotNull(message = "消息通道类型不能为空") MessageChannelType messageChannelType);

    /**
     * 删除缓存的消息的方法
     *
     * @param messageChannelType 消息通道的类型
     * @param messageNo 消息编码
     * Author summer
     * DateTime 2019-03-13 17:31
     * Version V1.0.0-RELEASE
     */
    void remove(@NotNull(message = "消息通道类型不能为空") MessageChannelType messageChannelType,
                @NotBlank(message = "消息编号不能为空") String messageNo);
}
