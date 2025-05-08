package bean;

public class Subject implements java.io.Serializable{
	//科目のbean
	private School school;
	private String cd;
	private String name;

	//ゲッター
	public School getSchool() {
		return this.school;
	}
	public String getCd() {
		return this.cd;
	}
	public String getName() {
		return this.name;
	}

	//セッター
	public void setSchool(School school) {
		this.school = school;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public void setName(String name) {
		this.name = name;
	}
}