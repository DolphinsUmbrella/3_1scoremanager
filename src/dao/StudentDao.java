package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao{

	public List<Student> get(String no) throws Exception{
		List<Student> sList = new ArrayList<>();

		Connection con = getConnection();

		//NO	NAME  	ENT_YEAR  	CLASS_NUM  	IS_ATTEND  	SCHOOL_CD
		//テーブル情報を全取得したい場合は引数に空文字を指定してください
		PreparedStatement st = con.prepareStatement(
			"select * from student where no like ?");
		st.setString(1, "%"+no+"%");
		ResultSet rs = st.executeQuery();

		while (rs.next()){
			Student s = new Student();
			s.setNo(rs.getString("no"));
			s.setName(rs.getString("name"));
			s.setEntYear(rs.getInt("ent_year"));
			s.setClassNum(rs.getString("class_num"));
			s.setIsAttend(rs.getBoolean("is_attend"));
			s.setSchoolCd(rs.getString("school_cd"));
			sList.add(s);
		}
		st.close();
		con.close();

		return sList;
	}

	//filterは渡される引数によって適切なメソッドを実行します

	//学校のみ指定、初回遷移時のみ使用
	public List<Student> filter(String school) throws Exception{
		List<Student> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ?");
		st.setString(1, school);

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

	//すべて指定あり
	public List<Student> filter
		(String school, int entYear, String classNum, boolean isAttend
				) throws Exception{
		List<Student> list = new ArrayList<>();

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

	//学校コード、入学年度を指定。在学フラグは必ず入ります
	public List<Student> filter
		(String school, int entYear, boolean isAttend
				) throws Exception{
		List<Student> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and ent_year = ? "+
			"and is_attend = ?");
		st.setString(1, school);
		st.setInt(2, entYear);
		st.setBoolean(3, isAttend);

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

	//学校コード、クラスを指定。在学フラグは必ず入ります
	public List<Student> filter
		(String school, String classNum, boolean isAttend
				) throws Exception{
		List<Student> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and class_num = ? "+
			"and is_attend = ?");
		st.setString(1, school);
		st.setString(2, classNum);
		st.setBoolean(3, isAttend);

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

	//初回遷移時ではなく、在学フラグ以外の指定がない場合
	public List<Student> filter
		(String school, boolean isAttend
				) throws Exception{
		List<Student> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from student "+
			"where school_cd = ? "+
			"and is_attend = ?");
		st.setString(1, school);
		st.setBoolean(2, isAttend);

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

	//学生追加
	//4/23小柿：メソッド名は設計書通りのものに変更してください
	public int insertStudent(Student s) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into student values (?, ?, ?, ?, ?, ?)");
		st.setString(1, s.getNo());
		st.setString(2, s.getName());
		st.setInt(3, s.getEntYear());
		st.setString(4, s.getClassNum());
		st.setBoolean(5, s.getIsAttend());
		st.setString(6, s.getSchoolCd());

		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学生更新
	//4/23小柿：メソッド名は設計書通りのものに変更してください
	public int updateStudent(Student s) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update student"+
			"set name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_cd = ?"+
			"where no = ?");
		st.setString(1, s.getName());
		st.setInt(2, s.getEntYear());
		st.setString(3, s.getClassNum());
		st.setBoolean(4, s.getIsAttend());
		st.setString(5, s.getSchoolCd());
		st.setString(6, s.getNo());
		int line = st.executeUpdate();

		st.close();
		con.close();

		return line;
	}

	//学生削除
	//4/23小柿：使用することがなさそう
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