package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class CarsOnCampus {
	
	
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
	
	static class Car{
		private String plateNum;
		private int inTime;					//用秒来计算
		private boolean mode;				//true表示进，false表示出
		public int stayTime = 0;				//停留的时间
		public int loc;						//上一个in记录的位置
		public Car(String plateNum, int time, boolean mode, int stayTime, int loc) {
			this.plateNum = plateNum;
			this.inTime = time;
			this.mode = mode;
			this.stayTime = stayTime;
			this.loc = loc;
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
		Map<String, Car> cars = new HashMap<String, Car>();
		for(int i = 0;i < N;i++){
			String p = record[i].plateNum;
			if(!cars.containsKey(p)){				//如果map中不存在这辆车
				if(record[i].mode){
					cars.put(p, new Car(p, record[i].time, record[i].mode, 0, i));
				}
			}
			else{
				Car c = cars.get(p);
				if(record[i].mode){			//如果这次是in，无论上次是进还是出，上次的记录都无效了
						c.inTime = record[i].time;
						c.loc = i;
						c.mode = record[i].mode;
				}
				else{								//如果这次是out
					if(c.mode){					//如果上次是进，则这两次的记录都是有效的
						record[c.loc].valid = true;
						record[i].valid = true;
						int newStay = record[i].time - record[c.loc].time;
							c.stayTime += newStay;
						c.inTime = record[i].time;
						c.loc = i;
						c.mode = record[i].mode;
					}
				}
			}
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
		ArrayList<String> max = new ArrayList<String>();
		int maxTime = 0;
		Iterator<Entry<String, Car>> it =cars.entrySet().iterator();
		while(it.hasNext()){
			Car c = it.next().getValue();
			if(c.stayTime > maxTime){
				maxTime = c.stayTime;
				max.clear();
				max.add(c.plateNum);
			}
			else if(c.stayTime == maxTime)
				max.add(c.plateNum);
		}
		//按照车牌号字典顺序输出
		Collections.sort(max);
		for(int i = 0;i < max.size();i++){
			System.out.print(max.get(i) + " ");
		}
		System.out.print(String.format("%02d:%02d:%02d", maxTime / 3600, maxTime % 3600 / 60, maxTime % 60));
	}

}
