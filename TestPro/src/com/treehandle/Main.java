package com.treehandle;
import java.util.*;

public class Main {

	/**
	 * ˵����
	 * 1.getTopN������ֱ��ִ��
	 * 2.onChecked δ���ԣ�����ʱ����Կ���ʵ�ֵ�˼·��
	 * @param args
	 */
	public static void main(String[] args) {
		getTopN();
//		onChecked();
		
	}
	
	public static void getTopN() {
		DataNodeService service = new DataNodeService();
		List<Children> childrenList = service.initChildrenList();
		
		System.out.println(Arrays.toString(service.getTopN(childrenList, 3)));
	}
	
	public static void onChecked() {
		DataNodeService service = new DataNodeService();
		List<Children> childrenList = service.initChildrenList();
		service.onChecked(childrenList, "root1", false);
	}

}
