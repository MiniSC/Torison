package easyui;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级菜单
 * @author lenovo
 *
 */
public class Menus {
			private String id;
			private String  name;
			private String  icon;
			private List<Menus2>  list = new ArrayList<Menus2>();
			public Menus(String id, String name, String icon) {
				super();
				this.id = id;
				this.name = name;
				this.icon = icon;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getIcon() {
				return icon;
			}
			public void setIcon(String icon) {
				this.icon = icon;
			}
			public List<Menus2> getList() {
				return list;
			}
			
			//对于集合的添加 和删除
			
			public void add(Menus2 m2){
				list.add(m2);
			}
			public void remove(Menus2 m2){
				list.remove(m2);
			}
			@Override
			public String toString() {
				return "Menus [id=" + id + ", name=" + name + ", icon=" + icon
						+ "]";
			}
			
			
			
}
