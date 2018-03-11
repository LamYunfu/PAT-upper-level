package com.zju.algorithem.pat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BuildABinarySearchTree {
	static int tag = 0;
	static class Node{
		private  int value;
		private Node leftChild;
		private Node rightChild;
	}
	
	static void middleOrder(Node p, int[] arr){
		if(p != null){
			middleOrder(p.leftChild, arr);
			p.value = arr[tag++];
			middleOrder(p.rightChild, arr);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Node arr[] = new Node[N];
		int origin[] = new int[N];
		for(int i = 0;i < N;i++){
			arr[i] = new Node();
		}
		for(int i = 0;i < N;i++){
			int l,r;
			l = in.nextInt();
			r = in.nextInt();
			if(l != -1)
				arr[i].leftChild = arr[l];
			if(r != -1)
				arr[i].rightChild = arr[r];
		}
		for(int i = 0;i < N;i++)
			origin[i] = in.nextInt();
		Arrays.sort(origin); 				//从小到大排序
		//利用中序遍历把值放到对应的位置
		middleOrder(arr[0], origin);
		//层序遍历输出
		Queue<Node> result = new LinkedList<Node>();
		result.add(arr[0]);
		while(!result.isEmpty()){
			Node n = result.poll();
			if(n.leftChild != null) result.add(n.leftChild);
			if(n.rightChild != null) result.add(n.rightChild);
			if(result.isEmpty()) System.out.print(n.value);
			else{
				System.out.print(n.value + " ");
			}
		}
	}

}
