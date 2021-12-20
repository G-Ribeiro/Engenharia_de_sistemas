package algorithm;

abstract public class restrictions {
	
	private static double Pt_day = 2.5;
	private static double Pt_night = 5;
	
	
	public void restrictionsInit() {
		
	}
	
	//restriction max consumption on the day
	static public boolean restrictionMaxConsumptionDay(double totalConsumption) {
		
		return totalConsumption <= Pt_day;
	}
	
	//restriction max consumption on the night

	static public boolean restrictionMaxConsumptionNight(double totalConsumption) {
		
		return totalConsumption <= Pt_night;
	}
	
	
	//restriction watering motor execution slots -> returns the earliest time to start and the latest time to finish in the form of the slot position in the timeline
	//		                                        wateringHours[0] = earliest time to start | wateringHours[1] = latest time to finish
	static public int[] restrictionWateringHours() {
		
		int[] wateringHours = {12,19};
		
		return wateringHours;
	}
	
	
	//restriction for all equipments that only should work at the daytime (equipment that is turn on manually)
	static public boolean restrictionWorkInTheDaytime(equipment e) {
		
		return e.getWorkMode();
	}

}
