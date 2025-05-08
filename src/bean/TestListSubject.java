package bean;

import java.util.HashMap;
import java.util.Map;
public class TestListSubject implements java.io.Serializable{
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer,Integer> points = new HashMap<>();

	//ゲッター
	public int getEntYear() {
		return entYear;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public String getClassNum() {
		return classNum;
	}
	public Map<Integer, Integer> getPoints() {
		return points;
	}
	public String getPoint(int Key){
		Integer Value = points.get(Key);
		return Value != null? Value.toString():null;
	}

	//セッター
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}
	public void putPoint(int Key,int Value){
		points.put( Key, Value );
	}


}
