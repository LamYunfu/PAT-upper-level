# Cars On Campus



### 题目描述**

```
Zhejiang University has 6 campuses and a lot of gates.  From each gate we can collect the in/out times and the plate numbers of the cars crossing the gate.  Now with all the information available, you are supposed to tell, at any specific time point, the number of cars parking on campus, and at the end of the day find the cars that have parked for the longest time period.
```

### **输入描述:**

```
Each input file contains one test case.  Each case starts with two positive integers N (<= 10000), the number of records, and K (<= 80000) the number of queries.  Then N lines follow, each gives a record in the format
plate_number hh:mm:ss status
where plate_number is a string of 7 English capital letters or 1-digit numbers; hh:mm:ss represents the time point in a day by hour:minute:second, with the earliest time being 00:00:00 and the latest 23:59:59; and status is either in or out.
Note that all times will be within a single day. Each "in" record is paired with the chronologically next record for the same car provided it is an "out" record. Any "in" records that are not paired with an "out" record are ignored, as are "out" records not paired with an "in" record. It is guaranteed that at least one car is well paired in the input, and no car is both "in" and "out" at the same moment. Times are recorded using a 24-hour clock.


Then K lines of queries follow, each gives a time point in the format hh:mm:ss.  Note: the queries are given in ascending order of the times.
```

### **输出描述:**

```
For each query, output in a line the total number of cars parking on campus.  The last line of output is supposed to give the plate number of the car that has parked for the longest time period, and the corresponding time length.  If such a car is not unique, then output all of their plate numbers in a line in alphabetical order, separated by a space.
```

### **输入例子:**

```
16 7
JH007BD 18:00:01 in
ZD00001 11:30:08 out
DB8888A 13:00:00 out
ZA3Q625 23:59:50 out
ZA133CH 10:23:00 in
ZD00001 04:09:59 in
JH007BD 05:09:59 in
ZA3Q625 11:42:01 out
JH007BD 05:10:33 in
ZA3Q625 06:30:50 in
JH007BD 12:23:42 out
ZA3Q625 23:55:00 in
JH007BD 12:24:23 out
ZA133CH 17:11:22 out
JH007BD 18:07:01 out
DB8888A 06:30:50 in
05:10:00
06:30:50
11:00:00
12:23:42
14:00:00
18:00:00
23:59:00
```

### **输出例子:**

```
1
4
5
2
1
0
1
JH007BD ZD00001 07:20:09
```

### 大佬代码

```c++
作者：牛客89号
链接：https://www.nowcoder.com/discuss/478?type=8
来源：牛客网

稍微有点麻烦的模拟，模拟停车场。每条记录是 时间 hh:mm:ss, 车牌号， in/out。 表示车的出入记录。查询一些时间点的车数，求停留时间最长的车（可能很多）。
关键问题是，每辆车的记录可能有问题，比如同一辆车可能有很多连续的in，或者很多连续的out。原则是一辆车很多连续的in只考虑最后一个in，然后只考虑它后面第一个out和它匹配。简单地说很多连续的in最后一个有效，很多连续的out第一个有效。
时间的处理，hh:mm:ss转变为秒数hh * 3600 + mm * 60 + ss, 因为数据都是一天的。我们把记录按照车牌号排序——因为输出停留最长时间的车也是按照车牌号字典顺序输出的。排好顺序后相同车牌的车在一起，再按时间顺序排序。这样同一辆车的记录按照时间顺序排好了。注意相同时间（应该没有）又in又out哪个先都无所谓，因为即使把它们匹配，停留的时间也是0。
在保证车牌照相同的前提下，对同一辆车，先循环找到目前连续的in中最后一个in——忽略它之前所有的out和in。
如果能找到第一个in，再继续找到第一个out,忽略它之前所有的in,其实这里根本没必要循环，因为之前的in是连续的最后一个，所以这个位置显然要么是out，要么是别的车牌，但是为了清晰我还是写了循环——应该不会执行的。
重点就是我写的两个函数givein, giveout，有一个找不到就可以直接退出计算下一辆车了。
然后对同一辆车，它的停留时间是累加的（out time - in time)，并且我们可以用一个大数组a，记录在某个时刻车出入的变化量，in时刻车变化量加1,out时刻车变量减1。
所有的车都处理完后，我们已经知道了哪些车停留的时间最长，并且按照我们处理的顺序，停留时间最长的那些车已经放在数组里了——这个顺序恰好是车牌照的字典序，因为我们是按照车牌照排序的。
还有一个问题就是每个时刻的车数，我们已经记录下来变化量，比如某时刻要加1（不一定是1，同一时刻可能很多车出入），某时刻可能要减1。我们从0到3600 ＊ 24 - 1即从00:00:00到23:59:59秒累加这些变化量就能得到某个时刻的真正车数——其实就一个循环a[i] += a[i - 1]即可。
然后要查询哪个时刻，直接输出那个时刻即可。

```

```c++
作者：牛客89号
链接：https://www.nowcoder.com/discuss/478?type=8
来源：牛客网

#include <algorithm>
#include <cstdio>
#include <cstring>
#include <string>
#include <vector>
using namespace std;
 
struct node {
char s[10];
int t;
bool in;
};
 
 
const int M = 3600 * 24;
int a[M];
vector<node> v;
char s[5];
vector<string> answer;
 
bool cmp(const node &a, const node &b) {
int c = strcmp(a.s, b.s);
    if (c < 0) {
        return true;
    }
    if (c > 0) {
        return false;
    }
    return a.t < b.t;
}
 
bool givein(int &i, int j) {
    for (;(i < j) && (!v[i].in); ++i)
    ;
    if (i >= j) {
        return false;
    }
    for (; (i < j) && (v[i].in); ++i)
    ;
    --i;
    return true;
}   
 
bool giveout(int &i, int j) {
    for (; (i < j) && (v[i].in); ++i)
    ;
    return i < j;
}
 
int main() {
int n, q;
    scanf("%d%d",&n,&q);
    v.resize(n);
    for (int i = 0; i < n; ++i) {
        int x, y, z;
        scanf("%s %d:%d:%d %s", v[i].s, &x,&y,&z, s);
        v[i].t = x * 3600 + y * 60 + z;
        v[i].in = (s[0] == 'i');
    }
    sort(v.begin(), v.end(), cmp);
    int best = -1;
    for (int i = 0; i < n;) {
        int m;
        for (m = i; (m < n) && (strcmp(v[i].s, v[m].s) == 0); ++m)
        ;
        string name = v[i].s;
        int may = 0;
        for  (may = 0;;) {
            if (!givein(i, m)) {
                break;
            }
            int j = i;
            if (!giveout(i, m)) {
                break;
            }
            ++a[v[j].t];
            --a[v[i].t];
            may += v[i].t - v[j].t;
        }
        if (may > best) {
            best = may;
            answer.resize(1);
            answer[0] = name;
        }
        else if (may == best) {
            answer.push_back(name);
        }
    }
    for (int i = 1; i < M; ++i) {
        a[i] += a[i - 1];
    }
    for (;q;--q) {
        int x, y, z;
        scanf("%d:%d:%d",&x,&y,&z);
        printf("%d\n",a[x * 3600 + y * 60 + z]);
    }
    for (int i = 0; i < answer.size(); ++i) {
        printf("%s ",answer[i].c_str());
    }
    printf("%02d:%02d:%02d\n", best / 3600, best % 3600 / 60, best % 60);
}
```

