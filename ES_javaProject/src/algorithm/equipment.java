package algorithm;


public class equipment {

	private int id_equipment;
	private int power;
	
	public equipment(int id, int power) {
		this.id_equipment = id;
		this.power = power;
		
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getID() {
		return this.id_equipment;
	}
}
