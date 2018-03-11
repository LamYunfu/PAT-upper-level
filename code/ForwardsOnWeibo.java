package com.zju.algorithem.pat;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ForwardsOnWeibo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();			//the number of users
		int L = in.nextInt();			//the maxinum layer
		int net[][] = new int[N + 1][N + 1];
		for(int i = 1;i <= N;i++){		//������ͼ,��ͼ��ֵΪ1�Ĵ�����Բ������Դ������ķ�˿
			int num = in.nextInt();
			for(int j = 0;j < num;j++){
				int k = in.nextInt();
				net[k][i] = 1;
			}
		}
		int searchNum = in.nextInt();
		int search[] = new int[searchNum];		//Ҫ��ѯ�Ľ��ı��
		int result[] = new int[N + 1];		//���յĽ��
		Queue<Integer> index = new LinkedList<Integer>();		//��Ž���±�
		Queue<Integer> depth = new LinkedList<Integer>();		//��Ŷ�Ӧ�����
		for(int i = 0;i < searchNum;i++){
			search[i] = in.nextInt();
		}
		//����bfs�������������������ͼ��Ӧ��i���ܵ������С��L������
		for(int i = 0;i < searchNum;i++){
			int num = search[i];
			boolean visited[] = new boolean[N + 1];
			index.add(num);
			depth.add(0);
			result[num] = -1;
			visited[num] = true;
			while(!index.isEmpty()){
				int node = index.poll();
				int dp = depth.poll();
				result[num]++;			//���������
				if(dp < L){				//��������еĽ������С��L
					for(int j = 1;j <= N;j++){
						if(net[node][j] == 1 && !visited[j]){
							index.add(j);
							depth.add(dp + 1);
							visited[j] = true;
						}
					}
				}
			}
		}
		for(int i = 0;i < searchNum;i++){
			System.out.println(result[search[i]]);
		}
	}

}
