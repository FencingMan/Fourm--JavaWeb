package com.web.test;

import java.util.ArrayList;
import java.util.List;

import com.web.bean.Article;
import com.web.dao.ArticleDAO;

public class FileDeleteTest {
	public static List<String> list = new ArrayList<String>();
	public static void main(String[] args) {
		ArticleDAO dao = new ArticleDAO();
		Article a = new Article();
		a.setAid(42);
		a = dao.get(a,"aid");
		String str = a.getContent();
		//System.out.println("![](/cnmd/upload/img/".length());//21
		//System.out.println("50daf2d9-012a-48b3-a071-52ed7ead30a4.jpg".length());//40
		String temp = diGui(str);
		String[] strary = temp.split("鸡巴");
		for(int i = 0 ; i <=strary.length-2;i++){
			System.out.println(strary[i]);
		}
	}
	
	
	public static String diGui(String str){
		if(str.contains("![](/cnmd/upload/img/")){
			String filename= str.substring(str.indexOf("![](/cnmd/upload/img/")+21, str.indexOf("![](/cnmd/upload/img/")+61);
			return filename+"鸡巴"+diGui(str.substring(str.indexOf("![](/cnmd/upload/img/")+61));
		}else{
			return str;
		}
	}
}
