package com.biz.jdbc.exec;

import java.util.Scanner;

import com.biz.jdbc.model.StudentVO;
import com.biz.jdbc.service.StdService;
import com.biz.jdbc.service.StdServiceImp_01;

public class StdExec_08 {

	/*
	 * 키보드에서 학번을 입력받고 ]해당하는 학번의 학생정보를 보여준 후 다시 키보드에서 주소를 입력받아서 학생정보를 업데이트 실행
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		StdService ss = new StdServiceImp_01();

		while (true) {

			System.out.print("학번 >>(종료 :-E)>>");
			String strNo = scan.nextLine();
			if (strNo.contentEquals("-E"))
				break;

			StudentVO vo = ss.findeByNum(strNo);

			if (vo == null) {
				System.out.println("학생정보 없음");
				continue;
			}

			System.out.println(vo);
			System.out.println("=================================");

			System.out.print("주소>>");
			String strAddr = scan.nextLine();
			if(strAddr.isEmpty()) continue;
			
			vo.setSt_addr(strAddr);
			ss.update(vo);

		}
	}
}
