package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;


@WebServlet("*.Members")
public class MembersController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getRequestURI();
		MembersDAO dao = MembersDAO.getInstance();
		
		try {
			if(cmd.equals("/login.members")) {
				// userId, Pw 가져오기
				String userId = request.getParameter("userId");
				String userPassword = request.getParameter("userPassword");
				
				// 로그인 확인
				boolean checkLogin = dao.checkLogin(userId, userPassword);
				
				// 로그인 성공시
				if(checkLogin) {
					// Session 에 userId 저장
					request.getSession().setAttribute("userId", userId);
					// index.jsp 로 다시 보내기
					response.sendRedirect("/");
				
				}else { // 실패시
					response.sendRedirect("/error.html");
				}
			
			}else if(cmd.equals("/join.members")) {
				
			}else if(cmd.equals("")) {

			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
