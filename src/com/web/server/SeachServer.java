package com.web.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.web.bean.Article;
import com.web.bean.Show;
import com.web.bean.Users;
import com.web.dao.ArticleDAO;
import com.web.dao.UsersDAO;

public class SeachServer {
		
		//按贴子title查询
		//sql select * from article where title=?
	public static List<Show>  seachTitle(String title){
		// 实例化文章表
		ArticleDAO dao1 = new ArticleDAO();
		// 获取Article所有数据
		List<Article> list1 = dao1.list(Article.class);
		// 实例化用户表
		UsersDAO dao2 = new UsersDAO();
		// 实例化显示表
		List<Show> showList = new ArrayList<Show>();
		//循环遍历
		for (Article a : list1) {
			if(a.getTitle().indexOf(title)!=-1){
				Show show = new Show();
				show.setTitle(a.getTitle());
				show.setPublishTime(a.getPublishTime());
				show.setBrowseNum(a.getBrowseNum());
				//根据userId查询Users中所有的数据
				Users u = dao2.get(new Users(a.getUserId()), "userId");
				show.setUname(u.getUname());
				show.setCommentnum(dao1.queryForInt("select count(*) from comments where aid="+a.getAid()));
				show.setPhotoName(u.getPhotoName());
				show.setAid(a.getAid());
				// 将设置的数据添加到集合中
				showList.add(show);
			}
		}
		Collections.sort(showList,new Comparator<Show>() {
			public int compare(Show o1, Show o2) {
				return o2.getAid()-o1.getAid() ;
			};
		});
		System.out.println(showList.toString());
		return showList;
	}
	//按发帖人查询
	public static List<Show>  seachUname(String uname){
		// 实例化文章表
		ArticleDAO dao1 = new ArticleDAO();
		// 获取Article所有数据
		List<Article> list1 = dao1.list(Article.class);
		// 实例化用户表
		UsersDAO dao2 = new UsersDAO();
		// 实例化显示表
		List<Show> showList = new ArrayList<Show>();
		//循环遍历
		for (Article a : list1) {
			//根据userId查询Users中所有的数据
			Users u = dao2.get(new Users(a.getUserId()), "userId");
			if(u.getUname().equals(uname)){
				Show show = new Show();
				show.setTitle(a.getTitle());
				show.setPublishTime(a.getPublishTime());
				show.setBrowseNum(a.getBrowseNum());
				show.setCommentnum(dao1.queryForInt("select count(*) from comments where aid="+a.getAid()));
				show.setUname(u.getUname());
				show.setPhotoName(u.getPhotoName());
				show.setAid(a.getAid());
				// 将设置的数据添加到集合中
				showList.add(show);
			}
		}
		Collections.sort(showList,new Comparator<Show>() {
			public int compare(Show o1, Show o2) {
				return o2.getAid()-o1.getAid() ;
			};
		});
		System.out.println(showList.toString());
		return showList;
	}
	
}

