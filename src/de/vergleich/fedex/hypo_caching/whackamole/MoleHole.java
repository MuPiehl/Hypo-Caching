package de.vergleich.fedex.hypo_caching.whackamole;

import java.util.Observable;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MoleHole extends Observable {

	private final int x;
	private final int y;
	private final int radiusHole = 30;
	private final int radiusMole = 20;

	private static final Paint HOLE = PaintColor.rgb(119, 81, 0);
	private static final Paint MOLE = PaintColor.rgb(119, 81, 0);

	private boolean active = false;
	private int hitCount = 0;

	public MoleHole(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, radiusHole, HOLE);
		if (active) {
			canvas.drawCircle(x, y, radiusMole, MOLE);
		}
	}

	public int getHitCount() {
		return hitCount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void check(int xClick, int yClick) {
		if (active) {
			final double deltaX = Math.pow((x - xClick), 2);
			final double deltaY = Math.pow((x - xClick), 2);

			final double calcRadius = Math.sqrt(deltaX + deltaY);

			if (calcRadius < radiusHole) {
				hitCount++;
			}
		}
	}
}
