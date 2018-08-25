package cn.edu.cqu.project_test_activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.cqu.project_test_dao.ExportToCSV;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_dao.PatientTestDAO;
import cn.edu.cqu.project_test_dao.SleepDiaryDAO;
import cn.edu.cqu.project_test_model.PatientInformation;
import cn.edu.cqu.project_test_model.PatientTest;
import cn.edu.cqu.project_test_model.SleepDiary;

import com.zghaikun.sleep_client.R;

public class SleepHealthyActivity extends Activity{
	public static final String action = "broadcast.action";
	public static final String AlarmAction = "alarm.action";
	private RelativeLayout rl;
	private RelativeLayout rl2;
	private int i = 0,j;
	private TextView text;
	private EditText edit;
	private Button button;
	private Button resultbutton;
	private String[] PatientInformation = {"2�������Ա� ��","3���������� ��","4������ְҵ ��","5���Ļ��̶ȣ�","6��ʧ��ʱ�䣺","7��������ʷ��","8���ֻ����룺"};
	String date;
	String phone;
	private String[] PImformation = new String[9];
	private String resulttext[] = new String[9];
	private String buttontext;
	
	private RadioGroup radioGroup;
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private RadioButton button05;
	private RadioButton button06;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleephealth);
		
		text = (TextView) findViewById(R.id.textView);
		edit = (EditText) findViewById(R.id.editText);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		button05 = (RadioButton) findViewById(R.id.button05);
		button06 = (RadioButton) findViewById(R.id.button06);
		resultbutton = (Button) findViewById(R.id.reslutbutton);
		rl = (RelativeLayout) findViewById(R.id.relativeLayout);
		rl2 = (RelativeLayout) findViewById(R.id.relativelayout2);
		rl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				rl.setVisibility(View.INVISIBLE);
				rl2.setVisibility(View.VISIBLE);
				return false;
			}
		});
		button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//if((edit.getText().toString().equals(""))||button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
					//	&& button04.isChecked()==false&& button05.isChecked()==false&& button06.isChecked()==false){
				if((!edit.getText().toString().equals(""))||(button01.isChecked()==true) ||( button02.isChecked()==true)||( button03.isChecked()==true)
						||( button04.isChecked()==true)||( button05.isChecked()==true)||( button06.isChecked()==true)){
				
						/*	new AlertDialog.Builder(SleepHealthyActivity.this)
							.setTitle("��ܰ��ʾ")
							.setMessage("�����룡")
							.setPositiveButton("ȷ��", null)
							.show();
							return;*/
					text.setText(PatientInformation[i]);	
					i++;
					//edit.setText("");
					//PImformation = new String[]{};
					int id =  radioGroup.getCheckedRadioButtonId();
					System.out.println("�õ�id---" + id);
					//PImformation[i] = edit.getText().toString() + buttontext;
					PImformation[i] = edit.getText().toString();
					resulttext[i] = buttontext;
					System.out.println("PImformation[i]------>" + i + "," + PImformation[i]);
					System.out.println("ѡ�е�button----" + resulttext[i]);
					//radioGroip.clearCheck(); 
					edit.setText("");
					radioGroup.clearCheck();
						}
				else{
					/*text.setText(PatientInformation[i]);	
					i++;
					//edit.setText("");
					//PImformation = new String[]{};
					PImformation[i] = edit.getText().toString();
					System.out.println("PImformation[i]------>" + i + "," + PImformation[i]);
					//radioGroip.clearCheck(); 
					edit.setText("");
					radioGroup.clearCheck();*/
					new AlertDialog.Builder(SleepHealthyActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("�����룡")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				if(i == 1 && i == 7){
					//����ֻ����������
					edit.setInputType(InputType.TYPE_CLASS_NUMBER);
				}
				if(i == 1){
					radioGroup.setVisibility(View.VISIBLE);
					edit.setVisibility(View.INVISIBLE);
					button01.setText("��");
					button02.setText("Ů");
					button03.setVisibility(View.INVISIBLE);
					button04.setVisibility(View.INVISIBLE);
					button05.setVisibility(View.INVISIBLE);
					button06.setVisibility(View.INVISIBLE);
				}
				if(i == 2){
					radioGroup.setVisibility(View.INVISIBLE);
					edit.setVisibility(View.VISIBLE);
					//Ĭ����������
					edit.setKeyListener(new DigitsKeyListener(false,true));
				}
				if(i == 3){
					radioGroup.setVisibility(View.VISIBLE);
					edit.setVisibility(View.INVISIBLE);
					button01.setText("����");
					button02.setText("ũ��");
					button03.setText("����Ա");
					button04.setText("ѧ��");
					button05.setText("����");
//					button06.setText("����");
					
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
					button05.setVisibility(View.VISIBLE);
//					button06.setVisibility(View.VISIBLE);
				}
				if(i == 4){
					radioGroup.setVisibility(View.VISIBLE);
					edit.setVisibility(View.INVISIBLE);
					button01.setText("��ä");
					button02.setText("Сѧ");
					button03.setText("����");
					button04.setText("����");
					button05.setText("ר/����");
					button06.setText("˶/��");
					button06.setVisibility(View.VISIBLE);
				}
				if(i == 5){
					button01.setText("1-3����");
					button02.setText("4-6����");
					button03.setText("7-12����");
					button04.setText("1-10��");
					button05.setText("10������");
//					button06.setText("һ������");
					button06.setVisibility(View.INVISIBLE);
				}
				if(i == 6){
					button01.setText("��");
					button02.setText("��Ѫѹ");
					button03.setText("����");
					button04.setText("���Ĳ�");
					button05.setText("��Ѫ֬");
					button06.setText("����");
					button06.setVisibility(View.VISIBLE);
				}
				if(i == 7){
					radioGroup.setVisibility(View.INVISIBLE);
					edit.setVisibility(View.VISIBLE);
//					String phone = edit.getText().toString();
//					if(phone.length() != 11){
//						Toast.makeText(SleepHealthyActivity.this, "������ĵ绰���벻�Ϸ���", Toast.LENGTH_SHORT).show();
//						edit.setText("");
//					}
				}
				if(i == PatientInformation.length ){
					//edit.setVisibility(View.INVISIBLE);
					button.setVisibility(View.INVISIBLE);
					resultbutton.setVisibility(View.VISIBLE);
					//Currentdate();
				}
				
			}
		});
		resultbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//edit.setVisibility(View.INVISIBLE);
				phone = edit.getText().toString();
				if(phone.length() != 11){
					Toast.makeText(SleepHealthyActivity.this, "������ĵ绰���벻�Ϸ���", Toast.LENGTH_SHORT).show();
					edit.setText("");
				}else{
					Currentdate();
					PInformation();
					finish();
				}
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
			if(checkedId == button06.getId()){
				j = 4;
				buttontext = button06.getText().toString();
				System.out.println("buttontext ---" + buttontext);
			}
		}
		
	}
	//��ȡ��ǰ����
	public void Currentdate(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		date = year + "." + month + "." + day;
		//text.setText("��¼���ڣ�" + date);
		System.out.println(date);
	}
	//���Ҫ���������
	public void PInformation(){
		String patient_name = PImformation[1];
		String patient_gender = resulttext[2];
		String patient_age = PImformation[3];
		
		String patient_postion = resulttext[4];
		String patient_scde = resulttext[5];
		String patient_insomnia = resulttext[6];
		String patient_mhistory = resulttext[7];
		
		String patient_phone = phone;
		String currentdate = date;
		
//		AppShare appShare = new AppShare();
//		appShare.setPatientname(patient_name);
		//��װ
		PatientInformation patient = new PatientInformation(currentdate,patient_name, patient_gender, patient_age, patient_postion, patient_scde, patient_insomnia, patient_mhistory, patient_phone);
		
		PatientInformationDAO pi = new PatientInformationDAO(SleepHealthyActivity.this);
		//��������
		pi.insert(patient);
		
		//���뻼�߲�������
		PatientTest patientTest = new PatientTest();
		System.out.println("�������߲��Ա��");
		PatientTestDAO ptdao = new PatientTestDAO(SleepHealthyActivity.this);
		ptdao.insert(patientTest);
		
		//���뻼��˯���ռ�����
		SleepDiary sleepDiary = new SleepDiary();
		System.out.println("����˯���ռǱ��");
		SleepDiaryDAO sddao = new SleepDiaryDAO(SleepHealthyActivity.this);
		sddao.insert(sleepDiary);
		
		Toast.makeText(SleepHealthyActivity.this, "��һ�����ݡ����ݱ���ɹ���", Toast.LENGTH_SHORT).show();
		
//		Cursor c = pi.export();
//		ExportToCSV export2CSV = new ExportToCSV();
//		//�������ļ�����
//		//String filename = patient_name + "patient_table.csv";
//		//export2CSV.exportToCSV(c, filename);
//		export2CSV.setName(patient_name);
//		export2CSV.exportToCSV(c, "patient_table.csv");
		
//		//���������㲥
//		Intent intent = new Intent(action);
//		//intent.putExtra("data", "data");
//		sendBroadcast(intent);
//		setAlarmTime(SleepHealthyActivity.this);
//		System.out.println("test------------");
		
		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		//��ȡ����
		int count = pref.getInt("count", 0);
		Editor editor = pref.edit();
		//��ȡ��ǰ����
		String CDate = Current_date();
		//�����һ�ε�ʱ������
		if (count == 0) {
			//���浱ǰ����
			editor.putString("date", CDate);
			Toast.makeText(this, "��ǰʱ�䱣��ɹ�", Toast.LENGTH_SHORT).show();
		}
		editor.putInt("count", ++count);
		editor.commit();
	}
	
	//��ȡ��ǰ����
	public String Current_date(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int munite = c.get(Calendar.MINUTE);
		String date = year + "," + month + "," + day + "," + hour + "," + munite;
		//text.setText("��¼���ڣ�" + date);
		System.out.println(date);
		return date;
	}
	
	public void setAlarmTime(Context context) {
		//��ȡϵͳ�����ӷ���
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		//��������
		Intent intent = new Intent(AlarmAction);
		//
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		//int interval = 1000 * 60 * 60 * 24;// 24Сʱ
		//24Сʱ̫���ˣ������Ȳ���1���ӵ��ܲ���ʵ��
		int interval = 1000 * 60;// 24Сʱ
		//�������ӣ�System.currentTimeMillis() + intervalϵͳ��ǰʱ��+24Сʱ
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);
		   
	}
}
