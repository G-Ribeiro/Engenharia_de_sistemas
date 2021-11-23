package algorithm;

//
//Information about each tariff we have
//


public class tariff {
	
	private int tarType;  // tariff type --> 1 = Simple | 2 = Bi-hourly | 3 = Summer Tri-hourly | 4 = Winter Tri-hourly
	private float[] price;  //Array prices that have the prices of the tariff.
									// Simple -> price[0] = normal price 
									// Bi-hourly -> price[0] = empty price | price[1] = outside empty price
									// Both tri-hourly -> price[0] = empty price | price[1] = full price | price[2] = tip/rush price
	private double availablePower; // Power stipulated
	
	//Constructor
	public tariff(int tarType,double availablePower, float[] price) {
		
		this.tarType = tarType;
		this.price = new float[price.length];
		this.price = price;
		this.availablePower = availablePower;
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
	
	public float[] getPrices() {
		return this.price;
	}
	
	public double getPowerAvailable() {
		return this.availablePower;
	}

}
