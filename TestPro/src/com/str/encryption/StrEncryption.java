package com.str.encryption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StrEncryption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		calStrLength();
	}
	
	/**
	 * ����̨���룺time,me,ball�ַ�����ͨ��������������ļ����ַ��� s=time#ball# ,��Ӧ��Ҳ������������indexs = {0,2,5},
	 * ���������ͨ���ַ���s������index����ת����ԭ�ַ��������������С���ַ������ȣ�
	 * ���磺���룺time,me,ball,��� 10��
	 * ǰ�᣺0<strs<=2000��0< strs[i] < 7
	 * 
	 */
	public static void calStrLength() {
		Scanner scanner = new Scanner(System.in);
		String restStr = "";
		while(scanner.hasNext()) {
			
			String line = scanner.nextLine();
			String [] strs = line.split(",");
			
			List<Integer> indexs = new ArrayList<>();
			indexs.add(0);
			
			//1.�жϺ�һ���ַ����Ƿ���ȫ������ǰһ���ַ���
			//2.��Ҫ��ӻ�������
			int index = 1;
			restStr = strs[0] + "#";
			while(index < strs.length) {
				if(restStr.length() > strs[index].length()) {
					int strIndex = restStr.indexOf(strs[index]);
					if(strIndex < 0) {//��ʾ��������Ӧ���ַ���
						indexs.add(restStr.length());
						restStr = restStr + strs[index] + "#";
					}else {
						indexs.add(strIndex);
					}
				}
				index++;
				
			}
//			System.out.println(indexs.toString());
			System.out.println(restStr);
			System.out.println(restStr.length());
			
		}
		
	}
	

	

	

}
