package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembersDAO;
import dto.MembersDTO;


@WebServlet("*.members")
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
				// 값 받아오기
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				// ID 중복 확인
				boolean checkDuplicateId = dao.checkDuplicateId(id);

				// 중복 아닐시 insert
				if(checkDuplicateId) {
					dao.addMember(new MembersDTO(id, pw, name, phone, email, zipcode, address1, address2, null));
					response.sendRedirect("/");
				}else {
					response.sendRedirect("/error.html");
				}

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
