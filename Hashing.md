# Hashing

### **题目描述**

```
The task of this problem is simple: insert a sequence of distinct positive integers into a hash table, and output the positions of the input numbers.  The hash function is defined to be "H(key) = key % TSize" where TSize is the maximum size of the hash table.  Quadratic probing (with positive increments only) is used to solve the collisions.
Note that the table size is better to be prime.  If the maximum size given by the user is not prime, you must re-define the table size to be the smallest prime number which is larger than the size given by the user.
Quadratic probing  			二次探测
prime  		 素数
```

### **输入描述:**

```
Each input file contains one test case.  For each case, the first line contains two positive numbers: MSize (<=104) and N (<=MSize) which are the user-defined table size and the number of input numbers, respectively.  Then N distinct positive integers are given in the next line.  All the numbers in a line are separated by a space.
```

### **输出描述:**

```
For each test case, print the corresponding positions (index starts from 0) of the input numbers in one line.  All the numbers in a line are separated by a space, and there must be no extra space at the end of the line.  In case it is impossible to insert the number, print "-" instead.
```

### **输入例子:**

```
4 4
10 6 4 15
```

### **输出例子:**

```
0 1 4 -
```


### 结果

哈希表的大小应该为素数

二次探索法

```java
package com.zju.algorithem.pat;

import java.util.Scanner;

public class Hashing {
	public static int changeToPrime(int initialSize){
		if(initialSize <= 2)	return 2;
		int prime = initialSize;
		while(!isPrime(prime)) prime++;
		return prime;
	}

	private static boolean isPrime(int prime) {
		int end = (int) Math.sqrt(prime);
		for(int i = 2;i <= end; i++){
			if(prime % i == 0){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int MSize = in.nextInt();	//user_defined table size
		int N = in.nextInt();		//the numbers of input numbers
		//表的大小需要是素数
		int newSize = changeToPrime(MSize);		//如果给的表的大小不是素数，则使得表的大小变成素数
		int arr[] = new int[N];
		for(int i = 0; i < N;i++){
			arr[i] = in.nextInt();
		}
		int table[] = new int[newSize];
		for(int i = 0;i < N;i++){
			int num = 1;				//二次探索的因子
			int index = arr[i] % newSize;
			while(table[index] != 0 && num < newSize){
				index = (arr[i] + num * num) % newSize;
				num++;
			}
			if(table[index] == 0){
				table[index] = arr[i];
				System.out.print(index);
			}
			else{
				System.out.print("-");
			}
			if(i != N-1)
				System.out.print(" ");
		}
	}
}
```

