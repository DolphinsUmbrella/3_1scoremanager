

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session = request.getSession();

		String id = request.getParameter("teacher_id");
		String password = request.getParameter("password");
		TeacherDao tDao = new TeacherDao();

		Teacher t = tDao.get(id);

		//idが存在しない
		if (Objects.isNull(t.getId())){
			String message = "ログインに失敗しました。存在しないIDです。";
			request.setAttribute("message", message);
			System.out.println(message);
			response.sendRedirect("/scoremanager/login.jsp");
			return null;
		}

		//パスワードが違う
		if (!t.getPassword().equals(password)){
			String message = "ログインに失敗しました。IDまたはパスワードが正しくありません。";
			request.setAttribute("message", message);
			System.out.println(message);
			response.sendRedirect("/scoremanager/login.jsp");
			return null;
		}

		System.out.println("認証成功");

		session.setAttribute("user", t);

		response.sendRedirect("main/Menu.action");

		return null;
	}
}