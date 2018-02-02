# All Roads Lead to Rome

### 问题描述

```
Indeed there are many different tourist routes from our city to Rome.  You are supposed to find your clients the route with the least cost while gaining the most happiness.
```

### 输入描述

```
Each input file contains one test case.  For each case, the first line contains 2 positive integers N (2<=N<=200), the number of cities, and K, the total number of routes between pairs of cities; followed by the name of the starting city.  The next N-1 lines each gives the name of a city and an integer that represents the happiness one can gain from that city, except the starting city.  Then K lines follow, each describes a route between two cities in the format "City1 City2 Cost".  Here the name of a city is a string of 3 capital English letters, and the destination is always ROM which represents Rome.
```

* 第一行: N:城市的总数     K:城市之间的所有的路途的数量  开始城市的总数
* N - 1行：下面N - 1个城市的名字和相应的幸福指数
* K 行：城市一     城市二       两个城市之间所花的时间



### 输出描述

```
For each test case, we are supposed to find the route with the least cost.  If such a route is not unique, the one with the maximum happiness will be recommended.  If such a route is still not unique, then we output the one with the maximum average happiness -- it is guaranteed by the judge that such a solution exists and is unique.
Hence in the first line of output, you must print 4 numbers: the number of different routes with the least cost, the cost, the happiness, and the average happiness (take the integer part only) of the recommended route.  Then in the next line, you are supposed to print the route in the format "City1->City2->...->ROM".
```

**寻找路径的优先级**

1.花费的时间最少

2.最多的总幸福指数

3.平均幸福指数最高



输出4个数：

有最小时间花费的路径的条数

花费的时间

总的幸福数

推荐路径平均幸福数（只保留整数部分）

打印出路径的格式：City1 -> City2 ->........->ROM



### 输入例子

```
6 7 HZH
ROM 100
PKN 40
GDN 55
PRS 95
BLN 80
ROM GDN 1
BLN ROM 1
HZH PKN 1
PRS ROM 2
BLN HZH 2
PKN GDN 1
HZH PRS 1
```



### 输出例子

```
3 3 195 97
HZH->PRS->ROM
```


```java
链接：https://www.nowcoder.com/questionTerminal/bf8045decb1348a3bd6967305cbdad4c
来源：牛客网

import java.util.HashMap;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();//numbers of city
        int k = in.nextInt();//numbers of routes
        String start = in.next();//start city
        int[] costs = new int[n];//每一个点到顶点的cost
        int[] hpns = new int[n];//每一个点到顶点的happiness值
        int[] steps = new int[n];//每一条路径的节点数，不包括头节点
        int[] routes = new int[n];//到达每个点的路由数量
        int[] parent = new int[n];//每一个结点的父节点
        int[][] M = new int[n][n];//邻接矩阵
        HashMap<String, Integer> index = new HashMap<String, Integer>();//记录每个城市的下标
        boolean[] visited = new boolean[n];//标记已经访问的结点
        int[] h = new int[n];//每个城市的幸福值
        String[] names = new String[n];//每个城市的名称
        routes[0] = 1;
        parent[0] = -1;
        index.put(start, 0);
        names[0] = start;
        //初始化
        for(int i = 1;i<n;i++){
            costs[i] = Integer.MAX_VALUE;
            hpns[i] = Integer.MIN_VALUE;
            names[i] = in.next();
            index.put(names[i], i);
            h[i] = in.nextInt();
        }
        int end = index.get("ROM");
         
        for(int i = 0;i<k;i++){
            int s = index.get(in.next());
            int e = index.get(in.next());
            int cost = in.nextInt();
            M[s][e] = cost;
            M[e][s] = cost;
        }
        for(int t = 0;t<n;t++){
            int v = -1;
            for(int i = 0;i<n;i++){
                if (!visited[i] && ((v < 0) || (costs[i] < costs[v])))
                    v = i;
            }
            visited[v] = true;
            for(int i = 0;i<n;i++){
                if(!visited[i]&&M[v][i]!=0){
                    int cost = costs[v] + M[v][i];
                    int happy = hpns[v] + h[i];
                    int step = steps[v] + 1;
                    boolean flag = false;
                    if(cost<costs[i]){
                        costs[i] = cost;
                        routes[i] = routes[v];
                        flag = true;
                    }else if(cost==costs[i]){
                        routes[i] += routes[v];
                        if(happy>hpns[i]){
                            flag = true;
                        }else if(happy==hpns[i] && step<steps[i]){
                            flag = true;
                        }
                    }
                    if(flag){
                        costs[i] = cost;
                        hpns[i] = happy;
                        steps[i] = step;
                        parent[i] = v;
                    }
                }
            }
        }
        int total = steps[end];
        int happiness = hpns[end];
        int avg = happiness/total;
        total++;
        System.out.println(routes[end]+" "+costs[end]+" "+happiness+" "+avg);
        String[] result = new String[total];
        int j = total-1;
        while(end!=-1){
            result[j--] = names[end];
            end = parent[end];
        }
        for(int i = 0;i<result.length-1;i++){
            System.out.print(result[i]+"->");
        }
        System.out.println(result[total-1]);
    }
}
```









```C++
链接：https://www.nowcoder.com/questionTerminal/bf8045decb1348a3bd6967305cbdad4c
来源：牛客网

#include <iostream>
#include <map>
#include <vector>
#include <limits.h>
 
using namespace std;
 
vector<vector<int> > cost;      // 记录每条边的花费，为0表示不通
vector<bool> visited;           // 判断是否访问过
vector<int> happiness, path;    // 节点的幸福度，中间路径
map<string, int> cityMap;       // 节点映射，求出城市编号
vector<string> numMap;
vector<int> numOfLeastCostRoute, citiesInRoute; // 最短路径数、路径上的节点数（不包括第一个）
vector<int> totalHappiness, totalCost;          // 节点0到节点i的总幸福度，总费用（最小值）
int N, K; string startCity;
 
void dijkstra();
 
int main()
{
    ios::sync_with_stdio(false);
    // 读入基本数据
    cin >> N >> K >> startCity;
    // 初始化变量
    cost.resize(N, vector<int>(N, 0));
    visited.resize(N, false);
    happiness.resize(N, 0);
    path.resize(N, -1);
    numMap.resize(N);
    numOfLeastCostRoute.resize(N, 0);
    citiesInRoute.resize(N, INT_MAX);
    totalHappiness.resize(N, 0);
    totalCost.resize(N, INT_MAX);
    // 读取幸福度和费用
    numMap[0] = startCity;
    cityMap[startCity] = 0;
    for(int i=1; i<N; i++) {
        cin >> numMap[i] >> happiness[i];
        cityMap[numMap[i]] = i;
    }
    string srcCity, desCity; int src, des, tempCost;
    for(int i=0; i<K; i++) {
        cin >> srcCity >> desCity >> tempCost;
        src = cityMap[srcCity];
        des = cityMap[desCity];
        cost[src][des] = tempCost;
        cost[des][src] = tempCost;
    }
 
    //计算并输出结果
    dijkstra();
    int end = cityMap["ROM"];
    cout << numOfLeastCostRoute[end] << " " << totalCost[end] <<\
        " " << totalHappiness[end] << " " << (totalHappiness[end]\
        /citiesInRoute[end]) << endl;
    vector<string> route;
    while(end != -1) {
        route.push_back(numMap[end]);
        end = path[end];
    }
    for(int i=route.size()-1; i>0; i--) {
        cout << route[i] << "->";
    }
    cout << route[0];
 
    return 0;
}
 
void dijkstra()
{
    totalCost[0] = 0;
    numOfLeastCostRoute[0] = 1;
    citiesInRoute[0] = 0;
 
    int k; // k为每轮中花费最少的城市
    for(int i=0; i<N; i++) {
        k = -1;
        for(int j=0; j<N; j++) {
            if(!visited[j] && (k<0 || totalCost[j]<totalCost[k])) {
                    k = j;
            }
        }
        visited[k] = true;
        // 优化
        for(int j=0; j<N; j++) {
            if(!visited[j] && cost[k][j] > 0) {
                int tempCost = totalCost[k] + cost[k][j];
                if(tempCost < totalCost[j]) {
                    totalCost[j] = tempCost;
                    numOfLeastCostRoute[j] = numOfLeastCostRoute[k];
                    path[j] = k;
                    totalHappiness[j] = totalHappiness[k] + happiness[j];
                    citiesInRoute[j] = citiesInRoute[k]+1;
                } else if(tempCost == totalCost[j]) {
                    numOfLeastCostRoute[j] += numOfLeastCostRoute[k];
                    if(totalHappiness[j] < (totalHappiness[k] + happiness[j])\
                     || (totalHappiness[j]==totalHappiness[k]+happiness[j]\
                     && citiesInRoute[j] > citiesInRoute[k]+1)) {
                        path[j] = k;
                        totalHappiness[j] = totalHappiness[k] + happiness[j];
                        citiesInRoute[j] = citiesInRoute[k]+1;
                    }
                }
            }
        }
    }
}
```

