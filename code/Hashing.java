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
/*
		public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();//table size
		int n = in.nextInt();//input size
		int[] input = new int[n];
		for(int i = 0;i<n;i++)
		input[i] = in.nextInt();
		m = re(m);
		int[] table = new int[m];
		for(int i = 0;i<n;i++){
		int key = input[i];
		int index = key%m;
		int inc = 1;
		while(inc<m&&table[index]!=0){
		index = (key+inc*inc)%m;
		inc++;
		}
		if(table[index]==0){
		table[index] = key;
		System.out.print(index);
		}else{
		System.out.print("-");
		}
		if(i+1!=n)
		System.out.print(" ");
		}
		System.out.println();
		}
		private static int re(int size){
		if(size<=2)
		return 2;
		while(!isPrime(size))
		size++;
		return size;
		}
		private static boolean isPrime(int num){
		int limit = (int)Math.sqrt(num);
		for(int i = 2;i<=limit;i++)
		if(num%i==0)
		return false;
		return true;
		}
*/

