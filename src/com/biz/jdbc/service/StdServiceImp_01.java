package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.config.DbConnection;
import com.biz.jdbc.model.StudentVO;

import oracle.jdbc.driver.DBConversion;

/*
 * StdService 인터페이스(설계도)에 기반한
 * 실제 클래스를 구현
 * 1. DB Connection을 설정
 */
public class StdServiceImp_01 implements StdService {

	private List<StudentVO> stdList = null;

	private Connection dbConn = null;
//	private String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//	private String userName = "user5";
//	private String password = "1234";

	public StdServiceImp_01() {
		this.dbConn = DbConnection.getDBConnection();
		// 데이터 리스트를 만들어서 외부로 전달하기 위해 사용하는
		// stdList를 생성자에서 초기화를 하고 계속 사용을 하면
		// selectAll()을 실행할 ?때마다 리스트가 계속 쌓이게된다.
		// 리스트를 초기화 하는 코드는
		// 리스트를 만들기 직전에 위치해야 한다.
		// stdList = new ArrayList<StudentVO>();
	}

	// dbConnection은 외ㅏ부에서 실행되지 못하도록 private선언한다.
	// dbConnection이 자주 실행되는 것은 driver를 계속 on load 시키고
	// 통로를 새로 설정하는 과정이 반복되어 문제를 일으킬 수 있기 때문이다.
//	private void dbConnection() {
//
////		try {
////			// 1. jdbcDriver On loda 시키기
////			Class.forName(jdbcDriver);
////
////			// 2. 연결통로(port) 설정하기
////			dbConn = DriverManager.getConnection(url, userName, password);
////		} catch (ClassNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}

	@Override
	public List selectAll() {
		// TODO 여기는 전체 리스트를 select하는 매서드이다.
		String sql = " SELECT * FROM tbl_student";

		// String으로 된 sqp 문장을 DBMS 방식의 코드로 변환하여
		// DBMS에게 전달하는 역할을 수행한다.
		PreparedStatement pStr = null;

		try {
			// sql문을 DBMS 코드로 변환(컴파일)하여 잠시 보관중
			pStr = this.dbConn.prepareStatement(sql);

			// query를 실행하고
			// DBMS 보낸 결과를 ResultSet 데이터 구조로 바꾸어
			// 리턴한다.

			// ResultSet구조의 객체를 선언하여 데이터를 수신한다.
			ResultSet rs = pStr.executeQuery();
			/*
			 * ResultSet : DBMS가 보낸 데이터를 배열형태로 보관을 하고 next() 매서드를 실행하면 한줄씩 데이터를 읽어온다. 단, 읽는
			 * 방향은 일방통행이다. 처음부터 ~ 끝방향으로
			 */
			// rs.next();
			// if(rs.next()) System.out.println("데이터가 있다.");
			// else System.out.println("데이터가 없다.");

			stdList = new ArrayList<StudentVO>();
			while (rs.next()) {
				StudentVO vo = new StudentVO();
				/*
				 * ST_NO ST_NAME ST_ADDR ST_GRADE ST_HEIGHT ST_WEIGHT ST_NICK ST_NICK_REM
				 * ST_DEPT_NAME
				 */
				// old 코드
				vo.setSt_no(rs.getString(1));
				vo.setSt_name(rs.getString(2));
				vo.setSt_addr(rs.getString(3));
				vo.setSt_grade(rs.getInt(4));
				vo.setSt_height(rs.getInt(5));
				vo.setSt_weight(rs.getInt(6));
				vo.setSt_nick(rs.getString(7));
				vo.setSt_nick_rem(rs.getString(8));
				vo.setSt_dept_name(rs.getString(9));

				// new 코드
				vo.setSt_no(rs.getString("st_no"));
				vo.setSt_name(rs.getString("st_name"));
				vo.setSt_addr(rs.getString("st_addr"));
				vo.setSt_grade(rs.getInt("st_grade"));
				vo.setSt_height(rs.getInt("st_height"));
				vo.setSt_weight(rs.getInt("st_weight"));
				vo.setSt_nick(rs.getString("st_nick"));
				vo.setSt_nick_rem(rs.getString("st_nick_rem"));
				vo.setSt_dept_name(rs.getString("st_dept_name"));

				stdList.add(vo);
			}

			return stdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentVO findeByNum(String st_num) {
		// TODO 학번을 매개변수로 받아 한 학생의 정보를 리턴하는 매서드

		// sql문을 작성할 때 따옴표가 시작되는 곳과 끝나는 곳에 스페이스 필수
		String sql = "SELECT * FROM tbl_student ";
		sql += " WHERE st_no = " + st_num;
		System.out.println(sql);

		PreparedStatement ps = null;

		try {
			ps = dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				StudentVO vo = null;
				vo = new StudentVO();
				vo.setSt_no(rs.getString("st_no"));
				vo.setSt_name(rs.getString("st_name"));
				vo.setSt_addr(rs.getString("st_addr"));
				vo.setSt_grade(rs.getInt("st_grade"));
				vo.setSt_height(rs.getInt("st_height"));
				vo.setSt_weight(rs.getInt("st_weight"));
				vo.setSt_nick(rs.getString("st_nick"));
				vo.setSt_nick_rem(rs.getString("st_nick_rem"));
				vo.setSt_dept_name(rs.getString("st_dept_name"));
				return vo;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insert(StudentVO vo) {
		// TODO 학생정보를 vo에 받아 db에 insert 수행
		String sql = " INSERT INTO tbl_student ( ";
		sql += " ST_NO, ";			//1
		sql += " ST_NAME, ";		//2
		sql += " ST_ADDR, ";		//3
		sql += " ST_GRADE, ";		//4
		sql += " ST_HEIGHT, ";		//5
		sql += " ST_WEIGHT, ";		//6
		sql += " ST_NICK, ";		//7
		sql += " ST_NICK_REM, ";	//8
		sql += " ST_DEPT_NAME ) ";	//9
		sql += " Values(?,?,?,?,?,?,?,?,?) " ;

		PreparedStatement ps = null;
		try {
			ps = this.dbConn.prepareStatement(sql);
			ps.setString(1, vo.getSt_no());
			ps.setString(2, vo.getSt_name());
			ps.setString(3, vo.getSt_addr());
			ps.setInt(4, vo.getSt_grade());
			ps.setInt(5, vo.getSt_height());
			ps.setInt(6, vo.getSt_weight());
			ps.setString(7, vo.getSt_nick());
			ps.setString(8, vo.getSt_nick_rem());
			ps.setString(9, vo.getSt_dept_name());
			
			ps.executeUpdate();
			System.out.println("데이터 추가 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(StudentVO vo) {
		// TODO vo에 값을 받아서 기존 데이터를 업데이트 수행
		
		String sql = " UPDATE tbl_student SET ";
		sql += " ST_NAME = ?, ";			//1
		sql += " ST_ADDR = ?, ";
		sql += " ST_GRADE = ?, ";
		sql += " ST_HEIght = ?, ";
		sql += " ST_WEIGHT = ?, ";
		sql += " ST_NICK = ?, ";
		sql += " ST_NICK_REM = ?, ";
		sql += " ST_DEPT_NAME = ? ";
		sql += " WHERE ST_NO = ? ";
		
		PreparedStatement ps = null;
		
		try {
			ps = this.dbConn.prepareStatement(sql);
			ps.setString(9, vo.getSt_no());
			ps.setString(1, vo.getSt_name());
			ps.setString(2, vo.getSt_addr());
			ps.setInt(3, vo.getSt_grade());
			ps.setInt(4, vo.getSt_height());
			ps.setInt(5, vo.getSt_weight());
			ps.setString(6, vo.getSt_nick());
			ps.setString(7, vo.getSt_nick_rem());
			ps.setString(8, vo.getSt_dept_name());
			
			ps.executeUpdate();
			System.out.println("업데이트 성공");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	@Override
	public void delete(String st_num) {
		// TODO 여기는 한개의 레코드(데이터를) 삭제하는 method이다.
		
		String sql = " DELETE FROM tbl_student ";
		sql += " WHERE st_no = " + st_num;
		
		PreparedStatement ps = null;
		try {
			ps = this.dbConn.prepareStatement(sql);
			int ret = ps.executeUpdate();
			//삭제가 정상적으로 이루어졌는지 확인하는 방법으로
			// 아래 2가지 코드가 있지만
			// DBMS서버와 통신하는 과정에서
			// 레코드 정상적으로 삭제되면 반드시 0이상의 ㅏ\값을
			//리턴해서 RET에 담아주지만
			// 레코드에 삭제가 이루어지지 않을 경우
			// 정말 삭제할 레코드가 없는 경우도 있고
			// 이 경우는 0을 리턴할 것이아.
			
			//하지만 어떤 이유로 레코드가 있음에도
			// 삭제가 이루어지지 않았을 경우
			// 0 미만의 값을 리턴하는 경우도 있다.
			// 이런 경우 2번의 방법에서는
			// 정상적으로 삭제가 이루어졌다는 메세지를 보이게 된다
			// 따라서 1번 방법이 안정한 코드 작성법이다.
			
			
			//1번 검증방법
			if(ret>0) System.out.println(ret + "레코드 삭제성공");
			else System.out.println("삭제할 데이터가 없음");
			
			
			//2번 검증방법
			if(ret==0) System.out.println("삭제할 데이터가 없음");
			else System.out.println(ret + "레코드 삭제성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end delete
	
	public String makeStNO() {
		
		String sql = " select lpad(max(st_no) +1,3,'0') ";
		sql += " from tbl_student ";
		
		PreparedStatement ps = null;
		try {
			ps=this.dbConn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String strNum = rs.getString(1);
				return strNum;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}

}
