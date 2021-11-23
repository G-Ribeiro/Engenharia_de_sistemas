package algorithm;

//
//Information about each equipment that consumes (deciding if include the solar panel here)
//

public class equipment {

	private String name_equipment; // name of the equipment
	private int power; // power consumed stipulated (mean of the power if equipment is not known)
	private int execTime; // that has to execute per day
	private int minDuration; // minimum duration has to be on before turning the equipment off
	private boolean[] Xt; // timeline of the equipment
	
	//Constructor
	public equipment(String name_equipment, int power, int execTime, int minDuration) {
		
		this.name_equipment = name_equipment;
		this.power = power;
		this.execTime = execTime;
		
		if(minDuration > execTime) {
			
			System.out.println("[Warning]: minDuration inserted is bigger than execTime -> minDuration is going to be defined to" + execTime);
			this.minDuration = execTime;
		}
		else {
			this.minDuration = minDuration;
		}
		
		this.Xt = new boolean[24];	
	}
	
	
	
	//Define a time to work in the timeline
	public void turnOnInTimeline(int timestamp) {
		
		if(timestamp < 24 && timestamp > -1)
			Xt[timestamp] = true;
		else 
			System.out.println("[Error]: Invalid timestamp");
			
	}
	
	
	
	//All 'gets' for the info of all equipments
	
	public String getEquipment() {
		return this.name_equipment;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getExecTime() {
		return this.execTime;
	}
	
	public int getMinDuration() {
		return this.minDuration;
	}
	
	public boolean[] getTimeline() {
		return this.Xt;
	}
	
}
