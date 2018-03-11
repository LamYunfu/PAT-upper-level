package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

//简单粗暴的方法
public class HighestPriceInSupplyChain {
	private static int N;			//供应链中成员数目
	private static float P;			//供货商提供的初始价格
	private static float r;			//一次货物周转的货物上涨率
	private static int hPrice = 0;
	private static int numOfRetailers = 0;
	private static int[] parent;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		P = in.nextFloat();
		r = in.nextFloat();
		parent = new int[N];
		for(int i = 0;i < N;i++){
			int cur = in.nextInt();
			parent[i] = cur;
		}
		for(int i = 0;i < N;i++){			//对所有的结点进行溯源求深度的操作
			int cur = i;
			int depth = 0;
			while(cur != -1){
				cur = parent[cur];
				depth++;
			}
			if(depth > hPrice){
				hPrice = depth;
				numOfRetailers = 1;
			}
			else if(depth == hPrice){
				numOfRetailers++;
			}
		}
		System.out.println(String.format("%.2f", P * Math.pow(1 + r/100, hPrice - 1))  + " " + numOfRetailers);
	}

}
