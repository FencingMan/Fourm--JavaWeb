package com.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * 文章内容中的图片的上传提交保存
 * @author 杨潇
 *
 */
@WebServlet("/imgUpload")
public class ImgUploadServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory(10000,new File("/"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		try {
			List<FileItem> list = upload.parseRequest(req);
			FileItem item = list.get(0);
			String fileName = UUID.randomUUID().toString()+new String(item.getName().substring(item.getName().indexOf('.')));
			String p = this.getServletContext().getRealPath("/upload/img");
			File path = new File(p);
			if(!path.exists()){
				path.mkdirs();
			}
			File file = new File(path,fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			item.write(file);
			item.delete();
			PrintWriter out = resp.getWriter();
			out.write( "{\"success\": 1, \"message\":\"上传成功\",\"url\":\"/cnmd/upload/img/"+fileName+"\"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
