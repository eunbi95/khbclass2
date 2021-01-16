package bbs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.bo")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private BbsDAO BbsDAO;
	    private Bbs Bbs;
	    
    public BbsServlet() {
    	BbsDAO = new BbsDAO();
    	Bbs = new Bbs();
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
		if(command.equals("/writeAction.bo")) {
			String userID = null;
		 	if(session.getAttribute("userID") != null){
		 		userID = (String) session.getAttribute("userID");
		 	} //�ڷΰ��� ������(���� �� �ٽ� �����ϱ�)
		 	if(userID == null){
		 	
		 		script.println("<script>");
				script.println("alert('�α����� ���ּ���.')");
		 		script.println("location.href = 'login.jsp'");
		 		script.println("</script>");
		 	} else {
		 		if (request.getParameter("bbsContent").isEmpty()||request.getParameter("bbsTitle").isEmpty()||request.getParameter("bbsContent").equals("")||request.getParameter("bbsContent").equals("")){
		 		
			 		script.println("<script>");
			 		script.println("alert('�Է��� �ȵ� ������ �ֽ��ϴ�.')");
			 		script.println("history.back()");
			 		script.println("</script>");
			 	} else {
			 		BbsDAO BbsDAO = new BbsDAO();
			 		int bbsID = BbsDAO.write(request.getParameter("bbsTitle"), userID, request.getParameter("bbsContent"));
			 		if (bbsID == -1){
				 		script.println("<script>");
				 		script.println("alert('�۾��⿡ �����߽��ϴ�.')");
				 		script.println("history.back()");
				 		script.println("</script>");
				 	}
				 	else{
				 		script.println("<script>");
						script.println("location.href= \'bbs.jsp'");
				 		script.println("</script>");
				 	}
			 	}
		 	}
		
		}
		else if(command.equals("/updateAction.bo"))
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
			Bbs bbs = new BbsDAO().getBbs(bbsID);
			
			if (!userID.equals(bbs.getUserID())) {
				script.println("<script>");
				script.println("alert('������ �����ϴ�.')");
				script.println("location.href = 'bbs.jsp'");
				script.println("</script>");				
			} else {
		 		if (request.getParameter("bbsContent").isEmpty()||request.getParameter("bbsTitle").isEmpty()||request.getParameter("bbsContent").equals("")||request.getParameter("bbsContent").equals("")){
					script.println("<script>");
					script.println("alert('�Է��� �ȵ� ������ �ֽ��ϴ�')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					BbsDAO bbsDAO = new BbsDAO();
					int result = bbsDAO.update(bbsID, request.getParameter("bbsTitle"),request.getParameter("bbsContent"));
					if (result == -1) {
						script.println("<script>");
						script.println("alert('�ۼ����� �����߽��ϴ�')");
						script.println("history.back()");
						script.println("</script>");
					} else {
						script.println("<script>");
						script.println("location.href= \'bbs.jsp'");
						script.println("</script>");
					}
				}
			}
		}
		else if(command.equals("/deleteAction.bo"))
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
			int boardID = 0;
			if (request.getParameter("boardID") != null){
				boardID = Integer.parseInt(request.getParameter("boardID"));
			}
			Bbs bbs = new BbsDAO().getBbs(bbsID);
			if (!userID.equals(bbs.getUserID())) {

				script.println("<script>");
				script.println("alert('������ �����ϴ�.')");
				script.println("location.href = 'bbs.jsp'");
				script.println("</script>");				
			} else {
				BbsDAO bbsDAO = new BbsDAO();
				int result = bbsDAO.delete(bbsID);
				if (result == -1) {
					
					script.println("<script>");
					script.println("alert('�ۻ����� �����߽��ϴ�')");
					script.println("history.back()");
					script.println("</script>");
				} else {

					script.println("<script>");
					script.println("location.href= \'bbs.jsp?boardID="+boardID+"\'");
					script.println("</script>");
				}
			}
		}
	}

}
