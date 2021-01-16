package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import user.UserDAO;
import user.User;


@WebServlet("*.us")
public class Userservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  	private User user;
	    private UserDAO userDAO;
	   
	
	    public Userservlet() {
	    	user = new User();
	    	userDAO = new UserDAO();
	    }
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request,response);
		}  
  
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter script = response.getWriter();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		HttpSession session= request.getSession();
		if(command.equals("/loginAction.us")) {
			String userID = null;
			if(session.getAttribute("userID") != null){
				userID = (String)session.getAttribute("userID");
			}
			if(userID != null){
			
				script.println("<script>");
				script.println("alert('�̹� �α����� �Ǿ��ֽ��ϴ�.')");
				script.println("location.href='bbs.jsp'");
				script.println("</script>");
			}
			
				int result = userDAO.login(request.getParameter("userID"),request.getParameter("userPassword"));
				if(result == 1){
					session.setAttribute("userID", request.getParameter("userID"));
					script.println("<script>");
					script.println("alert('�α��� ����')");
					script.println("location.href='bbs.jsp'");
					script.println("</script>");
				}else if(result == 0){
					
					script.println("<script>");
					script.println("alert('��й�ȣ�� Ʋ���ϴ�')");
					script.println("history.back()");
					script.println("</script>");
				}else if(result == -1){
			
					script.println("<script>");
					script.println("alert('�������� �ʴ� ���̵��Դϴ�')");
					script.println("history.back()");
					script.println("</script>");
				}else if(result == -2){
				
					script.println("<script>");
					script.println("alert('�����ͺ��̽� �����Դϴ�')");
					script.println("history.back()");
					script.println("</script>");
				}
			
		}else if(command.equals("/joinAction.us"))
		{
			String userID = null;
			User userDTO =new User();
			userDTO.setUserID(request.getParameter("userID"));
			userDTO.setUserGender(request.getParameter("userGender"));
			userDTO.setUserName(request.getParameter("userName"));
			userDTO.setUserPassword(request.getParameter("userPassword"));
			userDTO.setUserPhoneNumber(request.getParameter("userPhoneNumber"));
			
		 	if(session.getAttribute("userID") != null){
		 		userID = (String) session.getAttribute("userID");
		 	}
		 	if(userID != null){
		 		
		 		script.println("<script>");
		 		script.println("�̹� �α����� �Ǿ� �ֽ��ϴ�')");
		 		script.println("location.href = 'bbs.jsp'");
		 		script.println("</script>");
		 	}
		 	if (userDTO.getUserID()==null||userDTO.getUserGender()==null||userDTO.getUserName()==null||userDTO.getUserPassword()==null||userDTO.getUserPhoneNumber()==null){

		 		script.println("<script>");
		 		script.println("alert('�Է��� �ȵ� ������ �ֽ��ϴ�.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	} else {
		 		UserDAO userDAO = new UserDAO();
		 		int result = userDAO.join(userDTO);
		 		if (result == -1){
		
			 		script.println("<script>");
			 		script.println("alert('�̹� �����ϴ� ���̵� �Դϴ�.')");
			 		script.println("history.back()");
			 		script.println("</script>");
			 	}
			 	else{
			 		session.setAttribute("userID",userDTO.getUserID());
			 		script.println("<script>");
			 		script.println("location.href = 'bbs.jsp'");
			 		script.println("</script>");
			 	}
		 	}
		}
		
		
		
	}

}
