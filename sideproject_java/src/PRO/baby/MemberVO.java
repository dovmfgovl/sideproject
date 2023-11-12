package PRO.baby;

public class MemberVO {
	private String jm_id = "";
	private String jm_pw = "";
	private String jm_name = "";
	private String jm_gender = "";
	private int jm_birthday = 0;
	
	//생성자의 파라미터 자리를 통해서 전역변수의 초기화(재정의) 함
	public MemberVO(String jm_id, String jm_pw, String jm_name, String jm_gender, int jm_birthday) {
		this.jm_id = jm_id;
		this.jm_pw = jm_pw;
		this.jm_name = jm_name;
		this.jm_gender = jm_gender;
		this.jm_birthday = jm_birthday;
	}
	public String getJm_id() {
		return jm_id;
	}
	public void setJm_id(String jm_id) {
		this.jm_id = jm_id;
	}
	public String getJm_pw() {
		return jm_pw;
	}
	public void setJm_pw(String jm_pw) {
		this.jm_pw = jm_pw;
	}
	public String getJm_name() {
		return jm_name;
	}
	public void setJm_name(String jm_name) {
		this.jm_name = jm_name;
	}
	public String getJm_gender() {
		return jm_gender;
	}
	public void setJm_gender(String jm_gender) {
		this.jm_gender = jm_gender;
	}
	public int getJm_birthday() {
		return jm_birthday;
	}
	public void setJm_birthday(int jm_birthday) {
		this.jm_birthday = jm_birthday;
	}
	
}