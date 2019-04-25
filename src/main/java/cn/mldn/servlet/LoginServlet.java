package cn.mldn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@SuppressWarnings("serial")
@WebServlet("/shiroLogin")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mid = request.getParameter("mid") ;
		String password = request.getParameter("password") ;
		Subject subject = SecurityUtils.getSubject() ;
		UsernamePasswordToken token = new UsernamePasswordToken(mid,password) ;
		System.out.println("----------------------------");
		subject.login(token);
		request.getSession().setAttribute("mid", mid);
		request.getRequestDispatcher("/pages/welcome.jsp").forward(request, response);
	} 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(SecurityUtils.getSubject().getPrincipal());
		this.doPost(request, response);
	}

}
