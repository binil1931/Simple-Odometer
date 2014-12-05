package com.binil.counter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{


	Button btnStart;
	TextView txtCounter;
	TextView txtCounter_digit;
    TextView txtCounter_decimal;
    
	int counter =  1;
	float num = (float) 1052.52;

	float dec_counter= (float) 0.01;
	int num_counter = 1;
	int rem;
	String value ;
	
	float final_value = 0;

	boolean status = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnStart = (Button) findViewById(R.id.btnStart);
		txtCounter = (TextView) findViewById(R.id.txtCounter);
		txtCounter_digit = (TextView) findViewById(R.id.txtCounter_digit);
		txtCounter_decimal = (TextView) findViewById(R.id.txtCounter_decimal);
		try
		{
		String numberD = String.valueOf(num);
		numberD = numberD.substring ( numberD.indexOf ( "." ) );
		Log.e("tag ", "reminder " + numberD);
        if(numberD.equalsIgnoreCase(".0"))  
        {
        	txtCounter.setVisibility(View.INVISIBLE);
        	txtCounter_decimal.setVisibility(View.INVISIBLE);
        }
		
		String decimal = numberD.substring(1, 3);
		rem = Integer.parseInt(decimal);
		
		
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
	}

	public void start(View v)
	{
		if(status == false)
		{       
			btnStart.setText("Stop");
			status = true;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					while(status!=false)
					{
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

									if(num_counter <= num)
									{
										String s_digit = String.format(""+num_counter);
										txtCounter_digit.setText(s_digit);
										num_counter = num_counter + 1;
									}

								
								
									
							}
						});
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					while(status!=false)
					{
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub

								

								
									if(counter <= rem)
									{

										String s_decimal = String.format("" +counter);
										txtCounter.setText(s_decimal);
										counter = counter + 1;
									}
									
							}
						});
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		else if (status == true)
		{
			btnStart.setText("Start");
			status = false;
			initCouner();
		}
	}

		private void initCouner() {
			// TODO Auto-generated method stub
			counter = 0;
			num_counter=0;
		}


}
