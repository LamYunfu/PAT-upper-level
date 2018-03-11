package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

//�򵥴ֱ��ķ���
public class HighestPriceInSupplyChain {
	private static int N;			//��Ӧ���г�Ա��Ŀ
	private static float P;			//�������ṩ�ĳ�ʼ�۸�
	private static float r;			//һ�λ�����ת�Ļ���������
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
		for(int i = 0;i < N;i++){			//�����еĽ�������Դ����ȵĲ���
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
