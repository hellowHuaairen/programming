package com.test;

import java.util.List;

public class Children {
	//����
	private String name;
	//�Ƿ�Ҷ�ӽڵ�
	private boolean leaf;
	//�Ƿ�ѡ��
	private boolean checked;
	//����
	private Integer age;
	
	//���ڵ�id,����һ������ӵ�����
	private String parentId;
	//�ӽڵ�
	private List<Children> children;
	
	public Children() {}
	
	public Children(String name, boolean leaf, boolean checked, Integer age) {
		this.name = name;
		this.leaf = leaf;
		this.checked = checked;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<Children> getChildren() {
		return children;
	}
	public void setChildren(List<Children> children) {
		this.children = children;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
