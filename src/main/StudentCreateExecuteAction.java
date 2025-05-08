package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

			//学生登録完了画面の処理

			StudentDao sDao = new StudentDao();

			//4/30小柿：まずは実装作業ありがとうございます。お疲れ様です
			//	エラーが出そうな箇所にコメントに残しますので参考にしてください。


			//小柿：↓これ、まずStudentDaoにinsertというメソッドはありません。
			//	全体的に教科書とメソッドの名前の付け方が違うためわかりづらいとは思います。

			//	クラス図を見てもらえればわかると思いますが、
			//	insert(学生登録)がしたいときに使うメソッドは「seve」です。
			//	クラス図情報：学生一覧→dao.StudentDao
			//		   save(student: Student) boolean
			//	参考：メソッド名(引数名: 引数のデータ型) 返り値のデータ型

			//	ここからはしてほしいことを記述します
			//	①insertをsaveに変えてください
			//	②boolean(trueかfalse)が返り値に入ってきます。それ専用の変数を用意してください
			//	③そもそも引数のstudentの中身が空っぽです。値の格納をした後で実行してください

			//	変数の中身がこの時点でどうなっているかを把握できるようになると良いかなと思います
			sDao.insert(student);//学生登録

			//値の格納

			//入学年度
			String entYearStr = request.getParameter("entYear");

			//学生番号
			String no = request.getParameter("no");

			//氏名
			String name = request.getParameter("name");

			//クラス
			String classNum = request.getParameter("classNum");

			//登録処理
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setEntYear(Integer.parseInt(entYearStr));
			student.setClassNum(classNum);
			student.setSchool(user.getSchool());


			//小柿：ここでDaoから学生登録を実行してください。


			//小柿：この行は無くて良いです
			List<Student> sList = new ArrayList<>();

			//小柿：これはそんなに重要なことではないので覚えなくても大丈夫ですが、
			//	値がnullかを確認したいなら、
			//	Object.isNull(変数名)のように書くと良いです。
			//	== nullは予期しない処理を誘発する可能性があるため、できる限り
			//	isNullを使ってください。

			//	入力情報のチェックはJSPの方がするのでこの処理は無くても大丈夫です。

			//入力情報をチェック
			if (no == null || no.isEmpty()|| name== null || name.isEmpty()
					|| entYearStr == null|| entYearStr.isEmpty()
					|| classNum == null || classNum.isEmpty()){

				//エラー画面
				request.setAttribute("error","このフィールドを入力してください。");
			}


			//小柿：ここからreturnまでの間の処理もぜんぶ無くても良いです。(returnは残してください)

			//	なぜかというと、この処理はStudentCreateActionがしてくれるからです。
			//	このStudentCreateExecuteActionから学生登録画面(student-create.jsp)に行くまでに、
			//	必ずStudentCreateActionを経由します。
			//	そのため、わざわざここでこの処理をしてあげなくても
			//	StudentCreateActionが処理をしてくれるわけです。
			//	ページ遷移、データのやり取りの様子はシーケンス図で確認できます。

			//	このサーブレットがどんな処理をする必要があるかの把握は必ずしてください

			//入学年度選択用
			List<Integer> year = new ArrayList<>();
			LocalDate now = LocalDate.now();
			int nowYear = now.getYear();

			//今が1～3月なら今の年-1、年度を取得したいため
			if (now.getMonthValue() <= 3){
				nowYear--;
			}

			for (int y = nowYear+1; y > nowYear-10; y--){
				year.add(y);
			}

			//クラス選択用
			ClassNumDao cDao = new ClassNumDao();

			//小柿：エラー吐いてますがこの処理は消すので気にしないで良いです
			List<ClassNum> cList = cDao.get("", user.getSchool());

			request.setAttribute("sList", sList);
			request.setAttribute("cList", cList);
			request.setAttribute("year", year);


		// 学生登録完了画面を表示

		return "student_create_done.jsp";
	}
}