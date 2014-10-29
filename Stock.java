public class Stock {
	private String name;
	private double price;
	private int quantity;
	public Stock(){
		name = null;
		price = 0;
		quantity = 0;
	}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public double getPrice(){return price;}
	public void setPrice(double price){this.price = price;}
	public int getQuantity(){return quantity;}
	public void setQuantity(int quantity){this.quantity = quantity;}
}