package net.abysmal.engine.utils;

import java.util.ArrayList;

public class HugeInteger {

	public ArrayList<Short> number = new ArrayList<Short>();

	public HugeInteger() {
		number.add(0, (short) 0);
	}
	
	public HugeInteger(short value) {
		number.add(0, (short) value);
		checkOverflow();
	}

	public HugeInteger(short[] value) {
		int length = value.length;
		if (value != null && value.length != 0) { 
			for (int i = 0; i < length; i++) {
				number.add(i, value[0]);
			}
		}
	}

	public HugeInteger add(HugeInteger a) {
		a.checkOverflow();
		checkOverflow();
		if (number.size() > a.number.size()) {
			for (int i = 0; i < a.number.size(); i++) {
				number.set(i, (short) (number.get(i) + a.number.get(i)));
			}
		} else {
			for (int i = 0; i < number.size(); i++) {
				number.set(i, (short) (number.get(i) + a.number.get(i)));
			}
			for (int i = number.size(); i < a.number.size(); i++) {
				number.set(i, (short) a.number.size());
			}
		}
		checkOverflow();
		return this;
	}

	//TODO Probably shitty, no carry etc.
	public HugeInteger sub(HugeInteger a) {
		a.checkOverflow();
		checkOverflow();
		if (number.size() > a.number.size()) {
			for (int i = 0; i < a.number.size(); i++) {
				number.set(i, (short) (number.get(i) - a.number.get(i)));
			}
		} else {
			for (int i = 0; i < number.size(); i++) {
				number.set(i, (short) (number.get(i) - a.number.get(i)));
			}
			for (int i = number.size(); i < a.number.size(); i++) {
				number.set(i, (short) a.number.size());
			}
		}
		checkOverflow();
		return this;
	}
	
	public void checkOverflow() {
		for (int i = 0; i < number.size(); i++) {
			if (Math.abs(number.get(i)) >= 10000) {
				byte carry = (byte) (Math.abs(number.get(i)) / 10000);
				if (number.get(i) >= 10000 && number.size() - 1 > i) {
					number.set(i, (short) (number.get(i) - carry * 10000));
					number.set(i + 1, (short) (number.get(i + 1) + carry));
				} else if (number.get(i) >= 10000) {
					number.set(i, (short) (number.get(i) - carry * 10000));
					number.add((short) carry);
				}
				if (number.get(i) <= -10000 && number.size() - 1 > i) {
					number.set(i, (short) (number.get(i) + carry * 10000));
					number.set(i + 1, (short) (number.get(i + 1) - carry));
				} else if (number.get(i) <= -10000) {
					number.set(i, (short) (number.get(i) + carry * 10000));
					number.add((short) -carry);
					System.out.print("did it");
				}
			}
		}
	}

	@Override
	public String toString() {
		String numberS = "";
		for (int i = 0; i < number.size(); i++) {
			Short abs = (short) (Math.abs(number.get(i)));
			String currentSegment = abs.toString();
			while (currentSegment.length() < 4 && number.size() - 1 > i)
				currentSegment = "0" + currentSegment;
			numberS = currentSegment + numberS;
		}
		if (Math.abs(number.get(number.size() - 1)) != number.get(number.size() - 1))
			numberS = "-" + numberS;
		return numberS;
	}

	public boolean largerThanOrEqualTo(HugeInteger cost) {
		byte longest = this.number.size() > cost.number.size() ? (byte)1 : this.number.size() < cost.number.size() ? (byte)2 : 0;
		int sizeL = 0;
		int sizeS = 0;
		if(longest == 1 || longest == 0){
			sizeL = this.number.size();
			sizeS = cost.number.size();
		}
		else{
			sizeL = cost.number.size();
			sizeS = this.number.size();
		}
		for(int i = sizeL-1; i >= sizeS; i--){
			if(longest == 1){
				if(number.get(i) > 0) return true;
			}else if(longest == 0){
				if(cost.number.get(i) > 0) return false;
			}
		}
		
		for(int i = sizeS-1; i >= 0; i--){
			if(number.get(i) > cost.number.get(i)) return true;
			else if(number.get(i) < cost.number.get(i)) return false;
		}
		
		return true;
	}
}