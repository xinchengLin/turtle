package beone.entity;

public class Order {
	private String name;//   move   chaxun 
	private String content;//up     ph 
	private int key;//up     ph 
	
	
	public Order() {
		super();
	}
	public Order(String name, String content, int key) {
		super();
		this.name = name;
		this.content = content;
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	

}
