package com.minihouse.resolver;

import com.minihouse.annotation.AuthUser;
import com.minihouse.vo.AuthUserVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasSessionAuthAnnotation = parameter.hasParameterAnnotation(AuthUser.class);
        boolean hasAuthUserType = AuthUserVO.class.isAssignableFrom(parameter.getParameterType());
        return hasSessionAuthAnnotation && hasAuthUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = httpServletRequest.getSession(false);

        if (session == null) {
            return null;
        }

        return session.getAttribute("AUTH_USER");
    }
}
