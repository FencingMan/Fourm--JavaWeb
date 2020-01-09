package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.w3c.dom.UserDataHandler;

import com.web.bean.Collectlike;
import com.web.bean.Point;
import com.web.dao.CollectlikeDAO;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;
import com.web.util.DBUtil;

public class LikeServer {
		
	/**
	 * 查询返回收藏数据条数
	 * @aid
	 */
	public static int getLikes(Integer aid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int c=0;
		String sql = "select count(*) from collectlike where aid=? and cltype='点赞'";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			while (rs.next()) {
				c = rs.getInt(1);
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
	 * 查询某用户对某帖子是否点赞
	 * @aid
	 */
	public  static int getLike(Integer aid,Integer userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int L=0;
		String sql = "select count(*) from collectlike where aid=? and userid=? and cltype='点赞'";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, aid);
			ps.setInt(2, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				L = rs.getInt(1);
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
		return L;
	}
	/**
	 * 某用户取消对某帖子点赞
	 * @aid
	 */
	public static void delLike(Integer aid,Integer userid) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from collectlike where aid=? and userid=? and cltype='点赞'";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, aid);
			ps.setInt(2, userid);
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
	/**
	 * 某用户对某帖子点赞
	 * @aid 
	 * @userid
	 *  收藏点赞ID dao.queryForInt("select collectlike_seq.nextval from dual")
	 */
	public static void addLike(Integer aid,Integer userid) {
		CollectlikeDAO dao = new CollectlikeDAO();
		Collectlike cl = new Collectlike();
		cl.setClid( dao.queryForInt("select collectlike_seq.nextval from dual"));
		cl.setAid(aid);
		cl.setUserid(userid);
		PointDAO pdao = new PointDAO();
		pdao.save2(new Point(userid,5,"点赞",aid));
		UsersDAO udao = new UsersDAO();
		udao.updatePoint(userid, 5);
		cl.setCltype("点赞");
		dao.save2(cl);
	}
}
