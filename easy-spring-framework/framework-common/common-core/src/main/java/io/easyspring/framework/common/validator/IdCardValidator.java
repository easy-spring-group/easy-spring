package io.easyspring.framework.common.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义校验注解的校验类 - 验证身份证号是否合法
 *
 * 注意: 这里不需要自己注入, 当他实现了 ConstraintValidator 后, spring 会实现自动注入
 *
 * @author summer
 * DateTime 2019-02-15 17:54
 * @version V1.0.0-RELEASE
 */
@Slf4j
public class IdCardValidator implements ConstraintValidator<IdCard, String> {

    /**
     * 校验器初始化
     *
     * Author summer
     * DateTime 2019-02-15 17:54
     * @param idCard 要初始化的校验注解
     * Version V1.0.0-RELEASE
     */
	@Override
	public void initialize(IdCard idCard) {
		log.info("自定义校验注解的校验类 - 验证身份证号是否合法, idCard: {}", idCard);
	}

    /**
     * 校验逻辑
     *
     * Author summer
     * DateTime 2019-02-15 17:54
     * @param value 需要校验的数据
     * @param constraintValidatorContext 校验的上下文(包含校验的返回信息)
     * @return boolean
     * Version V1.0.0-RELEASE
     */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        log.info("需要验证的值 value: {}, ConstraintValidatorContext: {}", value, constraintValidatorContext);

        // 如果传入数据为空, 则直接返回 false
        if (StringUtils.isEmpty(value)) {
            return false;
        }

        /*
         * 执行校验
         */
        // 编译 18 位的正则表达式, 生成模板
        Pattern pattern18 = Pattern.compile(RegexConstant.ID_CARD_18);
        // 验证 18 位的身份证号
        Matcher matcher18 = pattern18.matcher(value);

        // 编译 15 位的正则表达式, 生成模板
        Pattern pattern15 = Pattern.compile(RegexConstant.ID_CARD_15);
        // 验证 15 位的身份证号
        Matcher matcher15 = pattern15.matcher(value);

        // 字符串是否与正则表达式相匹配
        return matcher18.matches() || matcher15.matches();
	}
}
