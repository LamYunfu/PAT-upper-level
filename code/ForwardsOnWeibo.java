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
		for(int i = 1;i <= N;i++){		//创建地图,地图中值为1的代表可以博主可以传给他的粉丝
			int num = in.nextInt();
			for(int j = 0;j < num;j++){
				int k = in.nextInt();
				net[k][i] = 1;
			}
		}
		int searchNum = in.nextInt();
		int search[] = new int[searchNum];		//要查询的结点的编号
		int result[] = new int[N + 1];		//最终的结果
		Queue<Integer> index = new LinkedList<Integer>();		//存放结点下标
		Queue<Integer> depth = new LinkedList<Integer>();		//存放对应的深度
		for(int i = 0;i < searchNum;i++){
			search[i] = in.nextInt();
		}
		//利用bfs广度优先搜索来找有向图相应点i所能到达距离小于L的数量
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
				result[num]++;			//最后结果计数
				if(dp < L){				//如果出队列的结点的深度小于L
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
