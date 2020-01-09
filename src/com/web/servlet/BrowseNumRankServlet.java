package com.web.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.bean.Article;
import com.web.bean.Temp;
import com.web.dao.ArticleDAO;
import com.web.util.RowMapper;

@WebServlet("/browseRank")
public class BrowseNumRankServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ArticleDAO dao = new ArticleDAO();
		List<Article> list = dao.queryForList("select * from (select rank() over (order by a.browsenum desc) as xuhao, a.* from article a) where xuhao<=5", new RowMapper<Article>() {
			
			@Override
			public Article mapRow(ResultSet rs) throws Exception {
				Article a = new Article();
				a.setAid(rs.getInt("aid"));
				a.setTitle(rs.getString("title"));
				return a;
			}
		});
		ArrayList<Temp> list2 = new ArrayList<Temp>();
		for(Article a : list){
			Temp t = new Temp();
			t.setTitle(a.getTitle());
			t.setAid(a.getAid());
			t.setNum(dao.queryForInt("select count(*) from comments where aid="+a.getAid()));
			list2.add(t);
		}
		req.getSession().setAttribute("browseRank", list2);
		req.getRequestDispatcher("forum_index.jsp").forward(req, resp);
	}
}
