package com.web.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.web.bean.Message;
import com.web.dao.MessageDAO;
import com.web.util.DBUtil;

public class MessageServer {
	
	/**
	 * 产生某用户对某帖子收藏点赞评论消息
	 * @aid 
	 * @userid
	 * 消息ID dao.queryForInt("select message_seq.nextval from dual")
	 */
	public static void addMSG(Integer aid,Integer userid,Integer state,Integer mtype) {
		MessageDAO dao = new MessageDAO();
		Message msg =  new Message();
		msg.setMid(dao.queryForInt("select message_seq.nextval from dual"));
		msg.setAid(aid);
		msg.setUserId(userid);
		msg.setState(state);
		dao.save2(msg);
	}
	
	/**
	 * 删除某用户对某帖子收藏点赞评论的消息
	 * @aid 
	 * @userid
	 */
	public static void delMSG(Integer aid,Integer userid,Integer mtype) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "delete from message where aid=? and userid=? and mtype=?";
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, aid);
			ps.setInt(2, userid);
			ps.setInt(3, mtype);
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
	 * 读取某用户对某帖子收藏点赞评论的消息后
	 * @aid 
	 * @userid
	 */
	public static void upMSG(Integer aid,Integer userid) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update message set state=1 where aid=? and userid=?";
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
}
