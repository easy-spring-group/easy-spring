package io.easyspring.service.message;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 消息的处理器
 *
 * @author summer
 * DateTime 2019-03-12 14:15
 * @version V1.0.0-RELEASE
 */
@Validated
public interface MessageProcessor {

    /**
     * 消息的创建方法
     *
     * @param receiverList 接收者集合
     * @param templateCode 模板识别码
     * @param extend 需要替换的参数
     * @param ignoreDelay 是否忽略延时发送, 不填写的时候默认 false
     * @return String
     * Author summer
     * DateTime 2019-03-12 18:01
     * Version V1.0.0-RELEASE
     */
    String create(@NotNull(message = "消息接收者不能为空") @NotEmpty(message = "消息接收者不能为空") List<String> receiverList,
                  @NotBlank(message = "模板识别码不能为空") String templateCode,
                  Map<String, Object> extend,
                  Boolean ignoreDelay);

    /**
     * 根据传入的消息通道和消息编码, 如果消息已经到了延期发送时间, 则执行发送操作
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编码
     * @return int
     * Author summer
     * DateTime 2019-03-13 18:31
     * Version V1.0.0-RELEASE
     */
    int sendDelayMessageIfExpire(@NotNull(message = "消息通道类型不能为空") MessageChannelType messageChannelType,
                                 @NotBlank(message = "消息编号不能为空") String messageNo);


    /**
     * 发送通道下所有到发送期的消息
     *
     * @param messageChannelType 消息通道类型
     * @return int
     * Author summer
     * DateTime 2019-03-14 10:23
     * Version V1.0.0-RELEASE
     */
    int sendDelayMessagesIfExpire(@NotNull(message = "消息通道类型不能为空") MessageChannelType messageChannelType);

    /**
     * 撤回未发送的消息的方法
     *
     * @param messageChannelType 消息通道类型
     * @param messageNo 消息编码
     * Author summer
     * DateTime 2019-03-14 09:44
     * Version V1.0.0-RELEASE
     */
    void recall(@NotNull(message = "消息通道类型不能为空") MessageChannelType messageChannelType,
                @NotBlank(message = "消息编号不能为空") String messageNo);
}
