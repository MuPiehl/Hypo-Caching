package de.vergleich.fedex.hypo_caching.whackamole;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Playground extends View implements Observer{

	private static final Paint EARTH = PaintColor.rgb(165, 114, 0);
	private static final Paint MOLEHOLE = PaintColor.rgb(119, 81, 0);
	private static final int PADDING = 10;

	public Playground(Context context, AttributeSet attr) {
		super(context, attr);
	}

	public Playground(Context context) {
		this(context, null);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawPlayGround(canvas);
		drawMoleHoles(canvas);
	}

	private void drawMoleHoles(Canvas canvas) {
		int xSpace = canvas.getWidth() / 5;
		int ySpace = canvas.getHeight() / 5;

		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < 4; y++) {
				int xPos = PADDING + (x * xSpace);
				int yPos = PADDING + (y * ySpace);
				drawMoleHole(canvas, xPos, yPos);
			}
		}
	}

	private void drawMoleHole(Canvas canvas, int xPos, int yPos) {
		canvas.drawCircle(xPos, yPos, 30, MOLEHOLE);
	}

	private void drawPlayGround(Canvas canvas) {
		int posLeft = PADDING;
		int posTop = PADDING;
		int posRight = PADDING + canvas.getWidth();
		int posBottom = PADDING + canvas.getHeight();
		canvas.drawRect(posLeft, posTop, posRight, posBottom, EARTH);
	}
	
	private static class PaintColor extends Paint{
		private PaintColor(int red, int green, int blue) {
			super();
			setColor(Color.rgb(red, green, blue));
		}
				
		public static PaintColor rgb(int red, int green, int blue){
			return new PaintColor(red, green, blue);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
}
