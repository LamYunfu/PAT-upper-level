package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;


public class CarsOnCampusAnotherWay {

	static class Record implements Comparable{
		private String plateNum;
		private int time = 0;					//用秒来计算
		private boolean mode;				//true表示进，false表示出
		private boolean valid = false;				//标识这个记录是否合法
		public Record(String plateNum, int time, boolean mode) {
			this.plateNum = plateNum;
			this.time = time;
			this.mode = mode;
		}
		
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Record r = (Record)o;
			if(this.plateNum.compareTo(r.plateNum) > 0){
				return 1;
			}
			else if(this.plateNum.equals(r.plateNum)){
				if(this.time < r.time) return -1;
				else if(this.time == r.time) return 0;
				else return 1;
			}
			else return -1;
		}
	}
	
	static class Car implements Comparable{
		String plateNumber;
		int stayTime = 0;
		public Car(String plateNumber, int stayTime) {
			this.plateNumber = plateNumber;
			this.stayTime = stayTime;
		}
		@Override
		public int compareTo(Object o) {
			Car c = (Car)o;
			if(c.stayTime > this.stayTime) return 1;
			else if(c.stayTime == this.stayTime)	return 0;
			else return -1;
		}
		
	}
	public static int IntegerFormate(String ts){
		String t[] = ts.split(":");
		int time = Integer.valueOf(t[0]) * 60 * 60 + Integer.valueOf(t[1]) * 60 + Integer.valueOf(t[2]);
		return time;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		Record record[] = new Record[N];
		int numOfCars[] = new int[24 * 60 * 60];
		int query[] = new int[K];
		for(int i = 0;i < N;i++){
			String plateNum = in.next();
			int time = IntegerFormate(in.next());
			boolean mode = (in.next().equals("in"));
			record[i] = new Record(plateNum, time, mode);
		}
		Arrays.sort(record);
		/**
		 * 判断记录是否合法
		 */
		ArrayList<Car> cars = new ArrayList<Car>();
		for(int i = 0;i < N;i++){
			int j;
			String p = record[i].plateNum;
			//找到这一辆车的最后一个记录,j是最后一条记录的下一个
			for(j = i;  j < N && record[j].plateNum.equals(p);j++);
			int preTime = 0;
			for(int k = i; k < j;k++){
				if(!record[k].mode){}			//如果这一条记录是出，跳过这一条
				else{								//如果这一条记录是进
					if(k + 1 < j && record[k + 1].mode){}			//如果下一个也是进的话，跳过这一个
					else{							//如果下一个是出的话，匹配成功
						if(k + 1 < j){
							record[k].valid = true;
							record[k + 1].valid = true;
							preTime += (record[k + 1].time - record[k].time);
							k = k + 1;				//跳过下一条Out
						}
					}
				}
			}
			i = j - 1;
			cars.add(new Car(p, preTime));
		}
		/**
		 * 计算每个时刻车的进出情况
		 */
		for(int i = 0;i < N;i++){
			if(record[i].valid){
				if(record[i].mode) numOfCars[record[i].time]++;
				else numOfCars[record[i].time]--;
			}
		}
		for(int i = 1;i < 24 * 60 * 60;i++){
			numOfCars[i] += numOfCars[i - 1];
		}
		
		for(int i = 0;i < K;i++){
			String time = in.next();
			int t = IntegerFormate(time);
			query[i] = t;
		}
		
		for(int i = 0;i < K;i++){
			System.out.println(numOfCars[query[i]]);
		}
		//找出停留时间最长的车
		Collections.sort(cars);
		int maxTime = (cars.get(0)).stayTime;
		int i = 0;
		while(cars.get(i).stayTime == maxTime) i++;
		for(int j = 0;j < i;j++){
			System.out.print((cars.get(j)).plateNumber + " ");
		}
		System.out.print(String.format("%02d:%02d:%02d", maxTime / 3600, maxTime % 3600 / 60, maxTime % 60));
	}
}
