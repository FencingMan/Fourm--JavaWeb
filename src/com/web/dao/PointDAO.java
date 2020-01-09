package com.web.dao;

import java.sql.ResultSet;

import com.web.bean.Point;
import com.web.util.BaseDAO;
import com.web.util.RowMapper;

public class PointDAO extends BaseDAO<Point>{
	public Point query(int aid,int userid){
		return this.queryForObject("select * from point where aid=? and userid=? and ptype='查看帖子'", new RowMapper<Point>() {
			
			@Override
			public Point mapRow(ResultSet rs) throws Exception {
				Point p = new Point();
				return p;
			}
		}, aid, userid);
	}
}
