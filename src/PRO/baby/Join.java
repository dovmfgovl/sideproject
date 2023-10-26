package PRO.baby;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class Join extends JDialog implements ActionListener{
    //선언부
    JPanel jPanel_center = new JPanel();
    JLabel jLabel_info = new JLabel("*는 필수 입력 항목 입니다.");
    JLabel jLabel_id = new JLabel("*아이디");
    JTextField jTextField_id = new JTextField(10);
    JButton jButton_idDupli = new JButton("중복확인");
    JLabel jLabel_pw = new JLabel("*비밀번호");
    JPasswordField jPasswordField_pw = new JPasswordField(20); //비밀번호 암호화(*) 처리로 표시되도록 함.
    JLabel jLabel_pwCheck = new JLabel("*비밀번호 확인");
    JPasswordField jPasswordField_pwCheck = new JPasswordField(20);//비밀번호 확인도 암호화(*) 처리로 표시되도록 함.
    JButton jButton_pwDupli = new JButton("중복확인");
    JLabel jLabel_name = new JLabel("*이름");
    JTextField jTextField_name = new JTextField(30);
    JLabel jLabel_gender = new JLabel("*성별");
    String[] genderList = {"남자","여자"};
    JComboBox jComboBox_gender = new JComboBox(genderList);
    JLabel jLabel_birthDay = new JLabel("*생년월일");
    JTextField jTextField_birthDay = new JTextField(20);
    JLabel jLabel_hobby = new JLabel("취미");
    JTextField jTextField_hobby = new JTextField(20);
    JScrollPane jScrollPane = null;
    JPanel jPanel_south = new JPanel();
    JButton jButton_ins = new JButton("등록");
    JButton jButton_close = new JButton("닫기");
    
    String id = "", pass = "", passCheck = "", name = "", gender = "", birthday = "";
    
    Login login = null;
    MemberDao memberDao = new MemberDao(this);
    //생성자
    public Join(){
        initDisplay();
    }
    public Join(Login login) {
    	this.login = login; //재정의, 치환
	}
	//화면처리부
    public void initDisplay() {
        jPanel_center.setLayout(null);
        jLabel_info.setBounds(10, 10, 100, 20);
        jLabel_id.setBounds(20, 20, 100, 20);
        jTextField_id.setBounds(120, 20, 120, 20);
        jButton_idDupli.setBounds(280, 20, 85, 20);
        jLabel_pw.setBounds(20, 45, 100, 20);
        jPasswordField_pw.setBounds(120, 45, 150, 20);
        jLabel_pwCheck.setBounds(20, 70, 100, 20);
        jPasswordField_pwCheck.setBounds(120, 70, 150, 20);
        jButton_pwDupli.setBounds(280, 70, 85, 20);
        jLabel_name.setBounds(20, 95, 100, 20);
        jTextField_name.setBounds(120, 95, 120, 20);
        jLabel_gender.setBounds(20, 120, 100, 22);
        jComboBox_gender.setBounds(120, 120, 150, 22);
        jComboBox_gender.setFont(new Font("굴림",1,14));
        jLabel_birthDay.setBounds(20, 150, 100, 20);
        jTextField_birthDay.setBounds(120, 150, 150, 20);
        jLabel_hobby.setBounds(20, 180, 100, 20);
        jTextField_hobby.setBounds(120, 180, 150, 20);
        jPanel_center.add(jLabel_id);
        jPanel_center.add(jTextField_id);
        jPanel_center.add(jButton_idDupli);
        jPanel_center.add(jLabel_pw);
        jPanel_center.add(jPasswordField_pw);
        jPanel_center.add(jLabel_pwCheck);
        jPanel_center.add(jPasswordField_pwCheck);
        jPanel_center.add(jButton_pwDupli);
        jPanel_center.add(jLabel_name);
        jPanel_center.add(jTextField_name);
        jPanel_center.add(jLabel_gender);
        jPanel_center.add(jComboBox_gender);
        jPanel_center.add(jLabel_birthDay);
        jPanel_center.add(jTextField_birthDay);
        jPanel_center.add(jLabel_hobby);
        jPanel_center.add(jTextField_hobby);
        jPanel_south.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jPanel_south.add(jButton_ins);
        jPanel_south.add(jButton_close);
        this.add("South",jPanel_south);
        jScrollPane = new JScrollPane(jPanel_center);
        this.add("Center",jScrollPane);
        this.setTitle("회원가입");
        this.setSize(400, 500);
        this.setVisible(true);
        jButton_pwDupli.addActionListener(this);
        jButton_ins.addActionListener(this);
        jButton_idDupli.addActionListener(this);
    }
    public static void main(String[] args) {
    	new Join();       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	id = jTextField_id.getText();
		pass = new String(jPasswordField_pw.getPassword());
		passCheck = new String(jPasswordField_pwCheck.getPassword());
		name = jTextField_name.getText();
		gender = genderList.toString();
		birthday = jTextField_birthDay.getText();
    	
		//String sql = "insert into user_info(id, password, name, gender, birthday) values (?,?,?,?,?)";
    	    	
        Object obj = e.getSource();
        //id 중복확인 -DB를 통해 중복된 id 있는지 확인
        if (obj == jButton_idDupli) {   
        	Object check = memberDao.idCheck(id);
        	if(check != id) {
        		JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다.", "아이디 사용 가능", 1);
        		//return;
        	} else {
        		JOptionPane.showMessageDialog(this, "이미 존재하는 아이디 입니다.", "아이디 사용 불가", 0);
        	}
        }
        //비밀번호 조건 충족 확인 + 비밀번호와 비밀번호 확인의 중복 확인
        Pattern passPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); //8자 영문+특문+숫자
		Matcher passMatcher = passPattern.matcher(pass);
        if(obj == jButton_pwDupli) {
        	if(!passMatcher.find()) {
    			JOptionPane.showMessageDialog(this, "비밀번호는 8자 이상 영문, 특수문자, 숫자로 구성되어야 합니다.", "비밀번호 오류", 0);
    		}else if(!pass.equals(passCheck)) {
        		JOptionPane.showMessageDialog(this, "비밀번호와 비밀번호 확인이 동일하지 않습니다.", "비밀번호 오류", 0);
        	}else {
        		JOptionPane.showMessageDialog(this, "비밀번호가 동일합니다.", "비밀번호 확인", 1);
        	}
        }
        //등록 버튼을 눌렀을 때, *필수정보를 모두 입력하라는 문구 띄우기 + 회원가입 완료 후 로그인 창 띄우기
        else if(obj == jButton_ins) {
        	if("".equals(id) || "".equals(pass) || "".equals(passCheck) || "".equals(name) || "".equals(gender) || "".equals(birthday)) {
        		JOptionPane.showMessageDialog(this, "* 필수 입력 항목을 모두 입력하세요.", "*필수 입력 항목 미기입", 2);
        	} else {
        		JOptionPane.showMessageDialog(this, "회원가입이 완료 되었습니다.", "회원 가입 축하!", 1);
        		setVisible(false); //회원가입 화면 종료
        	}
        }
    }
    
}
