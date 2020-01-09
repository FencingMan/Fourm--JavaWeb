package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {

	private static BasicDataSource dataSource = null;

	private static BasicDataSource dataSourse;
	static {
		// ��ʼ�����ӳ�
		dataSourse = new BasicDataSource();
		// ��������
		dataSourse.setDriverClassName(PropertiesUtil
				.getProperty(PropertiesUtil.DRIVER));
		// �������ӵ�ַ
		dataSourse.setUrl(PropertiesUtil.getProperty(PropertiesUtil.URL));
		// �����û�
		dataSourse.setUsername(PropertiesUtil
				.getProperty(PropertiesUtil.USERNAME));
		// ��������
		dataSourse.setPassword(PropertiesUtil
				.getProperty(PropertiesUtil.PASSWORD));
		// ������������
		dataSourse.setMaxIdle(50);
		// ���ӵȴ�ʱ��
		dataSourse.setMaxWait(200);
		// ������Ӹ���
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
