package bean;

public class Subject implements java.io.Serializable{
	//科目のbean
	private String schoolCd;
	private String schoolName;
	private String cd;
	private String name;

	//ゲッター
	public String getSchoolCd() {
		return this.schoolCd;
	}
	public String getSchoolName() {
		return this.schoolName;
	}
	public String getCd() {
		return this.cd;
	}
	public String getName() {
		return this.name;
	}

	//セッター
	public void setSchoolCd(String schoolCd) {
		this.schoolCd = schoolCd;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public void setName(String name) {
		this.name = name;
	}
}