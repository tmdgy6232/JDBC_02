package com.biz.jdbc.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.jdbc.model.ScoreVO;
import com.biz.jdbc.service.ScoreService;
import com.biz.jdbc.service.ScoreServiceImp_01;

public class ScoreEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		ScoreService ss = new ScoreServiceImp_01();
		List<ScoreVO> sList = ss.selectAll();
		
		while(true) {
			System.out.print("SEQ");
			String strSeq = scan.nextLine();
			long longSeq = Long.valueOf(strSeq);
			
			ScoreVO sVO = ss.fineById(longSeq);
			
					
		}
				
	}

}
