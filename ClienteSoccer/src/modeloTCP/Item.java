package modeloTCP;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Item {
	
	public static final String IMAGE_PLAYER="";
	private int id;
	private Point pos;
	private ImageIcon image;
	private String name;
	
	
	public Item(int id, Point pos, String name,ImageIcon im) {
		super();
		this.id = id;
		this.pos = pos;
		this.name=name;
		image=im;
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
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	
}
