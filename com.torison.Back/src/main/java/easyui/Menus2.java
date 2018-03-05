package easyui;

public class Menus2 {
	private String id;
	private String  name;
	private String  icon;
	private String  url;
	
	public Menus2(String id, String name, String icon, String url) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.url = url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Menus2 [id=" + id + ", name=" + name + ", icon=" + icon
				+ ", url=" + url + "]";
	}
	
	
}
