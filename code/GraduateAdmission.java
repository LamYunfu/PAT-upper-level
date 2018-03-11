package com.zju.algorithem.pat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraduateAdmission {
	public static class Student implements Comparable<Student>{
		private float grade;
		private float GE;
		private float GI;
		public int []quate;
		
		public Student(float GE, float GI, int[] quate){
			this.GE = GE;
			this.GI = GI;
			this.grade = (GE + GI) / 2;
			this.quate = quate;
		}
		
		@Override
		public int compareTo(Student o) {
			// TODO Auto-generated method stub
			if(this.grade > o.grade) return 1;
			else if(this.grade == o.grade){
				if(this.GE > o.GE) return 1;
				else if(this.GE == o.GE) return 0;
				else return -1;
			}
			else
				return -1;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();		//�����ߵ�����
		int M = in.nextInt();		//ѧУ������
		int K = in.nextInt();		//���־Ը��Ŀ
		Student student[] = new Student[N];
		int quate[] = new int[M];		//ÿ��ѧУ�������ݶ�
		int choice[][] = new int[N][K];
		for(int i = 0;i < M;i++){
			quate[i] = in.nextInt();
		}
		
		for(int i = 0;i < N;i++){
			float GE = in.nextFloat();
			float GI = in.nextFloat();
			for(int j = 0;j < K;j++){
				choice[i][j] = in.nextInt();
			}
			student[i] = new Student(GE, GI, choice[i]);
		}
		Arrays.sort(student);
	}

}
