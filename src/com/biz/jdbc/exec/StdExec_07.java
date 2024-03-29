package com.biz.jdbc.exec;

import java.util.Scanner;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 키보드로
		 * 학생이름, 학년, 학과를 입력받고
		 * 데이터를 추가
		 */
		
		Scanner scan = new Scanner(System.in);
		StdService ss = new StdServiceImp_01();
		
		
		System.out.print("학번 >>");
		String strNo = scan.nextLine();
				
		System.out.print("이름 >>");
		String strName = scan.nextLine();
		
		System.out.print("학년 >>");
		String strGrade = scan.nextLine();
		int intGrade = Integer.valueOf(strGrade);
		
		System.out.print("학과 >>");
		String strDept = scan.nextLine();
		
		StudentVO vo = new StudentVO();
		
		vo.setSt_no(strNo);
		vo.setSt_grade(intGrade);
		vo.setSt_dept_name(strDept);
		vo.setSt_name(strName);
		
		ss.insert(vo);
	}
	

}
