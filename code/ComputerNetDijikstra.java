package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

public class ComputerNetDijikstra {
	static class Path{
		public ArrayList<Integer> route = new ArrayList<Integer>();
		public void output(){
			for(int i = 0;i < route.size() - 1;i++){
				System.out.print(route.get(i) + "->");
			}
			System.out.println(route.get(route.size() - 1));
		}
	}
	static int map[][] = {
			{999, 1, 5, 999, 999, 999, 999, 999, 999},
			{1, 999, 3, 7, 5, 999, 999, 999, 999},
			{5, 3, 999, 999, 1, 7, 999, 999, 999},
			{999, 7, 999, 999, 2, 999, 3, 999, 999},
			{999, 5, 1, 2, 999, 3, 6, 9, 999},
			{999, 999, 7, 999, 3, 999, 999, 5, 999},
			{999, 999, 999, 3, 6, 999, 999, 2, 7},
			{999, 999, 999, 999, 9, 5, 2, 999, 4},
			{999, 999, 999, 999, 999, 999, 7, 4, 999},						
	};
	
	public static void findShortestPath(int k) {
		boolean visited[] = new boolean[9];
		int[] costs = new int[9];					//记录从起始点到各个点的最短路径长度
		Path[] path = new Path[9];
		//初始化
		for(int i = 0;i < 9;i++){
			path[i] = new Path();
			path[i].route.add(k);
			if(map[k][i] != 999){
				costs[i] = map[k][i];
			}
			else{
				costs[i] = Integer.MAX_VALUE;
			}
		}
		//Dijikstra算法部分
		for(int i = 0;i < 9;i++){				//找8次每次找出距离原点最近的路径
			int v = -1;
			for(int j = 0;j < 9;j++){
				if(!visited[j] && ((v < 0) || costs[j] < costs[v])){
					v = j;
				}
			}
			visited[v] = true;
			path[v].route.add(v);
			//利用新加入的结点更新每个结点距离这个结点的最短路径
			for(int j = 0;j < 9;j++){
				if(!visited[j] && map[j][v] != 999){
					int newCost = costs[v] + map[j][v];
					if(newCost < costs[j]){
						costs[j] = newCost;
						copyPath(path[j].route, path[v].route);
					}
				}
			}
		}
		for(int i = 0;i < 9;i++){
			if(i != k){
				System.out.println("-------------------- 测试 ----------------------");
				System.out.println("从 " + k + " 到" + i + "的最短距离为：" + costs[i]);
				System.out.print("从 " + k + " 到" + i + "的最短路径为：");
				path[i].output();
			}
		}
	}

	private static void copyPath(ArrayList<Integer> route, ArrayList<Integer> o) {
		// TODO Auto-generated method stub
		route.clear();
		for(int i = 0;i < o.size();i++){
			route.add(o.get(i));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("请输入你想要计算的起始结点");
		int N = in.nextInt();
		findShortestPath(N);
	}
}
