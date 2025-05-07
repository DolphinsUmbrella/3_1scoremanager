package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao{

	//クラス図情報：
	//	get(cd:String): School
	public School searchSchool(String cd) throws Exception{

		School sc = new School();

		Connection con = getConnection();

		//SQL文も必要に応じて修正お願いします
		PreparedStatement st = con.prepareStatement(
			"select * from school where cd like ?");
		st.setString(1, "%"+cd+"%");

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
		}
		st.close();
		con.close();

		return sc;
	}

	//学校追加(開校！)
	//悲しいかな  使用想定なし
	public int insertSchool(String cd, String name) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into values(?, ?)");
		st.setString(1, cd);
		st.setString(2, name);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学校情報更新
	//使用想定なし
	public int updateSchool(String cd, String name) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update school set name = ? where cd = ?");
		st.setString(1, name);
		st.setString(2, cd);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学校情報削除
	//使用想定なし
	public int deleteSchool(String cd) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"delete from school where cd = ?");
		st.setString(1, cd);

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

}