package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class SchoolDao extends Dao{

	//学校コードでselect検索　引数が空でselect *を実行
	public List<School> searchSchool(String schoolCd) throws Exception{
		List<School> scList = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from school where cd like ?");
		st.setString(1, "%"+schoolCd+"%");

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			School sc = new School();
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
			scList.add(sc);
		}
		st.close();
		con.close();

		return scList;
	}

	//学校追加(開校！)
	public int insertSchool(String schoolCd, String schoolName) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into values(?, ?)");
		st.setString(1, schoolCd);
		st.setString(2, schoolName);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学校情報更新
	public int updateSchool(String schoolCd, String schoolName) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update school set name = ? where cd = ?");
		st.setString(1, schoolName);
		st.setString(2, schoolCd);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学校情報削除
	public int deleteSchool(String schoolCd) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"delete from school where cd = ?");
		st.setString(1, schoolCd);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
}