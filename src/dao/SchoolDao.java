package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends Dao{

	//クラス図情報：
	//	get(cd:String): School
	public School get(String cd) throws Exception{

		School sc = new School();

		Connection con = getConnection();

		//SQL文も必要に応じて修正お願いします
		PreparedStatement st = con.prepareStatement(
			"select * from school where cd = ?");
		st.setString(1, cd);

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			sc.setCd(rs.getString("cd"));
			sc.setName(rs.getString("name"));
		}
		st.close();
		con.close();

		return sc;
	}
}