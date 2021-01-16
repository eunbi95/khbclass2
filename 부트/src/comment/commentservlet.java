package comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("*.co")
public class commentservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Comment CommentDTO;
    private CommentDAO CommentDAO;
    
public commentservlet() {
	CommentDTO = new Comment();
	CommentDAO = new CommentDAO();
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	PrintWriter script = response.getWriter();
	String requestURI = request.getRequestURI();
	String contextPath = request.getContextPath();
	String command = requestURI.substring(contextPath.length());
	HttpSession session= request.getSession();
	if(command.equals("/commentAction.co")) {
		
		String userID = null;
	 	if(session.getAttribute("userID") != null){
	 		userID = (String) session.getAttribute("userID");
	 	}

	 	if(userID == null){
	 		
	 		script.println("<script>");
			script.println("alert('�α����� ���ּ���.')");
	 		script.println("location.href = 'login.jsp'");
	 		script.println("</script>");
	 	} 
	 	else{
		 	int bbsID = 0; 
		 	if (request.getParameter("bbsID") != null){
		 		bbsID = Integer.parseInt(request.getParameter("bbsID"));
		 	}
		 	
		 	if (bbsID == 0){
		 		script.println("<script>");
		 		script.println("alert('��ȿ���� ���� ���Դϴ�.')");
		 		script.println("location.href = 'login.jsp'");
		 		script.println("</script>");
		 	}
	 		if (request.getParameter("commentText").equals("")||request.getParameter("commentText").isEmpty()){
		 		script.println("<script>");
		 		script.println("alert('�Է��� �ȵ� ������ �ֽ��ϴ�.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	} else {
		 		CommentDAO commentDAO = new CommentDAO();
		 		int commentID = commentDAO.write(bbsID, userID, request.getParameter("commentText"));
		 		if (commentID == -1){
			 	
			 		script.println("<script>");
			 		script.println("alert('��� ���⿡ �����߽��ϴ�.')");
			 		script.println("history.back()");
			 		script.println("</script>");
			 	}
		 		else
			 		script.println("<script>");
			 		script.println("location.href=document.referrer;");
			 		script.println("</script>");
			 	}
		 	}
		 }
	else if(command.equals("/commentDeleteAction.co"))
	{
		String userID = null;
		if (session.getAttribute("userID") != null) {//�������̵��̸����� ������ �����ϴ� ȸ������ 
			userID = (String) session.getAttribute("userID");//�������̵� �ش� ���ǰ��� �־��ش�.
		}
		if (userID == null) {
			script.println("<script>");
			script.println("alert('�α����� �ϼ���.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		} 
		int bbsID = 0;
		if (request.getParameter("bbsID") != null){
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		//����� ��ȿ���� �Ǻ�
		int commentID = 0;
		if (request.getParameter("commentID") != null) {
			commentID = Integer.parseInt(request.getParameter("commentID"));
		}
		if (commentID == 0) {
			script.println("<script>");
			script.println("alert('��ȿ���� ���� ��� �Դϴ�.')");
			script.println("history.back()");
			script.println("</script>");
		}
		Comment comment = new CommentDAO().getComment(commentID);
		if (!userID.equals(comment.getUserID())) {
			script.println("<script>");
			script.println("alert('������ �����ϴ�.')");
			script.println("history.back()");
			script.println("</script>");				
		} else {
			CommentDAO commentDAO = new CommentDAO();
			int result = commentDAO.delete(commentID);
			if (result == -1) {
				script.println("<script>");
				script.println("alert('��� ������ �����߽��ϴ�')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				script.println("<script>");
				script.println("location.href=document.referrer;");
				script.println("</script>");
			}
		}
	}
	else if(command.equals("/commentUpdateAction.co"))
	{
		String userID = null;
		if (session.getAttribute("userID") != null) {//�������̵��̸����� ������ �����ϴ� ȸ������ 
			userID = (String) session.getAttribute("userID");//�������̵� �ش� ���ǰ��� �־��ش�.
		}
		
		int commentID = 0;
		if (request.getParameter("commentID") != null){
			commentID = Integer.parseInt(request.getParameter("commentID"));
		}
		String commentText = "";
		if (request.getParameter("commentText")!=null){
			commentText = request.getParameter("commentText");
		}
		
		if (userID == null) {
			script.println("<script>");
			script.println("alert('�α����� �ϼ���.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		} 
		//���� ��ȿ���� �Ǻ�
		int bbsID = 0;
		if (request.getParameter("bbsID") != null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		if (bbsID == 0) {
		
			script.println("<script>");
			script.println("alert('��ȿ���� ���� �� �Դϴ�.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");
		}
		Comment comment = new CommentDAO().getComment(commentID);

		if (!userID.equals(comment.getUserID())) {
			script.println("<script>");
			script.println("alert('������ �����ϴ�.')");
			script.println("location.href = 'bbs.jsp'");
			script.println("</script>");				
		} else {
	 		if (comment.getCommentText().equals("")){
				script.println("<script>");
				script.println("alert('�Է��� �ȵ� ������ �ֽ��ϴ�')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				CommentDAO commentDAO = new CommentDAO();
				int result = commentDAO.update(commentID, commentText);
				if (result == -1) {
					script.println("<script>");
					script.println("alert('�ۼ����� �����߽��ϴ�')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					script.println("<script>");
					script.println("location.href= \'view.jsp?bbsID="+bbsID+"\'");
					script.println("</script>");
				}
			}
		}
	}
	
}
}


