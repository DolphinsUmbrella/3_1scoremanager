package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	public List<TestListSubject> postFilter(ResultSet rSet,School school) throws Exception{
		List<TestListSubject> list = new ArrayList<>();

		while(rSet.next()){
			TestListSubject ts = new TestListSubject();
			ts.setEntYear(rSet.getInt("ent_year"));
			ts.setClassNum(rSet.getString("class_num"));
			ts.setStudentNo(rSet.getString("student_no"));
			ts.setStudentName(rSet.getString("name"));

			ts.putPoint(rSet.getInt("no"), rSet.getInt("point"));

			/*
			Map<Integer, Integer> points = new HashMap<>();
			points.put(rSet.getInt("no"), rSet.getInt("point"));
			ts.setPoints(points);
			*/
		}

		return list;
	}

	public List<TestListSubject> filter
	(int entYear, String classNum, Subject subject, School school
			) throws Exception{

	Connection con = getConnection();

	//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
	PreparedStatement st = con.prepareStatement(
		"select b.ent_year, b.class_num, b.no as student_no,b.name, a.subject_cd, a.no, a.point "+
		"from test as a "+
		"join student as b "+
		"on a.student_no = b.no "+
		"where b.ent_year = ? "+
		"and b.class_num = ? "+
		"and a.subject_cd = ? "+
		"and a.school_cd = ?");
	st.setInt(1, entYear);
	st.setString(2, classNum);
	st.setString(3, subject.getCd());
	st.setString(4, school.getCd());

	ResultSet rs = st.executeQuery();

	List<TestListSubject> list = postFilter(rs, school);

	st.close();
	con.close();

	return list;
}
}

