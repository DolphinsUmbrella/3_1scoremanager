package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}

		String subjectCd = request.getParameter("cd");

		Subject sub = new Subject();
		sub.setCd(subjectCd);
		sub.setSchool(user.getSchool());

		SubjectDao subDao = new SubjectDao();
		boolean result = subDao.delete(sub);

		if (result){
			return "subject_delete_done.jsp";
		}
		else{
			//削除に失敗している場合はエラーページへ　これは仮
			return "subject_delete_done.jsp";
		}
	}
}