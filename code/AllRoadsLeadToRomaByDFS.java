package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

public class AllRoadsLeadToRomaByDFS {
	private static int N;		//��������
	private static int K;		//����֮���·;��
	private static boolean visited[];			//�ж��Ƿ��߹�
	private static ArrayList<Integer> path = new ArrayList<Integer>();			//�����洢��ǰ�ҵ���·��
	private static ArrayList<Integer> result;		//���������̵�·��
	private static int minTime = Integer.MAX_VALUE;			//���ʱ��
	private static int maxTotalHap = 0;						//������Ҹ�ָ��
	private static int maxAverHap = 0;					//���ƽ���Ҹ�ָ��
	private static int minRoad = 0;
	private static String cityName[];
	private static int hapIndex[];
	private static String start;
	
	public static int findIn(String name){
		int index = 0;
		for(int i = 0;i < N;i++){
			if(cityName[i].equals(name)){
				index = i;
			}
		}
		return index;
	}
	
	public static void dfs(int[][] adj, int des, int from, int cur, int time){
		time += adj[from][cur];
		path.add(cur);
		visited[cur] = true;
		if(cur == des){
			if(time <= minTime){
				int total = 0, aver = 0;
				for(int i = 0;i < path.size();i++){
					total += hapIndex[path.get(i)];
				}
				aver = total / (path.size() - 1);
				if(time < minTime){
					minRoad = path.size();
					minTime = time;
					result = new ArrayList(path);
					maxTotalHap = total;
					maxAverHap = aver;
				}
				else{
					if(total > maxTotalHap || (total == maxTotalHap && aver > maxAverHap)){
						minRoad = path.size();
						minTime = time;
						result = new ArrayList(path);
						maxTotalHap = total;
						maxAverHap = aver;
					}
				}
			}
		}
		else{
			for(int i = 1;i < N;i++){
				if(!visited[i] && adj[cur][i] != 0){
					dfs(adj, des, cur, i, time);
				}
			}
		}
		path.remove(path.size() - 1);		//�������һ���ӽ����ģ�Ҳ����cur
		visited[cur] = false;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		K = in.nextInt();
		cityName = new String[N];
		hapIndex = new int[N];
		int adj[][] = new int[N][N];
		cityName[0] = in.next();			//cityName[0]����ʼ�ĳ���
		visited = new boolean[N];
		for(int i = 1;i < N;i++){
			cityName[i] = in.next();
			hapIndex[i] = in.nextInt();
		}
		for(int i = 0;i < K;i++){
			String K1 = in.next();
			String K2 = in.next();
			int time = in.nextInt();
			int k1 = findIn(K1);
			int k2 = findIn(K2);
			adj[k1][k2] = time;
			adj[k2][k1] = time;
		}
		dfs(adj, findIn("ROM"), 0, 0, 0);
		System.out.println(minRoad + " " + minTime + " " + maxTotalHap + " " + maxAverHap);
		for(int i = 0 ;i < result.size() - 1;i++){
			System.out.print(cityName[result.get(i)] + "->");
		}
		System.out.println(cityName[result.get(result.size() - 1)]);
	}
}
