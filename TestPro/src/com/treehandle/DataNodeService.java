package com.treehandle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataNodeService {
	
	private final static String ROOT_CATE = "0";
	/**
	 * 初始化数据
	 * @return
	 */
	public List<Children> initChildrenList(){
		List<Children> resList = new ArrayList<>();
		Children c1 = new Children("roo1",false, false, 0);
		Children c11 = new Children("roo11",true, false, 4);
		Children c12 = new Children("roo12",true, false, 2);
		Children c13 = new Children("roo13",false, false, 0);
		Children c131 = new Children("roo131",true, false, 11);
		Children c132 = new Children("roo132",true, false, 32);
		Children c133 = new Children("roo133",true, false, 16);
		
		List<Children> c13ChildList = new ArrayList<>();
		c13ChildList.add(c131);
		c13ChildList.add(c132);
		c13ChildList.add(c133);
		c13.setChildren(c13ChildList);
		
		List<Children> c1ChildList = new ArrayList<>();
		c1ChildList.add(c11);
		c1ChildList.add(c12);
		c1ChildList.add(c13);
		c1.setChildren(c1ChildList);
		resList.add(c1);
		
		Children c2 = new Children("roo2",false, false, 0);
		Children c21 = new Children("roo21",true, false, 7);
		Children c22 = new Children("roo22",true, false, 33);
		Children c23 = new Children("roo23",true, false, 55);
		List<Children> c2ChildList = new ArrayList<>();
		c2ChildList.add(c21);
		c2ChildList.add(c22);
		c2ChildList.add(c23);
		c2.setChildren(c2ChildList);
		resList.add(c2);
		
		return resList;
	}

	/*问题一：
	实现一个树控件的勾选事件响应函数，若一个节点下的任意一个叶子节点勾选则该节点与其所有的父节点都被勾选；若勾选的节点含有子节点，则自动勾选所有子节点。*/
	// 勾选事件函数
	// dataTreeList   如上所示的树状数据结构
	// checkedName 本次事件勾选的节点名称
	// isLeaf   True 表示本次勾选的节点是叶子节点
	// 返回
	// void    直接修改dataTree中checked属性

	// For Java
	public void onChecked(List<Children> dataTreeList, String checkedName, Boolean isLeaf) {
		
		if(null != dataTreeList && dataTreeList.size() > 0) {
			
			//1.获取所有节点，生成每个节点的父子关系parentId,将树形菜单转为列表
			List<Children> allChildList = new ArrayList<>();
			getAllNodes(dataTreeList, allChildList, ROOT_CATE);
			
			//2.在列表中按照 checkedName和isLeaf树形，匹配对应的节点
			Children matchedChild = allChildList.stream()
					.filter(c-> c.getName().equals(checkedName) && c.isLeaf() == isLeaf)
					.collect(Collectors.toList()).get(0);
			
			//3.在列表allChildList中查找节点（matchedChild）的所有父节点名称，或者查找它的所有子节点名称
			List<String> checkedNames = getCheckedNodeNames(allChildList, matchedChild);
			
			//4.通过勾选名称的数组，设置选中状态
			checkChildNodesByName(dataTreeList, checkedNames);
		}
	}
	
	/**
	 *  查找需要勾选的节点名称的数组，若为叶子节点则其所有父节点勾选，若为非叶子节点，则勾选其下的所有子节点
	 * @param allChildList
	 * @param matchedChild
	 * @return
	 */
	
	List<String> getCheckedNodeNames(List<Children> allChildList, Children matchedChild){
		
		List<String> names = new ArrayList<>();
		names.add(matchedChild.getName());
		if(matchedChild.isLeaf()) {
			//叶子节点
			getParentNames(names, allChildList, matchedChild.getParentId());
		}else {
			//非叶子节点
			getChidrenNodeNames(names, allChildList, matchedChild.getParentId());
		}
		return names;
	}
	
	/**
	 * 若为叶子节点则其所有父节点勾选
	 * @param names
	 */
	void getParentNames(List<String> names, List<Children> allChildList, String parentId) {
		
		if(null != allChildList && allChildList.size() > 0) {
			Children parentNode = allChildList.stream().filter(c->c.getName().equals(parentId))
				.collect(Collectors.toList()).get(0);
			if(parentNode.getParentId().equals(ROOT_CATE)) {
				return;
			}else {
				names.add(parentNode.getName());
				getParentNames(names, allChildList, parentNode.getParentId());
			}
			
		}
	}
	
	/**
	 *  若为非叶子节点，则勾选其下的所有子节点
	 * @param names
	 * @param allChildList
	 * @param parentId
	 */
	void getChidrenNodeNames(List<String> names, List<Children> allChildList, String parentId) {
		
		if(null != allChildList && allChildList.size() > 0) {
			List<Children> childrenList = allChildList.stream().filter(c->c.getParentId().equals(parentId))
				.collect(Collectors.toList());
			
			childrenList.forEach(c->{
				if(c.isLeaf()) {
					names.add(c.getName());
				}else {
					getChidrenNodeNames(names, allChildList, c.getName());
				}
			});
			
		}
	}
	
	/**
	 * 根据节点名称数组设置选中节点
	 * @param childirenList
	 */
	void checkChildNodesByName(List<Children> childirenList,List<String> checkedNames) {
		
		if(null != childirenList && childirenList.size()> 0) {
			for(int i=0;i< childirenList.size();i++) {
				Children child = childirenList.get(i);
				for(int j=0;j<checkedNames.size();j++) {
					if(child.getName().equals(checkedNames.get(i))) {
						child.setChecked(true);
						if(!child.isLeaf()) {
							checkChildNodesByName(child.getChildren(), checkedNames.subList(1, checkedNames.size() - 1));
						}
					}	
				}
			}
		}
	}
	
	/*问题二：
	实现一个函数，求该组织中年龄最大的N个人。（其中保证叶子节点都是人）*/

	// 求最大年龄函数
	// dataTreeList   如上所示的树状数据结构
	// N    N个人的定义，N=[1, 100]
	// 返回
	// []     返回最大N个人的名字数据，从大到小[name1,name2,…]

	// For Java
	public String[] getTopN(List<Children> dataTreeList, int N) {
		
		String [] names  = new String [N];
		//1.获取所有的叶子节点
		List<Children> leafList = new ArrayList<>();
		getLeafNodes(dataTreeList, leafList);
		//2.通过对象属性age进行排序
		List<Children> restChildrenList = leafList.stream().sorted(Comparator.comparing(Children::getAge).reversed()).collect(Collectors.toList());
		//3.返回最大N个人的名字
		if(restChildrenList.size() >= N) {
			for(int i=0;i< N;i++) {
				names[i] = restChildrenList.get(i).getName();
			}
		}else {
			for(int i=0;i< restChildrenList.size();i++) {
				names[i] = restChildrenList.get(i).getName();
			}
		}
		return names;

	}
	
	
	/**
	 * 获取树形菜单所有节点
	 * @param srcChildrenList
	 * @param resChildrenList
	 */
	public void getAllNodes(List<Children> srcChildrenList, List<Children> resChildrenList, String parentId) {
		if(null != srcChildrenList && srcChildrenList.size() > 0) {
			for(int i=0;i< srcChildrenList.size();i++) {
				Children tempChild = srcChildrenList.get(i);
				tempChild.setParentId(parentId);
				if(tempChild.isLeaf()) { //叶子节点则设置parentId
					resChildrenList.add(tempChild);
				}else {
					//非叶子节点则继续遍历
					getAllNodes(tempChild.getChildren(), resChildrenList, tempChild.getName());
				}
			}
		}
	}
	
	/**
	 * 获取所有叶子节点
	 * @param srcChildrenList
	 * @param resChildrenList
	 */
	public void getLeafNodes(List<Children> srcChildrenList, List<Children> resChildrenList) {
		if(null != srcChildrenList && srcChildrenList.size() > 0) {
			for(int i=0;i< srcChildrenList.size();i++) {
				Children tempChild = srcChildrenList.get(i);
				if(tempChild.isLeaf()) { //叶子节点则添加到集合
					resChildrenList.add(tempChild);
				}else {
					//非叶子节点则继续遍历
					getLeafNodes(tempChild.getChildren(), resChildrenList);
				}
			}
		}
	}

}
