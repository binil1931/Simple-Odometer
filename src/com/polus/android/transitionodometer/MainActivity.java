package com.polus.android.transitionodometer;

import android.R.integer;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
	int i = 0,next = 1;
	int digit_count = 0;
	LinearLayout layout_odmeter;
	int number_of_digits = 0;
	int[] number;
	Boolean flag = false;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		layout_odmeter = (LinearLayout) findViewById(R.id.layout_odmeter);

		float digit =(float) 2548;
		int length = Integer.parseInt((digit+"").length()+"");

		Log.e("tag", "length " + length);
		digit_count = length - 1;
		number_of_digits =length ; 

		number = new int[length];
		for(int count = 0 ; count < length; count ++)
		{
			char a_char = (""+digit).charAt(count);
			number[count] = Character.getNumericValue(a_char); 

			Log.e("tag", "number a_char " + a_char);
			Log.e("tag", "number " + number[count]);

		}

		startanimation(number[0],1);
		Log.e("tag", "check");
	}

	private void startanimation(final int f, final int pos) 
	{
		Handler handler = new Handler();

		i=0;
		
		final TextView[] tv = new TextView[number_of_digits];
		LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		for(int l= 0 ; l<number_of_digits;l++)
		{
			tv[l]= new TextView(MainActivity.this);
			tv[l].setLayoutParams(lparams);
			tv[l].setId(100+l);
			tv[l].setTextColor(Color.parseColor("#FFFFFF"));
			layout_odmeter.addView(tv[l]);
		}

		try
		{		
			for(int j=0; j<=f; j++)
			{
				handler.postDelayed(new Runnable() 
				{
					@Override
					public void run() 
					{ 
						final Animation animation = new TranslateAnimation(0, 0, 0, 80);
						animation.setDuration(1000);
						animation.setRepeatCount(-1);

						tv[pos].setText(""+i);
						tv[pos].setAnimation(animation);

						Log.e("tag", "animatio i  "+i);
						Log.e("tag", "animatio f "+f);

						if(i == f )
						{
							Log.e("tag", "same  "+i);
							animation.cancel();
							if(digit_count > 0)
							{
								startanimation(number[next],2);
								next++;	
								digit_count--;
							}
						}
						else 
						{
							Log.e("tag", "NOT same  "+i);
							i++;
						}
					}
				}, 400*j );
			}
		}
		catch(NullPointerException e)
		{

		}
	}
}