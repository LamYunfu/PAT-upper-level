package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

public class HighestPriceInSupplyChainDFS {
	private static int N;			//��Ӧ���г�Ա��Ŀ
	private static float P;			//�������ṩ�ĳ�ʼ�۸�
	private static float r;			//һ�λ�����ת�Ļ���������
	private static int hPrice = 0;
	private static int numOfRetailers = 0;
	private static Node []nodes;
	public static class Node{
		public ArrayList<Integer> child = new ArrayList<Integer>();
	}
	
	public static void dfs(Node cur, int depth){
		if(cur.child.size() == 0){
			if(depth > hPrice){
				hPrice = depth;
				numOfRetailers = 1;
			}
			else if(depth == hPrice){
				numOfRetailers++;
			}
		}
		for(int i = 0;i < cur.child.size();i++){
			dfs(nodes[cur.child.get(i)], depth+1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		P = in.nextFloat();
		r = in.nextFloat();
		/**
		 * java�жԷǻ������ݳ�ʼ��ʱ������ʹ��new
		 * ��ʹ��new���������Ժ󣬴�ʱ���黹ֻ��һ����������
		 * ���ʹ�������е�Ԫ�أ����׳�nonePointerException
		 * ֻ���ٴ����µĶ��󣬲��ҰѶ���ֵ���������ã���������������ĳ�ʼ������
		 */
		nodes = new Node[N];
		for(int i = 0;i < N;i++){
			Node newNode = new Node();
			nodes[i] = newNode;
		}
		int root = 0;
		for(int i = 0;i < N;i++){
			int parent = in.nextInt();
			if(parent == -1) root = i;
			else nodes[parent].child.add(i);
		}
		dfs(nodes[root], 0);
		System.out.println(String.format("%.2f", P * Math.pow(1 + r/100, hPrice))  + " " + numOfRetailers);
	}

}
