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
		int[] costs = new int[n];		//ÿ���㵽�����cost
		int[] hpns = new int[n];		//ÿ���㵽�����happiness
		int[] steps = new int[n];		//ÿһ��·���Ľ������������ͷ���
		int[] routes = new int[n];		//����ÿ�����·������
		int[] parent = new int[n];		//ÿһ�����ĸ����,������¼·��������ʱ���ŵ�
		int[][] M = new int[n][n];		//���ڴ洢ͼ���ڽӾ���
		HashMap<String, Integer> index = new HashMap<String, Integer>();	//���ڼ�¼ÿ�����е��±�
		boolean[] visited = new boolean[n];		//����Ѿ����ʵĽ��
		int[] h = new int[n];		//ÿ�����е��Ҹ�ֵ
		String[] names = new String[n];				//ÿ�����е�����
		routes[0] = 1;			//������ʼ���·��ֻ��һ��
		parent[0] = -1;
		index.put(start, 0);
		names[0] = start;
		//��ʼ��
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
			M[s][e] = cost;				//����ͼ
			M[e][s] = cost;
		}
		//��ʼ������ԭ������ľ���
//		for(int i = 0;i < n;i++){					//����������ʼ��������ֻ�ǳ�ʼ������̾���
//			if(M[0][i] != 0)						//���������Ҹ�ֵ�����·���ȵȶ�û�г�ʼ�������Ǽٳ�ʼ��
//				costs[i] = M[0][i];
//		}
//		for(int i = 0;i < n;i++){				//���ʼ��
//			if(M[0][i] != 0){
//				costs[i] = M[0][i];
//				parent[i] = 0;
//				hpns[i] = h[i];
//				steps[i] = 1;
//				routes[i] = 1;
//			}
//		}
		/**
		 * Dijikstra�㷨����
		 */
		for(int t = 0;t < n;t++){				//��n�Σ���n���㵽ԭ������·��
			int v = -1;
			for(int i = 0;i < n;i++){			//�ҳ�һ����ԭ������ĵ�
				if(!visited[i] && ((v < 0) || (costs[i] < costs[v])))
					v = i;
			}
			visited[v] = true;				//�����Ѿ��ҵ����·���ĵ�ļ��ϵ���
//			if(v == end) break;				//������Ҫ��¼�ܵ����ʱ��·������������˲��������
			//���øõ��������������·��
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
						routes[i] = routes[v];			//��¼����㵽��������·������
					}
					else if(newCost == costs[i]){
						routes[i] = routes[i] + routes[v];			//ԭ����·������ + ˢ�µ������·��
						if(happiness > hpns[i] || (happiness == hpns[i] && step < steps[i])){
							costs[i] = newCost;
							hpns[i] = happiness;
							steps[i] = step;
							parent[i] = v;			//��¼·��
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
