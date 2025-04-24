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
			"select subject.name,test.subjectcd,test.no,test.point from subject,test"
			+ "inner join subject "
			+ "on subject.subject_cd=test.subject_cd"
			+ "inner join student "
			+ "on test.student_no=student.no where student_no like ?");
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

	public List<TestListStudent> postFilter(ResultSet rSet,String school) throws Exception{
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
	(String subjectName, String subjectCd, int num, int point
			) throws Exception{
	List<TestListStudent> list = new ArrayList<>();

	Connection con = getConnection();

	PreparedStatement st = con.prepareStatement(
		"select * from student "+
		"where school_cd = ? "+
		"and ent_year = ? "+
		"and class_num = ? "+
		"and is_attend = ?");
	st.setString(1, school);
	st.setInt(2, entYear);
	st.setString(3, classNum);
	st.setBoolean(4, isAttend);

	ResultSet rs = st.executeQuery();

	while (rs.next()){
		Student s = new Student();
		s.setNo(rs.getString("no"));
		s.setName(rs.getString("name"));
		s.setEntYear(rs.getInt("ent_year"));
		s.setClassNum(rs.getString("class_num"));
		s.setIsAttend(rs.getBoolean("is_attend"));
		s.setSchoolCd(rs.getString("school_cd"));
		list.add(s);
	}
	st.close();
	con.close();

	return list;
}
}

