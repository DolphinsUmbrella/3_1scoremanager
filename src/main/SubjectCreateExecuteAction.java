package main;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{
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

		Subject sub = new Subject();
		sub.setCd(subjectCd);
		sub.setName(subjectName);
		sub.setSchool(user.getSchool());

		SubjectDao subDao = new SubjectDao();

		//入力値チェック
		//科目コードが3文字でない場合は科目コード、科目名をセットし
		//メッセージを入力する
		System.out.println("科目コード文字数：" + subjectCd.length());
		if (subjectCd.length() != 3){
			System.out.println("エラー：科目コードが3文字でない");
			request.setAttribute("cd", subjectCd);
			request.setAttribute("name", subjectName);
			request.setAttribute("subjectCdMessage", "科目コードは3文字で入力して下さい");
			return "subject_create.jsp";
		}

		//科目コードが重複
		if (Objects.nonNull(subDao.get(subjectCd, user.getSchool()).getCd())){
			System.out.println("エラー：科目名が重複している");
			request.setAttribute("cd", subjectCd);
			request.setAttribute("name", subjectName);
			request.setAttribute("subjectNameMessage", "科目コードが重複しています");
			return "subject_create.jsp";
		}

		try{
			boolean result = subDao.save(sub);

			if (result){
				return "subject_create_done.jsp";
			}
			else{
				//エラーページへの遷移がしたいです　これは仮
				return "subject_create_done.jsp";
			}
		}catch (Exception e) {
			return "../error.jsp";
		}


	}
}