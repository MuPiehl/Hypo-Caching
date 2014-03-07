package de.vergleich.fedex.hypo_caching.whackamole;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import de.vergleich.fedex.hypo_caching.R;

public class MoleHole {

	private final int x;
	private final int y;
	private final int radiusHole = 50;

	private static final Paint HOLE = PaintColor.rgb(119, 81, 0);

	private boolean active = false;
	private int hitCount = 0;
	private final Bitmap mole;
	private final Bitmap hole;

	public MoleHole(int x, int y, Resources resources) {
		this.x = x + radiusHole;
		this.y = y + radiusHole;
		hole = BitmapFactory
				.decodeResource(resources, R.drawable.whackamole_60);
		mole = BitmapFactory.decodeResource(resources,
				R.drawable.whackamole_mole60);
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(hole, x - 60, y - 30, HOLE);
		if (active) {
			canvas.drawBitmap(mole, x - 10, y - 40, HOLE);
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

	public boolean check(float eventX, float eventY) {
		if (active) {
			final double deltaX = Math.pow((x - eventX), 2);
			final double deltaY = Math.pow((y - eventY), 2);

			final double calcRadius = Math.sqrt(deltaX + deltaY);

			if (calcRadius < radiusHole) {
				hitCount++;
				active = false;
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "[active=" + (active ? "true" : "false") + "  " + x + "|" + y
				+ "]";
	}
}
