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
	private String[] question = {"1����˯���ѣ�","2��ά��˯�����ѣ�","3�����ѣ�","4������Ŀǰ��˯��ģʽ����/������̶���Σ���",
			"5������Ϊ����ʧ���ڶ��̶���Ӱ���������ճ����ܣ�","6������ʧ������Ӱ�������������������������ڱ����������ʧ�̶߳���Σ�",
			"7������Ŀǰ��˯������ĵ���/ʹ��̶ȣ�"};
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
							.setTitle("��ܰ��ʾ")
							.setMessage("����û��ѡ���κ�һ��ѡ�")
							.setPositiveButton("ȷ��", null)
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
				System.out.println("ѡ��Ľ��----��" + resulttext[i]);
				if(i == 1){
					radioGroup.setVisibility(View.VISIBLE);
				}
				if(i == 4){
					button01.setText("������ ");
					button02.setText("����");
					button03.setText("һ��");
					button04.setText("������");
					button05.setText("�ܲ�����");
				}
				if(i == 5){
					button01.setText("û�и���");
					button02.setText("��΢");
					button03.setText("��Щ");
					button04.setText("�϶�");
					button05.setText("�ܶ����");
				}
				if(i > 5 && i < question.length + 1){
					button01.setText("û��");
					button02.setText("һ��");
					button03.setText("��Щ");
					button04.setText("�϶�");
					button05.setText("�ܶ�");
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
							.setTitle("��ܰ��ʾ")
							.setMessage("����û��ѡ���κ�һ��ѡ�")
							.setPositiveButton("ȷ��", null)
							.show();
							return;
						}
				RelativeLayout ll = (RelativeLayout) findViewById(R.id.linearLayout);
				ll.setVisibility(View.INVISIBLE);
				TextView tv = (TextView) findViewById(R.id.resulttitle);
				tv.setVisibility(View.VISIBLE);
				score += j;
				tv.setText("���ĵ÷�Ϊ��" + score);
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
	//���Ҫ���������
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
				System.out.println("��ǰID----" + pd.getMaxId());
				Toast.makeText(SleepnessActivity.this, "��ʧ�̶߳����������ݱ���ɹ���", Toast.LENGTH_SHORT).show();
				
				//���������㲥
				Intent intent = new Intent(action3);
				//intent.putExtra("data3", "data3");
				sendBroadcast(intent);
				setAlarmTime(SleepnessActivity.this);
			}

			
			public void setAlarmTime(Context context) {
				//��ȡϵͳ�����ӷ���
				AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				//��������
				Intent intent = new Intent(AlarmAction3);
				//
				PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				//int interval = 1000 * 60 * 60 * 24;// 24Сʱ
				//24Сʱ̫���ˣ������Ȳ���1���ӵ��ܲ���ʵ��
				int interval = 1000 * 60;// 24Сʱ
				//�������ӣ�System.currentTimeMillis() + intervalϵͳ��ǰʱ��+24Сʱ
				am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);
				   
			}
}
