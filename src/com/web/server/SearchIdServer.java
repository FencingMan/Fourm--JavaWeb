package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.web.util.DBUtil;

/**
 * 根据帖子id查询发帖人的userid
 * @author cjt
 *
 */
public class SearchIdServer {
	
	/**
	 * 根据帖子id查询发帖人的userid
	 * @param aid
	 * @return
	 */
	public int getUidByAid(Integer aid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int uid=0;
		String sql = "select userid from article where aid=?";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			while (rs.next()) {
				uid = rs.getInt(1);
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
		return uid;
	}

}
