package de.vergleich.fedex.hypo_caching.whackamole;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Playground extends View implements Observer{

	private static final Paint EARTH = PaintColor.rgb(165, 114, 0);
	private static final int PADDING = 10;
	
	private final List<MoleHole> moleHoles;

	public Playground(Context context, AttributeSet attr) {
		super(context, attr);
		moleHoles = new ArrayList<MoleHole>();
		initMoleHoles();		
		setOnClickListener(new PlaygroundClickListener());
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
	private void initMoleHoles() {
		
		int xSpace = getWidth() / 5;
		int ySpace = getHeight() / 5;

		for (int x = 1; x < 4; x++) {
			for (int y = 1; y < 4; y++) {
				int xPos = PADDING + (x * xSpace);
				int yPos = PADDING + (y * ySpace);
				MoleHole mole = new MoleHole(xPos, yPos);
				mole.addObserver(this);
			}
		}
	}

	private void drawMoleHoles(Canvas canvas) {
		for (MoleHole molehole : moleHoles) {
			molehole.draw(canvas);
		}
	}


	private void drawPlayGround(Canvas canvas) {
		int posLeft = PADDING;
		int posTop = PADDING;
		int posRight = PADDING + canvas.getWidth();
		int posBottom = PADDING + canvas.getHeight();
		canvas.drawRect(posLeft, posTop, posRight, posBottom, EARTH);
	}
	
	

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
	
	private class PlaygroundClickListener implements OnClickListener{

		@Override
		public void onClick(View view) {
//			mole.
		}
		
	}
}
