//��������:
//82
//9 75 65 42 22 3 69 5 41 11 77 25 10 45 17 2 43 15 47 58 56 82 31 44 73 24 63 4 29 37 72 81 64 8 26 62 68 21 18 14 19 1 71 67 35 39 61 48 46 59 52 13 34 20 79 30 12 50 51 7 23 60 57 49 74 27 32 6 33 40 36 54 55 70 80 38 53 66 78 76 16 28
//59 58 57 55 56 52 51 49 54 28 39 48 34 50 38 42 43 41 53 21 18 35 31 46 27 26 20 30 45 37 17 11 32 33 40 36 6 2 47 16 5 1 15 22 9 19 8 44 23 25 14 13 24 3 10 4 12 7 29 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82
//
//��Ӧ���Ӧ��Ϊ:
//
//Heap Sort
//58 56 57 55 39 52 51 49 54 28 35 48 34 50 38 42 43 41 53 21 18 29 31 46 27 26 20 30 45 37 17 11 32 33 40 36 6 2 47 16 5 1 15 22 9 19 8 44 23 25 14 13 24 3 10 4 12 7 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82
//
//������Ϊ:
//
//Heap Sort
//58 55 57 49 56 52 51 42 54 28 39 48 34 50 38 32 43 41 53 21 18 35 31 46 27 26 20 30 45 37 17 11 29 33 40 36 6 2 47 16 5 1 15 22 9 19 8 44 23 25 14 13 24 3 10 4 12 7 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82
//Q
//�鿴ȫ��
package com.zju.algorithem.pat;

import java.util.Scanner;

public class InsertionOrHeapSort {
	
	static int b[];
	public static void heapAdjest(int cur, int size){			//size����Ŀǰû���ź���Ĺ�ģ��С
		int lchild = 2 * cur + 1;
		int rchild = 2 * cur + 2;
		int max = cur;
		if(cur <= size /2){			//�����αҶ�ӽ��Ļ����ǾͲ����ٵ�����
			if(b[max] < b[lchild] && lchild < size) max = lchild;
			if(b[max] < b[rchild] && rchild < size) max = rchild;
			if(max != cur){
				int temp = b[cur];
				b[cur] = b[max];
				b[max] = temp;
				heapAdjest(max, size);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int a[] = new int[N];				//��ʼ����
		b = new int[N];				//����һ���̶ȵ�����
		for(int i = 0;i < N;i++){
			a[i] = in.nextInt();
		}
		for(int i = 0;i < N;i++){
			b[i] = in.nextInt();
		}
		//��������Ǵ�ʲô�ط���ʼ�����,��һ��Ҫ�ŵĵط���index + 1
		int index = 0;
		while(b[index] < b[index + 1]){
			index++;
		}
		index += 1;
		//����ǲ���������ô��ʱ��index��ʼ,����������������Ӧ����һ����
		String type = "Insertion Sort";
		for(int i = index;i < N;i++){
			if(a[i] != b[i]){
				type = "Heap Sort";
				break;
			}
		}
		if(type.equals("Insertion Sort")){
			int temp = b[index];
			for(int i = index - 1;i > 0;i--){
				if(b[i] > temp){
					b[i + 1] = b[i];
				}
				else {
					b[i + 1] = temp;
					break;
				}
			}
		}
		else{			//����Ƕ���������		
			//�����ҵ�Ŀǰ�Ѿ��ź��������±�
			int cur;
			for(cur = N -1; b[cur] > b[0]; cur--);   //�ҵ�û���ź������ǰһ���±�
			int temp = b[0];
			b[0] = b[cur];
			b[cur] = temp;
			heapAdjest(0, cur);
		}
		System.out.println(type);
		for(int i = 0;i < N - 1;i++){
			System.out.print(b[i] + " ");
		}
		System.out.println(b[N - 1]);
	}

}
