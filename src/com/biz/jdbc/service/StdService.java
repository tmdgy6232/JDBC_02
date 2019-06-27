package com.biz.jdbc.service;

import java.util.List;

import com.biz.jdbc.model.StudentVO;

/*
 * CRUE를 구현해보자
 * 1. DBConnection을 설정하기
 * 		실제 구현할 클래스에서 만들 부분
 * 2. SELECT를 구현해보기
 * 3. INSERT를 구현해보기
 * 4. UPDATE, DELETE 구현해보기
 */

public interface StdService {

	//전체 리스트를 가져올 METHOD
	// DBMS에게 SELECT * FROM [Table]을 실행하여
	// 전체 리스트를 가져오는 일을 수행할 예정
	// 가져온 리스트를 java의 list 자료구조로 변환시켜
	// 요청한 곳에 리턴해줄 것.
	public List selectAll();
	
	
	//학번을 기준으로 한 학생의 정보를 가져올 METHOd
	//학번을 매개변수로 전달하고
	//학번을 where로 하여 select를 수행한 후
	// 한 학생의 정보를 가져와서 vo에 담아
	// 요청한곳에 return한다.
	public StudentVO findeByNum(String st_num);
	
	
	// 학생 정보를 DB INSERT할 METHOD
	// 추가 (create)하고자 하는 학생의 정보를
	// VO에 담아서 매개변수로 전달해 주고
	// INSERT를 수행하도록 한다.
	public void insert(StudentVO vo);
	
	// 학생정보를 수정(UPDATE)할 METHOD
	// 수정(UPDATE)를 수행할 때는
	// 1. 학생정보(한사람의) 정보를 조회하고 : vo에 받기
	// 2. 수정할 칼럼(항목)이 있으면 그 칼럼의 값만 변경을 하고
	// 3. 나머지 값은 그대로 유지하여
	// 4. VO에 담겨있는 값을 매개변수로 전달하여
	// 5. 업데이트를 수행한다.
	public void update(StudentVO vo);
	
	// 학생정보를 삭제(DELETE)할 METHOD
	//삭제할 때는 학번 하나만 매개변수로 전달하고
	//델리트를 수행한다.
	public void delete(String st_num);
	
	
}
