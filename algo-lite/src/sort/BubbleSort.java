package sort;

import java.util.Arrays;

public class BubbleSort {

	/*
	 * ͨ�������Ƚϣ������Ԫ�طŵ����棬��һ���ҵ�һ����������Ȼ�󲻿������һ������
	 * �ٴδ�ͷ��ʼ�����Ƚϣ����δ�����ŵ����档����������
	 */
	static void sort(int[] data){
		for(int i=data.length-1; i>=0; i--){
			for(int j=0; j<i; j++){
				if(data[j] > data[j+1]){
					swap(data, j, j+1);
				}
			}
		}
	}

	private static void swap(int[] data, int i, int j) {
		int temp = data[j];
		data[j] = data[i];
		data[i] = temp;
	}
	
	public static void main(String[] args) {
		int[] data = {4,2,6,8,1,23, 18};
		BubbleSort.sort(data);
		System.out.println(Arrays.toString(data));
	}
}
