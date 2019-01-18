package com.bcdbook.security.core.social.view;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 绑定结果视图
 *
 * @author summer
 * @date 2019-01-17 18:16
 * @version V1.0.0-RELEASE
 */
public class EasyConnectView extends AbstractView {

    /**
     * 绑定/解绑后的关联信息,
     * 当 map 中有此字段时, 表明这是一个绑定操作
     * 否则则是解绑操作
     */
    private static final String CONNECTIONS_KEY = "connections";

    /**
     * 在绑定/解绑成功后返回视图的方法
     *
     * @author summer
     * @date 2019-01-17 18:16
     * @param model 绑定结果的 model 对象
     * @param request 请求信
     * @param response 返回信息
     * @return void
     * @version V1.0.0-RELEASE
     */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	    // 设置请求的返回数据类型
		response.setContentType("text/html;charset=UTF-8");

		// 根据有没有 connections 信息来确定这是一个绑定操作还是解绑操作, 然后返回对应的数据
		if (model.get(CONNECTIONS_KEY) == null) {
			response.getWriter().write("<h3>解绑成功</h3>");
		} else {
			response.getWriter().write("<h3>绑定成功</h3>");
		}

	}

}
