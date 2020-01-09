package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.bean.Article;
import com.web.dao.ArticleDAO;
import com.web.util.DBUtil;
import com.web.util.RowMapper;

public class MyartilceServer {
		/*
		 * 获取我的帖子列表
		 */
		public static List<Article> getArticles(Integer userid) {
			ArticleDAO dao = new ArticleDAO();
			List<Article> Articles = dao.queryForList("select aid ,title,publishtime,browsenum from article where userid =?", new RowMapper<Article>() {
				
				@Override
				public Article mapRow(ResultSet rs) throws Exception {
					Article article = new Article();
					article.setAid(rs.getInt("aid"));
					article.setTitle(rs.getString("title"));
					article.setPublishTime(rs.getString("publishtime"));
					article.setBrowseNum(rs.getInt("browseNum"));
					return article;
				}
			}, userid);
			return Articles;
		}
		
		/*
		 * 删除我的帖子
		 */
		public static void delArticle(Integer aid,Integer userid){
			
			
			Connection con = null;
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			String sql1 = "delete from article where aid=? and userid=?";
			String sql2 = "delete from collectlike where aid=? and userid=? ";
			try {
				
				con = DBUtil.getCon();
				//删除帖子
				ps1 = con.prepareStatement(sql1);
				ps1.setInt(1, aid);
				ps1.setInt(2, userid);
				ps1.executeUpdate();
				//删除收藏点赞
				ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, aid);
				ps2.setInt(2, userid);
				ps2.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ps1.close();
					ps2.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		/*
		 * 获取我收藏的帖子列表
		 */
		public static List<Article> getCollectArticles(Integer userid) {
			ArticleDAO dao = new ArticleDAO();
			List<Article> Articles = dao.queryForList("select aid ,title,publishtime,browsenum from article where aid in (select aid from collectlike where userid =? and cltype='收藏')", new RowMapper<Article>() {
				
				@Override
				public Article mapRow(ResultSet rs) throws Exception {
					Article article = new Article();
					article.setAid(rs.getInt("aid"));
					article.setTitle(rs.getString("title"));
					article.setPublishTime(rs.getString("publishtime"));
					article.setBrowseNum(rs.getInt("browseNum"));
					return article;
				}
			}, userid);
			return Articles;
		}
		/*
		 * 删除我收藏的帖子
		 */
		public static void delCollectArticle(Integer aid,Integer userid) {
			 CollectServer.delCollect(aid,userid);
		}
		
		public static void main(String[] args) {
			
			ArticleDAO dao = new ArticleDAO();
			Article article = dao.get(new Article(3), "aid");
			String con = article.getContent();
			System.out.println(con);
			getphoto(con);
			
		}
		public static List<String> photolist = new ArrayList<String>();
		
		
		public static  String  getphoto(String s){
			if(!s.contains("(/cnmd/upload/img/")){
				
				
//				return "没有啦";
			}
			int index1 = s.indexOf("(/cnmd/upload/img/");
			String temp = s.substring(index1+18);
			System.out.println(temp);
			String s2 =temp.substring(index1,40); 
			System.out.println(s2);
			System.out.println(s.substring(index1+58));
			photolist.add(s2);
			String s3 = s.substring(index1+58);
			return getphoto(s3);
			
		}
}
