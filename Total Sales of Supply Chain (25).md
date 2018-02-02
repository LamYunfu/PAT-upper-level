# Total Sales of Supply Chain (25)

### **题目描述**

```
A supply chain is a network of retailers（零售商）, distributors（经销商）, and suppliers（供应商）-- everyone involved in moving a product from supplier to customer.

Starting from one root supplier, everyone on the chain buys products from one's supplier in a price P and sell or distribute them in a price that is r% higher than P.  Only the retailers will face the customers.
It is assumed that each member in the supply chain has exactly one supplier except the root supplier, and there is no supply cycle.

Now given a supply chain, you are supposed to tell the total sales from all the retailers.
```

### **输入描述:**

```
Each input file contains one test case.  For each case, the first line contains three positive numbers: N (<=105), the total number of the members in the supply chain (and hence their ID's are numbered from 0 to N-1, and the root supplier's ID is 0); P, the unit price given by the root supplier; and r, the percentage rate of price increment for each distributor or retailer.  Then N lines follow, each describes a distributor or retailer in the following format:
Ki ID[1] ID[2] ... ID[Ki]
where in the i-th line, Ki is the total number of distributors or retailers who receive products from supplier i, and is then followed by the ID's of these distributors or retailers.  Kj being 0 means that the j-th member is a retailer, then instead the total amount of the product will be given after Kj.  All the numbers in a line are separated by a space.
```

### **输出描述:**

```
For each test case, print in one line the total sales we can expect from all the retailers, accurate up to 1 decimal place.  It is guaranteed that the number will not exceed 1010.
```

### **输入例子:**

* 总的商家	初始单价		涨价幅度
* 一共有多少个商家从他这里拿货	拿货的商家的ID
* 如果拿货商家是0,表示这个商家是一个零售商，后面一个数是他要拿货的总数

```
10 1.80 1.00			
3 2 3 5
1 9
1 4
1 7
0 7
2 6 1
1 8
0 9
0 4
0 3
```

### **输出例子:**

```
42.4
```
### 回溯法

```java
package com.zju.algorithem.pat;

import java.util.Scanner;

public class TotalSaleOfASupplyChain {

	static int supplier[];
	static int level[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		double P = in.nextDouble();			//the origin price
		double r = in.nextDouble();			//the increment of price;
		supplier = new int[N];		//每个商家的供应商
		int numOfRetalier = 0;					//经销商的数量
		int retalier[] = new int[N];			//存放经销商的下标
		int	need[] = new int[N];
		level = new int[N];
		supplier[0] = -1;					//0号供应商是根结点
		for(int i = 0;i < N;i++){
			int num = in.nextInt();
			if(num == 0){
				retalier[numOfRetalier++] = i;
				need[i] = in.nextInt();
			}
			else{
				for(int j = 0;j < num;j++){
					int t = in.nextInt();
					supplier[t] = i;
				}
			}
		}
		double sales = 0;				//总营业额
		//计算每一个经销商的层级
		for(int i = 0;i < numOfRetalier;i++){
			int t = retalier[i];
			int depth = 0;
			while(supplier[t] != -1){
				t = supplier[t];
				depth++;
			}
			sales += need[retalier[i]] * (P * Math.pow(1 + r / 100, depth));
		}
		System.out.println(String.format("%.1f", sales));
	}

}

```

