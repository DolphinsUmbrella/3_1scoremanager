package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
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

		///学生登録完了画面の処理
		//OK。

		//値の格納

		//入学年度
		String entYearStr = request.getParameter("entYear");

		//学生番号
		String no = request.getParameter("no");

		//氏名
		String name = request.getParameter("name");

		//クラス
		String classNum = request.getParameter("classNum");

		String isAttendStr = request.getParameter("isAttend");
		boolean isAttend;
		if (Objects.isNull(isAttendStr)){
			isAttend = false;
		}
		else{
			isAttend = true;
		}


		//登録処理
		Student s = new Student();
		s.setNo(no);
		s.setName(name);
		s.setEntYear(Integer.parseInt(entYearStr));
		s.setClassNum(classNum);
		s.setIsAttend(isAttend);
		s.setSchool(user.getSchool());

		StudentDao sDao = new StudentDao(); //学生Dao

		//入力値をチェック エラーメッセージを表示

		//入力年度が選択されてない場合
		if(entYearStr.equals("0")){

			//訂正
			request.setAttribute("no",no);
			request.setAttribute("name", name);
			request.setAttribute("classNum", classNum);
			request.setAttribute("isAttend", isAttend);

			//messageはOKです！setAttributeはよく使うので引数の形を頭に叩き込んでください。
			request.setAttribute("entYearMessage","入学年度を選択してください");

			//シーケンス図情報
			//ーーーーーーーーーーーーーーーーーーーーーーーーーー
			//学生番号、氏名、クラス番号をセットし、
			//「入学年度を選択してください」とメッセージを表示する
			//ーーーーーーーーーーーーーーーーーーーーーーーーーー

			//修正完了
			//フォワード先はstudent_create.jsp
			//シーケンス図の「alt」と黒枠で囲ってある部分が該当の箇所ですので、
			//矢印の先にあるファイル名を確認してください。
			return "student_create.jsp";
		}

		//入力された学生番号が重複してる場合
		if(Objects.nonNull(sDao.get(no).getNo())){
			// 入学年度と学生番号はそのまま保持
			Student stu =  new Student();
			String stu_no = stu.getNo();
			int entYear = stu.getEntYear();
			String Class = stu.getClassNum();

			System.out.println("保持された値");
			System.out.println("入学年度："+ entYear);
			System.out.println("学生番号："+ no);

			//ここは入学年度もセットしてください
			//ここに遷移するなら必ず入学年度は正しく入力されています

			//訂正
			request.setAttribute("entYear", entYear);
			request.setAttribute("no",stu_no);
			request.setAttribute("name", name);
			request.setAttribute("classNum", Class);
			request.setAttribute("isAttend", isAttend);
			request.setAttribute("studentNoMessage", "学生番号が重複しています");

			//修正

			return "student_create.jsp";
		}

		//確認出力
		System.out.println(s.getNo()+"："+s.getName()+":");

		boolean result = sDao.save(s);//学生登録
		System.out.println(result);

		//変数resultを有効活用してあげるとなお良しです。
		//具体的には、「DB更新に失敗した場合はエラーページに遷移する」です。
		//シーケンス図には無いのでチャレンジ課題的な扱いで。

		if(result){
			// 学生登録完了画面を表示
			return "student_create_done.jsp";
		}else{
			// 学生登録完了画面を表示

			//5/12追記
			//形は正しいですが、遷移先がこれならエラーメッセージなりが必要です。
			//今の状態だと何も変化がなく、きちんと追加がされたかは一覧を見ない限り判断ができません

			//とはいえエラーページがまだなので、完成次第修正しましょう

			return "student_create_done.jsp";
		}

		//コードに影響は無いので話半分で良いですが、
		//途中なんかインデントがズレています。(インデント1つ分右にずれている)
		//これがpythonだった場合はエラーが出るので、
		//ちょっと気にしながらコーディングすると良いかと思います。
	}
}