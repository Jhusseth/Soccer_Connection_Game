package modelTCP;

import java.awt.Point;

public class Item {
	
	public static final String IMAGE_PLAYER="";
	private int id;
	private Point pos;
	private String image;
	private String name;
	
	
	public Item(int id, Point pos, String image,String name) {
		super();
		this.id = id;
		this.pos = pos;
		this.image = image;
		this.name=name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point getPos() {
		return pos;
	}
	public void setPos(Point pos) {
		this.pos = pos;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
