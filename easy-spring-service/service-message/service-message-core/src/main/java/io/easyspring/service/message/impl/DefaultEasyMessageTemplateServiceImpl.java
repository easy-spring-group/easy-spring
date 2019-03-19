package io.easyspring.service.message.impl;

import io.easyspring.service.message.EasyMessageTemplateService;
import io.easyspring.service.message.support.EasyMessageTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的消息模板 service
 *
 * @author summer
 * @date 2019-03-13 12:17
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class DefaultEasyMessageTemplateServiceImpl implements EasyMessageTemplateService {

    /**
     * 通过模板的识别码, 获取模板对象的方法
     *
     * @param templateCode 模板识别码
     * @return com.yinbaochina.management.risk.message.manage.support.EasyMessageTemplate
     * @author summer
     * @date 2019-03-13 12:17
     * @version V1.0.0-RELEASE
     */
    @Override
    public EasyMessageTemplate getByCode(String templateCode) {
        log.warn("获取消息模板的方法, 需要自己实现, 请实现接口 EasyMessageTemplateService");
        return null;
    }
}
