package com.zju.algorithem.pat;

import java.util.Scanner;

public class ConsecutiveFactors {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int minStart = N;
		int maxLength = 0;
		for(int start = 2; start < Math.sqrt(N); start++){
			if(N % start == 0){
				int tempN = N;
				int tempS = start;
				int length = 0;
				while(tempN % tempS == 0){
					tempN = tempN / tempS;
					tempS++;
					length++;
				}
				if(length > maxLength || (length == maxLength && start < minStart)){
					maxLength = length;
					minStart = start;
				}
			}
		}
		if(maxLength == 0){				//如果因子只有1和本身，输出本身
			System.out.print(1 + "\n" + N);
		}
		else{
			System.out.println(maxLength);
			for(int i = 0;i < maxLength - 1;i++){
				System.out.print(minStart + "*");
				minStart++;
			}
			System.out.print(minStart);
		}
	}

}
