package bean;

public class Test implements java.io.Serializable{
	private String studentNo;
	private String studentName;
	private String subjectCd;
	private String subjectName;
	private String schoolCd;
	private String schoolName;
	private int no;
	private int point;
	private String classNum;

	//ゲッター
	public String getStudentNo() {
		return this.studentNo;
	}
	public String getStudentName() {
		return this.studentName;
	}
	public String getSubjectCd() {
		return this.subjectCd;
	}
	public String getSubjectName() {
		return this.subjectName;
	}
	public String getSchoolCd() {
		return this.schoolCd;
	}
	public String getSchoolName() {
		return this.schoolName;
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
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public void setStudetntName(String studentName) {
		this.studentName = studentName;
	}
	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public void setSchoolCd(String schoolCd) {
		this.schoolCd = schoolCd;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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