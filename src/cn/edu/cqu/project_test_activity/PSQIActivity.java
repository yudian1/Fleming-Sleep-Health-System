package cn.edu.cqu.project_test_activity;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_dao.PatientTestDAO;
import cn.edu.cqu.project_test_model.PatientTest;

public class PSQIActivity extends Activity{
	public static final String action2 = "broadcast.action2";
	public static final String AlarmAction2 = "alarm.action2";
	private RelativeLayout rl;
	private RelativeLayout rl2;
	private int i = 0,j;
	private String [] question = {"2、在最近一个月中，您从上床到入睡需要时间","3、在最近一个月中，您早上起床时间","4、在最近一个月中，您夜间实际睡眠时间",
			"5、最近一个月中，您是否出现下列情况，并描述其程度：","1)不能在30分钟内入睡","2)在晚上睡眠过程中醒来或早醒","3)晚上起床上洗手间",
			"4)晚上睡觉时出现呼吸不畅","5)晚上睡觉出现大声咳嗽或鼾声","6)晚上睡觉感到寒冷","7)晚上睡觉感到太热","8)晚上睡觉做恶梦","9)晚上睡觉身上出现疼痛不适",
			"10)其他影响睡眠情况","11)是否要服药物才能入睡","12)是否在开车、吃饭、或参加社会活动时时常感到困倦","13)在积极完成事情上是否感到精力不足",
			"14)是与人同睡一床，或有室友","6、在最近一个月中，总的来说，您认为自己的睡眠质量："};
	private RadioGroup radioGroup;
	private RadioButton button01 ;
	private RadioButton button02 ;
	private RadioButton button03 ;
	private RadioButton button04;
	private RadioButton button05;
	private RadioButton button06;
	private RadioButton button07;
	private String buttontext;
	private int resulttext[] = new int[20];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.psqi);
		
		final TextView questiontext = (TextView) findViewById(R.id.textview);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		button05 = (RadioButton) findViewById(R.id.button05);
		button06 = (RadioButton) findViewById(R.id.button06);
		button07 = (RadioButton) findViewById(R.id.button07);
		rl = (RelativeLayout) findViewById(R.id.relativeLayout);
		rl2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
		rl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				rl.setVisibility(View.GONE);
				rl2.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		final Button resultbutton = (Button) findViewById(R.id.reslutbutton);
		
		final Button nextButton = (Button) findViewById(R.id.nextbutton);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//questiontext.setText(question[i]);	
				//i ++;
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false&& button05.isChecked()==false&& button06.isChecked()==false&& button07.isChecked()==false && i != 4){
							
							new AlertDialog.Builder(PSQIActivity.this)
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
							.show();
							return;
						}
				else{
					questiontext.setText(question[i]);	
					i++;
					radioGroup.clearCheck();
				}
				resulttext[i]= j;
				System.out.println("选择的结果----》" + "i"+ resulttext[i]);
				if(i == 1){
					button01.setText("小于15分钟");
					button02.setText("16-30分钟");
					button03.setText("31-45分钟");
					button04.setText("46-60分钟");
					button05.setText("1小时以上");
					//button06.setVisibility(View.GONE);
					//button07.setVisibility(View.GONE);
				}
				if(i == 2){
					button01.setText("5点以前");
					button02.setText("5-6点");
					button03.setText("6-7点");
					button04.setText("7-8点");
					button05.setText("8-9点");
				}
				if(i == 3 ){
					//radioGroup.setVisibility(View.GONE);
					button01.setText("小于4小时");
					button02.setText("4-6小时");
					button03.setText("6-8小时");
					button04.setText("8-10小时");
					button05.setText("10小时以上");
				}
				if(i == 4){
					radioGroup.setVisibility(View.GONE);
				}
				if(i > 4 && i < question.length){
					radioGroup.setVisibility(View.VISIBLE);
					//button03.setVisibility(View.GONE);
					button06.setVisibility(View.VISIBLE);
					button07.setVisibility(View.VISIBLE);
					button01.setText("1次/周");
					button02.setText("2次/周");
					button03.setText("3次/周");
					button04.setText("4次/周");
					button05.setText("5次/周");
					button06.setText("6次/周");
					button07.setText("7次/周");
				}
				if(i == question.length){
					nextButton.setVisibility(View.GONE);
					resultbutton.setVisibility(View.VISIBLE);
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
					button05.setVisibility(View.GONE);
					button06.setVisibility(View.GONE);
					button07.setVisibility(View.GONE);
					button01.setText("很好");
					button02.setText("较好");
					button03.setText("较差");
					button04.setText("很差");
				}	
			}
		});
		resultbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false&& button05.isChecked()==false&& button06.isChecked()==false&& button06.isChecked()==false){
							
							new AlertDialog.Builder(PSQIActivity.this)
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
							.show();
							return;
						}
				//radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
				resulttext[19]= j;
//				for (int i = 0; i < resulttext.length; i++) {
//					System.out.println("resulttext----" + resulttext[i]);
//				}
				PInformation();
				finish();
			}
		});
	}

	public class RadioGroupListener implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			if(checkedId == button01.getId()){
				j = 0;
				buttontext = button01.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
			if(checkedId == button02.getId()){
				j = 1;
				buttontext = button02.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
			if(checkedId == button03.getId()){
				j = 2;
				buttontext = button03.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
			if(checkedId == button04.getId()){
				j = 3;
				buttontext = button04.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
			if(checkedId == button05.getId()){
				j = 4;
				buttontext = button05.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
			if(checkedId == button06.getId()){
				j = 5;
				buttontext = button06.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
			if(checkedId == button07.getId()){
				j = 6;
				buttontext = button07.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
		}
		
	}
	//获得要保存的数据
		public void PInformation(){
			int[] result = new int[19];
			result[0] = resulttext[0];
			result[1] = resulttext[1];
			result[2] = resulttext[2];
			result[3] = resulttext[3];
			result[4] = resulttext[5];
			result[5] = resulttext[6];
			result[6] = resulttext[7];
			result[7] = resulttext[8];
			result[8] = resulttext[9];
			result[9] = resulttext[10];
			result[10] = resulttext[11];
			result[11] = resulttext[12];
			result[12] = resulttext[13];
			result[13] = resulttext[14];
			result[14] = resulttext[15];
			result[15] = resulttext[16];
			result[16] = resulttext[17];
			result[17] = resulttext[18];
			result[18] = resulttext[19];
			
			String pqsi1 = "";
			for(int i = 0;i < result.length;i++){
				pqsi1 += String.valueOf(result[i]);
			}
			System.out.println("pqsi----->" + pqsi1);
			PatientTest patientTest = new PatientTest();
			patientTest.setPqsi(pqsi1);
			PatientTestDAO ptdao = new PatientTestDAO(PSQIActivity.this);
			PatientInformationDAO pd = new PatientInformationDAO(PSQIActivity.this);
			ptdao.updatePQSI(pd.getMaxId(), patientTest);
			System.out.println("当前ID----" + pd.getMaxId());
			Toast.makeText(PSQIActivity.this, "【睡眠治疗评估】数据保存成功！", Toast.LENGTH_SHORT).show();
			
			//发送两个广播
			Intent intent = new Intent(action2);
			sendBroadcast(intent);
			setAlarmTime(PSQIActivity.this);
		}
		
		public void setAlarmTime(Context context) {
			//获取系统的闹钟服务
			AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			//过滤条件
			Intent intent = new Intent(AlarmAction2);
			//
			PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
			//int interval = 1000 * 60 * 60 * 24;// 24小时
			//24小时太长了，还是先测试1分钟的能不能实现
			int interval = 1000 * 60;// 24小时
			//设置闹钟，System.currentTimeMillis() + interval系统当前时间+24小时
			am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);
			   
		}
		
}
