# Programming
编程题目积累

## 编程题目直达

#### 1 现有一个树控件,实现两个功能：
- 问题1：实现一个树控件的勾选事件响应函数，若一个节点下的任意一个叶子节点勾选则该节点与其所有的父节点都被勾选；若勾选的节点含有子节点，则自动勾选所有子节点。<br/>

```
// 勾选事件函数
// dataTreeList   如上所示的树状数据结构
// checkedName 本次事件勾选的节点名称
// isLeaf   True 表示本次勾选的节点是叶子节点
// 返回
// void    直接修改dataTree中checked属性

// For Java
void onChecked(List<DataTree> dataTreeList, String checkedName, Boolean isLeaf) {

}
```

- 问题2：实现一个函数，求该组织中年龄最大的N个人。（其中保证叶子节点都是人）
  
```
// 求最大年龄函数
// dataTreeList   如上所示的树状数据结构
// N    N个人的定义，N=[1, 100]
// 返回
// []     返回最大N个人的名字数据，从大到小[name1,name2,…]

// For Java
String[] getTopN(List<DataTree> dataTreeList, int N) {

}
```
参考代码：[树形菜单问题](TestPro/src/com/treehandle/Main.java)
  
#### 2 获取加密密码的字符串长度
控制台输入：time,me,ball字符串，通过程序可生成它的加密字符串 s=time#ball# ,对应的也可以生成索引indexs = {0,2,5},逆向操作，通过字符串s和索引index可以转化出原字符串。计算输出最小的字符串长度？
例如：输入：time,me,ball,输出 10。
前提：0<strs<=2000，0< strs[i] < 7

参考代码：[密码加密计算长度](TestPro/src/com/str/encryption/StrEncryption.java)


