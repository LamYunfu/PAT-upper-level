package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

public class HighestPriceInSupplyChainDFS {
	private static int N;			//供应链中成员数目
	private static float P;			//供货商提供的初始价格
	private static float r;			//一次货物周转的货物上涨率
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
		 * java中对非基本数据初始化时，必须使用new
		 * 在使用new创建数组以后，此时数组还只是一个引用数组
		 * 如果使用数组中的元素，会抛出nonePointerException
		 * 只有再创建新的对象，并且把对象赋值给数组引用，到这里才是真正的初始化结束
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
