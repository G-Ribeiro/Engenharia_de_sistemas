package algorithm;


public class equipment {

	private int id_equipment;
	private int power;
	
	public equipment(int id, int power) {
		this.id_equipment = id;
		this.power = power;
		
	}
	
	private int getPower() {
		return this.power;
	}
	
	private int getID() {
		return this.id_equipment;
	}
}
