package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.web.util.DBUtil;

/**
 * 根据帖子id或者userid查询相应的人的姓名
 * @author cjt
 *
 */
public class SearchNameServer {
	
	/**
	 * 根据帖子id查询相应的发帖人姓名
	 * @param aid
	 * @return
	 */
	public String getNameByAid(Integer aid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String c="";
		String sql = "select uname from users where userid=(select userid from article where aid=?)";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			while (rs.next()) {
				c = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	
	/**
	 * 根据userid查询相应的人的姓名
	 * @param userid
	 * @return
	 */
	public String getNameByUid(Integer userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String c="";
		String sql = "select uname from users where userid=?";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				c = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return c;
	}

}
