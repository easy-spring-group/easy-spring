package com.bcdbook.framework.common.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义校验注解的校验类 - 验证手机号是否合法
 *
 * 注意: 这里不需要自己注入, 当他实现了 ConstraintValidator 后, spring 会实现自动注入
 *
 * @author summer
 * @date 2018-12-19 13:21
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    /**
     * 校验器初始化
     *
     * @author summer
     * @date 2018-12-19 13:23
     * @param telephone 要初始化的校验注解
     * @return void
     * @version V1.0.0-RELEASE
     */
	@Override
	public void initialize(Telephone telephone) {
		log.info("自定义校验注解的校验类 - 验证手机号是否合法, telephone: {}", telephone);
	}

    /**
     * 校验逻辑
     *
     * @author summer
     * @date 2018-12-19 13:23
     * @param value 需要校验的数据
     * @param constraintValidatorContext 校验的上下文(包含校验的返回信息)
     * @return boolean
     * @version V1.0.0-RELEASE
     */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        log.info("需要验证的值 value: {}, ConstraintValidatorContext: {}", value, constraintValidatorContext);

        // 如果传入数据为空, 则直接返回 false
        if (StringUtils.isEmpty(value)) {
            return false;
        }

        // 编译正则表达式, 生成模板
        Pattern pattern = Pattern.compile(RegexConstant.TELEPHONE);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
	}
}
