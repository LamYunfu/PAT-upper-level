package com.zju.algorithem.pat;

import java.util.Scanner;

public class TotalSaleOfASupplyChain {

	static int supplier[];
	static int level[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		double P = in.nextDouble();			//the origin price
		double r = in.nextDouble();			//the increment of price;
		supplier = new int[N];		//ÿ���̼ҵĹ�Ӧ��
		int numOfRetalier = 0;					//�����̵�����
		int retalier[] = new int[N];			//��ž����̵��±�
		int	need[] = new int[N];
		level = new int[N];
		supplier[0] = -1;					//0�Ź�Ӧ���Ǹ����
		for(int i = 0;i < N;i++){
			int num = in.nextInt();
			if(num == 0){
				retalier[numOfRetalier++] = i;
				need[i] = in.nextInt();
			}
			else{
				for(int j = 0;j < num;j++){
					int t = in.nextInt();
					supplier[t] = i;
				}
			}
		}
		double sales = 0;				//��Ӫҵ��
		//����ÿһ�������̵Ĳ㼶
		for(int i = 0;i < numOfRetalier;i++){
			int t = retalier[i];
			int depth = 0;
			while(supplier[t] != -1){
				t = supplier[t];
				depth++;
			}
			sales += need[retalier[i]] * (P * Math.pow(1 + r / 100, depth));
		}
		System.out.println(String.format("%.1f", sales));
	}

}
