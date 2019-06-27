package com.biz.jdbc.exec;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		StdService ss = new StdServiceImp_01();
		
		String stNO = "101";
		
		StudentVO vo = ss.findeByNum(stNO);
		
		if(vo==null) System.out.println("학생 데이터가 없음");
		else {
			
			//조회한 vo에서 stNO를 추출하여 그 값을 기준으로 삭제
			ss.delete(vo.getSt_no());
			
			//선택한 학번을 기준으로 삭제
			
			ss.delete(stNO);
		}
		
	}

}
