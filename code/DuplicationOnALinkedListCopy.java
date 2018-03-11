package com.zju.algorithem.pat;

import java.util.Scanner;

public class DuplicationOnALinkedListCopy {
	static int next[] = new int[1000000];
	static int value[] = new int[1000000];
	static boolean mark[] = new boolean[10005];
	
	public static void Output(int h, int t){
		if(t >= 0){
			next[t] = -1;
			for(; h >= 0; h = next[h]){
				System.out.print(String.format("%05d %d ", h, value[h]));
				if(next[h] > 0){
					System.out.println(String.format("%05d", next[h]));
				}
				else 
					System.out.println("-1");
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		int head = in.nextInt();
		int N = in.nextInt();
		for(int i = 0;i < N;i++){
			int a = in.nextInt();			//��ַ
			int v = in.nextInt();			//���ֵ
			int n = in.nextInt();			//��һ�����ĵ�ַ
			value[a] = v;
			next[a] = n;	
		}
		int h1 = -1;					//�������������ͷ
		int h2 = -1;					//ȥ��������������ͷ
		int t1 = -1;
		int t2 = -1;
		
		for(;head >= 0; head = next[head]){
			int Value = (value[head] >= 0) ? value[head] : (-1 * value[head]);		//�Ƚ�����ֵת��Ϊ����
			if(mark[Value]){				//�����ֵ�Ѿ������ˣ�����뵽�Ƴ�������
				if(t2 < 0){				//���������δ����
					h2 = t2 = head;
				}
				else{
					next[t2] = head;
					t2 = head;
				}
			}
			else{				//���������
				mark[Value] = true;
				if(t1 < 0){
					h1 = t1 = head;
				}
				else{
					next[t1] = head;
					t1 = head;
				}
			}
		}
		/**
		 * �����������
		 */
		Output(h1, t1);
		Output(h2, t2);
	}

}
