package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.web.bean.Collectlike;
import com.web.bean.Point;
import com.web.dao.CollectlikeDAO;
import com.web.dao.PointDAO;
import com.web.dao.UsersDAO;
import com.web.util.DBUtil;

public class CollectServer {
		
		
		/**
		 * 查询返回收藏数据条数
		 * @aid
		 */
		public static int getCollects(Integer aid) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int c=0;
			String sql = "select count(*) from collectlike where aid=? and cltype='收藏'";
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
		 * 查询某用户对某帖子是否收藏
		 * @aid
		 */
		public static int getCollect(Integer aid,Integer userid) {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int L=0;
			String sql = "select count(*) from collectlike where aid=? and userid=? and cltype='收藏'";
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
		 * 某用户取消对某帖子收藏
		 * @aid 
		 * @userid
		 */
		public static void delCollect(Integer aid,Integer userid) {
			Connection con = null;
			PreparedStatement ps = null;
			String sql = "delete from collectlike where aid=? and userid=? and cltype=?";
			try {
				con = DBUtil.getCon();
				ps = con.prepareStatement(sql);
				ps.setInt(1, aid);
				ps.setInt(2, userid);
				ps.setString(3, "收藏");
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
		 * 某用户对某帖子收藏
		 * @aid 
		 * @userid
		 *  收藏点赞ID dao.queryForInt("select collectlike_seq.nextval from dual")
		 */
		public static void addCollect(Integer aid,Integer userid) {
			CollectlikeDAO dao = new CollectlikeDAO();
			Collectlike cl = new Collectlike();
			cl.setClid( dao.queryForInt("select collectlike_seq.nextval from dual"));
			PointDAO pdao = new PointDAO();
			pdao.save2(new Point(userid,5,"收藏",aid));
			UsersDAO udao = new UsersDAO();
			udao.updatePoint(userid, 5);
			cl.setAid(aid);
			cl.setUserid(userid);
			cl.setCltype("收藏");
			dao.save2(cl);
		}
		
		/**
		 * 查询收藏点赞表中的所有数据
		 */
		public static List<Collectlike> getCollectlike(){
			Connection con = null;
			PreparedStatement ps = null;
			String sql = "select * from collectlike";
			List<Collectlike> l = new ArrayList<Collectlike>();
			try {
				con = DBUtil.getCon();
				ps = con.prepareStatement(sql);
				ps.executeQuery();
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Collectlike c = new Collectlike();
					c.setAid(rs.getInt("aid"));
					c.setClid(rs.getInt("clid"));
					c.setUserid(rs.getInt("userid"));
					c.setCltime(rs.getString("cltime"));
					c.setCltype(rs.getString("cltype"));
					l.add(c);
				}
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
			return l;
		}
		
}
