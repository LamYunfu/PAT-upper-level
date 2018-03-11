package com.zju.algorithem.pat;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//3 4 5 2
//1 1 1 1
//1 1 1 1
//1 1 1 1
//0 0 1 1
//0 0 1 1
//0 0 1 1
//1 0 1 1
//0 1 0 0
//0 0 0 0
//1 0 1 1
//0 0 0 0
//0 0 0 0
//0 0 0 1
//0 0 0 1
//1 0 0 0
public class AcuteStroke {
	public static int M;
	public static int N;
	public static int L;
	public static class Cell{
		private int x;			//������
		private int y;			//������
		private int z;
		public Cell(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public static boolean isInBrain(int x, int y, int z){
//		if(x >= 0 && x < M && y >= 0 && y < N && z >= 0 && z < L) return true;
//		return false;
		return x >= 0 && x < M && y >= 0 && y < N && z >= 0 && z < L;
	}
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int T;
		int count = 0;			//���������Ŀ
		M = in.nextInt();
		N = in.nextInt();
		L = in.nextInt();
		T = in.nextInt();
		//ͻ��������ɧ����
		int dx[] = {0, 0, 1, -1, 0, 0};
		int dy[] = {0, 0, 0, 0, 1, -1};
		int dz[] = {1, -1, 0, 0, 0, 0};
		int brain[][][] = new int[M][N][L];			//����ͼ
//		boolean visited[][][] = new boolean[L][M][N];		//�ж��Ƿ�������ϸ��
//		ͨ������Ѱ���ĵ��ֵ����Ϊ0��ʾ��Ѱ�������
			for(int i = 0;i < M ;i++)
				for(int j = 0;j < N;j++)
					for(int k = 0;k < L;k++){
					brain[i][j][k] = in.nextInt();
				}
		
			for(int i = 0;i < M;i++){
				for(int j = 0;j < N;j++){
					for(int k = 0;k < L;k++){
					if(brain[i][j][k] == 1){
						Queue<Cell> queue = new LinkedList<Cell>();
						Cell c = new Cell(i, j, k);
						brain[i][j][k] = 0;
						queue.add(c);
						int countQueue = 0;
						while(!queue.isEmpty()){
							Cell top = queue.poll();
							countQueue++;
							//��pop������ϸ������Χϸ���Ž�����
							int x = top.x;
							int y = top.y;
							int z = top.z;
							for(int m = 0;m < 6;m++){
								int tx = x + dx[m];
								int ty = y + dy[m];
								int tz = z + dz[m];
								if( isInBrain(tx, ty, tz) && brain[tx][ty][tz] == 1){
									Cell e = new Cell(tx, ty, tz);
									brain[tx][ty][tz] = 0;
									queue.add(e);
								}
							}
						}
						System.out.println(countQueue);
						if(countQueue >= T) {
							count += countQueue;
						}
					}
				}
			}

		}
		System.out.println(count);
	} 
}
