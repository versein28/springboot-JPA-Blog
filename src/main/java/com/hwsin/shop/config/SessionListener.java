/*
 * package com.hwsin.shop.config;
 * 
 * import java.util.Date;
 * 
 * import javax.servlet.annotation.WebListener; import
 * javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpSession;
 * import javax.servlet.http.HttpSessionEvent; import
 * javax.servlet.http.HttpSessionListener;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.context.WebApplicationContext; import
 * org.springframework.web.context.request.RequestContextHolder; import
 * org.springframework.web.context.request.ServletRequestAttributes; import
 * org.springframework.web.context.support.WebApplicationContextUtils;
 * 
 * import com.hwsin.shop.model.Visit; import
 * com.hwsin.shop.service.VisitService;
 * 
 * @WebListener public class SessionListener implements HttpSessionListener {
 * 
 * @Override // 세션 생성될 때 동작 public void sessionCreated(HttpSessionEvent
 * sessionEvent) {
 * 
 * HttpSession session = sessionEvent.getSession(); // 등록되어있는 빈을 사용할수 있도록 설정해준다
 * WebApplicationContext wac = WebApplicationContextUtils
 * .getRequiredWebApplicationContext(session.getServletContext()); // request를
 * 파라미터에 넣지 않고도 사용할수 있도록 설정 HttpServletRequest req = ((ServletRequestAttributes)
 * RequestContextHolder.currentRequestAttributes()) .getRequest();
 * 
 * VisitService visitService = (VisitService) wac.getBean("visitService"); //
 * ip값 String ip = req.getRemoteAddr(); System.out.println("req.getRemoteAddr()"
 * +req.getRemoteAddr()); // 전체 방문자 수 증가 visitService.setVisitTotalCount(ip); //
 * log
 * System.out.println(" Session ID ".concat(sessionEvent.getSession().getId()).
 * concat(" created at ") .concat(new Date().toString())); }
 * 
 * @Override public void sessionDestroyed(HttpSessionEvent sessionEvent) {
 * 
 * }
 * 
 * }
 */