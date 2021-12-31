package algorithm;

//
//Information about each equipment that consumes (deciding if include the solar panel here)
//

public class equipment {

	private String name_equipment; // name of the equipment
	private double power; // power consumed stipulated (mean of the power if equipment is not known)
	private int execTime; // timeslots of 30 mins that has to execute per day
	private int minDuration; // timeslots of 30 mins for the minimum duration has to be on before turning the equipment off
	public boolean[] Xt; // timeline of the equipment divided to timeslots of 30 mins
	public double[] acumCons = new double[48]; // Cit on the equations file. Acumulated power at t time with d duration 
	public boolean alreadyFullySchedulled;
	private boolean daytime;
	private int id;
	
	//Constructor
	public equipment(int id, String name_equipment, double power, int execTime, int minDuration, boolean workmode) {
		
		this.id = id;
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
		daytime = workmode;
	}
	

	public equipment() {
		
	}


	//Define a time to work in the timeline
	public void turnOnInTimeline(int timestamp) {
		
		if(timestamp < 48 && timestamp > -1)
			Xt[timestamp] = true;
		else 
			System.out.println("[Error]: Invalid timestamp");
			
	}
	
	//Returning is the equipment is already implemented on the scheduling timeline
	public boolean isAlreadySchedulled() {
		return this.alreadyFullySchedulled;
	}


	//Define if the equipment is scheduled
	public void setScheduled() {
		this.alreadyFullySchedulled = true;
		
	}
	
	public void setID(int id) {
		this.id = id;
	}
	

	public void calcCumulatedPower(int starting_time, int duration,  double [] timelineTariff) {
		for (int i = starting_time; i < (starting_time+duration); i++) {
  			this.acumCons[starting_time] += (this.power/2) * timelineTariff[i%48];
		}
	}
	
	public void resetEquipment() {
		this.alreadyFullySchedulled = false;
		
		for(int i = 0; i < 48; i++) {
			this.Xt[i] = false;
			this.acumCons[i] = 0;
		}
	}
	
	//All 'gets' for the info of all equipments
	
	public int getID() {
		return this.id;	
	}
	
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
	
	public boolean getWorkMode() {
		return this.daytime;
	}
	
	public boolean getXtPosition(int position) {
		return this.Xt[position];
	}
	
	
	//clone equipment
	
	public equipment cloneEquipment() {
		
		equipment newEquip = new equipment(this.getID(), this.getEquipment(),this.getPower(),this.getExecTime(),this.getMinDuration(),this.getWorkMode());
		newEquip.alreadyFullySchedulled = this.isAlreadySchedulled();
		
		for(int i = 0; i < this.Xt.length;i++) {
			newEquip.Xt[i] = this.Xt[i];
			newEquip.acumCons[i] = this.acumCons[i];
		}
		
		
		return newEquip;
		
	}
	
	//print equipment values
	public String toString() {
		
		String print = "Equipment name: " + this.getEquipment() + " || Power=" + 
		this.getPower() + "kW" + " || Execute time: " + (this.getExecTime()/2) + 
		"h || Scheduled? " + this.isAlreadySchedulled() + "\n---" + this.getEquipment()+ 
		" timeline---\nEach timeslot represents 30 mins of the day\n";
		
		for(int i = 0; i < 48; i++)
			print += "Slot no" + i + " :" + this.getTimeline()[i] + 
			" || acumCons["+ i + "] = " + this.acumCons[i] + "\n";
			
		
		print += "\n";
		
		return print;
		
		
	}
	
}
