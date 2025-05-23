package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

//既存の学生のデータを変更する画面に遷移するアクション
public class StudentUpdateAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{

		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");


		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//jsp側のnoを呼び出す
		String no = request.getParameter("no");

		//確認用
		System.out.println(no);


		//選択した生徒の情報を検索
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(no);

		//クラス一覧を取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> cList = cDao.filter(user.getSchool());

		request.setAttribute("cList", cList);

		//遷移してもデータを保持できるようにする
		//5/14小柿：sessionにいるならrequestの分は不要では？一度コメントアウトしています
		//request.setAttribute("student", student);
		session.setAttribute("student", student);


		return "student_update.jsp";
	}
}