# Duplication on A Linked List

### **题目描述**

```
Given a singly linked list L with integer keys, you are supposed to remove the nodes with duplicated absolute values of the keys.  That is, for each value K, only the first node of which the value or absolute value of its key equals K will be kept.  At the mean time, all the removed nodes must be kept in a separate list.  For example, given L being 21→-15→-15→-7→15, you must output 21→-15→-7, and the removed list -15→15.
```

### **输入描述:**

```
Each input file contains one test case.  For each case, the first line contains the address of the first node, and a positive N (<= 105) which is the total number of nodes.  The address of a node is a 5-digit nonnegative integer, and NULL is represented by -1.
Then N lines follow, each describes a node in the format:
Address Key Next
where Address is the position of the node, Key is an integer of which absolute value is no more than 104, and Next is the position of the next node.
```

### **输出描述:**

```
For each case, output the resulting linked list first, then the removed list.  Each node occupies a line, and is printed in the same format as in the input.
```

### **输入例子:**

```
00100 5
99999 -7 87654
23854 -15 00000
87654 15 -1
00000 -15 99999
00100 21 23854
```

### **输出例子:**

```
00100 21 23854
23854 -15 99999
99999 -7 -1
00000 -15 87654
87654 15 -1
```
### 大佬代码

```
PAT的链表题，表示链表的方法很诡异，类似的表示还有1052， 1074。也不难，本题给定一个存储整数的链表，只保存每种绝对值第一次出现的节点，同一个绝对值第二次及以后的出现都扔掉，保留的节点和扔掉的节点分别组成两个小链表。
链表存放整数的绝对值不超过10000，所以可以用bool数组或者bitmap保存某个绝对值在之前是否出现过。
不知道有没有极端情况，输入的链表就是个空的……
总之，我们可以先新建立两个表头h1, h2 和表尾t1, t2，初始都是空的，表示我们最后保留的链表和过滤掉的链表。链表的优势就是对每个节点可以单独折腾……
我们对原始链表里每个节点，可以通过我们那个bool数组判断这个绝对值是否出现过，如果出现过就接到第二个链表末尾，否则就放到第一个链表的末尾。接在链表末尾的操作注意和用指针差不多，注意是否是第一个表头节点，同时别忘更新最后一个元素的地址，因为每次都要接在最后一个元素后面……当然接在前面再翻转一次也是可以的。
最后输出也要注意链表为空的情况，还有注意最后一个位置的next是-1。
难度不大，就是每种细节的处理而已。
```

```c++
链接：https://www.nowcoder.com/questionTerminal/d7600bad163a4751bccebe5021a7d802
来源：牛客网

#include <cstdio>
#include <cstring>
#include <string>
using namespace std;
  
int Next[1000000];
int a[1000000];				//存放结点的值
bool mark[10005];			//判断这个结点是否出现过
  
int main() {
int n;
int head;
    for (scanf("%d%d",&head,&n);n;--n) {
        int x;
        scanf("%d",&x);
        scanf("%d%d",a + x, Next + x);
    }
    int h1 = -1, h2 = -1, t1 = -1, t2 = -1;
    for (; head >= 0; head = Next[head]) {
        int x = (a[head] >= 0)?a[head]:(-a[head]);		//先将每一个结点的值都转化成为正数用以判断
        if (mark[x]) {
            if (t2 < 0) {
                h2 = t2 = head;
            }
            else {
                t2 = Next[t2] = head;
            }
        }
        else {
            mark[x] = true;
            if (t1 < 0) {
                h1 = t1 = head;
            }
            else {
                t1 = Next[t1] = head;
            }
        }
    }
    if (t1 >= 0) {
        Next[t1] = -1;
        for (head = h1; head >= 0; head = Next[head]) {
            printf("%05d %d", head, a[head]);
            if (Next[head] >= 0) {
                printf(" %05d\n", Next[head]);
            }
            else {
          f      puts(" -1");
            }
        }
    }
    if (t2 >= 0) {
        Next[t2] = -1;
        for (head = h2; head >= 0; head = Next[head]) {
            printf("%05d %d", head, a[head]);
            if (Next[head] >= 0) {
                printf(" %05d\n", Next[head]);
            }
            else {
                puts(" -1");
            }
        }
    }
              
    return 0;
}
```

