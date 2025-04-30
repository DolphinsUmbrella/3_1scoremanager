package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

	public List<TestListStudent> get(String student_no) throws Exception{
		List<TestListStudent> TestsList = new ArrayList<>();

		Connection con = getConnection();
		//テーブル情報を全取得したい場合は引数に空文字を指定してください
		PreparedStatement st = con.prepareStatement(
			"select subject.name,test.subjectcd,test.no,test.point from test"
			+ "inner join subject "
			+ "on subject.cd=test.subject_cd"
			+ "where student_no like ?");
		st.setString(1, "%"+student_no+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			TestListStudent ts = new TestListStudent();
			ts.setSubjectName(rs.getString("subjectname"));
			ts.setSubjectCd(rs.getString("subjectcd"));
			ts.setNum(rs.getInt("num"));
			ts.setPoint(rs.getInt("point"));
			TestsList.add(ts);
		}
		st.close();
		con.close();

		return TestsList;
	}

	public List<TestListStudent> postFilter(ResultSet rSet,Student student) throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		try{
			while(rSet.next()){
				TestListStudent ts = new TestListStudent();
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

	public List<TestListStudent> filter
	(Student student) throws Exception{

	Connection con = getConnection();

	PreparedStatement st = con.prepareStatement(
		"select * from student "+
		"where no = ? ");
	st.setString(1, student.getNo());
	ResultSet rs = st.executeQuery();

	List<TestListStudent> list = postFilter(rs, student);
	st.close();
	con.close();

	return list;
}
}

