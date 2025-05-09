package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{

	public Test get(Test test) throws Exception{

		Connection con = getConnection();

		//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
		PreparedStatement st = con.prepareStatement(
			"select * from test "+
			"where student_no = ? "+
			"and subject_cd = ? "+
			"and school_cd = ? "+
			"and no = ?");
		st.setString(1, test.getStudent().getNo());
		st.setString(2, test.getSubject().getCd());
		st.setString(3, test.getSchool().getCd());
		st.setInt(4, test.getNo());
		ResultSet rs = st.executeQuery();

		//楽したい！
		List<Test> tList = postFilter(rs, test.getSchool());

		st.close();
		con.close();

		//1行しかとってこないはずなので
		return tList.get(0);
	}

	private List<Test> postFilter(ResultSet rs, School school) throws Exception{
		List<Test> list = new ArrayList<>();

		while (rs.next()){
			Test t = new Test();

			//こんなんしないといかんの？？
			Student stu = new Student();
			stu.setNo(rs.getString("student_no"));
			t.setStudent(stu);

			Subject sub = new Subject();
			sub.setCd(rs.getString("subject_cd"));
			t.setSubject(sub);

			School sch = new School();
			sch.setCd(rs.getString("school_cd"));
			t.setSchool(sch);

			t.setNo(rs.getInt("no"));
			t.setPoint(rs.getInt("point"));
			t.setClassNum(rs.getString("class_num"));
			list.add(t);
		}

		return list;
	}

	//試験結果追加、一度に複数回insertするため配列で受け取ります
	public boolean save(List<Test> test) throws Exception{
		Connection con = getConnection();

		//これの処理わからん
		int line = 0;

		for (Test t : test){
			boolean result = save(t, con);
			if (result){
				line++;
			}
		}
		con.close();

		//仕様書が何をしてほしいのか何もわからん
		//全部正常にinsertできたらtrue
		//1行でも異常があればfalse、たぶん正解はこれではない
		if (test.size() == line){
			return true;
		}else{
			return false;
		}
	}

	//おそらく1行ずつinsertするためのもの
	private boolean save(Test t, Connection con) throws Exception{
		PreparedStatement st = con.prepareStatement(
			"insert into test values(?, ?, ?, ?, ?, ?)");
		st.setString(1, t.getStudent().getNo());
		st.setString(2, t.getSubject().getCd());
		st.setString(3, t.getSchool().getCd());
		st.setInt(4, t.getNo());
		st.setInt(5, t.getPoint());
		st.setString(6, t.getClassNum());

		int line = st.executeUpdate();

		st.close();

		if (line > 0){
			return true;
		}else{
			return false;
		}
	}

	//
	public List<Test> filter(
			int entYear, String classNum, Subject subject, int num, School school
		)throws Exception{
		List<Test> list = new ArrayList<>();

		Connection con = getConnection();

		/*
			select a.ent_year, a.class_num, a.no as student_no, a.name, b.point
			from student as a
			join test as b
			on a.no = b.student_no
			where a.ent_year = 2024
			and b.subject_cd = '101'
			and b.school_cd = 'tes'
			and b.no = 1
		 */
		PreparedStatement st = con.prepareStatement(
			"select a.ent_year, a.class_num, a.no as student_no, a.name, b.point "+
			"from student as a "+
			"join test as b "+
			"on a.no = b.student_no "+
			"where a.ent_year = ? "+
			"and b.subject_cd = ? "+
			"and b.class_num = ? "+
			"and b.school_cd = ? "+
			"and b.no = ?");
		st.setInt(1, entYear);
		st.setString(2, subject.getCd());
		st.setString(3, classNum);
		st.setString(4, school.getCd());
		st.setInt(5, num);

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			Test t = new Test();
			//学生情報
			Student s = new Student();
			s.setClassNum(rs.getString("class_num"));
			s.setEntYear(rs.getInt("ent_year"));
			s.setName(rs.getString("name"));
			s.setNo(rs.getString("student_no"));
			//beanを改変する必要があります、影響範囲が広くてやばい
			t.setStudent(s);

			t.setSchool(school);
			t.setPoint(rs.getInt("point"));

			list.add(t);
		}
		st.close();
		con.close();

		return list;
	}

	// 成績更新 (StudentUpdateExecuteAction.javaで利用)
		public int updateTest(Test t) throws Exception {
		    Connection con = null;
		    PreparedStatement st = null;
		    int line = 0;

		    try {
		        con = getConnection();

		        // 正しいSQLクエリ：testテーブルの更新
		        String sql = "UPDATE test SET subject_cd = ?, point = ?,no =? " +
		                     "WHERE student_no = ?";
		        st = con.prepareStatement(sql);

		        // パラメータ設定
		        st.setString(1, t.getSubject().getCd());      // 科目コード
		        st.setInt(2, t.getPoint());                    // 点数
		        st.setInt(3, t.getNo());                       // test回数
		        st.setString(4, t.getStudent().getNo());        // 学生番号
		        line = st.executeUpdate();
		    } catch (Exception e) {
		        throw new Exception("成績更新中にエラーが発生しました", e);
		    } finally {
		        if (st != null) st.close();
		        if (con != null) con.close();
		    }

		    return line;
		}
}