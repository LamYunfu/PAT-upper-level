package com.zju.algorithem.pat;

import java.util.HashMap;
import java.util.Scanner;

//6 7 HZH
//ROM 100
//PKN 40
//GDN 55
//PRS 95
//BLN 80
//ROM GDN 1
//BLN ROM 1
//HZH PKN 1
//PRS ROM 2
//BLN HZH 2
//PKN GDN 1
//HZH PRS 1
public class AllRoadsLeadToRomaDijikstra {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();	//numbers of city
		int k = in.nextInt();	//numbers of route
		String start = in.next();		//starting city
		int[] costs = new int[n];		//每个点到顶点的cost
		int[] hpns = new int[n];		//每个点到顶点的happiness
		int[] steps = new int[n];		//每一条路径的结点数，不包括头结点
		int[] routes = new int[n];		//到达每个点的路径数量
		int[] parent = new int[n];		//每一个结点的父结点,用来记录路径，但是时倒着的
		int[][] M = new int[n][n];		//用于存储图的邻接矩阵
		HashMap<String, Integer> index = new HashMap<String, Integer>();	//用于记录每个城市的下标
		boolean[] visited = new boolean[n];		//标记已经访问的结点
		int[] h = new int[n];		//每个城市的幸福值
		String[] names = new String[n];				//每个城市的名字
		routes[0] = 1;			//到达起始点的路径只有一条
		parent[0] = -1;
		index.put(start, 0);
		names[0] = start;
		//初始化
		for(int i = 1;i < n;i++){
			costs[i] = Integer.MAX_VALUE;
			hpns[i] = Integer.MAX_VALUE;
			names[i] = in.next();
			h[i] = in.nextInt();
			index.put(names[i], i);
		}
		int end = index.get("ROM");
		
		for(int i = 0;i < k;i++){
			int s = index.get(in.next());
			int e = index.get(in.next());
			int cost = in.nextInt();
			M[s][e] = cost;				//无向图
			M[e][s] = cost;
		}
		//初始化距离原点最近的距离
//		for(int i = 0;i < n;i++){					//不能这样初始化，这样只是初始化了最短距离
//			if(M[0][i] != 0)						//其它包括幸福值，最短路径等等都没有初始化，这是假初始化
//				costs[i] = M[0][i];
//		}
//		for(int i = 0;i < n;i++){				//真初始化
//			if(M[0][i] != 0){
//				costs[i] = M[0][i];
//				parent[i] = 0;
//				hpns[i] = h[i];
//				steps[i] = 1;
//				routes[i] = 1;
//			}
//		}
		/**
		 * Dijikstra算法部分
		 */
		for(int t = 0;t < n;t++){				//找n次，找n个点到原点的最短路径
			int v = -1;
			for(int i = 0;i < n;i++){			//找出一个离原点最近的点
				if(!visited[i] && ((v < 0) || (costs[i] < costs[v])))
					v = i;
			}
			visited[v] = true;				//加入已经找到最短路径的点的集合当中
//			if(v == end) break;				//由于需要记录总的最短时间路径的条数，因此不能有这句
			//利用该点更新其它点的最短路径
			for(int i = 0;i < n;i++){
				if(!visited[i] && M[v][i] != 0){
					int newCost = costs[v] + M[v][i];
					int happiness = hpns[v] + h[i];
					int step = steps[v] + 1;
					if(newCost < costs[i]){
						costs[i] = newCost;
						hpns[i] = happiness;
						steps[i] = step;
						parent[i] = v;
						routes[i] = routes[v];			//记录从起点到这里的最短路径条数
					}
					else if(newCost == costs[i]){
						routes[i] = routes[i] + routes[v];			//原来的路径条数 + 刷新点带来的路径
						if(happiness > hpns[i] || (happiness == hpns[i] && step < steps[i])){
							costs[i] = newCost;
							hpns[i] = happiness;
							steps[i] = step;
							parent[i] = v;			//记录路径
						}
					}
				}
			}
		}
		int avg = hpns[end] / steps[end];
		System.out.println(routes[end]+" "+costs[end]+" "+hpns[end]+" "+avg);
		int j = steps[end];
		String[] path = new String[j + 1];
		for(int i = j ;i >= 0;i--){
			path[i] = names[end];
			end = parent[end];
		}
		for(int i = 0;i < path.length - 1;i++){
			System.out.print(path[i] + "->");
		}
		System.out.println(path[path.length - 1]);
	}
}
