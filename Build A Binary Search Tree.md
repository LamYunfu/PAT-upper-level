# Build A Binary Search Tree

### **题目描述**

```
A Binary Search Tree (BST) is recursively defined as a binary tree which has the following properties:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees. 

Given the structure of a binary tree and a sequence of distinct integer keys, there is only one way to fill these keys into the tree so that the resulting tree satisfies the definition of a BST.  You are supposed to output the level order traversal sequence of that tree.  The sample is illustrated by Figure 1 and 2.

给定二叉搜索树的结构和序列，把序列放到唯一的结点，最后层序遍历输出树

```

### **输入描述:**

```
Each input file contains one test case.  For each case, the first line gives a positive integer N (<=100) which is the total number of nodes in the tree.  The next N lines each contains the left and the right children of a node in the format "left_index right_index", provided that the nodes are numbered from 0 to N-1, and 0 is always the root.  If one child is missing, then -1 will represent the NULL child pointer.  Finally N distinct integer keys are given in the last line.
```

### **输出描述:**

```
For each test case, print in one line the level order traversal sequence of that tree.  All the numbers must be separated by a space, with no extra space at the end of the line.

```

![805592_1434173853368_1099A (1)](C:\Users\蓝云甫\Desktop\材料\pat甲级算法\图片\805592_1434173853368_1099A (1).jpg)

### 输入例子:**

```
9
1 6
2 3
-1 -1
-1 4
5 -1
-1 -1
7 -1
-1 8
-1 -1
73 45 11 58 82 25 67 38 42
```

### **输出例子:**

```
58 25 82 11 38 67 45 73 42
```
### 解题思路

* 二叉搜索树的中序遍历就是树中数据从小到大的有序排列
* 二叉排序树有一个特点，就是中序遍历时，能够得到递增序列。利用这个性质，在对该结构二叉树进行中序遍历，同时对关键字进行赋值。然后就层序输出该树就好。

```java
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

```

