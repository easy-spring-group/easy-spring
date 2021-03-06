package io.easyspring.security.core.validate.code.image;

import io.easyspring.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片验证码处理器
 *
 * @author summer
 * @version V1.0.0-RELEASE
 * DateTime 2019-01-17 13:46
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     *
     * Author summer
     * DateTime 2019-01-17 13:45
     * @param request servlet 请求信息
     * @param imageCode 图片验证码
     * @throws IOException io 流异常
     * Version V1.0.0-RELEASE
     */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws IOException {
	    // 获取 response 对象
        HttpServletResponse servletResponse = request.getResponse();
        // 校验 response 对象是否为空
        if (servletResponse == null) {
            throw new RuntimeException("发送图片验证码时, response 对象为空");
        }

        //禁止图像缓存。
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setHeader("Cache-Control", "no-cache");

        // 把图片信息写入到返回流中
        ImageIO.write(imageCode.getImage(),
                "JPEG",
                servletResponse.getOutputStream());
	}

}
