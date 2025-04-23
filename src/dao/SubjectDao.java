package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao{
	//4/23小柿：join使ってますがいらないと思うので、
	//	sql文の修正をお願いしたいのと、while内のschool_nameは削除してもらえると…

	//科目コードでselect検索　引数が空でselect *を実行
	public List<Subject> get(String subjectCd, String schoolCd) throws Exception{
		List<Subject> subList = new ArrayList<>();

		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(
			"select * from subject where school_cd = ? and cd like ?");
		st.setString(1, schoolCd);
		st.setString(2, "%"+subjectCd+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			Subject sub = new Subject();
			sub.setSchoolCd(rs.getString("school_cd"));
			sub.setCd(rs.getString("cd"));
			sub.setName(rs.getString("name"));
			subList.add(sub);
		}
		st.close();
		con.close();

		return subList;
	}

	//科目追加
	//4/23小柿：設計書通りにメソッド名の変更をお願いします
	public int insertSubject(String subjectCd, String subjectName, String schoolCd) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into subject values(?, ?, ?)");
		st.setString(1, schoolCd);
		st.setString(2, subjectCd);
		st.setString(3, subjectName);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//科目情報更新
	public int updateSubject(String schoolCd, String subjectCd, String subjectName) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update subject set name = ?"+
			"where school_cd = ? and cd = ?");
		st.setString(1, subjectName);
		st.setString(2, schoolCd);
		st.setString(3, subjectCd);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//科目削除
	public int deleteSubject(String schoolCd, String subjectCd) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"delete from subject"+
			"where school_cd = ? and cd = ?");
		st.setString(1, schoolCd);
		st.setString(2, subjectCd);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
}