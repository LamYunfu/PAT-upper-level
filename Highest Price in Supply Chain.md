# Highest Price in Supply Chain 

### 题目描述

```
A supply chain is a network of retailers（零售商）, distributors（经销商）, and suppliers（供应商）-- everyone involved in moving a product from supplier to customer.

Starting from one root supplier, everyone on the chain buys products from one's supplier in a price P and sell or distribute them in a price that is r% higher than P.  
It is assumed that each member in the supply chain has exactly one supplier except the root supplier, and there is no supply cycle.

Now given a supply chain, you are supposed to tell the highest price we can expect from some retailers.
```

* 求从零售商哪里能够拿到的最高的价格
* 很明显这是一个树的深度优先遍历的问题

### 输入描述

```
Each input file contains one test case.  For each case, The first line contains three positive numbers: N (<=105), the total number of the members in the supply chain (and hence they are numbered from 0 to N-1); P, the price given by the root supplier; and r, the percentage rate of price increment for each distributor or retailer.  Then the next line contains N numbers, each number Si is the index of the supplier for the i-th member.  Sroot for the root supplier is defined to be -1.  All the numbers in a line are separated by a space.
```

* 总的供应商数 出厂价 两个供应商之间价格增加的百分比
* 第i个商户对应的供应商，出厂商的供应商故作 -1



### 输出描述

```
For each test case, print in one line the highest price we can expect from some retailers, accurate up to 2 decimal places, and the number of retailers that sell at the highest price.  There must be one space between the two numbers.  It is guaranteed that the price will not exceed 1010.
```

能得到的最高单价（精确到小数点后面两位）， 卖最高售价的零售商的数目

#### 输入例子

```
9 1.80 1.00
1 5 4 4 -1 4 5 3 6
```

#### 输出例子

```
1.85 2
```

## 解题方法

#### 简单粗暴倒溯法

```java
package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Scanner;

//简单粗暴的方法
public class HighestPriceInSupplyChain {
	private static int N;			//供应链中成员数目
	private static float P;			//供货商提供的初始价格
	private static float r;			//一次货物周转的货物上涨率
	private static int hPrice = 0;
	private static int numOfRetailers = 0;
	private static int[] parent;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		P = in.nextFloat();
		r = in.nextFloat();
		parent = new int[N];
		for(int i = 0;i < N;i++){
			int cur = in.nextInt();
			parent[i] = cur;
		}
		for(int i = 0;i < N;i++){			//对所有的结点进行溯源求深度的操作
			int cur = i;
			int depth = 0;
			while(cur != -1){
				cur = parent[cur];
				depth++;
			}
			if(depth > hPrice){
				hPrice = depth;
				numOfRetailers = 1;
			}
			else if(depth == hPrice){
				numOfRetailers++;
			}
		}
		System.out.println(String.format("%.2f", P * Math.pow(1 + r/100, hPrice - 1))  + " " + numOfRetailers);
	}

}

```

#### 中规中矩深度优先遍历法

```java
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
				numOfRetailers = 0;
			}
			if(depth == hPrice){
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
```

#### 中规中矩层序遍历法

```java
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

```

