package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {

	private static BasicDataSource dataSource = null;

	private static BasicDataSource dataSourse;
	static {
		// 初始化连接池
		dataSourse = new BasicDataSource();
		// 设置驱动
		dataSourse.setDriverClassName(PropertiesUtil
				.getProperty(PropertiesUtil.DRIVER));
		// 设置连接地址
		dataSourse.setUrl(PropertiesUtil.getProperty(PropertiesUtil.URL));
		// 设置用户
		dataSourse.setUsername(PropertiesUtil
				.getProperty(PropertiesUtil.USERNAME));
		// 设置密码
		dataSourse.setPassword(PropertiesUtil
				.getProperty(PropertiesUtil.PASSWORD));
		// 最大空闲连接数
		dataSourse.setMaxIdle(50);
		// 连接等待时间
		dataSourse.setMaxWait(200);
		// 最大连接个数
		dataSourse.setMaxActive(200);
	}

	public static Connection getCon() {
		Connection con = null;
		try {
			con = dataSourse.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
