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

	public Test get(Student student, School school, Subject subject,int no) throws Exception{

		Connection con = getConnection();

		//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
		PreparedStatement st = con.prepareStatement(
			"select s.ent_year,t.class_num,t.subject_cd,t.no from test as t "+
			"inner join student as s "+
			"on s.no=t.student_no "+
			"where ent_year = ? "+
			"and t.class_num = ? "+
			"and t.subject_cd = ? "+
			"and t.school_cd = ? "+
			"and t.no = 1");

		st.setInt   (1, student.getEntYear());
		st.setString(2, student.getClassNum());
		st.setString(3, subject.getCd());
		st.setString(4, school.getCd());
		st.setInt   (5, no);
		ResultSet rs = st.executeQuery();

		List<Test> tList = postFilter(rs, school);

		st.close();
		con.close();

		//1行しかとってこないはずなので
		return tList.get(0);
	}

	private List<Test> postFilter(ResultSet rs, School school) throws Exception{
		List<Test> list = new ArrayList<>();

		while (rs.next()){
			Test t = new Test();
			Student stu = new Student();
			Subject sub = new Subject();
			School sch = new School();

			stu.setEntYear(rs.getInt("ent_year"));
			t.setClassNum(rs.getString("class_num"));
			sub.setCd(rs.getString("subject_cd"));
			t.setSubject(sub);
			t.setNo(rs.getInt("num"));
			sch.setCd(rs.getString("school_cd"));
			t.setSchool(sch);
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
			"select a.ent_year, a.class_num, a.no as student_no, a.name, b.no as count, b.point "+
			"from student as a "+
			"left join test as b "+
			"on a.no = b.student_no "+
			"where (b.no = ? or b.no is null) "+
			"and (b.subject_cd = ? or b.subject_cd is null) "+
			"and a.ent_year = ? "+
			"and a.class_num = ? "+
			"and a.school_cd = ? "+
			"order by a.no");

		st.setInt(3, entYear);
		st.setString(2, subject.getCd());
		st.setString(4, classNum);
		st.setString(5, school.getCd());
		st.setInt(1, num);

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

			//nullの場合0が入るらしい  余計なことするな
			int point = rs.getInt("point");

			if (point == 0){
				//入ってきた値が0の場合追加で検索、testテーブルに存在すれば0、無ければ-1を格納
				st = con.prepareStatement(
					"select * from test "+
					"where student_no = ? "+
					"and subject_cd = ? "+
					"and school_cd = ? "+
						"and no = ?");
				st.setString(1, s.getNo());
				st.setString(2, subject.getCd());
				st.setString(3, school.getCd());
				st.setInt(4, num);

				ResultSet rsPoint = st.executeQuery();

				System.out.println("検索結果："+rsPoint.getRow());

				//検索結果が存在しない場合は-1を格納
				if (rsPoint.getRow() == 0){
					t.setPoint(-1);
				}else{
					t.setPoint(point);
				}

			}else{
				t.setPoint(point);
			}

			System.out.println("点数："+t.getPoint());

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