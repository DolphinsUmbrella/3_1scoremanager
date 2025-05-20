import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import tool.Action;

public class LoginAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection con = null;

        try {
            // JNDI (Java Naming and Directory Interface) を使用してデータソースを取得
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/scoremanager"); // context.xml の name 属性と一致させる
            con = ds.getConnection();

            return "login.jsp"; // 正常に処理が完了した場合

        } catch (SQLException e) {
            // データベース接続エラー
            e.printStackTrace();

            request.setAttribute("errorMessage", "データベースへの接続に失敗しました。");
            request.setAttribute("errorDetail", "SQLException: " + e.getMessage());

            return "error.jsp";

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}