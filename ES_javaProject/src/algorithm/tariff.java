package algorithm;

//
//Information about each tariff we have
//


public class tariff {
	
	private int tarType;  // tariff type --> 1 = Simple | 2 = Bi-hourly | 3 = Summer Tri-hourly | 4 = Winter Tri-hourly
	private double[] price;  //Array prices that have the prices of the tariff.
									// Simple -> price[0] = normal price 
									// Bi-hourly -> price[0] = empty price | price[1] = outside empty price
									// Both tri-hourly -> price[0] = empty price | price[1] = full price | price[2] = tip/rush price
	private double availablePower; // Power stipulated
	public double[] timelineTariff;
	
	//Constructor
	public tariff(int tarType,double availablePower, double[] price) {
		
		this.tarType = tarType;
		this.price = new double[3];
		this.price = price;
		this.availablePower = availablePower;
		this.timelineTariff = new double[48];
		
		if(tarType == 1) {
			for(int i = 0; i < timelineTariff.length; i++)
				this.timelineTariff[i] = price[0];
		
		}
		else if(tarType == 2) {
			for(int i = 0; i < 16; i++)
				this.timelineTariff[i] = price[0];
			
			for(int i = 16; i < 44; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 44; i < 48; i++)
				this.timelineTariff[i] = price[0];
			
		}
		
		else if(tarType == 3) {
			for(int i = 0; i < 16; i++)
				this.timelineTariff[i] = price[0];
			
			for(int i = 16; i < 21; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 21; i < 26; i++)
				this.timelineTariff[i] = price[2];
			
			for(int i = 26; i < 39; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 39; i < 42; i++)
				this.timelineTariff[i] = price[2];
			
			for(int i = 42; i < 44; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 44; i < 48; i++)
				this.timelineTariff[i] = price[0];
			
		}
		
		else if(tarType == 4) {
			for(int i = 0; i < 16; i++)
				this.timelineTariff[i] = price[0];
			
			for(int i = 16; i < 18; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 18; i < 21; i++)
				this.timelineTariff[i] = price[2];
			
			for(int i = 21; i < 36; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 36; i < 41; i++)
				this.timelineTariff[i] = price[2];
			
			for(int i = 41; i < 44; i++)
				this.timelineTariff[i] = price[1];
			
			for(int i = 44; i < 48; i++)
				this.timelineTariff[i] = price[0];
			
		}
	}
	
	
	//All 'gets' for the info of all tariffs
	
	public String getTariffTypeByName() {
		
		String result = "";
		
		if(this.tarType == 1)
			result = "Simple";
		else if(this.tarType == 2)
			result = "Bi-hourly";
		else if(this.tarType == 3)
			result = "Summer Tri-hourly";
		else if(this.tarType == 4)
			result = "Winter Tri-hourly";
		
		return result;
	}
	
	public int getTariffType() {
		return this.tarType;
	}
	
	public double[] getPrices() {
		return this.price;
	}
	
	public double getPowerAvailable() {
		return this.availablePower;
	}
	
	
	public double[] getTimelineTariff() {
		return timelineTariff;
	}


	//print tariff values
	public String toString() {
		String print = "Tariff nº: " + this.getTariffType() +  " - " + this.getTariffTypeByName() + " || Power=" + this.getPowerAvailable() + "kW || ";
		
		if(this.getTariffType() == 1)
			print += "Price=" + this.getPrices()[0] + "€";
		
		else if(this.getTariffType() == 2)
			print += "PriceEmpty=" + this.getPrices()[0] + "€ ; " + "PriceOutside=" + this.getPrices()[1] + "€";
		
		else if(this.getTariffType() == 3 || this.getTariffType() == 4)
			print += "PriceEmpty=" + this.getPrices()[0] + "€ ; " + "PriceFull=" + this.getPrices()[1] + "€ ; " + "PriceRush=" + this.getPrices()[2] + "€";
		
		print += "\n---Timeline of Prices---\nEach timeslot represents 30 mins of the day\n";
		
		for(int i = 0; i < 48; i++) {
			print += "Slot nº" + i + " : Price=" + this.timelineTariff[i] + "€\n";
		}
			
			
		return print;
		
		
	}

}
