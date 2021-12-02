package algorithm;

public class restrictions {
	
	private double Pt_day = 5;
	private double Pt_night = 2.5;
	
	
	public void restrictionsInit() {
		
	}
	
	//restriction max consumption on the day
	public boolean restrictionMaxConsumptionDay(int totalConsumption) {
		
		return totalConsumption <= Pt_day;
	}
	
	//restriction max consumption on the night
	public boolean restrictionMaxConsumptionNight(int totalConsumption) {
		
		return totalConsumption <= Pt_night;
	}
	
	
	//restriction watering motor execution slots -> returns the earliest time to start and the latest time to finish in the form of the slot position in the timeline
	//		                                        wateringHours[0] = earliest time to start | wateringHours[1] = latest time to finish
	public int[] restrictionWateringHours() {
		
		int[] wateringHours = {12,19};
		
		return wateringHours;
	}
	
	
	//restriction for all equipments that only should work at the daytime (equipment that is turn on manually)
	public boolean restrictionWorkInTheDaytime(equipment e) {
		
		return e.getWorkMode();
	}

}
