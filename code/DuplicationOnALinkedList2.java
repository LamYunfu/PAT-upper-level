package com.zju.algorithem.pat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicationOnALinkedList2 {

	static class Node{
		private int adress;
		private int value;
		private int nextAdd;
		private Node nextNode;
		
		public Node(){}
		public Node(int adress, int value, int nextAdd) {
			this.adress = adress;
			this.value = value;
			this.nextAdd = nextAdd;
		}
	}
	
	public static void Output(Node s){
		Node temp = s.nextNode;
		while(temp.nextNode != null){
			System.out.println(String.format("%05d %d %05d", temp.adress, temp.value, temp.nextNode.adress));
			temp = temp.nextNode;
		}
		System.out.println(String.format("%05d %d -1", temp.adress, temp.value));
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int start = in.nextInt();
		int N = in.nextInt();
		Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		ArrayList<Integer> remain = new ArrayList<Integer>();
		
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
		Node faNode = new Node();
		Node foNode = new Node();
		Node remainStart = faNode;
		Node removeStart = foNode;
		do{
			int p = Math.abs(s.value);
			if(remain.contains(p)){  
				foNode.nextNode = s;
				foNode = s;
			}
			else{
				remain.add(p);
				faNode.nextNode = s;
				faNode = s;
			}
			s = s.nextNode;
		}while(s != null);
		faNode.nextNode = null;
		foNode.nextNode = null;
		Output(remainStart);
		Output(removeStart);
	}
}
