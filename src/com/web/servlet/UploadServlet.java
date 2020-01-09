package com.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.web.bean.Users;
import com.web.dao.UsersDAO;


@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		UsersDAO dao = new UsersDAO();
		Users f = new Users();
		int fid = dao.getFid();
		f.setUserId(fid);
		StringBuffer date = new StringBuffer();
//		System.out.println(f);
		try {
			//判断文件是否上传
			boolean flag = ServletFileUpload.isMultipartContent(req);
			if (flag) {
				//服务器临时保存上传文件的位置 保存在根路径下
				DiskFileItemFactory factory = new DiskFileItemFactory(1000000, new File("/"));
				//客户端的所有文件上传到服务器
				ServletFileUpload upload = new ServletFileUpload(factory);
				//设置字符编码
				upload.setHeaderEncoding("utf-8");
				//从请求对象中解析出所有表单元素内容（文本域，文件）返回集合
				List<FileItem> list = upload.parseRequest(req);
				//遍历
				for (FileItem item : list) {
					if (item.getFieldName().equals("file")) {//!!!不是根据实体类的fname 获取的是文件标签的name
						//如果不是 获取文件名称
						String filename = item.getName();
						f.setPhotoName(fid+filename);
						//获取项目根目录
						String path = this.getServletContext().getRealPath("/upload/photo");
						//创建保存文件目录
						File upfile = new File(path);
						//第一次上传目录不存在，则创建
						if (!upfile.exists()) {
							upfile.mkdirs();
						}
						//保存文件到upload下的photo目录下
						File file = new File(upfile, fid+filename);
						//写入文件
						item.write(file);
						//删除临时文件
						item.delete();
					} else if(item.getFieldName().equals("username")){
						String username = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						f.setUname(username);
					} else if(item.getFieldName().equals("password")){
						String password = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						f.setPassword(password);
					} else if(item.getFieldName().equals("email")){
						String email = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						f.setEmail(email);
					} else if(item.getFieldName().equals("sex")){
						String sex = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						f.setSex(sex);
					}else if(item.getFieldName().equals("sel1")){
						String year = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						date.append(year+"-");
					}else if(item.getFieldName().equals("sel2")){
						String month = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						date.append(month+"-");
					}else if(item.getFieldName().equals("sel3")){
						String day = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						date.append(day);
					}
					
				}
				
				String str = date.toString();
				Date d = java.sql.Date.valueOf(str);
				f.setBirth(d);
				System.out.println(f);
				if (!f.getUserId().equals("")&&!f.getUname().equals("")&&!f.getEmail().equals("")&&!f.getPassword().equals("")&&!f.getSex().equals("")&&!f.getBirth().equals("")&&!f.getPhotoName().equals("")) {
					dao.save2(f);
				}
				resp.sendRedirect("reg.jsp");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
