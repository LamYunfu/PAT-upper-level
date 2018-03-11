package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

public class PublicBikeManagement {
	private static int Cmax;
	private static int N;
	private static int Sp;
	private static int M;
	private static ArrayList<Integer> path = new ArrayList<Integer>();		//�ݴ浱ǰ·�����˲��ʱ���Ƴ��˲���Ǹ���
	public static ArrayList<Integer> result;								//�ݴ��Ѿ��õ���һ�����·��
	private static int minTime = Integer.MAX_VALUE;							//�ݴ�·������ʹʱ��
	private static int send;
	private static int collect;
	private static boolean[] visited;
	private static int []C;
	/**
	 * 
	 * @param from			ǰһ����㣬�����ں��涨λ·��
	 * @param cur			��ǰ����Ľ��
	 * @param des			Ŀ�ĵأ�Ҳ����Ŀ����
	 * @param adj			ͼ���ڽӾ���
	 * @param time			��ǰѰ������·�����ۼ�ʱ��
	 */
	public static void dfs(int from, int cur, int des, int [][]adj,int time){		//time�ǵ�ǰ����·��Ŀǰ�ۼƵ�����ʱ��
		visited[cur] = true;
		path.add(cur);
		time += adj[from][cur];
		if(cur == des){
			/**
			 * ������ArrayList�д������·��
			 * ���㵱ǰ����·��·�̳���
			 * ������Ҫ���ϼҴ������������г�
			 * ������Ҫ�����ϼҼ������г�
			 */
			int s = 0, c = 0;
			for(int i = 1 ;i < path.size(); i++){		//ע��Ҫ������㿪ʼ
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
		else{		//��ȱ���
			for(int i = 0;i < N;i++){
				if(!visited[i] && adj[cur][i] != 0){
					dfs(cur, i, des, adj, time);
				}
			}
		}
		//�˲����
		path.remove(path.size() - 1);
		visited[cur] = false;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Cmax = in.nextInt();	//ÿ��վ���������
		N = in.nextInt();	//��վ����
		N = N + 1;
		Sp = in.nextInt();	//����վ����±�
		M = in.nextInt();	//·������
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
