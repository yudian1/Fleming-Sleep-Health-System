package cn.edu.cqu.project_test_activity;

import com.zghaikun.sleep_client.R;

import cn.edu.cqu.project_test_dao.PatientTestDAO;
import cn.edu.cqu.project_test_dao.SleepDiaryDAO;
import cn.edu.cqu.project_test_model.PatientTest;
import cn.edu.cqu.project_test_model.SleepDiary;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PatientInfoSleepDiary extends Activity{
	private String patientid;
	private String day01;
	private String day02;
	private String day03;
	private String day04;
	private String day05;
	private String day06;
	private String day07;

	private ListView lvday01;
	private ListView lvday02;
	private ListView lvday03;
	private ListView lvday04;
	private ListView lvday05;
	private ListView lvday06;
	private ListView lvday07;

	
	//private String str;
	//String day[] = new String [7];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_sleepdiary);
		
		lvday01 = (ListView) findViewById(R.id.listview01);
		lvday02 = (ListView) findViewById(R.id.listview02);
		lvday03 = (ListView) findViewById(R.id.listview03);
		lvday04 = (ListView) findViewById(R.id.listview04);
		lvday05 = (ListView) findViewById(R.id.listview05);
		lvday06 = (ListView) findViewById(R.id.listview06);
		lvday07 = (ListView) findViewById(R.id.listview07);
		
		//获得传递过来的id
		Intent intent = getIntent();
		patientid = intent.getStringExtra("patientid");
		int i = Integer.parseInt(patientid);
		SleepDiaryDAO sddao = new SleepDiaryDAO(PatientInfoSleepDiary.this);
		SleepDiary sleepdiaty = sddao.find(i);
		day01 = sleepdiaty.getDay01();
		day02 = sleepdiaty.getDay02();
		day03 = sleepdiaty.getDay03();
		day04 = sleepdiaty.getDay04();
		day05 = sleepdiaty.getDay05();
		day06 = sleepdiaty.getDay06();
		day07 = sleepdiaty.getDay07();
		System.out.println(day01 + "---" +day02 + "---" +day02 + "---" +day04 + "---" +day05 + "---" +day06 + "---" +day07);
		ShowInfo();// 显示睡眠治疗评估
	}
	private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
		
			if(day01 == null){
			}
			else{
				System.out.println("开始处理数据");
				String strday1[] = data(day01);
				ArrayAdapter<String> arrayAdapter1 = null;// 创建ArrayAdapter对象
				arrayAdapter1 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday1);
				lvday01.setAdapter(arrayAdapter1);
			}
			if(day02 == null){
			}
			else{
				String strday2[] = data(day02);
				ArrayAdapter<String> arrayAdapter2 = null;// 创建ArrayAdapter对象
				arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday2);
				lvday02.setAdapter(arrayAdapter2);
			}
			if(day03 == null){
			}
			else{
				String strday3[] = data(day03);
				ArrayAdapter<String> arrayAdapter3 = null;// 创建ArrayAdapter对象
				arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday3);
				lvday03.setAdapter(arrayAdapter3);
			}
			if(day04 == null){
			}
			else{
				String strday4[] = data(day04);
				ArrayAdapter<String> arrayAdapter4 = null;// 创建ArrayAdapter对象
				arrayAdapter4 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday4);
				lvday04.setAdapter(arrayAdapter4);
			}
			if(day05 == null){
			}
			else{
				String strday5[] = data(day05);
				ArrayAdapter<String> arrayAdapter5 = null;// 创建ArrayAdapter对象
				arrayAdapter5 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday5);
				lvday05.setAdapter(arrayAdapter5);
			}
			if(day06 == null){
			}
			else{
				String strday6[] = data(day06);
				ArrayAdapter<String> arrayAdapter6 = null;// 创建ArrayAdapter对象
				arrayAdapter6 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday6);
				lvday06.setAdapter(arrayAdapter6);
			}
			if(day07 == null){
			}
			else{
				String strday7[] = data(day07);
				ArrayAdapter<String> arrayAdapter7 = null;// 创建ArrayAdapter对象
				arrayAdapter7 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strday7);
				lvday07.setAdapter(arrayAdapter7);
			}
			
			
		}
	private String[] data(String str){
		String strday[] = new String[10];
		for(int i = 0; i < 4;i++){
			strday[i] = str.substring(i, i+1);
		}
		System.out.println("str-----" + str);
		strday[4] = str.substring(4, 9);
		strday[5] = str.substring(9, 14);
		strday[6] = str.substring(14, 15);
		strday[7] = str.substring(15, 20);
		strday[8] = str.substring(20, 25);
		strday[9] = str.substring(25, 30);
		if(strday[0].equals("0")){
			strday[0] = "无";
		}
		else if(strday[0].equals("1")){
			strday[0] = "轻度";
		}
		else if(strday[0].equals("2")){
			strday[0] = "中度";
		}
		else if(strday[0].equals("3")){
			strday[0] = "重度";
		}
		else if(strday[0].equals("4")){
			strday[0] = "极重度";
		}
		
		for(int i = 1; i < 3; i++){
			if(strday[i].equals("0")){
				strday[i] = "小于10分钟";
			}
			else if(strday[i].equals("1")){
				strday[i] = "10-30分钟";
			}
			else if(strday[i].equals("2")){
				strday[i] = "30-60分钟";
			}
			else if(strday[i].equals("3")){
				strday[i] = "大于60分钟";
			}
		}
		if(strday[3].equals("0")){
			strday[3] = "是";
		}
		else if(strday[3].equals("1")){
			strday[3] = "否";
		}
		
		if(strday[6].equals("0")){
			strday[6] = "0次";
		}
		else if(strday[6].equals("1")){
			strday[6] = "1次";
		}
		if(strday[6].equals("0")){
			strday[6] = "2次";
		}
		else if(strday[6].equals("1")){
			strday[6] = "3次";
		}
		else if(strday[6].equals("2")){
			strday[6] = "4次以上";
		}
		return strday;
	}		
		
}
