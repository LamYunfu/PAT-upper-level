package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

public class PublicBikeManagement {
	private static int Cmax;
	private static int N;
	private static int Sp;
	private static int M;
	private static ArrayList<Integer> path = new ArrayList<Integer>();		//暂存当前路径，退层的时候移除退层的那个点
	public static ArrayList<Integer> result;								//暂存已经得到的一条最短路径
	private static int minTime = Integer.MAX_VALUE;							//暂存路径的行使时间
	private static int send;
	private static int collect;
	private static boolean[] visited;
	private static int []C;
	/**
	 * 
	 * @param from			前一个结点，用于在后面定位路径
	 * @param cur			当前深入的结点
	 * @param des			目的地，也就是目标结点
	 * @param adj			图的邻接矩阵
	 * @param time			当前寻找这条路径的累计时间
	 */
	public static void dfs(int from, int cur, int des, int [][]adj,int time){		//time是当前这条路径目前累计的行走时间
		visited[cur] = true;
		path.add(cur);
		time += adj[from][cur];
		if(cur == des){
			/**
			 * 遍历在ArrayList中存的这条路径
			 * 计算当前这条路的路程长度
			 * 计算需要从老家带出来几辆自行车
			 * 计算需要带回老家几辆自行车
			 */
			int s = 0, c = 0;
			for(int i = 1 ;i < path.size(); i++){		//注意要跳过起点开始
				int j = path.get(i);
				if(C[j] > Cmax / 2 ){
					c += (C[j] - Cmax / 2);
				}
				else if(C[j] < Cmax / 2){
					if(c > (Cmax / 2) - C[j]){
						c -= (Cmax / 2 - C[j]);
					}
					else{
						s += ((Cmax / 2) - C[j] - c);
						c = 0;
					}
				}
			}
			if(time == minTime){
				if(s < send || (s == send && c < collect)){
					minTime = time;
					result = new ArrayList<Integer>(path);
					send = s;
					collect = c;
				}
			}
			else if(time < minTime){
				minTime = time;
				result = new ArrayList<Integer>(path);
				send = s;
				collect = c;
			}
		}
		else{		//深度遍历
			for(int i = 0;i < N;i++){
				if(!visited[i] && adj[cur][i] != 0){
					dfs(cur, i, des, adj, time);
				}
			}
		}
		//退层操作
		path.remove(path.size() - 1);
		visited[cur] = false;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Cmax = in.nextInt();	//每个站点最大容量
		N = in.nextInt();	//总站点数
		N = N + 1;
		Sp = in.nextInt();	//问题站点的下标
		M = in.nextInt();	//路的条数
		C = new int[N];
		int [][]adj = new int[N][N];
		visited = new boolean[N];
		for(int i = 1;i < N;i++){
			C[i] = in.nextInt();
		}
		int Si, Sj, Tij;
		for(int i = 0 ;i < M; i++){
			Si = in.nextInt();
			Sj = in.nextInt();
			Tij = in.nextInt();
			adj[Si][Sj] = Tij;
			adj[Sj][Si] = Tij;
		}
		dfs(0, 0, Sp, adj, 0);
		System.out.print(send+" ");
		for(int i = 0;i<result.size()-1;i++)
		System.out.print(result.get(i)+"->");
		System.out.print(result.get(result.size()-1)+" ");
		System.out.println(collect);
	}
}
