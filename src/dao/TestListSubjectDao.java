package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Test;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	public List<TestListSubject> get(String class_num) throws Exception{
		List<TestListSubject> list = new ArrayList<>();

		Connection con = getConnection();
		//テーブル情報を全取得したい場合は引数に空文字を指定してください
		PreparedStatement st = con.prepareStatement(
			"select student.entYear,student.no,student.name,student.class_num,test.point from test"
			+ "inner join student "
			+ "on student.class_num=test.class_num"
			+ "where class_num like ?");
		st.setString(1, "%"+class_num+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			TestListSubject ts = new TestListSubject();
			Test t = new Test();
			ts.setEntYear(rs.getInt("entyear"));
			ts.setStudentNo(rs.getString("studentno"));
			ts.setStudentName(rs.getString("studentname"));
			ts.setClassNum(rs.getString("classnum"));
			t.setPoint(rs.getInt("points"));
			list.add(ts);
		}
		st.close();
		con.close();

		return list;
	}
	public List<TestListSubject> postFilter(ResultSet rSet,String school) throws Exception{
		List<TestListSubject> list = new ArrayList<>();
		try{
			while(rSet.next()){
				TestListSubject ts = new TestListSubject();
				ts.setSubjectName(rSet.getString("subjectname"));
				ts.setSubjectCd(rSet.getString("subjectcd"));
				ts.setNum(rSet.getInt("num"));
				ts.setPoint(rSet.getInt("point"));
				list.add(ts);
			}
		}
		catch(SQLException | NullPointerException e){
			e.printStackTrace();
		}

		return list;
	}

	public List<TestListSubject> filter
	(String subjectName, String subjectCd, int no, int point
			) throws Exception{
	List<TestListSubject> list = new ArrayList<>();

	Connection con = getConnection();

	PreparedStatement st = con.prepareStatement(
		"select subject.name,test.subjectcd,test.no,test.point from subject,test"
		+"inner join subject "
		+"on subject.subject_cd=test.subject_cd"
		+"where subjectname = ? "
		+"and subjectcd = ? "
		+"and no = ? "
		+"and point = ?");
	st.setString(1, subjectName);
	st.setString(2, subjectCd);
	st.setInt(3, no);
	st.setInt(4, point);

	ResultSet rs = st.executeQuery();

	while (rs.next()){
		TestListSubject ts = new TestListSubject();
		ts.setSubjectName(rs.getString("subjectname"));
		ts.setSubjectCd(rs.getString("subjectcd"));
		ts.setNum(rs.getInt("num"));
		ts.setPoint(rs.getInt("point"));
		list.add(ts);
	}
	st.close();
	con.close();

	return list;
}
}

