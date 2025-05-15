package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		String subjectCd = request.getParameter("cd");
		String subjectName = request.getParameter("name");

		//確認用出力
		System.out.println("科目コード："+subjectCd);
		System.out.println("科目名："+subjectName);

		Subject sub = new Subject();
		sub.setCd(subjectCd);
		sub.setName(subjectName);
		sub.setSchool(user.getSchool());

		SubjectDao subDao = new SubjectDao();

		boolean result = subDao.update(sub);

		if (result){
			return "subject_update_done.jsp";
		}
		else{
			//変更に失敗している場合はエラーページへ　これは仮
			return "subject_update_done.jsp";
		}

	}
}