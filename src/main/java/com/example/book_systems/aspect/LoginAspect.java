package com.example.book_systems.aspect;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.criterion.PropertyProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import com.example.book_systems.constans.UserAndPersonInfoRtnCode;
import com.example.book_systems.vo.respone.MsgRes;

@Component
@Aspect
public class LoginAspect {

	// slf4j
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// 切入點，定義 controller 下所有程式都被包含
	// execution 方法執行時觸發、*(返回 Response 任意型態)、controller package 路徑、
	// .*(回傳 Requery 任意型態)、.*(..)(任意方法，任意參數(兩個點))
	@Pointcut("execution (public * com.example.book_systems.controller.*.*(..)) && "
			+ "!execution (public * com.example.book_systems.controller.*.login(..)) && "
			+ "!execution (public * com.example.book_systems.controller.*.forget(..)) && "
			+ "!execution (public * com.example.book_systems.controller.*.sign(..)) && "
			+ "!execution (public * com.example.book_systems.controller.*.checkPwdToken(..)) && "
			+ "!execution (public * com.example.book_systems.controller.*.replacePwd(..)) ")
	public void pointcut() {
		
	}
	
	@Around("pointcut()")
	public Object arround(ProceedingJoinPoint pjp) throws Throwable{
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		String account = (String) session.getAttribute("account");
		String pwd = (String) session.getAttribute("pwd");
		
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new MsgRes(UserAndPersonInfoRtnCode.ACCOUNT_NOT_LOGIN.getCode(),UserAndPersonInfoRtnCode.ACCOUNT_NOT_LOGIN.getMessage());
		}
		
		Object[] obj = new Object[] {account,pwd};
		
		Object result = pjp.proceed(obj);
		
		return result;
	}
}
