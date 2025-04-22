package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;

public class ClassNumDao extends Dao{

	public List<ClassNum> get(String classNum, String schoolCd) throws Exception{
		List<ClassNum> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from class_num "+
			"where school_cd = ? "+
			"and class_num like ?");
		st.setString(1, schoolCd);
		st.setString(2, "%"+classNum+"%");

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			ClassNum c = new ClassNum();
			c.setClassNum(rs.getString("class_num"));
			c.setSchoolCd(rs.getString("school_cd"));
			list.add(c);
		}
		st.close();
		con.close();

		return list;
	}

	//クラス追加
	public int insertClass(String schoolCd, String classNum) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into class_num values(?, ?)");
		st.setString(1, schoolCd);
		st.setString(2, classNum);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//クラス情報更新
	public int updateClass(String schoolCd, String classNum, String newClassNum) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update class_num set classNum = ? where school_cd = ? and class_num = ?");
		st.setString(2, newClassNum);
		st.setString(2, schoolCd);
		st.setString(3, classNum);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//クラス情報削除
	public int deleteClass(String schoolCd, String classNum) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"delete from class_num where school_cd = ?, class_num = ?");
		st.setString(1, schoolCd);
		st.setString(2, classNum);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
}