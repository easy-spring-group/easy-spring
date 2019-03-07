package io.easyspring.security.sso.server.confirm;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * {@link SsoApprovalEndpoint} 对象中需要一个返回视图, 而 Spring 自带的返回视图是私有的,
 * 所以这里重新写一个类用于返回
 *
 * @author summer
 * @date 2019-02-23 20:00
 * @version V1.0.0-RELEASE
 */
public class SsoSpelView implements View {

    private final String template;

    private final String prefix;

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private final StandardEvaluationContext context = new StandardEvaluationContext();

    private PlaceholderResolver resolver;

    public SsoSpelView(String template) {
        this.template = template;
        this.prefix = new RandomValueStringGenerator().generate() + "{";
        this.context.addPropertyAccessor(new MapAccessor());
        this.resolver = new PlaceholderResolver() {
            @Override
            public String resolvePlaceholder(String name) {
                Expression expression = parser.parseExpression(name);
                Object value = expression.getValue(context);
                return value == null ? null : value.toString();
            }
        };
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>(model);
        String path = ServletUriComponentsBuilder.fromContextPath(request).build()
                .getPath();
        map.put("path", (Object) path==null ? "" : path);
        context.setRootObject(map);
        String maskedTemplate = template.replace("${", prefix);
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(prefix, "}");
        String result = helper.replacePlaceholders(maskedTemplate, resolver);
        result = result.replace(prefix, "${");
        response.setContentType(getContentType());
        response.getWriter().append(result);
    }

}
