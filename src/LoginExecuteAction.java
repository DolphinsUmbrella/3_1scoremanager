

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

		//idとパスワードがどちらも存在しない場合
		if (Objects.isNull(id) || id.isEmpty() && (Objects.isNull(password) || password.isEmpty())){
		    String message = "IDとパスワードを入力してください。";
		    request.setAttribute("message", message);
		    //エラーメッセージを送る時はフォワードのほうがいいっぽい
			//response.sendRedirect("/scoremanager/Login.action");

		    request.getRequestDispatcher("/Login.action").forward(request, response);
		    return null;
		}

		//idが存在しない
		if (Objects.isNull(t.getId())){
			String message = "ログインに失敗しました。存在しないIDです。";
			request.setAttribute("teacher_id", id);
			//request.setAttribute("password", password);←秘密保持的な意味でここいる？
			request.setAttribute("message", message);
			System.out.println(message);

			request.getRequestDispatcher("/Login.action").forward(request, response);
			return null;
		}

		//パスワードが違う
		if (!t.getPassword().equals(password)){
			String message = "ログインに失敗しました。パスワードが正しくありません。";
			request.setAttribute("teacher_id", id);
			request.setAttribute("password", password);
			request.setAttribute("message", message);
			System.out.println(message);

			request.getRequestDispatcher("/Login.action").forward(request, response);
			return null;
		}

		System.out.println("認証成功");

		session.setAttribute("user", t);

		response.sendRedirect("main/Menu.action");

		return null;
	}
}