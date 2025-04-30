package bean;

public class ClassNum implements java.io.Serializable{
	private School school;
	private String class_num;

	//ゲッター
	public School getSchool() {
		return this.school;
	}
	public String getClass_num() {
		return this.class_num;
	}

	//セッター
	public void setSchool(School school) {
		this.school = school;
	}
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
}