package com.treehandle;
import java.util.*;

public class Main {

	/**
	 * 说明：
	 * 1.getTopN方法可直接执行
	 * 2.onChecked 未测试，你有时间可以看下实现的思路。
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
