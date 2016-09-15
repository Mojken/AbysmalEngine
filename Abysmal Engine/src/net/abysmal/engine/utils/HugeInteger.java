package net.abysmal.engine.utils;

import java.util.ArrayList;

public class HugeInteger {

	public ArrayList<Short> number = new ArrayList<Short>();

	public HugeInteger() {
		number.add(0, (short) 0);
	}

	public HugeInteger(short[] value) {
		int length = value.length;
		if (value != null && value.length != 0) { 
			for (int i = 0; i < length; i++) {
				number.add(i, value[0]);
			}
		}
	}

	public void add(HugeInteger a) {
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
}
