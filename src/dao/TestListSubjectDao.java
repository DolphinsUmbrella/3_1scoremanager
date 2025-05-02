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
			//もし回数以外の主キーが一致している場合、同一人物の同一科目
			//そのため同じインスタンスに格納してやる必要があります
			String studentNo = rSet.getString("student_no");

			String endStudentNo;
			if (list.size() == 0){
				endStudentNo = "none";
			}
			//現時点のlistから末尾に格納されている学生番号を取得
			else{
				endStudentNo = list.get(list.size()-1).getStudentNo();
				//学校コード、科目コードはすべて同一である前提のため無視
			}

			//学生番号が一致している場合
			//list末尾のインスタンスにputPointを実行
			if (studentNo.equals(endStudentNo)){
				list.get(list.size()-1).putPoint(rSet.getInt("no"), rSet.getInt("point"));
			}
			//一致しない場合
			//新しいインスタンスを作成
			else{
				TestListSubject ts = new TestListSubject();
				ts.setEntYear(rSet.getInt("ent_year"));
				ts.setClassNum(rSet.getString("class_num"));
				ts.setStudentNo(rSet.getString("student_no"));
				ts.setStudentName(rSet.getString("name"));

				ts.putPoint(rSet.getInt("no"), rSet.getInt("point"));

				list.add(ts);
			}

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
		//order by句をつけることで回数以外の主キーを揃えます
		"select b.ent_year, b.class_num, b.no as student_no,b.name, a.subject_cd, a.no, a.point "+
		"from test as a "+
		"join student as b "+
		"on a.student_no = b.no "+
		"where b.ent_year = ? "+
		"and b.class_num = ? "+
		"and a.subject_cd = ? "+
		"and a.school_cd = ? "+
		"order by a.student_no, a.subject_cd, a.school_cd, a.no");
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

