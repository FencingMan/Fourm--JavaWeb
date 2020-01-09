package com.web.dao;

import com.web.bean.Message;
import com.web.util.BaseDAO;

public class MessageDAO extends BaseDAO<Message>{
	public int getCountNum(Integer userid){
		return this.queryForInt("select count(*) from collectlike where userid="+userid);
	}
}
