package algorithm;

//
//Information about each equipment that consumes (deciding if include the solar panel here)
//

public class equipment {

	private String name_equipment; // name of the equipment
	private double power; // power consumed stipulated (mean of the power if equipment is not known)
	private int execTime; // timeslots of 30 mins that has to execute per day
	private int minDuration; // timeslots of 30 mins for the minimum duration has to be on before turning the equipment off
	private boolean[] Xt; // timeline of the equipment divided to timeslots of 30 mins
	private boolean alreadyFullySchedulled;
	
	//Constructor
	public equipment(String name_equipment, double power, int execTime, int minDuration) {
		
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
		
		this.Xt = new boolean[48];
		this.alreadyFullySchedulled = false;
	}
	



	//Define a time to work in the timeline
	public void turnOnInTimeline(int timestamp) {
		
		if(timestamp < 24 && timestamp > -1)
			Xt[timestamp] = true;
		else 
			System.out.println("[Error]: Invalid timestamp");
			
	}
	
	//Returning is the equipment is already implemented on the scheduling timeline
	public boolean isAlreadySchedulled() {
		return this.alreadyFullySchedulled;
	}
	
	//All 'gets' for the info of all equipments
	
	public String getEquipment() {
		return this.name_equipment;
	}
	
	public double getPower() {
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
	
	//print equipment values
	public String toString() {
		
		String print = "Equipment name: " + this.getEquipment() + " || Power=" + this.getPower() + "kW" + " || Execute time: " + (this.getExecTime()/2) + "h || Scheduled? " + this.isAlreadySchedulled() + "\n---" + this.getEquipment()+ " timeline---\nEach timeslot represents 30 mins of the day\n";
		
		for(int i = 0; i < 48; i++)
			print += "Slot nº" + i + " :" + this.getTimeline()[i] + "\n";
		
		print += "\n";
		
		return print;
		
		
	}
	
}
