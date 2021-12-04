package main;
import java.util.ArrayList;

import algorithm.equipment;
import algorithm.restrictions;
import algorithm.objective_function;
import algorithm.tariff;
import algorithm.scheduling;


public class main {
	
	//global variables
	static tariff simple;
	static tariff bi_hourly;
	static tariff tri_hourly_summer;
	static tariff tri_hourly_winter;
	static equipment cylinder1;
	static equipment cylinder2;
	static equipment cylinder3;
	static equipment wateringMotor;
	static equipment heater1;
	static equipment heater2;
	static equipment heater3;
	static equipment washingMachine;
	static equipment dishwasher;
	static equipment fridge;
	static restrictions restrics;
	static objective_function obj_function;
	
	
	//main function
	public static void main(String[] args) {
		
		initSystem();
		
		
	}

	
	//initialization of the constant components of the system (tariffs, equipments, etc...)
	private static void initSystem() {
		
		scheduling scheduler = new scheduling();
		
		//objective_function init
		//obj_function = new objective_function();
		
		//restrictions init
		//restrics = new restrictions();
		//restrics.restrictionsInit();
		
		//tariffs init
		double[] priceT = new double[3];
		
		priceT[0] = 0.0786;
		simple = new tariff(1, 6.9, priceT);
		
		priceT[0] = 0.0327;
		priceT[1] = 0.108;
		bi_hourly = new tariff(2, 6.9, priceT);
		
		priceT[0] = 0.0327;
		priceT[1] = 0.0811;
		priceT[2] = 0.108;
		tri_hourly_summer = new tariff(3, 6.9, priceT);
		tri_hourly_winter = new tariff(4, 6.9, priceT);
		
		/*
		//tariffs prints
		System.out.println(simple.toString());
		System.out.println(bi_hourly.toString());
		System.out.println(tri_hourly_summer.toString());
		System.out.println(tri_hourly_winter.toString());
		*/
		
		//equipment init
		cylinder1 = new equipment("Cylinder1",1.5,6,6,false);
		scheduler.addEquipment(cylinder1);
		
		cylinder2 = new equipment("Cylinder2",1.5,6,6,false);
		scheduler.addEquipment(cylinder2);
		
		cylinder3 = new equipment("Cylinder3",1.5,6,6,false);
		scheduler.addEquipment(cylinder3);
		
		wateringMotor = new equipment("WateringMotor",1.15,2,2,false);
		scheduler.addEquipment(wateringMotor);
		
		heater1 = new equipment("Heater1",1,20,20,true);
		scheduler.addEquipment(heater1);
		
		heater2 = new equipment("Heater2",1,20,20,true);
		scheduler.addEquipment(heater2);
		
		heater3 = new equipment("Heater3",1,20,20,true);
		scheduler.addEquipment(heater3);
		
		washingMachine = new equipment("WashingMachine",1.5,2,2,true);
		scheduler.addEquipment(washingMachine);
		
		dishwasher = new equipment("Dishwasher",1.125,8,8,true);
		scheduler.addEquipment(dishwasher);
		
		fridge = new equipment("Fridge", 0.150, 48,48,false);
		scheduler.addEquipment(fridge);
		
//		scheduler.getEquipList().get(9).Xt[0] = true;
//		scheduler.getEquipList().get(9).calcCumulatedPower(0, 4, simple.timelineTariff);
		
		
		objective_function.calcFuncObj(scheduler.getEquipList());
		
		
		//test of acum calc
//		System.out.println(cylinder1.acumCons[1]);
//		cylinder1.Xt[1] = true;
//		scheduler.calcAcumCons(scheduler.getEquipList(), simple);
//		System.out.println(cylinder1.acumCons[1]);
		
		//equipment prints
//		for(int i = 0; i <scheduler.getEquipList().size(); i++) 
//			System.out.println(scheduler.getEquipList().get(i).toString());
//		
	

//		scheduler.randomSols(scheduler.getEquipList());
//		
//		//equipment prints
//		for(int i = 0; i <scheduler.getEquipList().size(); i++) 
//			System.out.println(scheduler.getEquipList().get(i).toString());	
		
		//sh.randomSols();
		
	}

}
