package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class photovoltaicPanel {
	
	
	int id;
	String panelType;
	double maxPower;
	double[] powerTimeline;
	double[] sumPowerTimeline;
	double[] acumCons;
	
	public photovoltaicPanel(int id, String panelType, double maxPower) {
		
		this.id = id;
		this.powerTimeline = new double[48];
		this.sumPowerTimeline = new double[48];
		this.acumCons = new double[48];
		this.panelType = panelType;
		this.maxPower = maxPower;
		this.generatePanel(this.panelType,this.maxPower);
	}
	
	
	private void generatePanel(String panelType, double maxPower) {
		
		for(int i = 0; i < 48; i++) {
			
			if(panelType == "Clear Sky") {
				
				double randomPer = -10;
				
				if(i < 12 || i > 35) {
					
					if((i > 7 && i < 12) || (i < 41 && i > 25)) {
						randomPer = ThreadLocalRandom.current().nextDouble(0.001, 0.005);
					}
					else {
						randomPer = ThreadLocalRandom.current().nextDouble(0.0001, 0.001);
					}
					
					
					powerTimeline[i] = maxPower*randomPer;
				}
				
				else if(i > 11 && i < 23) {
					
					while(randomPer < 0 || randomPer > maxPower)
						randomPer = ThreadLocalRandom.current().nextDouble((powerTimeline[i-1] + (5*maxPower/1000)), (powerTimeline[i-1] + (110*maxPower/1000)));
					
					powerTimeline[i] = randomPer;
					
				}
				else if(i > 22 && i < 25) {
					
					randomPer = ThreadLocalRandom.current().nextDouble(0.9, 0.98);
					powerTimeline[i] = maxPower*randomPer;
				}
				else if(i > 24 && i < 36) {
					
					while(randomPer < 0)
						randomPer = ThreadLocalRandom.current().nextDouble((powerTimeline[i-1] - (150*maxPower/1000)), (powerTimeline[i-1] + (5*maxPower/1000)));
					
					powerTimeline[i] = randomPer;
					
				}
				
				
				
			
			}
			
		}
	}
	
	public void calcCumulatedPower(double [] timelineTariff) {
		for (int i = 0; i < 48; i++) {
			
  			this.acumCons[i] = (this.sumPowerTimeline[i]/1000 * timelineTariff[i%48])/2;
		}
	}
	
	
	//All get functions
	
	public int getID() {
		return this.id;
	}
	
	public String getPanelType() {
		
		return this.panelType;
	}
	
	public double[] getPowerTimeline() {
		return this.powerTimeline;
	}
	
	public double[] getEquipsTimeline() {
		return this.sumPowerTimeline;
	}
	
	public double getMaxPower() {
		return this.maxPower;
	}
	
	//print equipment values
	public String toString() {
		
		String print = "Photovoltaic Panel: " + this.getID() + " ||MaxPower=" + this.getMaxPower() + " ||Situation=" + this.getPanelType()
		+"\nGenerated panel timeline:\n";
		
		for(int i = 0; i < 48; i++)
			print += "Power on slot no" + i + " :" + this.getPowerTimeline()[i] + "\n";
			
		
		print += "\nMoney saved by the panel timeline:\n";
		for(int i = 0; i < 48; i++)
			print += "Slot no" + i + " :" + this.getEquipsTimeline()[i] + "\n";
		
		
		return print;
	}
		
		
	


}
