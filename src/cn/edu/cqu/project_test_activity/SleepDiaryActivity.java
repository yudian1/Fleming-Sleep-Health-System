package cn.edu.cqu.project_test_activity;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import cn.edu.cqu.project_test_dao.ExportToCSV;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_dao.SleepDiaryDAO;
import cn.edu.cqu.project_test_model.SleepDiary;

public class SleepDiaryActivity extends Activity{
	private Button nextbutton;
	private Button resultbutton;
	private TextView theday;
	private TextView questiontitle;
	private RadioGroup radioGroup;
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private RadioButton button05;
	private int i = 0,j;
	private LinearLayout ll2;
	int hour, minute1;
    static final int TIME_DIALOG_ID = 0;
    private EditText edittext; 
	//private String[] diary = {"�ڶ���","������","������","������","������","������"};
	private String[] question = {"2���������೤ʱ�䣿","3�������೤ʱ�䣿","4���Ƿ���ô���ҩ�","5���ϴ�ʱ�䣿",
			"6��˯��ʱ�䣿","7������������","8����˯�����ʱ��?","9����ʱ�䣿","10���糿����ʱ�䣿"};
	private int resulttext[] = new int[16];
	private String[] PImformation = new String[11];
	private String buttontext;
	String info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleepdiary);
		
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		button05 = (RadioButton) findViewById(R.id.button05);
		resultbutton = (Button) findViewById(R.id.resultbutton);
		ll2 = (LinearLayout) findViewById(R.id.linearlayout2);
		
		edittext = (EditText) findViewById(R.id.edittext);
		
		theday = (TextView) findViewById(R.id.day);
		questiontitle = (TextView) findViewById(R.id.questiontitle);
		nextbutton = (Button) findViewById(R.id.nextbutton);
		//����mainactivity���������bundle
		Bundle b=getIntent().getExtras();
		  //��ȡBundle����Ϣ
		info=b.getString("text");
		theday.setText(info);
		nextbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//questiontitle.setText(question[i]);
				//i++;
				if(button01.isChecked()==true || button02.isChecked()==true || button03.isChecked()==true
						|| button04.isChecked()==true || button05.isChecked()==true ||(!edittext.getText().toString().equals(""))){
							
							/*new AlertDialog.Builder(SleepDiaryActivity.this)
							.setTitle("��ܰ��ʾ")
							.setMessage("����û��ѡ���κ�һ��ѡ�")
							.setPositiveButton("ȷ��", null)
							.show();
							return;*/
					questiontitle.setText(question[i]);	
					i++;
					PImformation[i] = edittext.getText().toString();
					resulttext[i] = j;
					radioGroup.clearCheck();
					edittext.setText("");
					edittext.setOnTouchListener(new EditTextTouchListener());
						}
				else{
					/*questiontitle.setText(question[i]);	
					i++;
					radioGroup.clearCheck();
					edittext.setText("");
					edittext.setOnTouchListener(new EditTextTouchListener());*/
					new AlertDialog.Builder(SleepDiaryActivity.this)
					.setTitle("��ܰ��ʾ")
					.setMessage("����û��ѡ���κ�һ��ѡ�")
					.setPositiveButton("ȷ��", null)
					.show();
					return;
				}
				if(i == 1){
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
					button05.setVisibility(View.INVISIBLE);
					button01.setText("С��10����");
					button02.setText("10-30����");
					button03.setText("30-60����");
					button04.setText("����60����");
					//button05.setText("����50����");
				}
				if(i == 3){
					button03.setVisibility(View.INVISIBLE);
					button04.setVisibility(View.INVISIBLE);
					button05.setVisibility(View.INVISIBLE);
					button01.setText("��");
					button02.setText("��");
				}
				if(i == 4){
					//button03.setVisibility(View.VISIBLE);
					//button04.setVisibility(View.VISIBLE);
					//button05.setVisibility(View.VISIBLE);
					//button01.setText("18����ǰ");
					//button02.setText("18-20��");
					//button03.setText("20-22��");
					//button04.setText("22-24��");
					//button05.setText("24���Ժ�");
					radioGroup.setVisibility(View.INVISIBLE);
					ll2.setVisibility(View.VISIBLE);
					//edittext.setOnTouchListener(new EditTextTouchListener());
				}
				if(i == 6){
					radioGroup.setVisibility(View.VISIBLE);
					ll2.setVisibility(View.INVISIBLE);
					//edittext.setOnTouchListener(new EditTextTouchListener());
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
					button05.setVisibility(View.VISIBLE);
					button01.setText("0��");
					button02.setText("1��");
					button03.setText("2��");
					button04.setText("3��");
					button05.setText("4������");
				}
				if(i == 7){
					//button01.setText("24����ǰ");
					//button02.setText("0-2��");
					//button03.setText("2-4��");
					//button04.setText("4-6��");
					//button05.setText("6���Ժ�");
					radioGroup.setVisibility(View.INVISIBLE);
					ll2.setVisibility(View.VISIBLE);
					//edittext.setOnTouchListener(new EditTextTouchListener());
				}
				/*if(i == 8){
					button01.setText("4����ǰ");
					button02.setText("4-6��");
					button03.setText("6-8��");
					button04.setText("8-10��");
					button05.setText("10���Ժ�");
				}
				if(i == 8){
					button01.setText("6����ǰ");
					button02.setText("6-8��");
					button03.setText("8-10��");
					button04.setText("10-12��");
					button05.setText("12���Ժ�");
				}*/
				if(i == question.length){
					nextbutton.setVisibility(View.INVISIBLE);
					resultbutton.setVisibility(View.VISIBLE);
				}
			}
		});
		resultbutton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if((edittext.getText().toString().equals(""))){
							
							new AlertDialog.Builder(SleepDiaryActivity.this)
							.setTitle("��ܰ��ʾ")
							.setMessage("����û��ѡ���κ�һ��ѡ�")
							.setPositiveButton("ȷ��", null)
							.show();
							return;
						}
				PImformation[10] = edittext.getText().toString();
				PInformation();
				finish();
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
				//System.out.println("result11----->" + j);
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
	 class EditTextTouchListener implements OnTouchListener{

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				showDialog(TIME_DIALOG_ID);
				return false;
			}
	    	
	    }
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
	        case TIME_DIALOG_ID: 
	        	//���� true����Ϊ24Сʱ�ƣ�false������Ϊ24
	            return new TimePickerDialog( this, mTimeSetListener, hour, minute1, true);
	    }
	    return null; 
		}
		   private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			    new TimePickerDialog.OnTimeSetListener() 
		   {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						 hour = hourOfDay;
				         minute1 = minute;  
				         String strhour = String.valueOf(hour);
				         String strminute = String.valueOf(minute1);
				         if(strhour.length() == 1){
				        	 strhour = "0" + strhour;
				         }
				         if(strminute.length() == 1){
				        	 strminute = "0" + strminute;
				         }
				         edittext.setText(strhour + ":" + strminute);
				         //Toast.makeText(getBaseContext(),  "��ѡ����ǣ� " + hour + ":" + minute1,Toast.LENGTH_SHORT).show();
					}
			    };
			 
			  //���Ҫ���������
				public void PInformation(){
					String day = info;
					String [] reslut = new String[10];
					reslut[0] = String.valueOf(resulttext[1]);
					reslut[1] = String.valueOf(resulttext[2]);
					reslut[2] = String.valueOf(resulttext[3]);
					reslut[3] = String.valueOf(resulttext[4]);
					reslut[4] = PImformation[5];
					reslut[5] = PImformation[6];
					reslut[6] = String.valueOf(resulttext[7]);
					reslut[7] = PImformation[8];
					reslut[8] = PImformation[9];
					reslut[9] = PImformation[10];
					String imformation = "";
					for(int i = 0; i < reslut.length;i++){
						imformation += reslut[i]; 
					}
					System.out.println("imformation-------" + imformation);
					SleepDiary sleepdiary = new SleepDiary();
					PatientInformationDAO pd = new PatientInformationDAO(SleepDiaryActivity.this);
					SleepDiaryDAO sddao = new SleepDiaryDAO(SleepDiaryActivity.this);
					if(day.equals("��һ��")){
						sleepdiary.setDay01(imformation);
						sddao.updateDAY01(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("�ڶ���")){
						sleepdiary.setDay02(imformation);
						sddao.updateDAY02(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("������")){
						sleepdiary.setDay03(imformation);
						sddao.updateDAY03(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("������")){
						sleepdiary.setDay04(imformation);
						sddao.updateDAY04(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("������")){
						sleepdiary.setDay05(imformation);
						sddao.updateDAY05(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("������")){
						sleepdiary.setDay06(imformation);
						sddao.updateDAY06(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("������")){
						sleepdiary.setDay07(imformation);
						sddao.updateDAY07(pd.getMaxId(), sleepdiary);
//						//�������
//						Cursor c = sddao.export();
//						ExportToCSV export2CSV = new ExportToCSV();
//						export2CSV.exportToCSV(c, "sleepdiary_day.csv");
					}
					Toast.makeText(SleepDiaryActivity.this, info+ "���ݱ���ɹ���", Toast.LENGTH_SHORT).show();
					
				}
}
