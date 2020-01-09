package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.web.util.DBUtil;

public class BrowseNumServer {
		
		public static String sql = "update article set browsenum=? where aid=?";
		
		

		public static int addBrowseNum(Integer aid,Integer BrowseNum){
			

			
				Connection con = null;
				PreparedStatement ps = null;
				int n = 0;
				try {
					con = DBUtil.getCon();
					ps = con.prepareStatement(sql);
					ps.setInt(1, BrowseNum);
					ps.setInt(2, aid);
					n = ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						ps.close();
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return n;
			
		}
}
