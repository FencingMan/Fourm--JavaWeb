package com.web.servlet;
/*
 * 查询出所有评论
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import oracle.net.aso.f;

import com.web.bean.Article;
import com.web.bean.Comments;
import com.web.bean.FirstComments;
import com.web.bean.SecondComments;
import com.web.bean.Users;
import com.web.dao.CommentsDAO;
import com.web.dao.UsersDAO;
import com.web.util.RowMapper;

@WebServlet("/comments")
public class FirstCommentsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置响应输出字符编码
		resp.setContentType("text/html;charset=utf-8");
		
		List<Comments> commentsList = new ArrayList<Comments>();
		
		/*
		 * 所有的一级评论
		 */
		//获取文章id
		String aid = req.getParameter("aid");
		//System.out.println(aid);
		// 实例化CommentsDAO
		CommentsDAO dao = new CommentsDAO();
		//实例化UsersDAO
		UsersDAO uDao = new UsersDAO();
		
		//查询出一级评论  
		
		List<Comments> firstComments = dao.queryForList("select * from comments where aid=? and superid=0",
				new RowMapper<Comments>() {
					@Override
					public Comments mapRow(ResultSet rs) throws Exception {
						Comments s = new Comments();
						s.setAid(rs.getInt("aid"));
						s.setC_content(rs.getString("c_content"));
						s.setCid(rs.getInt("cid"));
						s.setCommentTime(rs.getString("commentTime"));
						s.setSuperid(rs.getInt("superid"));
						s.setUname(rs.getString("uname"));
						s.setClevel(rs.getInt("clevel"));
						s.setUserId(rs.getInt("userId"));
						return s;
					}
				}, aid);
		//根据cid从大到小进行排序
		Collections.sort(firstComments, new Comparator<Comments>() {
			@Override
			public int compare(Comments o1, Comments o2) {
				//o2-o1为降序，o1-o2为升序
				return o2.getCid()-o1.getCid();
			}
		});
		
		//循环遍历  
		List<List<Comments>> secondCommentsList = new ArrayList<List<Comments>>();
		for (Comments fpl : firstComments) {
			
			List<Comments> secondComments = new ArrayList<Comments>();//一条一级评论里面的所有二级
			secondComments = dao.queryForList("select * from comments where aid=? and clevel=2 and superid=?", new RowMapper<Comments>() {

				@Override
				public Comments mapRow(ResultSet rs) throws Exception {
					Comments sc = new Comments();
					sc.setAid(rs.getInt("aid"));
					sc.setC_content(rs.getString("c_content"));
					sc.setCid(rs.getInt("cid"));
					sc.setCommentTime(rs.getString("commentTime"));
					sc.setSuperid(rs.getInt("superid"));
					sc.setUname(rs.getString("uname"));
					sc.setUserId(rs.getInt("userId"));
					sc.setClevel(rs.getInt("clevel"));
					return sc;
				}
			}, aid, fpl.getCid());
			secondCommentsList.add(secondComments);
		}	

		//System.out.println(firstComments.toString());
		//System.out.println("--"+secondCommentsList.toString());
		//绑定
			
		req.setAttribute("firstComments", firstComments);
		req.setAttribute("secondComments", secondCommentsList);
//		// 转发
		req.getRequestDispatcher("article_show.jsp").forward(req, resp);
	}
		
}
