package bean;

public class ClassNum implements java.io.Serializable{
	private String schoolCd;
	private String classNum;

	//ゲッター
	public String getSchoolCd() {
		return this.schoolCd;
	}
	public String getClassNum() {
		return this.classNum;
	}

	//セッター
	public void setSchoolCd(String schoolCd) {
		this.schoolCd = schoolCd;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
}