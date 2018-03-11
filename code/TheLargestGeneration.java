package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TheLargestGeneration {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N;			//��ͥ��Ա������
		int M;			//�к��ӵļ�ͥ��Ա����
		N = in.nextInt();
		M = in.nextInt();
		int hNum[] = new int[M + 2];			//ÿ���㼶������
		ArrayList<Integer> []children = new ArrayList[N + 1]; 		//���ӵĶ��У����������±�0���������ͥ��Ա���ڵĲ㼶
		for(int i = 1;i < N + 1;i++){
			children[i] = new ArrayList<Integer>();
			children[i].add(0);		//��ʼ�㼶��0
		}
		Arrays.fill(hNum, 0);					
		for(int i = 0;i < M;i++){	
			int index = in.nextInt();			//��ͥ��Ա���±�
			int num = in.nextInt();				//������Ŀ
			for(int j = 0;j < num;j++){
				int child = in.nextInt();
				children[index].add(child);
			}
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		children[1].set(0, 1);
		hNum[1] = 1;
		queue.add(01);
		while(!queue.isEmpty()){
			int top = queue.poll();
			int oldDepth = children[top].get(0);
			for(int i = 1;i < children[top].size();i++){
				int child = children[top].get(i);
				children[child].set(0, oldDepth + 1);			//�޸ĺ��ӽ�����ڲ㼶
				hNum[oldDepth + 1]++;					//��Ӧ�㼶��������
				queue.add(child);
			}
		}
		int maxHierarchy = 1;
		int maxNum = 0;
		for(int i = 1;i <= M + 1;i++){
			if(hNum[i] > maxNum){
				maxNum = hNum[i];
				maxHierarchy = i;
			}
		}
		System.out.println(maxNum + " " + maxHierarchy);
	}
}
