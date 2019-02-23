package io.easyspring.framework.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 异常的返回对象
 *
 * @author summer
 * @date 2019-01-07 18:26
 * @version V1.0.0-RELEASE
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResult {

    /**
     * 请求的状态码
     */
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    /**
     * 错误返回信息
     */
    private ErrorResultAbstract errorResult;

}
