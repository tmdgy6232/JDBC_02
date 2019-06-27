package com.biz.jdbc.exec;

import java.util.Scanner;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_10 {

	/*
	 * 키보드에서 학생정보를 입력받아서 (학번, 이름, 학년, 학과) 계속해서 insert를 수행하는 코드를 작성
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		StdService ss = new StdServiceImp_01();

		while (true) {
			System.out.println("================================");
			
			//stdService 인터페이스에 미처 선언하지 못한
			//makeStNo() 매서드를
			//StdServiceImp_01ffotmdp todtjdgkrh
			//사용을 하려고 한다.
			// 이럴때는 다소 복잡하지만 ss(stdservice 형)의 객체를
			// stdservbiceIMP_01로 cascading(형변환)을 실행해서
			//매서드를 호출해야한다.
			String strNo = ((StdServiceImp_01)ss).makeStNO();
			System.out.println(strNo + "학생 정보 등록");
			System.out.println("---------------------------");
			
			System.out.println("이름(종료:-E) >>");
			String strName = scan.nextLine();
			
			
			System.out.println("학년 >>");
			String strGrade = scan.nextLine();
			int intGrade = Integer.valueOf(strGrade);
			
			System.out.println("학과 >>");
			String strDept = scan.nextLine();
			
			StudentVO vo = new StudentVO();
			vo.setSt_no(strNo);
			vo.setSt_name(strName);
			vo.setSt_grade(intGrade);
			vo.setSt_dept_name(strDept);
			
			ss.insert(vo);
			
			
			
		}
	}
}
