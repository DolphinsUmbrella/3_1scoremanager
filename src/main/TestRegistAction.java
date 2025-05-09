package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();
		Teacher user = (Teacher)session.getAttribute("user");

		if (Objects.isNull(user)){
			response.sendRedirect("../Login.action");
			return "null";
		}



		//フィルター処理もやる

		//userの所属する学校のクラス情報を取得
		ClassNumDao cDao = new ClassNumDao();
		List<String> cList = cDao.filter(user.getSchool());

		//userの所属する学校の科目情報を取得
		SubjectDao subDao = new SubjectDao();
		Subject subList = subDao.get("", user.getSchool());

		request.setAttribute("cList", cList);
		request.setAttribute("subList", subList);
		return "test_regist.jsp";
	}
}