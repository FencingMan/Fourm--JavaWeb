package com.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.web.bean.Users;

@WebServlet("/alterPhoto")
public class AlterPhotoServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory(100000, new File("/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		System.out.println(ServletFileUpload.isMultipartContent(req));
		HttpSession session = req.getSession();
		Users u = (Users)session.getAttribute("user");
		try {
			List<FileItem> list = upload.parseRequest(req);
			FileItem item = list.get(0);
			String path = this.getServletContext().getRealPath("/upload/photo");
			String fileName = u.getPhotoName();
			File f = new File(path,fileName);
			item.write(f);
			item.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
