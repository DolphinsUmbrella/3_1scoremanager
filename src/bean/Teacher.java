package bean;

public class Teacher extends User{
	private String id;
	private String password;
	private String name;
	private School school;

	//ゲッター
	public String getId() {
		return this.id;
	}
	public String getPassword() {
		return this.password;
	}
	public String getName() {
		return this.name;
	}
	public School getSchool() {
		return this.school;
	}

	//セッター
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSchool(School school) {
		this.school = school;
	}
}