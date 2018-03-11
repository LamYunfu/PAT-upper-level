package com.zju.algorithem.pat;

import java.util.Scanner;

public class Kuchiguse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		String flush = in.nextLine();					//接收\n
		String arr[] = new String[N];
		char c[][] = new char[N][];
		for(int i = 0;i < N;i++){
			arr[i] = in.nextLine();
			c[i] = arr[i].toCharArray();
		}
		int start = 0, end = c[0].length -1;		//相同字符串的开始和结束下标
		int p = end;
		for(int i = 1;i < N;i++){
			int nc = c[i].length - 1;
			while(nc >= 0 && p >= 0 && c[i][nc] == c[0][p]){
				nc--;
				p--;
			}
			if(p == end){
				start = end;
				break;
			}
			p++;
			if(p > start)
				start = p;
			p = end;
		}
		if(start == end){
			System.out.println("nai");
		}
		else{
			for(int i = start;i <= end;i++){
				System.out.print(c[0][i]);
			}
		}
	}
}
