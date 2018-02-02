# Forwards On Weibo

### **题目描述**

```
Weibo is known as the Chinese version of Twitter.  One user on Weibo may have many followers, and may follow many other users as well.  Hence a social network is formed with followers relations.  When a user makes a post on Weibo, all his/her followers can view and forward his/her post, which can then be forwarded again by their followers.  Now given a social network, you are supposed to calculate the maximum potential amount of forwards for any specific user, assuming that only L levels of indirect followers are counted.
```

其实就是求有向图相应点i所能到达的距离小于L的点的数量。

算法时间要求相当宽3000ms，最简单的想法是按点为起点bfs探测树，任何follow距离设为1，记录深度到L时的点的数量，并输出。对N个点每个点探测1次，极限代价N*N*L。考虑到数字的规模，这个方法是可行的。通过一些具体的手法，可以使时间开销比较小，代码采用了这种方法。由于K没有限定，可能很大，所以干脆先把所有的N个点都求出结果，暂存，直接输出，当然用缓存会更好点。

### **输入描述:**

```
Each input file contains one test case.  For each case, the first line contains 2 positive integers: N (<=1000), the number of users; and L (<=6), the number of levels of indirect followers that are counted.  Hence it is assumed that all the users are numbered from 1 to N.  Then N lines follow, each in the format:

M[i] user_list[i]

where M[i] (<=100) is the total number of people that user[i] follows; and user_list[i] is a list of the M[i] users that are followed by user[i].  It is guaranteed that no one can follow oneself.  All the numbers are separated by a space.

Then finally a positive K is given, followed by K UserID's for query.
```

### **输出描述:**

```
For each UserID, you are supposed to print in one line the maximum potential amount of forwards this user can triger, assuming that everyone who can view the initial post will forward it once, and that only L levels of indirect followers are counted.
```

### **输入例子:**

```
7 3			
3 2 3 4
0
2 5 6
2 3 1
2 3 4
1 4
1 5
2 2 6
```

* 输入结点数、最大距离
* 结点相邻结点数 相邻结点编号
* 查询结点数 查询结点编号

### **输出例子:**

```
4
5
```