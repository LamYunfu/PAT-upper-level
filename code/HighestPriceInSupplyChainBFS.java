package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HighestPriceInSupplyChainBFS {
	private static int N;			//供应链中成员数目
	private static float P;			//供货商提供的初始价格
	private static float r;			//一次货物周转的货物上涨率
	private static int hPrice = 0;
	private static int numOfRetailers = 0;
	private static Node []nodes;
	public static class Node{
		public ArrayList<Integer> child = new ArrayList<Integer>();
		public int depth = 0;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		P = in.nextFloat();
		r = in.nextFloat();
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
		//层序遍历，队列
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(nodes[root]);
		while(!queue.isEmpty()){
			Node top = queue.poll();
			for(int i = 0;i < top.child.size();i++){
				Node addNode = nodes[top.child.get(i)];
				addNode.depth = top.depth + 1;
				queue.add(addNode);
			}
		}
		for(int i = 0;i < N;i++){
			if(nodes[i].depth > hPrice){
				hPrice = nodes[i].depth;
				numOfRetailers = 1;
			}
			else if(nodes[i].depth == hPrice){
				numOfRetailers++;
			}
		}
		System.out.println(String.format("%.2f", P * Math.pow(1 + r/100, hPrice))  + " " + numOfRetailers);
	}
}
