package com.microservice.casclient.conf;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Description 过滤掉不需要授权登录的页面
 * @Author libing
 * @Date 2018/12/14
 **/
public class UrlPatternMatcherStrategyImpl implements UrlPatternMatcherStrategy {
    private Pattern pattern;
    /**
     * description:判断是否匹配这个字符串
     * @param: [url]用户请求的连接
     * @return: true:不需要拦截
     *          false:必须要登录
     */
    @Override
    public boolean matches(String url) {
        //使用正则表达式来匹配需要忽略的连接
        return this.pattern.matcher(url).find();
    }

    /**
     * description:正则表达式的规则，该规则在配置AuthenticationFilter的ignorePattern中设置
     * @param: [pattern]
     * @return: void
     */
    @Override
    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
}
