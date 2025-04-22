package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao{

	public List<Test> searchTest(Test test) throws Exception{
		List<Test> testList = new ArrayList<>();

		Connection con = getConnection();

		//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
		PreparedStatement st = con.prepareStatement(
			"select a.student_no, b.name as student_name, a.subject_cd, c.name as subject_name,"+
			"a.school_cd, d.name as school_name, a.no, a.point, a.class_num"+
			"from test as a"+
			"join student as b on a.student_no = b.no"+
			"join subject as c on a.subject_cd = c.cd"+
			"join school as d on a.school_cd = d.cd"+
			"where a.student_no like ?"+
			"and a.subject_cd like ?"+
			"and a.school_cd like ?"+
			"and a.no like ?");
		st.setString(1, test.getStudentNo());
		st.setString(2, test.getSubjectCd());
		st.setString(3, test.getSchoolCd());
		st.setInt(4, test.getNo());
		ResultSet rs = st.executeQuery();
		
		while (rs.next()){
			Test t = new Test();
			t.setStudentNo(rs.getString("student_no"));
			t.setStudetntName(rs.getString("student_name"));
			t.setSubjectCd(rs.getString("subject_cd"));
			t.setSubjectName(rs.getString("subject_name"));
			t.setSchoolCd(rs.getString("school_cd"));
			t.setSchoolName(rs.getString("school_name"));
			t.setNo(rs.getInt("no"));
			t.setPoint(rs.getInt("point"));
			t.setClassNum(rs.getString("class_num"));
			testList.add(t);
		}
		st.close();
		con.close();
		
		return testList;
	}
	
	//試験結果追加、一度に複数insertも考えられるため配列で受け取れるように
	public int insertTest(List<Test> test) throws Exception{
		Connection con = getConnection();
		
		PreparedStatement st = con.prepareStatement("select * from test");
		int line = 0;
		for (Test t : test){
			st = con.prepareStatement(
				"insert into test values(?, ?, ?, ?, ?, ?)");
			st.setString(1, t.getStudentNo());
			st.setString(2, t.getSubjectCd());
			st.setString(3, t.getSchoolCd());
			st.setInt(4, t.getNo());
			st.setInt(5, t.getPoint());
			st.setString(6, t.getClassNum());
			line += st.executeUpdate();
		}
		st.close();
		con.close();
		
		return line;
	}
	
	public int updateTest(Test test) throws Exception{
		
	}
}