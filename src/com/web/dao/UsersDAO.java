package com.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.web.bean.FirstComments;
import com.web.bean.Users;
import com.web.util.BaseDAO;
import com.web.util.DBUtil;

public class UsersDAO extends BaseDAO<Users>{
	public int getFid(){
		return this.queryForInt("select user_seq.nextval from dual");
	}
	
	public String getName(Integer uid){
		return this.queryForString("select uname from users where userid="+uid);
	}
	
	public void updatePoint(Integer userId,Integer Point){
		Users u = new Users();
		u.setUserId(userId);
		Integer p = this.get(u, "userId").getPoint();
		u.setPoint(p+Point);
		this.update2(u, "userId");
	}
	
	
	public void updateBase(Users u){
		String sql = "update users set sex='" + u.getSex() + "',uname='"+u.getUname()+"',email='"+u.getEmail()+"' where userid="+u.getUserId();
		System.out.println(sql);
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//修改用户积分的方法
	public void updatePoint(Users u,int addNum){
		int point = u.getPoint()+addNum;
		String sql = "update users set point= " + point+"where userid="+u.getUserId();
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
