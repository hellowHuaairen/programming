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
	 * 控制台输入：time,me,ball字符串，通过程序可生成它的加密字符串 s=time#ball# ,对应的也可以生成索引indexs = {0,2,5},
	 * 逆向操作，通过字符串s和索引index可以转化出原字符串。计算输出最小的字符串长度？
	 * 例如：输入：time,me,ball,输出 10。
	 * 前提：0<strs<=2000，0< strs[i] < 7
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
			
			//1.判断后一个字符串是否完全包含于前一个字符串
			//2.主要添加基础条件
			int index = 1;
			restStr = strs[0] + "#";
			while(index < strs.length) {
				if(restStr.length() > strs[index].length()) {
					int strIndex = restStr.indexOf(strs[index]);
					if(strIndex < 0) {//表示不包含对应的字符串
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
