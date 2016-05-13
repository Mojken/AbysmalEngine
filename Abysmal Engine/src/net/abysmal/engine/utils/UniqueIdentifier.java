package net.abysmal.engine.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueIdentifier {

	private static List<Short>	ids		= new ArrayList<Short>();
	private static int				index	= 0;

	static {
		for (short i = Short.MIN_VALUE; i < Short.MAX_VALUE; i++){
			ids.add(i);
		}
		Collections.shuffle(ids);
	}

	private UniqueIdentifier() {}

	public static int getIdentifier() {
		if (index > ids.size() - 1) index = 0;
		return ids.get(index++);
	}
}