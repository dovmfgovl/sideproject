package PRO.baby;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
    //선언부
	Join join = new Join(this);
	String id = "", pass = "";
	String imgPath="C:\\Users\\SeulGi\\Desktop\\java\\workspace\\dev_java\\src\\image\\";
	JLabel jLabel_id = new JLabel("아이디");
	JLabel jLabel_pw = new JLabel("패스워드");

	Font jLabel_font = new Font("휴먼매직체", Font.BOLD, 17);
	JTextField jTextField_id = new JTextField("");
	JPasswordField jPasswordField_pw = new JPasswordField("");

	JButton jButton_login = new JButton(
			new ImageIcon(imgPath+"login.png"));
	JButton jButton_join = new JButton(
			new ImageIcon(imgPath+"confirm.png"));
		// JPanel에 쓰일 이미지아이콘
		ImageIcon ig = new ImageIcon(imgPath+"pink.PNG");
	JButton jButton_find = new JButton("ID/PW 찾기");
	public MemberVO memberVO;
	//생성자
    public Login(){
        initDisplay();
    }
    /* 배경이미지 */
	class mypanal extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(ig.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponents(g);
		}
	}
    //화면처리부
    public void initDisplay() {
    	setContentPane(new mypanal());
		
		/* 버튼과 텍스트필드 구성 */
		jButton_join.addActionListener(this);
		jButton_login.addActionListener(this);
		this.setLayout(null);
		this.setTitle("로그인");
		this.setSize(350, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(800, 250);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// id 라인
		jLabel_id.setBounds(45, 200, 80, 40);
		jLabel_id.setFont(jLabel_font);
		jTextField_id.setBounds(110, 200, 185, 30);
		this.add(jLabel_id);
		this.add(jTextField_id);

		// pw 라인
		jLabel_pw.setBounds(35, 240, 80, 40);
		jLabel_pw.setFont(jLabel_font);
		jPasswordField_pw.setBounds(110, 240, 185, 30);
		this.add(jLabel_pw);
		this.add(jPasswordField_pw);

		// 로그인 버튼 라인
		jButton_login.setBounds(175, 285, 120, 40);
		this.add(jButton_login);

		// 회원가입 버튼 라인
		jButton_join.setBounds(45, 285, 120, 40);
		this.add(jButton_join);	
//		jButton_join.addActionListener(this);
		
		// ID/PW 찾기 버튼 라인
		jButton_find.setBounds(115, 330, 100, 30);
		this.add(jButton_find);
    }
    public static void main(String[] args) {
    	new Login(); //전역변수 자리에 인스턴스화 한 객체들이 비로소 이때 로딩이 됨    	
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	id = jTextField_id.getText();
		pass = new String(jPasswordField_pw.getPassword());
		
        Object obj = e.getSource();
        //회원가입 버튼 누르면 회원가입 창 연결 + 로그인 하기
        //1)회원가입 버튼을 누르면 로그인 창은 꺼지고 회원가입 창이 켜짐
        //2)아이디나 비밀번호만 입력했을 때 "아이디와 비밀번호를 모두 입력하세요." 출력 
        //3)DB 연결 - 등록된 아이디나 비밀번호가 틀렸을 경우 "입력한 정보가 틀렸거나 회원가입 정보가 없습니다." 문구 출력
        //4)로그인 후 , 게시판으로 이동
        String nickName = id;
        if(obj == jButton_join) {
        	JOptionPane.showMessageDialog(this, "회원가입 창으로 이동합니다.", "회원가입 안내", 1);
        	join.initDisplay();	
        	this.setVisible(false);
        } else if("".equals(id) || "".equals(pass)) {
        	JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 모두 입력하세요.", "ID/PW 미기입", 0);
        } /*else if(DB 연결 - 비밀번호 일치하지 않을 경우) {
        	JOptionPane.showMessageDialog(this, "입력한 정보가 틀렸거나 회원가입 정보가 없습니다.", "ID/PW 에러", 0);        	
        }*/ else {
        	JOptionPane.showMessageDialog(this, nickName+"님. 환영합니다.", "로그인 성공", 1);
        	setVisible(false); //로그인 화면 종료
        	//게시판 이동
        }
    }
}
