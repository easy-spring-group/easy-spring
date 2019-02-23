package io.easyspring.security.core.social.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 社交账号绑定状态视图
 *
 * @author summer
 * @date 2019-01-17 18:23
 * @version V1.0.0-RELEASE
 */
@Component("connect/status")
public class EasyConnectionStatusView extends AbstractView {

    /**
     * 注入 Jackson 的
     */
    @Autowired
	private ObjectMapper objectMapper;
	
    /**
     * 获取社交账户绑定后的视图
     *
     * @author summer
     * @date 2019-01-17 18:23
     * @param model 模型信息
     * @param request 请求对象
     * @param response 返回对象
     * @return void
     * @version V1.0.0-RELEASE
     */
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	    // 获取关联结果信息
		Map<String, List<Connection<?>>> connections = (Map<String, List<Connection<?>>>) model.get("connectionMap");

		// 定义返回数据对象
		Map<String, Boolean> result = new HashMap<>();
		// 循环封装绑定时的关联结果信息的数据
		for (String key : connections.keySet()) {
			result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
		}

		// 设置返回结果数据的类型
		response.setContentType("application/json;charset=UTF-8");
		// 输出到前端
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
