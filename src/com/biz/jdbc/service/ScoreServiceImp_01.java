package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.config.DbConnection;
import com.biz.jdbc.model.ScoreVO;
import com.biz.jdbc.model.StudentVO;

/*
 * DBConnection 설정
 */
public class ScoreServiceImp_01 implements ScoreService {

	private List<ScoreVO> scList = null;

	private Connection dbConn = null;
//	private String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
//	private String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
//	private String userName = "user5";
//	private String password = "1234";
	
	
	
	public ScoreServiceImp_01() {

		this.dbConn = DbConnection.getDBConnection();
		
	}
	
//	private void dbconnection() {
//		try {
//			Class.forName(jdbcDriver);
//			dbConn = DriverManager.getConnection(url, userName, password);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}

	@Override
	/*
	 * tbl_score 테이블의 모든 데이터를 select한 후
	 * list로 변환하여 return
	 */
	public List<ScoreVO> selectAll() {
		// TODO 여기는 전체 성적데이터를 select해서 list로 return하는 매서드
		String sql = " SELECT * FROM tbl_score ";
		
		PreparedStatement pStr = null;
		
		try {
			pStr = this.dbConn.prepareStatement(sql);
			ResultSet rs = pStr.executeQuery();
			scList = new ArrayList<ScoreVO>();
			
			while(rs.next()) {
				ScoreVO vo = new ScoreVO();
				
				vo.setSc_seq(rs.getLong("sc_seq"));
				vo.setSc_date(rs.getString("sc_date"));
				vo.setSc_st_num(rs.getString("sc_st_num"));
				vo.setSc_subject(rs.getString("sc_subject"));
				vo.setSc_score(rs.getInt("sc_score"));
				scList.add(vo);
			}
			return scList;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ScoreVO fineById(long sc_seq) {
		// TODO 여기는 일련번호 값을 매개변수로 받아 성적데이터 1개의 레코드를 리턴
		String sql = " SELECT * FROM tbl_score ";
		sql += " where sc_seq = " + sc_seq;
		return null;
	}

	@Override
	public int insert(ScoreVO vo) {
		// TODO 여기는 데이터 추가
		String sql = " INSERT INTO tbl_score ( " ;
		sql += " SC_SEQ, " ; 
		sql += " SC_DATE, " ;
		sql += " SC_ST_NUM, " ;
		sql += " SC_SUBJECT, " ;
		sql += " SC_SCORE " ;
		sql += " values( seq_score.nextval ,?,?,?,? ) ";
		return 0;
	}

	@Override
	public int update(ScoreVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long sc_seq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreVO> findByNum(String st_no) {
	String sql = " SELECT * FROM tbl_score ";
	sql += " where sc_st_num = " + st_no;
		
		PreparedStatement pStr = null;
		
		try {
			pStr = this.dbConn.prepareStatement(sql);
			ResultSet rs = pStr.executeQuery();
			scList = new ArrayList<ScoreVO>();
			
			while(rs.next()) {
				ScoreVO vo = new ScoreVO();
				
				vo.setSc_seq(rs.getLong("sc_seq"));
				vo.setSc_date(rs.getString("sc_date"));
				vo.setSc_st_num(rs.getString("sc_st_num"));
				vo.setSc_subject(rs.getString("sc_subject"));
				vo.setSc_score(rs.getInt("sc_score"));
				scList.add(vo);
			}
			return scList;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return null;
		
	}

}
