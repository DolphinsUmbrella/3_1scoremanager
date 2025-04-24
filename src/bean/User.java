//クラス図_ログイン・ログアウト参照
//ログイン中かを格納するbeanであり、daoはありません
//多重ログインを避けるため？
package bean;

public class User implements java.io.Serializable{
	private boolean isAuthenticated;

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}



}
