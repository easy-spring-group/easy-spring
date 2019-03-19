package io.easyspring.service.message;


import io.easyspring.service.message.support.EasyMessageTemplate;

/**
 * 消息模板的 service
 *
 * @author summer
 * @date 2019-03-12 16:30
 * @version V1.0.0-RELEASE
 */
public interface EasyMessageTemplateService {

    /**
     * 通过模板的识别码, 获取模板对象的方法
     *
     * @param templateCode 模板识别码
     * @return io.easyspring.service.message.support.EasyMessageTemplate
     * @author summer
     * @date 2019-03-13 12:17
     * @version V1.0.0-RELEASE
     */
    EasyMessageTemplate getByCode(String templateCode);

}
