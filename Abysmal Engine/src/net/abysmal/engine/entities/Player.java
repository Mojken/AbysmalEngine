package net.abysmal.engine.entities;

public class Player extends Entity {

	int EXP, MP;
	public double movementSpeed = 5;
	public double sprintMultiplier = 2;
	public double crouchMultiplier = 0.3;

	public int getEXP() {
		return EXP;
	}

	public void setEXP(int EXP) {
		this.EXP = EXP;
	}
	
	public int getMP() {
		return MP;
	}

	public void setMP(int MP) {
		this.MP = MP;
	}
	
	public Player() {

	}

}