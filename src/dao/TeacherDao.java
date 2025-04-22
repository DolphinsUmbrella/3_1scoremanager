package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Teacher;

public class TeacherDao extends Dao{

	public Teacher get(String id) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from teacher where id = ?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();

		Teacher t = new Teacher();
		while (rs.next()){
			t.setId(rs.getString("id"));
			t.setPassword(rs.getString("password"));
			t.setName(rs.getString("name"));
			t.setSchoolCd(rs.getString("school_cd"));
		}
		st.close();
		con.close();

		return t;
	}

	public Teacher login(String id, String password) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from teacher where id = ?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();

		Teacher t = new Teacher();
		while (rs.next()){
			t.setId(rs.getString("id"));
			t.setPassword(rs.getString("password"));
			t.setName(rs.getString("name"));
			t.setSchoolCd(rs.getString("school_cd"));
		}
		st.close();
		con.close();

		return t;
	}

	public int insertTeacher(Teacher t) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into teacher values (?, ?, ?, ?)");
		st.setString(1, t.getId());
		st.setString(2, t.getPassword());
		st.setString(3, t.getName());
		st.setString(4, t.getSchool());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//教員情報の更新
	public int updateTeacher(Teacher t) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update teacher set password = ?, name = ?, school_cd = ?"+
			"where id = ?");
		st.setString(1, t.getPassword());
		st.setString(2, t.getName());
		st.setString(3, t.getSchool());
		st.setString(4, t.getId());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	public int deleteTeacher(String id) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"delete from teacher where id = ?");
		st.setString(1, id);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
}