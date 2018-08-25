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

public class HAMAActivity extends Activity{
	public static final String action4 = "broadcast.action4";
	public static final String AlarmAction4 = "alarm.action4";
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private RadioButton button05;
	private Button resultButton;
	private RadioGroup radioGroip;
	private RelativeLayout rl;
	private RelativeLayout rl2;
	private String[] questiontitle = {"2�����ţ����ŸС���ƣ�͡����ܷ��ɣ�������Ӧ���׿ޡ��������е�������","3�����£����ºڰ���İ���ˡ�һ�˶���������˳������м��˶�ĳ��ϡ�",
			"4��ʧ�ߣ�������˯�����ѡ�˯�ò�����Ρ����ʡ�ҹ�����Ѻ��ƣ�롣","5����֪���ܣ���Ƽ��䡢ע���ϰ���ע�������ܼ��У��������",
			"6�������ľ���ɥʧ��Ȥ������������ȱ����С����������ѡ�����ҹ�ᡣ","7�������Խ��ǣ�����ϵͳ��������ʹ�����������鶯��֫��鶯�����ݴ��������������",
			"8���о�ϵͳ�������ģ�����䷢�ȣ����������У������ʹ��","9����Ѫ��ϵͳ֢״���Ķ����١��ļ¡���ʹ��Ѫ�������С��赹�С��Ĳ���©��",
			"10������ϵͳ֢״�����ơ���Ϣ�С�̾Ϣ���������ѡ�","11��θ����֢״���������ѡ���������������(��ʳ��ʹ��θ������ʹ�����͡����ġ�θ������)����������к�����ؼ��ᡢ���ء�",
			"12����ֳ����ϵͳ֢״������Ƶ�����򼱡�ͣ�������䵭�������侫�������ܡ���ή","13��ֲ����֢״���ڸɡ����졢�԰ס��׳��������𡰼����񡱡�������ͷʹ��ë������",
			"14����̸ʱ��Ϊ���֣�(1)һ����֣����Ų�����ҧ��ָ��������ȭ����Ū�������漡�鶯����ͣ���㡢���齩Ӳ����ɫ�԰ף�(2)������֣����ʡ�����������ʱ���ʺ����졢�����ͫ�׷Ŵ���������������������ͻ����",
		};
	
	private int i = 0;
	private int j;
	private int score;
	private String buttontext;
	private int resulttext[] = new int[16];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hama);
		
		final TextView questiontext = (TextView) findViewById(R.id.questiontitle);
		radioGroip = (RadioGroup) findViewById(R.id.radiogroup);
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		button05 = (RadioButton) findViewById(R.id.button05);
		resultButton = (Button)findViewById(R.id.resultbutton);
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
		radioGroip.setOnCheckedChangeListener(new RadioGroupListener());
		
		final Button nextButton = (Button) findViewById(R.id.nextbutton);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false&& button05.isChecked()==false){
							
							new AlertDialog.Builder(HAMAActivity.this)
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
				System.out.println("ѡ��Ľ��----��" + i + "----" + resulttext[i]);
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
						&& button04.isChecked()==false&& button05.isChecked()==false){							
							new AlertDialog.Builder(HAMAActivity.this)
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
				score +=j;
				tv.setText("���ĵ÷�Ϊ��" + score);
				resulttext[14] = j;
				//��������
				PInformation();
				resultButton.setVisibility(View.GONE);
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
				resulttext[15] = score;
				String hama = "";
				for(int i = 1;i < resulttext.length;i++){
					hama += String.valueOf(resulttext[i]);
				}
				System.out.println("HAMA----->" + hama);
				PatientTest patientTest = new PatientTest();
				patientTest.setHama(hama);
				PatientTestDAO ptdao = new PatientTestDAO(HAMAActivity.this);
				PatientInformationDAO pd = new PatientInformationDAO(HAMAActivity.this);
				ptdao.updateHAMA(pd.getMaxId(), patientTest);
				System.out.println("��ǰID----" + pd.getMaxId());
				Toast.makeText(HAMAActivity.this, "����������һ�����ݱ���ɹ���", Toast.LENGTH_SHORT).show();
				
				//���������㲥
				Intent intent = new Intent(action4);
				//intent.putExtra("data4", "data4");
				sendBroadcast(intent);
				setAlarmTime(HAMAActivity.this);
			}
			
			public void setAlarmTime(Context context) {
				//��ȡϵͳ�����ӷ���
				AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				//��������
				Intent intent = new Intent(AlarmAction4);
				PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				//int interval = 1000 * 60 * 60 * 24;// 24Сʱ
				//24Сʱ̫���ˣ������Ȳ���1���ӵ��ܲ���ʵ��
				int interval = 1000 * 60;// 24Сʱ
				//�������ӣ�System.currentTimeMillis() + intervalϵͳ��ǰʱ��+24Сʱ
				am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);			   
			}

}
