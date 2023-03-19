package com.minihouse.interceptor;

import com.minihouse.annotation.AuthAccount;
import com.minihouse.exception.UnAuthorizedException;
import com.minihouse.vo.AuthUserVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class SignInInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthAccount authAccount = handlerMethod.getMethodAnnotation(AuthAccount.class);

        if (authAccount == null) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new UnAuthorizedException("권한이 존재하지 않습니다.");
        }

        AuthUserVO authUserVO = (AuthUserVO) session.getAttribute("AUTH_USER");

        if (authUserVO.getId() == null) {
            throw new UnAuthorizedException("권한이 존재하지 않습니다.");
        }

        return true;
    }
}
