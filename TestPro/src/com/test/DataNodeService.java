package com.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataNodeService {
	
	private final static String ROOT_CATE = "0";
	/**
	 * ��ʼ������
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

	/*����һ��
	ʵ��һ�����ؼ��Ĺ�ѡ�¼���Ӧ��������һ���ڵ��µ�����һ��Ҷ�ӽڵ㹴ѡ��ýڵ��������еĸ��ڵ㶼����ѡ������ѡ�Ľڵ㺬���ӽڵ㣬���Զ���ѡ�����ӽڵ㡣*/
	// ��ѡ�¼�����
	// dataTreeList   ������ʾ����״���ݽṹ
	// checkedName �����¼���ѡ�Ľڵ�����
	// isLeaf   True ��ʾ���ι�ѡ�Ľڵ���Ҷ�ӽڵ�
	// ����
	// void    ֱ���޸�dataTree��checked����

	// For Java
	public void onChecked(List<Children> dataTreeList, String checkedName, Boolean isLeaf) {
		
		if(null != dataTreeList && dataTreeList.size() > 0) {
			
			//1.��ȡ���нڵ㣬����ÿ���ڵ�ĸ��ӹ�ϵparentId,�����β˵�תΪ�б�
			List<Children> allChildList = new ArrayList<>();
			getAllNodes(dataTreeList, allChildList, ROOT_CATE);
			
			//2.���б��а��� checkedName��isLeaf���Σ�ƥ���Ӧ�Ľڵ�
			Children matchedChild = allChildList.stream()
					.filter(c-> c.getName().equals(checkedName) && c.isLeaf() == isLeaf)
					.collect(Collectors.toList()).get(0);
			
			//3.���б�allChildList�в��ҽڵ㣨matchedChild�������и��ڵ����ƣ����߲������������ӽڵ�����
			List<String> checkedNames = getCheckedNodeNames(allChildList, matchedChild);
			
			//4.ͨ����ѡ���Ƶ����飬����ѡ��״̬
			checkChildNodesByName(dataTreeList, checkedNames);
		}
	}
	
	/**
	 *  ������Ҫ��ѡ�Ľڵ����Ƶ����飬��ΪҶ�ӽڵ��������и��ڵ㹴ѡ����Ϊ��Ҷ�ӽڵ㣬��ѡ���µ������ӽڵ�
	 * @param allChildList
	 * @param matchedChild
	 * @return
	 */
	
	List<String> getCheckedNodeNames(List<Children> allChildList, Children matchedChild){
		
		List<String> names = new ArrayList<>();
		names.add(matchedChild.getName());
		if(matchedChild.isLeaf()) {
			//Ҷ�ӽڵ�
			getParentNames(names, allChildList, matchedChild.getParentId());
		}else {
			//��Ҷ�ӽڵ�
			getChidrenNodeNames(names, allChildList, matchedChild.getParentId());
		}
		return names;
	}
	
	/**
	 * ��ΪҶ�ӽڵ��������и��ڵ㹴ѡ
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
	 *  ��Ϊ��Ҷ�ӽڵ㣬��ѡ���µ������ӽڵ�
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
	 * ���ݽڵ�������������ѡ�нڵ�
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
	
	/*�������
	ʵ��һ�������������֯����������N���ˡ������б�֤Ҷ�ӽڵ㶼���ˣ�*/

	// ��������亯��
	// dataTreeList   ������ʾ����״���ݽṹ
	// N    N���˵Ķ��壬N=[1, 100]
	// ����
	// []     �������N���˵��������ݣ��Ӵ�С[name1,name2,��]

	// For Java
	public String[] getTopN(List<Children> dataTreeList, int N) {
		
		String [] names  = new String [N];
		//1.��ȡ���е�Ҷ�ӽڵ�
		List<Children> leafList = new ArrayList<>();
		getLeafNodes(dataTreeList, leafList);
		//2.ͨ����������age��������
		List<Children> restChildrenList = leafList.stream().sorted(Comparator.comparing(Children::getAge).reversed()).collect(Collectors.toList());
		//3.�������N���˵�����
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
	 * ��ȡ���β˵����нڵ�
	 * @param srcChildrenList
	 * @param resChildrenList
	 */
	public void getAllNodes(List<Children> srcChildrenList, List<Children> resChildrenList, String parentId) {
		if(null != srcChildrenList && srcChildrenList.size() > 0) {
			for(int i=0;i< srcChildrenList.size();i++) {
				Children tempChild = srcChildrenList.get(i);
				tempChild.setParentId(parentId);
				if(tempChild.isLeaf()) { //Ҷ�ӽڵ�������parentId
					resChildrenList.add(tempChild);
				}else {
					//��Ҷ�ӽڵ����������
					getAllNodes(tempChild.getChildren(), resChildrenList, tempChild.getName());
				}
			}
		}
	}
	
	/**
	 * ��ȡ����Ҷ�ӽڵ�
	 * @param srcChildrenList
	 * @param resChildrenList
	 */
	public void getLeafNodes(List<Children> srcChildrenList, List<Children> resChildrenList) {
		if(null != srcChildrenList && srcChildrenList.size() > 0) {
			for(int i=0;i< srcChildrenList.size();i++) {
				Children tempChild = srcChildrenList.get(i);
				if(tempChild.isLeaf()) { //Ҷ�ӽڵ�����ӵ�����
					resChildrenList.add(tempChild);
				}else {
					//��Ҷ�ӽڵ����������
					getLeafNodes(tempChild.getChildren(), resChildrenList);
				}
			}
		}
	}

}
