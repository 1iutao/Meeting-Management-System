package org.mm.meetingmanage.Interceptor;

import org.mm.meetingmanage.model.Employee;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

//拦截器
public class PermissInterceptor implements HandlerInterceptor {

    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();
        if ("/".equals(requestURL) || "/doLogin".equals(requestURL) || "/register".equals(requestURL) || "doReg".equals(requestURL)){
            return true;     //如果请求的格式是这三种则ture
        }
        HttpSession httpSession = request.getSession(true);
        Employee currentUser = (Employee) httpSession.getAttribute("currentUser");
        if (antPathMatcher.match("/admin/**", requestURL)) {
            if (currentUser != null) {
                if (currentUser.getRole() == 2) {
                    return true;
                }else {
                    response.getWriter().write("forbidden");
                    return false;
                }
            }
        }
        else if (currentUser != null) {
            return true;
        }
        response.sendRedirect("/");
        return false;
    }
}
