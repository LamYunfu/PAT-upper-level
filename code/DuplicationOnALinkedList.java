package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicationOnALinkedList {

	static class Node{
		private int adress;
		private int value;
		private int nextAdd;
		private Node nextNode;
		
		public Node(int adress, int value, int nextAdd) {
			this.adress = adress;
			this.value = value;
			this.nextAdd = nextAdd;
		}
	}
	
	public static void Output(ArrayList <Node> s){
		for(int i = 0;i < s.size() - 1;i++){
			Node n = s.get(i);
			Node next = s.get(i + 1);
			System.out.println(String.format("%05d %d %05d", n.adress, n.value, next.adress));
		}
		Node n = s.get(s.size() - 1);
		System.out.println(String.format("%05d %d -1", n.adress, n.value));
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int start = in.nextInt();
		int N = in.nextInt();
		Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		ArrayList<Integer> remain = new ArrayList<Integer>();
		ArrayList<Integer> remove = new ArrayList<Integer>();
		ArrayList<Node> remainNode = new ArrayList<Node>();
		ArrayList<Node> removeNode = new ArrayList<Node>();
		
		for(int i = 0;i < N;i++){
			int address = in.nextInt();
			int value = in.nextInt();
			int nextAdd = in.nextInt();
			nodeMap.put(address, new Node(address, value, nextAdd));
		}
		Node S = nodeMap.get(start);
		/**
		 * ´´½¨Á´±í
		 */
		while(S.nextAdd != -1){
			Node next = nodeMap.get(S.nextAdd);
			S.nextNode = next;
			S = next;
		}
		Node s = nodeMap.get(start);
		do{
			int p = s.value;
			if(remain.contains(p) || remain.contains(-1 * p)){  
				remove.add(p);
				removeNode.add(s);
			}
			else{
				remain.add(p);
				remainNode.add(s);
			}
			s = s.nextNode;
		}while(s != null);
		Output(remainNode);
		Output(removeNode);
	}

}
