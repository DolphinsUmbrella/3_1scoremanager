package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao{

	//クラス図情報：
	//	get(cd:String): Student
	//引数noじゃないんだ　たぶんnoで良いと思います
	public Student get(String no) throws Exception{
		Student s = new Student();

		Connection con = getConnection();

		//NO	NAME  	ENT_YEAR  	CLASS_NUM  	IS_ATTEND  	SCHOOL_CD
		PreparedStatement st = con.prepareStatement(
			"select * from student where no = ? order by no");
		st.setString(1, no);
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			s.setNo(rs.getString("no"));
			s.setName(rs.getString("name"));
			s.setEntYear(rs.getInt("ent_year"));
			s.setClassNum(rs.getString("class_num"));
			s.setIsAttend(rs.getBoolean("is_attend"));

			School school = new School();
			school.setCd(rs.getString("school_cd"));
			s.setSchool(school);
		}
		st.close();
		con.close();

		return s;
	}

	//フィルター後のリストへの格納処理のメソッド
	public List<Student> postFilter(ResultSet rSet,School school) throws Exception{
		List<Student> list = new ArrayList<>();
		try{
			while(rSet.next()){
				Student s = new Student();
				s.setNo(rSet.getString("no"));
				s.setName(rSet.getString("name"));
				s.setEntYear(rSet.getInt("ent_year"));
				s.setClassNum(rSet.getString("class_num"));
				s.setIsAttend(rSet.getBoolean("is_attend"));
				s.setSchool(school);
				list.add(s);
			}
		}
		catch(SQLException | NullPointerException e){
			e.printStackTrace();
		}

		return list;
	}
	//filterは渡される引数によって適切なメソッドを実行します

	//学校のみ指定、初回遷移時のみ使用
	public List<Student> filter(School school) throws Exception{

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? order by no");
		st.setString(1, school.getCd());
		ResultSet rs = st.executeQuery();

		List<Student> list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}

	//すべて指定あり
	public List<Student> filter
		(School school, int entYear, String classNum, boolean isAttend
				) throws Exception{

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and ent_year = ? "+
			"and class_num = ? "+
			"and is_attend = ? order by no");
		st.setString(1, school.getCd());
		st.setInt(2, entYear);
		st.setString(3, classNum);
		st.setBoolean(4, isAttend);

		ResultSet rs = st.executeQuery();

		List<Student> list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}

	//学校コード、入学年度を指定。在学フラグは必ず入ります
	public List<Student> filter
		(School school, int entYear, boolean isAttend
				) throws Exception{

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and ent_year = ? "+
			"and is_attend = ? order by no");
		st.setString(1, school.getCd());
		st.setInt(2, entYear);
		st.setBoolean(3, isAttend);

		ResultSet rs = st.executeQuery();

		List<Student> list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}

	//学校コード、クラスを指定。在学フラグは必ず入ります
	public List<Student> filter
		(School school, String classNum, boolean isAttend
				) throws Exception{

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and class_num = ? "+
			"and is_attend = ? order by no");
		st.setString(1, school.getCd());
		st.setString(2, classNum);
		st.setBoolean(3, isAttend);

		ResultSet rs = st.executeQuery();

		List<Student> list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}

	//初回遷移時ではなく、在学フラグ以外の指定がない場合
	public List<Student> filter
		(School school, boolean isAttend
				) throws Exception{

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and is_attend = ? order by no");
		st.setString(1, school.getCd());
		st.setBoolean(2, isAttend);

		ResultSet rs = st.executeQuery();

		List<Student> list = postFilter(rs, school);

		st.close();
		con.close();

		return list;
	}

	//学生追加
	//4/23小柿：メソッド名は設計書通りのものに変更してください
	public boolean save(Student s) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into student values (?, ?, ?, ?, ?, ?)");
		st.setString(1, s.getNo());
		st.setString(2, s.getName());
		st.setInt(3, s.getEntYear());
		st.setString(4, s.getClassNum());
		st.setBoolean(5, s.getIsAttend());
		st.setString(6, s.getSchool().getCd()); // Schoolオブジェクトから学校コードを取得
		int line = st.executeUpdate();

		st.close();
		con.close();

		if (line > 0){
			return true;
		}else{
			return false;
		}
	}

	//年度のみを重複なしで指定する。
	public List<Integer> selectInt_Year() throws Exception{

		List<Integer> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select distinct ent_year from student order by ent_year desc");
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			list.add(rs.getInt("ent_year"));
		}

		st.close();
		con.close();

		return list;
	}

	//クラスのみを重複なしで指定する。
	public List<String> selectClass_Num() throws Exception{

		List<String> list = new ArrayList<>();
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select distinct class_num from student");
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			list.add(rs.getString("class_num"));
		}

		st.close();
		con.close();

		return list;
	}


	//学生更新 (StudentUpdateExecuteAction.javaで利用)
	public int updateStudent(Student s) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update student "+
			"set name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_cd = ?"+
			"where no = ?");
		st.setString(1, s.getName());
		st.setInt(2, s.getEntYear());
		st.setString(3, s.getClassNum());
		st.setBoolean(4, s.getIsAttend());
		st.setString(5, s.getSchool().getCd());
		st.setString(6, s.getNo());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学生削除
	//使用想定なし
	public int deleteStudent(String no) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"delete from student where no = ?");
		st.setString(1, no);
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}
}