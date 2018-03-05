package easyui;

import java.util.ArrayList;

public class Test {
		public static void main(String[] args) {
			ArrayList<Menus> list = new ArrayList<Menus>();
			
			//首先添加一级菜单
			Menus  menus10 = new Menus("10", "一级菜单", "#");
			
				//添加二级菜单
			Menus2 menus102 = new Menus2("10", "一级菜单", "#","#");
			  menus10.add(menus102);
			  Menus2 menus103 = new Menus2("10", "一级菜单", "#","#");
			  menus10.add(menus103);
			  
			  //添加到一级菜单中
			  list.add(menus10);
			  ArrayList<Menus2> list2 = new ArrayList<Menus2>();
			  //循环打印出来
			  for (int i = 0,len = list.size(); i <len ; i++) {
				  //输出二级菜单
				  for (int j = 0, len2 = list.get(i).getList().size(); j <len2 ; j++) {
					  System.out.println(list.get(i).getList().get(j).toString());
				}
				  
			}
		}
}
