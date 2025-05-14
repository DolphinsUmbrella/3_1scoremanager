package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

//入力した変更の値に更新するアクション
public class StudentUpdateExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{

		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		//jspで入力されたデータを格納
		String name = request.getParameter("name");
		String classNum = request.getParameter("classNum");
		String isAttendStr = request.getParameter("isAttend");
		boolean isAttend = "true".equals(isAttendStr);

		//Student型で保存したデータを呼び出す
		Student student = (Student)session.getAttribute("student");

		//確認用
		System.out.println("スコープに格納された値");
		System.out.println("生徒名："+ name);
		System.out.println("クラス番号："+classNum);
		System.out.println("在学フラグ："+isAttend);

		if (Objects.nonNull(student)){
			// 入学年度と学生番号はそのまま保持
			String no = student.getNo();
			int entYear = student.getEntYear();

			School school = student.getSchool();

			System.out.println("保持された値");
			System.out.println("入学年度："+ entYear);
			System.out.println("学生番号："+ no);

			//更新用のStudentオブジェクトを作成
			Student s = new Student();
			s.setNo(no);
			s.setName(name);
			s.setEntYear(entYear);
			s.setClassNum(classNum);
			s.setIsAttend(isAttend);
			s.setSchool(school);

			//updateStudentメソッドを呼び出し
			StudentDao dao =new StudentDao();
			int update = dao.updateStudent(s);

			session.removeAttribute("student");

			if (update > 0){
				Student newStudent = dao.get(no);
				session.setAttribute("student", newStudent);
				return "student_update_done.jsp";
			} else {
				// 更新に失敗した場合のエラー処理
				request.setAttribute("errorMessage", "学生情報の更新に失敗しました。");
				return "student_update.jsp";

			}
		} else {
			// セッションに Student オブジェクトが見つからない場合のエラー処理
			request.setAttribute("errorMessage", "更新対象の学生情報が見つかりませんでした。");
			return "../error.jsp"; // またはエラーメッセージを表示するページ
		}
	}
}