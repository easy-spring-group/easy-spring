package io.easyspring.framework.common.exception;

import io.easyspring.framework.common.enums.ErrorResultEnum;
import io.easyspring.framework.common.result.ValidationFieldError;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
     * 根据传入的 ConstraintViolationException 封装自定义的数据校验异常
     *
     * @author summer
     * @date 2019-01-10 16:39
     * @param constraintViolationException 约束不匹配的异常对象
     * @version V1.0.0-RELEASE
     */
    public ValidationException(ConstraintViolationException constraintViolationException){
        super(ErrorResultEnum.PARAMETER_ERROR);

        // 获取错误的字段
        Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();

        // 封装成自己定义的错误校验集合
        List<ValidationFieldError> validationFieldErrorList = constraintViolationSet
                .stream()
                .filter(Objects::nonNull)
                .map(constraintViolation -> {
                    return new ValidationFieldError(constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage());
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
