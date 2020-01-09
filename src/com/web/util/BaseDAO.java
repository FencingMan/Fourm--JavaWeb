package com.web.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO<T> {

	public int save(T t) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// 反射出类的Class对象
			Class c = t.getClass();
			// 反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			// 获取表名
			String table = c.getName()
					.substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
			// 新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("insert into " + table + "(");
			for (Field f : fs) {
				sql.append(f.getName() + ",");
			}
			sql.setCharAt(sql.length() - 1, ')');
			sql.append(" values(");
			for (Field f : fs) {
				sql.append("?,");
			}
			sql.setCharAt(sql.length() - 1, ')');
			System.out.println(sql);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true);
				ps.setObject(i + 1, f.get(t));
			}
			count = ps.executeUpdate();
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
		return count;
	}

	/**
	 * 对象中含有null值插入数据库
	 * 
	 * @param t
	 * @return
	 */
	public int save2(T t) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		Class c = t.getClass();
		Field[] fs = c.getDeclaredFields();
		String table = c.getName().substring(c.getName().lastIndexOf('.') + 1)
				.toLowerCase();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into " + table + " (");
		try {
			con = DBUtil.getCon();
			for (Field f : fs) {
				f.setAccessible(true);
				if ((f.get(t)) != null) {
					sql.append(f.getName() + ",");
					count++;
				}
			}
			sql.setCharAt(sql.length() - 1, ')');
			sql.append(" values(");
			for (int i = 0; i < count; i++) {
				sql.append("?,");
			}
			sql.setCharAt(sql.length() - 1, ')');
			ps = con.prepareStatement(sql.toString());
			System.out.println(sql);
			for (int i = fs.length - 1; i >= 0; i--) {
				Field f = fs[i];
				f.setAccessible(true);
				Object o = null;
				if ((o = f.get(t)) != null) {
					ps.setObject(count--, o);
				}
			}
			count = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return count;
	}

	public int update(T t, String pkname) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// 反射出类的Class对象
			Class c = t.getClass();
			// 反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			// 获取表名
			String table = c.getName()
					.substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
			// 新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("update " + table + " set ");
			for (Field f : fs) {
				if (!f.getName().equals(pkname)) {
					sql.append(f.getName() + "=?,");
				}
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" where " + pkname + "=?");
			System.out.println(sql);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			int index = 1;
			for (Field f : fs) {
				if (!f.getName().equals(pkname)) {
					f.setAccessible(true);
					ps.setObject(index++, f.get(t));
				}
			}
			Field f = c.getDeclaredField(pkname);
			f.setAccessible(true);
			ps.setObject(index, f.get(t));
			count = ps.executeUpdate();
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
		return count;
	}
	
	/**
	 * 允许null值修改数据库
	 * @param t
	 * @param pkname
	 * @return
	 */
	
	public int update2(T t, String pkname) {
		int count = 0;
		int count2 = 1;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// 反射出类的Class对象
			Class c = t.getClass();
			// 反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			// 获取表名
			String table = c.getName()
					.substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
			// 新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("update " + table + " set ");
			for (Field f : fs) {
				f.setAccessible(true);
				if (!f.getName().equals(pkname)) {
					if(f.get(t)!=null){
						sql.append(f.getName() + "=?,");
						count++;
						count2++;
					}
				}
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" where " + pkname + "=?");
			System.out.println(sql);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			for(int i = fs.length-1;i>=0;i--){
				Field f = fs[i];
				f.setAccessible(true);
				if(f.get(t)!=null&&!f.getName().equals(pkname)){
					System.out.println(count);
					ps.setObject(count--, f.get(t));
				}
			}
			Field f = c.getDeclaredField(pkname);
			f.setAccessible(true);
			ps.setObject(count2, f.get(t));
			count = ps.executeUpdate();
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
		return count;
	}
	
	
	
	
	
	
	

	public int delete(T t, String pkname) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// 反射出类的Class对象
			Class c = t.getClass();
			// 获取表名
			String table = c.getName()
					.substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
			// 新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("delete from " + table + " where " + pkname + "=?");
			System.out.println(sql);

			// 反射出主键字段
			Field f = c.getDeclaredField(pkname);
			f.setAccessible(true);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			ps.setObject(1, f.get(t));
			count = ps.executeUpdate();
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
		return count;
	}

	public T get(T t, String pkname) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T obj = null;
		try {
			// 反射出类的Class对象
			Class c = t.getClass();
			// 反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			// 获取表名
			String table = c.getName()
					.substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
			// 新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			for (Field f : fs) {
				sql.append(f.getName() + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" from " + table + " where " + pkname + "=?");
			System.out.println(sql);

			Field field = c.getDeclaredField(pkname);
			field.setAccessible(true);
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			ps.setObject(1, field.get(t));
			rs = ps.executeQuery();
			while (rs.next()) {
				obj = (T) c.newInstance();
				for (Field f : obj.getClass().getDeclaredFields()) {
					f.setAccessible(true);
					if (f.getType().equals(String.class)) {
						f.set(obj, rs.getString(f.getName()));
					} else if (f.getType().equals(int.class)
							|| f.getType().equals(Integer.class)) {
						f.set(obj, rs.getInt(f.getName()));
					} else if (f.getType().equals(double.class)
							|| f.getType().equals(Double.class)) {
						f.set(obj, rs.getDouble(f.getName()));
					} else if (f.getType().equals(Date.class)) {
						f.set(obj, rs.getDate(f.getName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public List<T> list(Class c) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			// 反射类中所有的属性
			Field[] fs = c.getDeclaredFields();
			// 获取表名
			String table = c.getName()
					.substring(c.getName().lastIndexOf(".") + 1).toLowerCase();
			// 新增SQL语句拼接
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			for (Field f : fs) {
				sql.append(f.getName() + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" from " + table);
			System.out.println(sql);

			con = DBUtil.getCon();
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				T obj = (T) c.newInstance();
				for (Field f : obj.getClass().getDeclaredFields()) {
					f.setAccessible(true);
					if (f.getType().equals(String.class)) {
						f.set(obj, rs.getString(f.getName()));
					} else if (f.getType().equals(int.class)
							|| f.getType().equals(Integer.class)) {
						f.set(obj, rs.getInt(f.getName()));
					} else if (f.getType().equals(double.class)
							|| f.getType().equals(Double.class)) {
						f.set(obj, rs.getDouble(f.getName()));
					} else if (f.getType().equals(Date.class)) {
						f.set(obj, rs.getDate(f.getName()));
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 通用查询返回集合方法
	 * 
	 * @param sql
	 * @param rm
	 * @param args
	 * @return
	 */
	public List<T> queryForList(String sql, RowMapper<T> rm, Object... args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				T o = rm.mapRow(rs);
				list.add(o);
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
		return list;
	}

	/**
	 * 查询返回单个对象
	 * 
	 * @param sql
	 * @param rm
	 * @param args
	 * @return
	 */
	public T queryForObject(String sql, RowMapper<T> rm, Object... args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T o = null;
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				o = rm.mapRow(rs);
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
		return o;
	}

	/**
	 * 查询返回整型数据
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int queryForInt(String sql, Object... args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	/**
	 * 通用的时间修改方法
	 */
	public int updateTime(String sql) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			count = ps.executeUpdate();
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
		return count;
	}

	/**
	 * 查询返回单个字符串
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public String queryForString(String sql, Object... args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String s = null;
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				s = rs.getString(1);
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
		return s;
	}

}
