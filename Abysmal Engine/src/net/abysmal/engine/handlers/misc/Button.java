package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.graphics.geometry.Square;
import net.abysmal.engine.handlers.HID.Mouse;
import net.abysmal.engine.maths.Vector;

public class Button {

	public Square bounds;

	public Button(Square bounds) {
		this.bounds = bounds;
	}

	public boolean update(Mouse m) {
		Vector pos = new Vector(m.getClickInfo()[1][0], m.getClickInfo()[1][1]);
			if (bounds.isWithin(pos)) {
				m.clearClickInfo(1);
				return true;
		}
		return false;
	}
}