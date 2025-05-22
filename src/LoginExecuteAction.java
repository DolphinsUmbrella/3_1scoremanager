

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

		Teacher t = new Teacher();
		try{
			t = tDao.get(id);
		}catch (Exception e) {
			return "error.jsp";
		}

		//idとパスワードがどちらも存在しない場合
		if (Objects.isNull(id) || id.isEmpty() && (Objects.isNull(password) || password.isEmpty())){
		    String message = "IDまたはパスワードを入力してください。";
		    request.setAttribute("message", message);

		    //エラーメッセージを送る時はフォワードのほうがいいっぽい
			//response.sendRedirect("/scoremanager/Login.action");

		    request.getRequestDispatcher("/Login.action").forward(request, response);
		    return null;
		}

		//idが存在しない
		if (Objects.isNull(t.getId())){
			String message = "IDまたは、パスワードが違います。";
			request.setAttribute("teacher_id", id);
			request.setAttribute("message", message);
			System.out.println(message);

			request.getRequestDispatcher("/Login.action").forward(request, response);
			return null;
		}

		//パスワードが違う
		if (!t.getPassword().equals(password)){
			String message = "IDまたは、パスワードが違います。";
			request.setAttribute("teacher_id", id);
			request.setAttribute("password", password);
			request.setAttribute("message", message);
			System.out.println(message);

			request.getRequestDispatcher("/Login.action").forward(request, response);
			return null;
		}

		System.out.println("認証成功");
		t.setAuthenticated(true);

		session.setAttribute("user", t);

		response.sendRedirect("main/Menu.action");

		return null;
	}
}