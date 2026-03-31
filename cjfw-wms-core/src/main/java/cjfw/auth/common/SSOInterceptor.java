package cjfw.auth.common;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import nets.sso.agent.web.v9.SSOAuthn;

@RequiredArgsConstructor
public class SSOInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return SSOAuthn.authnAndSet(request, response, "https://waylodev.ifresh.co.kr:9443/api/cm/sso/v1.0/loginProc"); // request 객체에 'ssoUser'와 'ssoUrl' 속성이 설정됩니다.
	}
}
