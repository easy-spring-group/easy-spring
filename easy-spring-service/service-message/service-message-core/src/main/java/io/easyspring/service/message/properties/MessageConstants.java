package io.easyspring.service.message.properties;

/**
 * 消息的常量配置
 *
 * @author summer
 * @date 2019-03-19 20:46
 * @version V1.0.0-RELEASE
 */
public interface MessageConstants {

    /**
     * 短信消息的常量配置
     *
     * @author summer
     * @date 2019-03-19 20:52
     * @version V1.0.0-RELEASE
     */
    interface Sms {

        /**
         * 格式化类的 Bean 的名字
         */
        String BUILDER_BEAN_NAME = "smsMessageBuilder";
        /**
         * 发送器的 Bean 的名字
         */
        String SENDER_BEAN_NAME = "smsMessageSender";
        /**
         * 消息处理器的 Bean 的名字
         */
        String PROCESSOR_BEAN_NAME = "smsMessageProcessor";
    }

    /**
     * 邮件的常量配置
     *
     * @author summer
     * @date 2019-03-15 16:52
     * @version V1.0.0-RELEASE
     */
    interface Email {

        /**
         * 默认的主题
         */
        String DEFAULT_SUBJECT = "默认主题";

        /**
         * 默认的主题对应的 key
         */
        String SUBJECT_KEY = "subject";
        /**
         * 默认的是否是 html 类型的邮件
         */
        boolean DEFAULT_IS_HTML = true;

        /**
         * 是否是 html 的参数的 key
         */
        String IS_HTML_KEY = "isHtml";

        /**
         * 消息处理器的 Bean 的名字
         */
        String PROCESSOR_BEAN_NAME = "emailMessageProcessor";
        /**
         * 格式化类的 Bean 的名字
         */
        String BUILDER_BEAN_NAME = "emailMessageBuilder";
        /**
         * 发送器的 Bean 的名字
         */
        String SENDER_BEAN_NAME = "emailMessageSender";
    }

    /**
     * 系统消息的常量配置
     *
     * @author summer
     * @date 2019-03-15 16:52
     * @version V1.0.0-RELEASE
     */
    interface System {
        /**
         * 默认的标题
         */
        String DEFAULT_TITLE = "默认标题";
        /**
         * 默认的标题对应的 key
         */
        String TITLE_KEY = "title";

        /**
         * 默认的发送者
         */
        Long DEFAULT_SENDER = 0L;
        /**
         * 默认的发送者对应的 key
         */
        String SENDER_KEY = "sender";

        /**
         * 格式化类的 Bean 的名字
         */
        String BUILDER_BEAN_NAME = "systemMessageBuilder";
        /**
         * 发送器的 Bean 的名字
         */
        String SENDER_BEAN_NAME = "systemMessageSender";
        /**
         * 消息处理器的 Bean 的名字
         */
        String PROCESSOR_BEAN_NAME = "systemMessageProcessor";
    }
}
