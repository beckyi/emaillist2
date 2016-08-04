package kr.ac.sungkyul.emaillist.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.emaillist.dao.EmailListDao;
import kr.ac.sungkyul.emaillist.vo.EmailListVo;

/**
 * Servlet implementation class EmaillistServlet
 */
@WebServlet("/el")
public class EmaillistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		if("form".equals(actionName)){
			
			//forwarding
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/form.jsp");	//request 연장
			rd.forward(request, response);
			
		} if("insert".equals(actionName)){
			request.setCharacterEncoding("utf-8");
			String firstname = request.getParameter("fn");
			String lastname = request.getParameter("ln");
			String email = request.getParameter("email");
			
			EmailListVo vo = new EmailListVo();
			vo.setFirstname(firstname);
			vo.setLastname(lastname);
			vo.setEmail(email);
			   
			EmailListDao dao = new EmailListDao();
			dao.insert(vo);
			
			response.sendRedirect("/emaillist2/el");
			
		}else {	//list 출력
			EmailListDao dao = new EmailListDao();
			List<EmailListVo> list = dao.getList();
			
			//request 범위(scope)에 List 객체를 저장
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/list.jsp");	//request 연장
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	//같은 곳에서 한 루틴을 사용한다?
	}

}
