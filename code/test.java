package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {
	static class a{
		public a(){
			i = 0;
		}
		int i;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		ArrayList<a> ab = new ArrayList<a>();
		ab.add(new a());
		a A = ab.get(0);
		A.i = 6;
		System.out.println(ab.get(0).i);
		System.out.print(String.format("%02d:%02d:%02d",2, 1, 59));
		int arr[] = new int[]{3, 2, 1};
		Arrays.sort(arr);
		System.out.println(arr[0]);
	}
}
