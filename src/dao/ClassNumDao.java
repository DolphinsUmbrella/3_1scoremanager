package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao{

	//クラス取得
	public ClassNum get(String class_num, School school) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from class_num "+
			"where school_cd = ? "+
			"and class_num = ?");
		st.setString(1, school.getCd());
		st.setString(2, class_num);

		ResultSet rs = st.executeQuery();

		ClassNum c = new ClassNum();
		while (rs.next()){
			c.setClass_num(rs.getString("class_num"));
			School sc = new School();
			sc.setCd(rs.getString("school_cd"));
			c.setSchool(school);
		}
		st.close();
		con.close();

		return c;
	}

	//クラス絞込み
	public List<String> filter(School school) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from class_num "+
			"where school_cd = ?");
		st.setString(1, school.getCd());
		ResultSet rs = st.executeQuery();

		List<String> list = new ArrayList<>();
		while (rs.next()){
			list.add(rs.getString("class_num"));
		}
		st.close();
		con.close();

		return list;
	}

	//クラス追加
	//4/24小柿：おそらく正しいメソッド名はsave、返り値はboolean
	public boolean save(ClassNum classNum) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"insert into class_num values(?, ?)");
		st.setString(1, classNum.getSchool().getCd());
		st.setString(2, classNum.getClass_num());
		int line = st.executeUpdate();

		st.close();
		con.close();

		//更新行が1ならtrue,更新がなければfalse
		if (line > 0){
			return true;
		}else{
			return false;
		}
	}

	//クラス情報更新
	//4/24小柿：おそらく正しいメソッド名はsave、返り値はboolean
	public boolean save(ClassNum classNum, String newClassNum) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update class_num set classNum = ? where school_cd = ? and class_num = ?");
		st.setString(1, newClassNum);
		st.setString(2, classNum.getSchool().getCd());
		st.setString(3, classNum.getClass_num());
		int line = st.executeUpdate();

		st.close();
		con.close();

		//更新行が1ならtrue,更新がなければfalse
		if (line > 0){
			return true;
		}else{
			return false;
		}
	}


}