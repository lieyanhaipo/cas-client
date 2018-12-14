package com.microservice.casclient.controller;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * @Description  首页
 * @Author libing
 * @Date 2018/12/14
 **/
@Controller
public class IndexController {
    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request){
        //获取cas给我们传递回来的对象，这个对象在Session中
        //session的 key是 CONST_CAS_ASSERTION
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        Principal principal = assertion.getPrincipal();

        String loginName = principal.getName();
        System.out.printf("登录用户名:%s\r\n",loginName);

        System.out.printf("ValidFromDate:%s\r\n",assertion.getValidFromDate());
        System.out.printf("ValidUntilDate:%s\r\n",assertion.getValidUntilDate());
        System.out.printf("AuthenticationDate:%s\r\n",assertion.getAuthenticationDate());

        //获取自定义返回值的数据
        if (principal instanceof AttributePrincipal) {
            //cas传递过来的数据
            Map<String,Object> result =( (AttributePrincipal)principal).getAttributes();
            for(Map.Entry<String, Object> entry :result.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                System.out.printf("%s:%s\r\n",key,val);
            }
        }

        return "index";
    }
}
