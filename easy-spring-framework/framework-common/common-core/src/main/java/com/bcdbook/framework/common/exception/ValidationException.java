package com.bcdbook.framework.common.exception;

import com.bcdbook.framework.common.enums.ErrorResultEnum;
import com.bcdbook.framework.common.result.ValidationFieldError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 数据校验的异常类
 *
 * @author summer
 * @date 2019-01-07 16:55
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@Getter
public class ValidationException extends CommonException {

    /**
     * 数据校验的异常类的构造方法
     *
     * @author summer
     * @date 2019-01-07 16:54
     * @param bindingResult 数据校验的异常信息
     * @version V1.0.0-RELEASE
     */
    public ValidationException(BindingResult bindingResult){
        super(ErrorResultEnum.PARAMETER_ERROR);

        // 获取校验不通过的字段集合
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        // 封装成自己定义的错误校验集合
        List<ValidationFieldError> validationFieldErrorList = fieldErrors.stream()
                .filter(Objects::nonNull)
                .map(fieldError -> {
                    return new ValidationFieldError(fieldError.getField(), fieldError.getDefaultMessage());
                }).collect(Collectors.toList());

        this.setDetails(validationFieldErrorList);
    }

    /**
     * 数据校验的异常类的构造方法
     *
     * @author summer
     * @date 2019-01-08 17:04
     * @param message 异常信息
     * @version V1.0.0-RELEASE
     */
    public ValidationException(String message){
        super(message);
        this.setCode(ErrorResultEnum.PARAMETER_ERROR.getCode());
    }
}
