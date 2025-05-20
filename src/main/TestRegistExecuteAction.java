package main;

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
        int point;
        try {
            // リクエストパラメータから値を取得
            String pointStr = request.getParameter("point");  // 点数を文字列で取得

            // 点数が空白かどうかチェック
            if (pointStr == null || pointStr.isEmpty()) {
                request.setAttribute("error", "点数が空白です。正しい値を入力してください。");
                return "test_regist.jsp";  // 元のページに戻る
            }

            try {
                // 点数を数値に変換
                point = Integer.parseInt(pointStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "点数の値が数値ではありません。");
                return "test_regist.jsp";  // 元のページに戻る
            }

            // 点数の範囲チェック (0〜100)
            if (point < 0 || point > 100) {
                request.setAttribute("error", "点数は0から100の範囲で入力してください。");
                return "test_regist.jsp";  // 元のページに戻る
            }

            // Testオブジェクトの生成と値設定
            Test test = new Test();
            test.setPoint(point);

            // TestDaoを使って成績を更新
            TestDao dao = new TestDao();
            int result = dao.updateTes(test);

            if (result > 0) {
                // 更新成功時
                request.setAttribute("message", "成績の更新が完了しました。");
                return "test_regist_done.jsp";
            } else {
                // 更新失敗時
                request.setAttribute("error", "成績の更新に失敗しました。");
                return "error.jsp";
            }

        } catch (Exception e) {
            // エラーハンドリング
            e.printStackTrace();
            request.setAttribute("error", "成績更新中にエラーが発生しました: " + e.getMessage());
            return "error.jsp";
        }
    }
}