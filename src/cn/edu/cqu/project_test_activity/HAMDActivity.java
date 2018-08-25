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

public class HAMDActivity extends Activity{
	public static final String action5 = "broadcast.action5";
	public static final String AlarmAction5 = "alarm.action5";
	private RadioButton button00;
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private Button resultButton;
	private RadioGroup radioGroip;
	private RelativeLayout rl;
	private RelativeLayout rl2;
	private String[] questiontitle = {"2.����У���1�����Լ����е��Լ����������ˣ���2����Ϊ�Լ�������򷴸�˼�������Ĺ�ʧ�ʹ��󣻣�3����ΪĿǰ�ļ������Ƕ��Լ�����ĳͷ�������������룻��4������������ָ�����в�Իþ���",
			"3����ɱ����1�����û��û�����壻��2��ϣ������ȥ�����뵽�����йص��£���3�����������ɱ��ͷ������4��������ɱ��Ϊ��",
			"4����˯���ѣ�����ʧ�ߣ�����1����������˯���ѣ��ϴ���Сʱ���Բ�����˯����Ҫע��ƽʱ������˯��ʱ�䣩����2������ÿ�������˯���ѡ�",
			"5��˯�߲���ж�ʧ�ߣ�����1��˯��ǳ������Σ���2����ҹ����12������ǰ�����������������ϲ�������",
			"6�����ѣ�ĩ��ʧ�ߣ�����1�������ѣ���ƽʱ����1Сʱ������������˯��Ӧ�ų�ƽʱ��ϰ�ߣ�����2�����Ѻ��޷�������˯��",
			"7����������Ȥ����1������ʱ����������2���Է���ֱ�ӻ��ӱ��Ի��������ѧϰʧȥ��Ȥ����е�û����ʣ���ԥ���������ܼ�ֻ���ǿ���Լ�ȥ����������3���ʱ����ٻ��Ч�½���סԺ����ÿ��μӲ����Ͷ������ֲ���3Сʱ����4����Ŀǰ�ļ�����ֹͣ������סԺ�߲��μ��κλ����û�����˰����㲻����ɲ����ճ�����ע�ⲻ�ܷ�סԺ�ʹ�4�֣���",
			"8�����ͣ�ָ˼ά�����ﻺ����ע�������Լ��У������Լ��ˣ�����1���������з���������ͣ���2���������з����������ͣ���3��������������ѣ���4����ȫ���ܻش����⣨ľ������",
			"9����Խ����1�����ʱ��Щ���񲻶�����2���������񲻶���С�����ࣻ��3�����ܾ��������������������4�����֡�ҧ��ָ����ͷ����ҧ�촽��","10�������Խ��ǣ���1���ʼ�ʱ��������2���Է��ر���3���������̸��¶���������ǣ���4�����Ծ��֡�",
			"11�������Խ��ǣ�ָ���ǵ�����֢״���������ڸɡ����͡���к������������ʹ���ļ¡�ͷʹ�����Ȼ�����̾�����Լ���Ƶ�ͳ���������1����ȣ���2���жȣ��п϶�������֢״����3���ضȣ�����֢״���أ�Ӱ���������Ҫ������4������Ӱ������ͻ��",
			"12��θ����֢״����1��ʳ�����ˣ����������˹��������н�ʳ����2����ʳ�����˴ߴٻ��������ҪӦ��кҩ��������ҩ��",
			"13��ȫ��֢״����l����֫�������򾱲����ظУ���ʹ��ͷʹ��������ʹ��ȫ������ƣ�룻��2��֢״���ԡ�",
			"14����֢״��ָ�������ˣ��¾����ҵȣ�����1����ȣ���2���ضȣ���3�����ܿ϶��������Ա����߲��ʺϣ��������ܷ֣���","15���ɲ�����1����������ֹ�ע����2���������ǽ������⣻��3�����ɲ����룻��4����þ����ɲ����롣",
			"16�����ؼ��᣺����ʷ��������1�������������������ؼ��᣻��2���϶����ؼ��ᡣ�����ؼ�¼��������1��һ�������ؼ��ᳬ��0.5�����2��һ�������ؼ��ᳬ��1���",
			"17����֪������0��֪���Լ��в�������Ϊ��������1��֪���Լ��в�������̻�ʳ̫��������⣬������æ��������Ⱦ����Ҫ��Ϣ����2����ȫ�����в���"};
	
	private int i = 0;
	private int j;
	private int score;
	private String buttontext;
	private int resulttext[] = new int[19];
	//private int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hamd);
		
		final TextView questiontext = (TextView) findViewById(R.id.questiontitle);
		radioGroip = (RadioGroup) findViewById(R.id.radiogroup);
		button00 = (RadioButton) findViewById(R.id.button00);
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		resultButton = (Button)findViewById(R.id.resultbutton);
		radioGroip.setOnCheckedChangeListener(new RadioGroupListener());
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
		
		final Button nextButton = (Button) findViewById(R.id.nextbutton);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false && button00.isChecked()==false){
							
							new AlertDialog.Builder(HAMDActivity.this)
							.setTitle("��ܰ��ʾ")
							.setMessage("����û��ѡ���κ�һ��ѡ�")
							.setPositiveButton("ȷ��", null)
							.show();
							return;
						}
				else{
					questiontext.setText(questiontitle[i]);	
					i++;
					radioGroip.clearCheck();
				}
				score += j;
				System.out.println("result----->" + score);
				resulttext[i]= j;
				System.out.println("ѡ��Ľ��----��"+i+ ">>>>>>>" + resulttext[i]);
				if (i == 3) {
					button03.setVisibility(View.GONE);
					button04.setVisibility(View.GONE);
				}
				if (i == 6) {
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
				}
				if (i == 11) {
					button03.setVisibility(View.GONE);
					button04.setVisibility(View.GONE);
				}
				if (i == 13) {
					button03.setVisibility(View.VISIBLE);
//					button04.setVisibility(View.VISIBLE);
				}
				if (i == 14) {
//					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
				}
				if (i == 15) {
					button03.setVisibility(View.GONE);
					button04.setVisibility(View.GONE);
				}
				if(i == questiontitle.length){
					nextButton.setVisibility(View.GONE);
					resultButton.setVisibility(View.VISIBLE);
				}
			}
		});
		resultButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false && button00.isChecked() == false){
							
							new AlertDialog.Builder(HAMDActivity.this)
							.setTitle("��ܰ��ʾ")
							.setMessage("����û��ѡ���κ�һ��ѡ�")
							.setPositiveButton("ȷ��", null)
							.show();
							return;
						}
				RelativeLayout ll = (RelativeLayout) findViewById(R.id.linearLayout);
				ll.setVisibility(View.GONE);
				TextView tv = (TextView) findViewById(R.id.resulttitle);
				tv.setVisibility(View.VISIBLE);
				int array_score[] = new int[17];
				for (int i = 0; i < array_score.length; i++) {
					array_score[i] = j;
				}
				score += j;
				score -= array_score[13];
				tv.setText("���ĵ÷�Ϊ��" + score);
				resulttext[17] = j;
				System.out.println("ѡ��Ľ��>>>��"+ resulttext[17]);
				//��������
				PInformation();
				resultButton.setVisibility(View.GONE);
			}
		});
	}
	class RadioGroupListener implements OnCheckedChangeListener{

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
//			if(checkedId == button05.getId()){
//				j = 4;
//				buttontext = button05.getText().toString();
//				System.out.println("buttontext ---" + buttontext);
//			}
		}
		
	}
	//���Ҫ���������
	public void PInformation(){
		resulttext[18] = score;
		//int hamd_score = score;
		System.out.println("ѡ��Ľ��>>>��"+ resulttext[17]);
		
		String hamd = "";
		//������ת�����ַ���
		for(int i = 1;i < resulttext.length;i++){
			hamd += String.valueOf(resulttext[i]);
		}
		System.out.println("HAMD----->" + hamd);
		PatientTest patientTest = new PatientTest();
		patientTest.setHamd(hamd);
		PatientTestDAO ptdao = new PatientTestDAO(HAMDActivity.this);
		PatientInformationDAO pd = new PatientInformationDAO(HAMDActivity.this);
		ptdao.updateHAMD(pd.getMaxId(), patientTest);
		System.out.println("��ǰID----" + pd.getMaxId());
		Toast.makeText(HAMDActivity.this, "�����������������ݱ���ɹ���", Toast.LENGTH_SHORT).show();
		
		//���������㲥
		Intent intent = new Intent(action5);
		//intent.putExtra("data5", "data5");
		sendBroadcast(intent);
		setAlarmTime(HAMDActivity.this);
	}
	
	public void setAlarmTime(Context context) {
		//��ȡϵͳ�����ӷ���
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		//��������
		Intent intent = new Intent(AlarmAction5);
		//
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		//int interval = 1000 * 60 * 60 * 24;// 24Сʱ
		//24Сʱ̫���ˣ������Ȳ���1���ӵ��ܲ���ʵ��
		int interval = 1000 * 60;// 24Сʱ
		//�������ӣ�System.currentTimeMillis() + intervalϵͳ��ǰʱ��+24Сʱ
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);
		   
	}
}
