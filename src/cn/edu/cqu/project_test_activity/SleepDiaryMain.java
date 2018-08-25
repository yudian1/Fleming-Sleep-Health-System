package cn.edu.cqu.project_test_activity;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SleepDiaryMain extends Activity {
	private String text;
	private Button day01;
	private Button day02;
	private Button day03;
	private Button day04;
	private Button day05;
	private Button day06;
	private Button day07;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sleepdiarymain);
       
        day01 = (Button) findViewById(R.id.day01);
        day02 = (Button) findViewById(R.id.day02);
        day03 = (Button) findViewById(R.id.day03);
        day04 = (Button) findViewById(R.id.day04);
        day05 = (Button) findViewById(R.id.day05);
        day06 = (Button) findViewById(R.id.day06);
        day07 = (Button) findViewById(R.id.day07);
        day01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day01.getText().toString();
				intent();
			}
		});
        day02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day02.getText().toString();
				intent();
			}
		});
        day03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day03.getText().toString();
				intent();
			}
		});
        day04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day04.getText().toString();
				intent();
			}
		});
        day05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day05.getText().toString();
				intent();
			}
		});
        day06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day06.getText().toString();
				intent();
			}
		});
        day07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				text = day07.getText().toString();
				intent();
			}
		});
    }
    void intent(){
    	Intent intent= new Intent();
    	Bundle bundle = new Bundle();
    	bundle.putString("text", text);
		intent.setClass(SleepDiaryMain.this, SleepDiaryActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
    }
    
}
