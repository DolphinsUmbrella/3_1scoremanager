package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{
	//4/23小柿：join使ってますがいらないと思うので、
	//	sql文の修正をお願いしたいのと、while内のschool_nameは削除してもらえると…
	//4/25小柿：↑のやつ消しました


	//クラス図のgetメソッドに関する情報はこちら
	//	get(cd:String, school:School): Subject
	//()内は引数、後ろのSubjectは返り値です
	//22行目、メソッド内処理の修正をお願いします。不明な点は質問してください！
	public Subject get(String cd, School school) throws Exception{

		//DB接続
		Connection con = getConnection();

		//SQL文をセット
		//SUBJECTテーブルから、引数のSCHOOL_CD, CDに合致する行を検索
		PreparedStatement st = con.prepareStatement(
			"select * from subject "+
			"where school_cd = ? "+
			"and cd = ?");

		//上記SQL文の?部分を格納
		//setString, setIntなどデータ型によって使い分けてください(実はsetBooleanとかもある)
		st.setString(1, school.getCd());
		st.setString(2, cd);

		//SQLを実行し、ResultSet型の変数rsに実行結果を格納
		ResultSet rs = st.executeQuery();

		//rsを1行ずつ参照します
		//検索がきちんとできていれば結果は1行だけですが、
		//なんかwhile(rs.next())で格納しないとエラーが出ます
		Subject sub = new Subject();
		while (rs.next()){
			School sc = new School();
			sc.setCd(rs.getString("school_cd"));
			sub.setSchool(school);
			sub.setCd(rs.getString("cd"));
			sub.setName(rs.getString("name"));
		}

		//このclose()は必ずしてください
		//closeを実行した後はrsも使用不可になるため、上記で別のデータに直しています
		st.close();
		con.close();

		//return時はデータ型に注意
		//仕様書の返り値の型はListではなく  Subject  です
		return sub;
	}

	public List<Subject> filter(School school) throws Exception{
		List<Subject> list = new ArrayList<Subject>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"select * from subject where school_cd = ?");
		st.setString(1, school.getCd());

		ResultSet rs = st.executeQuery();

		while (rs.next()){
			Subject sub = new Subject();
			School sc = new School();
			sc.setCd(rs.getString("school_cd"));
			sub.setSchool(school);
			sub.setCd(rs.getString("cd"));
			sub.setName(rs.getString("name"));
			list.add(sub);
		}

		st.close();
		con.close();

		return list;
	}

	//科目追加
	//4/23小柿：設計書通りにメソッド名の変更をお願いします

	//クラス図情報：
	//	save(subject:Subject): boolean
	public boolean save(Subject subject) throws Exception{

		//DB接続
		Connection con = getConnection();

		//SQL文をセット
		PreparedStatement st = con.prepareStatement(
			"insert into subject values(?, ?, ?)");
		st.setString(1, subject.getSchool().getCd());
		st.setString(2, subject.getCd());
		st.setString(3, subject.getName());

		//SQLによってDBの更新が行われる場合はexecuteUpdateを使用します(insert,update,deleteのこと)
		//executeUpdateによって得られる値はint型、更新された行数が入ります
		//今回の場合、1行分正しくinsertが実行できたなら1が、
		//エラーによりテーブル更新が行われなかった場合は0が変数lineに格納されます
		int line = st.executeUpdate();

		st.close();
		con.close();

		if (line > 0){
			return true;
		}else{
			return false;
		}
	}
		//返り値はbooleanです
		//return lineでは仕様書に反しますので、
		//返り値がbooleanになるように処理を追加してください
		//正常にinsertが完了した場合は  true  を、
		//insertに失敗した場合は  false  を返すようにしてください

		//ClassNumDaoなどで同様の処理をしています。必要であれば参考にしてください！

	//科目情報更新
	//4/25小柿：使用想定がなさそうです　これはスルーで良いかも
	public boolean update(Subject s) throws Exception{
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"update subject set name = ? "+
			"where school_cd = ? and cd = ?");
		st.setString(1, s.getName());
		st.setString(2, s.getSchool().getCd());
		st.setString(3, s.getCd());
		int line = st.executeUpdate();

		st.close();
		con.close();

		if (line > 0){
			return true;
		}else{
			return false;
		}
	}

	//科目削除
	//クラス図情報：
	//	delete(subject:Subject): boolean
	public boolean delete(Subject s) throws Exception{

		//DB接続
		Connection con = getConnection();

		//SQL文をセット
		PreparedStatement st = con.prepareStatement(
			"delete from subject "+
			"where school_cd = ? "+
			"and cd = ?");
		st.setString(1, s.getSchool().getCd());
		st.setString(2, s.getCd());

		//SQL実行、削除が実行された行数が変数lineに格納されます
		int line = st.executeUpdate();

		st.close();
		con.close();

		//返り値はbooleanです
		//saveメソッドの方での説明の通りです

		if (line > 0){
			return true;
		}else{
			return false;
		}
	}
}