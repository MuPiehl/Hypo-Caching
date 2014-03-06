package de.vergleich.fedex.hypo_caching.whackamole;

import android.graphics.Color;
import android.graphics.Paint;

public class PaintColor extends Paint {
	private PaintColor(int red, int green, int blue) {
		super();
		setColor(Color.rgb(red, green, blue));
	}

	public static PaintColor rgb(int red, int green, int blue) {
		return new PaintColor(red, green, blue);
	}
}
