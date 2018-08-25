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
	//private String[] diary = {"第二天","第三天","第四天","第五天","第六天","第七天"};
	private String[] question = {"2、白天打盹多长时间？","3、锻炼多长时间？","4、是否服用催眠药物？","5、上床时间？",
			"6、睡着时间？","7、醒来次数？","8、入睡后觉醒时间?","9、起床时间？","10、早晨醒来时间？"};
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
		//接收mainactivity传输过来的bundle
		Bundle b=getIntent().getExtras();
		  //获取Bundle的信息
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
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
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
					.setTitle("温馨提示")
					.setMessage("您还没有选择任何一个选项！")
					.setPositiveButton("确定", null)
					.show();
					return;
				}
				if(i == 1){
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
					button05.setVisibility(View.INVISIBLE);
					button01.setText("小于10分钟");
					button02.setText("10-30分钟");
					button03.setText("30-60分钟");
					button04.setText("大于60分钟");
					//button05.setText("大于50分钟");
				}
				if(i == 3){
					button03.setVisibility(View.INVISIBLE);
					button04.setVisibility(View.INVISIBLE);
					button05.setVisibility(View.INVISIBLE);
					button01.setText("是");
					button02.setText("否");
				}
				if(i == 4){
					//button03.setVisibility(View.VISIBLE);
					//button04.setVisibility(View.VISIBLE);
					//button05.setVisibility(View.VISIBLE);
					//button01.setText("18点以前");
					//button02.setText("18-20点");
					//button03.setText("20-22点");
					//button04.setText("22-24点");
					//button05.setText("24点以后");
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
					button01.setText("0次");
					button02.setText("1次");
					button03.setText("2次");
					button04.setText("3次");
					button05.setText("4次以上");
				}
				if(i == 7){
					//button01.setText("24点以前");
					//button02.setText("0-2点");
					//button03.setText("2-4点");
					//button04.setText("4-6点");
					//button05.setText("6点以后");
					radioGroup.setVisibility(View.INVISIBLE);
					ll2.setVisibility(View.VISIBLE);
					//edittext.setOnTouchListener(new EditTextTouchListener());
				}
				/*if(i == 8){
					button01.setText("4点以前");
					button02.setText("4-6点");
					button03.setText("6-8点");
					button04.setText("8-10点");
					button05.setText("10点以后");
				}
				if(i == 8){
					button01.setText("6点以前");
					button02.setText("6-8点");
					button03.setText("8-10点");
					button04.setText("10-12点");
					button05.setText("12点以后");
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
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
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
	        	//参数 true设置为24小时制，false不设置为24
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
				         //Toast.makeText(getBaseContext(),  "您选择的是： " + hour + ":" + minute1,Toast.LENGTH_SHORT).show();
					}
			    };
			 
			  //获得要保存的数据
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
					if(day.equals("第一天")){
						sleepdiary.setDay01(imformation);
						sddao.updateDAY01(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("第二天")){
						sleepdiary.setDay02(imformation);
						sddao.updateDAY02(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("第三天")){
						sleepdiary.setDay03(imformation);
						sddao.updateDAY03(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("第四天")){
						sleepdiary.setDay04(imformation);
						sddao.updateDAY04(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("第五天")){
						sleepdiary.setDay05(imformation);
						sddao.updateDAY05(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("第六天")){
						sleepdiary.setDay06(imformation);
						sddao.updateDAY06(pd.getMaxId(), sleepdiary);
					}
					if(day.equals("第七天")){
						sleepdiary.setDay07(imformation);
						sddao.updateDAY07(pd.getMaxId(), sleepdiary);
//						//导出表格
//						Cursor c = sddao.export();
//						ExportToCSV export2CSV = new ExportToCSV();
//						export2CSV.exportToCSV(c, "sleepdiary_day.csv");
					}
					Toast.makeText(SleepDiaryActivity.this, info+ "数据保存成功！", Toast.LENGTH_SHORT).show();
					
				}
}
