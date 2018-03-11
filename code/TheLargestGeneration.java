package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TheLargestGeneration {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N;			//家庭成员总人数
		int M;			//有孩子的家庭成员人数
		N = in.nextInt();
		M = in.nextInt();
		int hNum[] = new int[M + 2];			//每个层级的人数
		ArrayList<Integer> []children = new ArrayList[N + 1]; 		//孩子的队列，其中其中下标0代表这个家庭成员所在的层级
		for(int i = 1;i < N + 1;i++){
			children[i] = new ArrayList<Integer>();
			children[i].add(0);		//初始层级是0
		}
		Arrays.fill(hNum, 0);					
		for(int i = 0;i < M;i++){	
			int index = in.nextInt();			//家庭成员的下标
			int num = in.nextInt();				//孩子数目
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
				children[child].set(0, oldDepth + 1);			//修改孩子结点所在层级
				hNum[oldDepth + 1]++;					//对应层级人数增加
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
