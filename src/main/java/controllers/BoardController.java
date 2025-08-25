package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import commons.Config;
import dao.BoardDAO;
//import dao.ReplyDAO;
import dto.BoardDTO;
//import dto.ReplyDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BoardDAO dao = BoardDAO.getInstance();
//		ReplyDAO reply_dao = ReplyDAO.getInstance();
		String cmd = request.getRequestURI();

		try {
			if(cmd.equals("/list.board")) {

				// 선택한 페이지 가져오기
				int cpage = 0;
				String cpageStr = request.getParameter("cpage");

				if(cpageStr != null) {
					cpage = Integer.parseInt(cpageStr);
				}else {
					cpage = 1;
				}

				// List 가져오기
				//List<BoardDTO> list = dao.getAllList();
				List<BoardDTO> list = dao.getSelectFromTo(cpage*Config.RECORD_COUNT_PER_PAGE-(Config.RECORD_COUNT_PER_PAGE-1),
															cpage*Config.RECORD_COUNT_PER_PAGE);
				String navi = dao.getPageNavi(cpage);

				// request 에 담기
				request.setAttribute("list", list);
				request.setAttribute("navi", navi);
				request.setAttribute("recordTotalCount", dao.getRecordTotalCount());
				request.setAttribute("recordCountPerPage", Config.RECORD_COUNT_PER_PAGE);
				request.setAttribute("naviCountPerPage", Config.NAVI_COUNT_PER_PATE);
				request.setAttribute("currentPage", cpage);

				request.getRequestDispatcher("/board/members_board.jsp").forward(request, response);

			}else if(cmd.equals("/writing_board.board")) { // 회원게시판 목록 출력
				request.getSession().getAttribute("loginId");
				request.getRequestDispatcher("/board/writing_board.jsp").forward(request, response);

			}else if(cmd.equals("/add_board.board")) { // 글 작성하면 추가
				// loginId 가져오기
//				String writer = (String)request.getSession().getAttribute("loginId");
//				String title = request.getParameter("title");
//				String contents = request.getParameter("contents");
//
//				BoardDTO dto = new BoardDTO(0, writer, title, contents, null, 0);
//				int result = dao.addBoard(dto);
//
//				if(result != 0) {
//					response.sendRedirect("/list.board?cpage=1");
//				}else {
//					response.sendRedirect("/error.html");
//				}

//			}else if(cmd.equals("/detail.board")) { // 글 제목 클릭시 내용 보여주기
//				int seq = Integer.parseInt(request.getParameter("seq"));
//				BoardDTO dto = dao.getListBySeq(seq);
//				List<ReplyDTO> list = reply_dao.getListBySeq(seq);
//
//				// request 에 dto, reply_list 담아서 보내기
//				request.setAttribute("dto", dto);
//				request.setAttribute("list", list);
//				request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
//
//			}else if(cmd.equals("/delete.board")) { // 글 주인이 글 삭제시
//				int seq = Integer.parseInt(request.getParameter("dto_seq2"));
//				int result = dao.deleteBySeq(seq);
//
//				if(result != 0) {
//					response.sendRedirect("/list.board");
//				}else {
//					response.sendRedirect("/error.html");
//				}
//			}else if(cmd.equals("/update.board")) { // 글 주인이 글 수정시
//				int seq = Integer.parseInt(request.getParameter("dto_seq"));
//				String title = request.getParameter("dto_title");
//				String contents = request.getParameter("dto_contents");
//
//				int result = dao.updateBySeq(title, contents, seq);
//
//				if(result != 0) {
//					response.sendRedirect("/detail.board?seq=" + seq);
//				}else {
//					response.sendRedirect("/error.html");
//				}
			}


		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
