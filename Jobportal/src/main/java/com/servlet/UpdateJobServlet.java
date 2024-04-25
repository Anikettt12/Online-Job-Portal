package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.jobs;

@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String title = req.getParameter("title");
			String location = req.getParameter("location");
			String category = req.getParameter("category");
			String status = req.getParameter("status");
			String desc = req.getParameter("desc");
			
			jobs j = new jobs();
			j.setId(id);
			j.setTitle(title);
			j.setLocation(location);
			j.setCategory(category);
			j.setStatus(status);
			j.setDescription(desc);
			
			
			HttpSession session = req.getSession();
			JobDAO dao = new JobDAO(DBConnect.getConn());
			boolean f = dao.updatejob(j);
			if (f) {
				session.setAttribute("succMsg", "job update Sucessfully");
				resp.sendRedirect("View_job.jsp");
			}
			else {
				session.setAttribute("succMsg", "Something Went Wrong");
				resp.sendRedirect("View_job.jsp");
			}
			
		}
	
		catch(Exception e ) {
			e.printStackTrace();
		}
	
}
}
