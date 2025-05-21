package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{

	public Test get(Test t) throws Exception{
		Test test = new Test();

		Connection con = getConnection();

		//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
		PreparedStatement st = con.prepareStatement(
			"select * from test "+
			"where student_no = ? "+
			"and subject_cd = ? "+
			"and school_cd = ? "+
			"and no = ?");

		st.setString(1, t.getStudent().getNo());
		st.setString(2, t.getSubject().getCd());
		st.setString(3, t.getSchool().getCd());
		st.setInt(4, t.getNo());

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			Student stu = new Student();
			stu.setNo(rs.getString("student_no"));
			test.setStudent(stu);

			Subject sub = new Subject();
			sub.setCd(rs.getString("subject_cd"));
			test.setSubject(sub);

			School sch = new School();
			sch.setCd(rs.getString("school_cd"));
			test.setSchool(sch);

			test.setPoint(rs.getInt("no"));
			test.setPoint(rs.getInt("point"));
			test.setClassNum(rs.getString("class_num"));
		}

		st.close();
		con.close();

		return test;
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
		System.out.println("saveメソッド起動");

		Connection con = getConnection();

		//これの処理わからん
		int line = 0;

		for (Test t : test){
			boolean result = save(t, con);
			if (result){
				line++;
			}
			System.out.println("結果..."+result);
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
		System.out.print("点数："+t.getPoint());

		Test sample = this.get(t);

		//点数が-1の場合は成績削除
		if (t.getPoint() == -1){
			//成績が存在しない場合は何もする必要が無い
			if (Objects.isNull(sample.getStudent())){
				return true;
			}
			System.out.println("...削除");
			PreparedStatement st = con.prepareStatement(
				"delete from test "+
				"where student_no = ? "+
				"and subject_cd = ? "+
				"and school_cd = ? "+
				"and no = ?");

			st.setString(1, t.getStudent().getNo());
			st.setString(2, t.getSubject().getCd());
			st.setString(3, t.getSchool().getCd());
			st.setInt(4, t.getNo());

			int line = st.executeUpdate();
			st.close();

			return line>0;
		}

		System.out.print("...データ：");
		System.out.print(sample.getStudent());

		//成績が存在しない場合はinsert
		if (Objects.isNull(sample.getStudent())){
			System.out.println("...追加");

			PreparedStatement st = con.prepareStatement(
					"insert into test "+
					"values (?, ?, ?, ?, ?, ?) ");
				st.setString(1, t.getStudent().getNo());
				st.setString(2, t.getSubject().getCd());
				st.setString(3,t.getSchool().getCd());
				st.setInt(4,t.getNo());
				st.setInt(5, t.getPoint());
				st.setString(6,t.getStudent().getClassNum());

				int line = st.executeUpdate();

				st.close();

				return line>0;
		}
		//成績が存在する場合はupdate
		else{
			System.out.println("...更新");

			PreparedStatement st = con.prepareStatement(
				"update test set point = ? "+
				"where student_no = ? "+
				"and subject_cd = ? "+
				"and school_cd = ? "+
				"and no = ?");
			st.setInt(1, t.getPoint());
			st.setString(2, t.getStudent().getNo());
			st.setString(3,t.getSubject().getCd());
			st.setString(4,t.getSchool().getCd());
			st.setInt(5,t.getNo());

			int line = st.executeUpdate();

			st.close();

			return line>0;
		}
	}

	//
	public List<Test> filter(
			int entYear, String classNum, Subject subject, int num, School school
		)throws Exception{
		List<Test> list = new ArrayList<>();

		Connection con = getConnection();

		/*
			select a.ent_year, a.class_num, a.no as student_no, a.name, b.no as count, b.point
			from student as a
			left join test as b
			on a.no = b.student_no
			where (b.no = 1 or b.no is null)
			and (b.subject_cd = '101' or b.subject_cd is null)
			and a.ent_year = '2025'
			and a.class_num =  '101'
			and a.school_cd = 'tes'
			order by a.no
		 */
		PreparedStatement st = con.prepareStatement(
			"select a.ent_year, a.class_num, a.no as student_no, a.name, b.no as count, b.point "+
			"from student as a "+
			"left join "+
			"(select * from test "+
			"where no = ?) as b "+
			"on a.no = b.student_no "+
			"where (b.no = ? or b.no is null) "+
			"and (b.subject_cd = ? or b.subject_cd is null) "+
			"and a.ent_year = ? "+
			"and a.class_num = ? "+
			"and a.school_cd = ? "+
			"order by a.no");

		st.setInt(4, entYear);
		st.setString(3, subject.getCd());
		st.setString(5, classNum);
		st.setString(6, school.getCd());
		st.setInt(2, num);
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

			//科目情報
			t.setSubject(subject);

			//学校情報
			t.setSchool(school);

			//n回目
			t.setNo(num);

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
}