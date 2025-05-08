package bean;

public class Test implements java.io.Serializable{
	private Student student;
	private Subject subject;
	private School school;
	private int no;
	private int point;
	private String classNum;

	//ゲッター
	public Student getStudent() {
		return this.student;
	}
	public Subject getSubject() {
		return this.subject;
	}
	public School getSchool() {
		return this.school;
	}
	public int getNo() {
		return this.no;
	}
	public int getPoint() {
		return this.point;
	}
	public String getClassNum() {
		return this.classNum;
	}

	//セッター
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

}