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

public class SleepnessActivity extends Activity{
	public static final String action3 = "broadcast.action3";
	public static final String AlarmAction3 = "alarm.action3";
	private RelativeLayout rl;
	private RelativeLayout rl2;
	private int i = 0;
	private String[] question = {"1、入睡困难：","2、维持睡眠困难：","3、早醒：","4、您对目前的睡眠模式满意/不满意程度如何？：",
			"5、您认为您的失眠在多大程度上影响了您的日常功能：","6、您的失眠问题影响了您的生活质量，您觉得在别人眼中你的失眠程度如何？",
			"7、您对目前的睡眠问题的担心/痛苦程度："};
	private Button resultbutton;
	private int j,score;
	private String buttontext;
	private int resulttext[] = new int[10];
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private RadioButton button05;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleepness);
		
		final TextView questiontext = (TextView) findViewById(R.id.questiontitle);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		button05 = (RadioButton) findViewById(R.id.button05);
		resultbutton = (Button) findViewById(R.id.resultbutton);
		rl = (RelativeLayout) findViewById(R.id.relativeLayout);
		rl2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
		radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
		rl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				rl.setVisibility(View.INVISIBLE);
				rl2.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		final Button nextbutton = (Button) findViewById(R.id.nextbutton);
		nextbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//questiontext.setText(question[i]);	
				//i ++;
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false&& button05.isChecked()==false && i != 0){
							
							new AlertDialog.Builder(SleepnessActivity.this)
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
				score += j;
				System.out.println("result----->" + score);
				resulttext[i]= j;
				System.out.println("选择的结果----》" + resulttext[i]);
				if(i == 1){
					radioGroup.setVisibility(View.VISIBLE);
				}
				if(i == 4){
					button01.setText("很满意 ");
					button02.setText("满意");
					button03.setText("一般");
					button04.setText("不满意");
					button05.setText("很不满意");
				}
				if(i == 5){
					button01.setText("没有干扰");
					button02.setText("轻微");
					button03.setText("有些");
					button04.setText("较多");
					button05.setText("很多干扰");
				}
				if(i > 5 && i < question.length + 1){
					button01.setText("没有");
					button02.setText("一点");
					button03.setText("有些");
					button04.setText("较多");
					button05.setText("很多");
				}
				if(i == question.length){
//					radioGroup.setVisibility(View.GONE);
					nextbutton.setVisibility(View.INVISIBLE);
					resultbutton.setVisibility(View.VISIBLE);
				}
			}
		});
		resultbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false&& button05.isChecked()==false){
							
							new AlertDialog.Builder(SleepnessActivity.this)
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
							.show();
							return;
						}
				RelativeLayout ll = (RelativeLayout) findViewById(R.id.linearLayout);
				ll.setVisibility(View.INVISIBLE);
				TextView tv = (TextView) findViewById(R.id.resulttitle);
				tv.setVisibility(View.VISIBLE);
				score += j;
				tv.setText("您的得分为：" + score);
				resulttext[8] = j;
				PInformation();
				resultbutton.setVisibility(View.INVISIBLE);
//				finish();
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
				System.out.println("result11----->" + j);
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
		}
		
	}
	//获得要保存的数据
			public void PInformation(){
				int[] result = new int[7];
				result[0] = resulttext[2];
				result[1] = resulttext[3];
				result[2] = resulttext[4];
				result[3] = resulttext[5];
				result[4] = resulttext[6];
				result[5] = resulttext[7];
				result[6] = resulttext[8];
				String sleepness = "";
				for(int i = 0;i < result.length;i++){
					sleepness += String.valueOf(result[i]);
				}
				System.out.println("sleepness----->" + sleepness);
				PatientTest patientTest = new PatientTest();
				patientTest.setSleepness(sleepness);
				PatientTestDAO ptdao = new PatientTestDAO(SleepnessActivity.this);
				PatientInformationDAO pd = new PatientInformationDAO(SleepnessActivity.this);
				ptdao.updateSleepness(pd.getMaxId(), patientTest);
				System.out.println("当前ID----" + pd.getMaxId());
				Toast.makeText(SleepnessActivity.this, "【失眠程度评估】数据保存成功！", Toast.LENGTH_SHORT).show();
				
				//发送两个广播
				Intent intent = new Intent(action3);
				//intent.putExtra("data3", "data3");
				sendBroadcast(intent);
				setAlarmTime(SleepnessActivity.this);
			}

			
			public void setAlarmTime(Context context) {
				//获取系统的闹钟服务
				AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				//过滤条件
				Intent intent = new Intent(AlarmAction3);
				//
				PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				//int interval = 1000 * 60 * 60 * 24;// 24小时
				//24小时太长了，还是先测试1分钟的能不能实现
				int interval = 1000 * 60;// 24小时
				//设置闹钟，System.currentTimeMillis() + interval系统当前时间+24小时
				am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);
				   
			}
}
