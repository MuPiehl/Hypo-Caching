package de.vergleich.fedex.hypo_caching.whackamole;

import java.util.ArrayList;
import java.util.List;

import de.vergleich.fedex.hypo_caching.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Playground extends View {

	private static final int MAX_AMOUNT_OF_MOLES = 9;
	private static final Paint EARTH = PaintColor.rgb(165, 114, 0);
	private static final Paint BLACK = PaintColor.rgb(0, 0, 0);
	private static final int PADDING_LEFT = 20;
	private static final int PADDING_TOP = 100;
	private static final int NUMBER_OF_MOLES = 4;

	private static final long INTERVAL = 5000;

	private final List<MoleHole> moleHoles;
	private boolean allSelected = false;
	private boolean stop = false;

	public Playground(Context context, AttributeSet attr) {
		super(context, attr);
		moleHoles = new ArrayList<MoleHole>();
		initMoleHoles();
		auswahl();
		new AsyncAuswahlTask().execute();
		Log.d(getClass().getSimpleName(), "Thread startet");
	}

	private void auswahl() {
		if (allSelected || stop) {
			return;
		}
		int count = 0;
		int countActive = 0;
		String tag = "Auswahl";
		while (count < NUMBER_OF_MOLES) {
			Log.d(tag, count + " ist kleiner " + NUMBER_OF_MOLES);
			int random = (int) Math.round((Math.random() * 8));
			Log.d(tag, "Random : " + random);
			if (random < moleHoles.size()) {
				Log.d(tag, "Random Valid");
				MoleHole hole = moleHoles.get(random);
				if (!hole.isActive()) {
					Log.d(tag, "Hole set Active");
					hole.setActive(true);
					count++;
				} else {
					Log.d(tag, random + " ist schon active");
					countActive++;
				}
			}
			if (countActive == MAX_AMOUNT_OF_MOLES) {
				allSelected = true;
				break;
			}
		}
	}

	public Playground(Context context) {
		this(context, null);
	}

	public void stop(){
		stop = true;
		Log.d("","Playground stop");
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawPlayGround(canvas);
		drawMoleHoles(canvas);
		canvas.drawText(getHintCount(), 50, 50, BLACK);
		if(stop){
			canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.done), 10,150,EARTH);
		}
//		canvas.drT
	}

	private String getHintCount() {
		int count = 0;
		for (MoleHole moleHole : moleHoles) {
			count += moleHole.getHitCount();
		}
		return "Hits : " + count;
	}

	private void drawPlayGround(Canvas canvas) {
		int posRight = canvas.getWidth();
		int posBottom = canvas.getHeight();
		canvas.drawRect(0, 0, posRight, posBottom, EARTH);
	}

	private void initMoleHoles() {

		int xSpace = 140;
		int ySpace = 150;

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int xPos = PADDING_LEFT + (x * xSpace);
				int yPos = PADDING_TOP + (y * ySpace);
				MoleHole molehole = new MoleHole(xPos, yPos, getResources());
				moleHoles.add(molehole);
			}
		}
		Log.i("InitMoleHoles", moleHoles.toString());
	}

	private void drawMoleHoles(Canvas canvas) {
		for (MoleHole molehole : moleHoles) {
			molehole.draw(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean dinge = super.onTouchEvent(event);
		final float eventX = event.getX();
		final float eventY = event.getY();
		final int action = event.getAction();

		boolean anyChange = false;
		int countDeselected = 0;
		if (MotionEvent.ACTION_DOWN == action) {
			for (MoleHole molehole : moleHoles) {
				boolean hit = molehole.check(eventX, eventY);
				if (hit) {
					anyChange = true;
				}
				if (!molehole.isActive()) {
					countDeselected++;
				}
			}
		}
		if (countDeselected == MAX_AMOUNT_OF_MOLES) {
			stop = true;
			Log.d(getClass().getSimpleName(), "Game End");
		 }

		if (anyChange) {
			allSelected = false;
		}
		invalidate();

		return dinge;
	}

	private class AsyncAuswahlTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				Thread.sleep(INTERVAL);

			} catch (InterruptedException e) {
				Log.e(getClass().getSimpleName(), e.toString());
			}
			if(!stop){
				Log.i("Vor der Auswahl", moleHoles.toString());
				auswahl();
				Log.d(getClass().getSimpleName(), "Active Molehole Ausgewaehlt");
				Log.i("Nach der Auswahl", moleHoles.toString());
			} else{
				Log.d("", "Auswahl Runs obwohl gestoppt");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {	
			if(!stop){
				new AsyncAuswahlTask().execute();
				Log.d("" ,"Start new Auswahl Task");
			}
		}

	}
}
