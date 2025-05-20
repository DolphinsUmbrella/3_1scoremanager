package main;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher user = (Teacher) session.getAttribute("user");

		if (Objects.isNull(user)) {
			response.sendRedirect("../Login.action");
			return null;
		}

		List<Test> tList = (List<Test>)session.getAttribute("tList");
		session.removeAttribute("tList");
		boolean errorFlag = false;

		try {
			//点数取得、値のチェックとtListに格納
			for (Test t : tList){
				// リクエストパラメータから点数を取得
				String pointStr = request.getParameter("point_"+t.getStudent().getNo());

				System.out.println("点数："+pointStr);

				//点数なしの場合-1を格納
				//Daoでは点数が-1の場合削除へ、
				//JSPに戻る場合は-1は空白として扱われる
				if (Objects.isNull(pointStr)){
					t.setPoint(-1);
					continue;
				}

				//int型へ変換
				int point = Integer.parseInt(pointStr);

				//点数がちゃんとしてない場合は-2を格納
				//JSPにて、-2が格納されている場合は
				//エラーメッセージを出力する処理をします
				if (point < 0 || point > 100) {
					t.setPoint(-2);
					errorFlag = true;
					continue;
				}

				//点数をリストに格納
				t.setPoint(point);
            }

			System.out.println("格納した点数：");
			for (Test t : tList){
				System.out.println(t.getPoint());
			}

			//点数が0～100でない箇所があった場合、
			//test_regist.jspに戻します
			if (errorFlag){
				session.setAttribute("tList", tList);
				return "test_regist.jsp";
			}

			//入力値がすべて正常であるためDBへ書き込み
			TestDao tDao = new TestDao();
			boolean result = tDao.save(tList);

			if (result){
				return "test_regist_done.jsp";
			}else{
				return "../error.jsp";
			}

        } catch (Exception e) {
        	System.out.println(e);
            return "../error.jsp";
        }
    }
}