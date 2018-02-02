# Consecutive numbers

### **题目描述**

```
Among all the factors of a positive integer N, there may exist several consecutive numbers.  For example, 630 can be factored as 3*5*6*7, where 5, 6, and 7 are the three consecutive numbers.  Now given any positive N, you are supposed to find the maximum number of consecutive factors, and list the smallest sequence of the consecutive factors.
```

### **输入描述:**

```
Each input file contains one test case, which gives the integer N (131).
```

### **输出描述:**

```
For each test case, print in the first line the maximum number of consecutive factors.  Then in the second line, print the smallest sequence of the consecutive factors in the format "factor[1]*factor[2]*...*factor[k]", where the factors are listed in increasing order, and 1 is NOT included.
```

### **输入例子:**

```
630
```

### **输出例子:**

```
3
5*6*7
```

### 思路

* 从2开始到sqrt(N),遍历每一个数，如果N刚好能够整除这个数start，那就从start开始，通过while循环逐个检查它后面的数，如果能够整除就继续自加扫描检查。同时记录好连续串的长度。
* 最后把得到的连续串长度和已经得到的最长的长度进行比较，如果这个长度比已经得到的最长长度更长，那就修改这个最长长度和最小起始位置。如果长度相等，那就比较最小起始位置，保留那个更小的结果。

### 结果代码

```java
package com.zju.algorithem.pat;

import java.util.Scanner;

public class ConsecutiveFactors {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int minStart = N;
		int maxLength = 0;
		for(int start = 2; start < Math.sqrt(N); start++){
			if(N % start == 0){
				int tempN = N;
				int tempS = start;
				int length = 0;
				while(tempN % tempS == 0){
					tempN = tempN / tempS;
					tempS++;
					length++;
				}
				if(length > maxLength || (length == maxLength && start < minStart)){
					maxLength = length;
					minStart = start;
				}
			}
		}
		if(maxLength == 0){				//如果因子只有1和本身，输出本身
			System.out.print(1 + "\n" + N);
		}
		else{
			System.out.println(maxLength);
			for(int i = 0;i < maxLength - 1;i++){
				System.out.print(minStart + "*");
				minStart++;
			}
			System.out.print(minStart);
		}
	}

}

```

