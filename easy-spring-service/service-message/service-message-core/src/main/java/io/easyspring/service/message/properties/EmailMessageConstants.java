package io.easyspring.service.message.properties;

/**
 * 邮件的常量配置
 *
 * @author summer
 * @date 2019-03-15 16:52
 * @version V1.0.0-RELEASE
 */
public interface EmailMessageConstants {

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
}
