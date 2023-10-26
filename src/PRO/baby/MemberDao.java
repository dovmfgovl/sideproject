package PRO.baby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {
	//선언부
	Connection connection = null; //db서버와 연결통로
	PreparedStatement pstmt = null; //연결통로로 쿼리문 전달할 전령 역할
	ResultSet resultSet = null; //오라클 커서 조작 -> 조회 결과 화면에 처리
	DBConnectionMgr dbMgr = null;
	Join join = null;
	//생성자
	public MemberDao(){
		
	}
	public MemberDao(Join join) {
		this.join = join;
	}
	//로그인 중복검사
	public boolean idCheck(String jm_id) {
		//List<Map<String, Object>> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(JM_ID) ");
		sql.append("FROM JOINMEMBER ");
		sql.append("WHERE JM_ID = ?");
		dbMgr = DBConnectionMgr.getInstance();
		try {
			connection = dbMgr.getConnection();
			pstmt = connection.prepareStatement(sql.toString());
			pstmt.setString(1, jm_id);
			resultSet = pstmt.executeQuery();
//			Map<String, Object> rmap = null;
//			while(resultSet.next()) {
//				rmap = new HashMap<>();
//				rmap.put("jm_id", resultSet.getString("jm_id"));
//				list.add(rmap);
//			}
//			System.out.println(list); //주소번지 출력 단위테스트
			resultSet.next(); // 행
			if (resultSet.getInt(1)/* 열 */ == 1) {
				return true;
			}
		} catch (SQLException se) {
			System.out.println(sql.toString()); //출력된 쿼리문을 토드에서 확인하기
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
