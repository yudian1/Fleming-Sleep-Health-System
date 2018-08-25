package cn.edu.cqu.project_test_activity;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.edu.cqu.project_test_dao.PatientTestDAO;
import cn.edu.cqu.project_test_model.PatientTest;

public class PatientInfoManage extends Activity{
	//String strType = "";// 创建字符串，记录管理类型
	private String pqsi;
	private String sleepness;
	private String hama;
	private String hamd;
	private String isi;

	private ListView lv01;
	private ListView lv02;
	private ListView lv03;
	private ListView lv04;
	private ListView lv05;
	
	private Button sdButton;
	String patientid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient_infomanage);
		
		
		lv01 = (ListView) findViewById(R.id.lv01);
		lv02 = (ListView) findViewById(R.id.lv02);
		lv03 = (ListView) findViewById(R.id.lv03);
		lv04 = (ListView) findViewById(R.id.lv04);
		lv05 = (ListView) findViewById(R.id.lv05);
		//获得传递过来的id
		Intent intent = getIntent();
		patientid = intent.getStringExtra("patientid");
		//将String型的id值转换成int型
		int i=Integer.parseInt(patientid);
		
		sdButton = (Button) findViewById(R.id.sleepdiary);
		sdButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(PatientInfoManage.this, PatientInfoSleepDiary.class);
				intent.putExtra("patientid", patientid);
				startActivity(intent);
			}
		});
		System.out.println("得到的id---" + i);
		PatientTestDAO ptdao = new PatientTestDAO(PatientInfoManage.this);
		PatientTest patienttest = ptdao.find(i);
		pqsi = patienttest.getPqsi();
		sleepness = patienttest.getSleepness();
		hama = patienttest.getHama();
		hamd = patienttest.getHamd();
		isi = patienttest.getIsi();
		System.out.println("得到的结果----" + pqsi + "----" + sleepness + "-----" + hama + "-----" + hamd + "---------" + isi);
		ShowInfo();// 显示睡眠治疗评估

	}
	
	private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
		//判断pqsi是否为空
		String strpqsi[] = new String[19];
			if(pqsi == null){
				strpqsi[1] = "您还没有做测试！";
			}
			else{
				
				for(int i = 0; i < pqsi.length();i++){
					strpqsi[i] = pqsi.substring(i, i+1);
				}
				System.out.println("22222222222" + strpqsi[0]);
				if(strpqsi[0].equals("0")){
					strpqsi[0] = "20点以前";
				}
				else if(strpqsi[0].equals("1")){
					strpqsi[0] = "20-21点";
				}
				else if(strpqsi[0].equals("2")){
					strpqsi[0] = "21-22点";
				}
				else if(strpqsi[0].equals("3")){
					strpqsi[0] = "22-23点";
				}
				else if(strpqsi[0].equals("4")){
					strpqsi[0] = "23-24点";
				}
				System.out.println("pqsi11111111--" + strpqsi[0]);
				
				if(strpqsi[1].equals("0")){
					strpqsi[1] = "小于15分钟";
				}
				else if(strpqsi[1].equals("1")){
					strpqsi[1] = "16-30分钟";
				}
				else if(strpqsi[1].equals("2")){
					strpqsi[1] = "30-45分钟";
				}
				else if(strpqsi[1].equals("3")){
					strpqsi[1] = "45-60分钟";
				}
				else if(strpqsi[1].equals("4")){
					strpqsi[1] = "1小时以上";
				}
				
				if(strpqsi[2].equals("0")){
					strpqsi[2] = "5点以前";
				}
				else if(strpqsi[2].equals("1")){
					strpqsi[2] = "5-6点";
				}
				else if(strpqsi[2].equals("2")){
					strpqsi[2] = "6-7点";
				}
				else if(strpqsi[2].equals("3")){
					strpqsi[2] = "7-8点";
				}
				else if(strpqsi[2].equals("4")){
					strpqsi[2] = "8-9点";
				}
				
				if(strpqsi[3].equals("0")){
					strpqsi[3] = "小于4小时";
				}
				else if(strpqsi[3].equals("1")){
					strpqsi[3] = "4-6小时";
				}
				else if(strpqsi[3].equals("2")){
					strpqsi[3] = "6-8小时";
				}
				else if(strpqsi[3].equals("3")){
					strpqsi[3] = "8-10小时";
				}
				else if(strpqsi[3].equals("4")){
					strpqsi[3] = "10小时以上";
				}
				
				for(int j = 0;j < 14;j++){
					if(strpqsi[4+j].equals("0")){
						strpqsi[4+j] = "1次/周";
					}
					else if(strpqsi[4+j].equals("1")){
						strpqsi[4+j] = "2次/周";
					}
					else if(strpqsi[4+j].equals("2")){
						strpqsi[4+j] = "3次/周";
					}
					else if(strpqsi[4+j].equals("3")){
						strpqsi[4+j] = "4次/周";
					}
					else if(strpqsi[4+j].equals("4")){
						strpqsi[4+j] = "5次/周";
					}
					else if(strpqsi[4+j].equals("5")){
						strpqsi[4+j] = "6次/周";
					}
					else if(strpqsi[4+j].equals("6")){
						strpqsi[4+j] = "7次/周";
					}
				}
				if(strpqsi[18].equals("0")){
					strpqsi[18] = "很好";
				}
				else if(strpqsi[18].equals("1")){
					strpqsi[18] = "较好";
				}
				else if(strpqsi[18].equals("2")){
					strpqsi[18] = "较差";
				}
				else if(strpqsi[18].equals("3")){
					strpqsi[18] = "很差";
				}
				ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
				arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strpqsi);
				lv01.setAdapter(arrayAdapter);
			}
			
			
			
			
			String[] strsleepness = new String[7];
			if(sleepness == null){
				strsleepness[1] = "您还没有做测试！";
				System.out.println("sleepness没有做测试");
			}
			else{
				for(int i = 0; i < sleepness.length();i++){
					strsleepness[i] = sleepness.substring(i, i+1);
				}
				for(int j = 0;j < 3;j++){
					if(strsleepness[j].equals("0")){
						strsleepness[j] = "无";
					}
					else if(strsleepness[j].equals("1")){
						strsleepness[j] = "轻度";
					}
					else if(strsleepness[j].equals("2")){
						strsleepness[j] = "中度";
					}
					else if(strsleepness[j].equals("3")){
						strsleepness[j] = "重度";
					}
					else if(strsleepness[j].equals("4")){
						strsleepness[j] = "极重度";
					}
				}
				if(strsleepness[3].equals("0")){
					strsleepness[3] = "很满意";
				}
				else if(strsleepness[3].equals("1")){
					strsleepness[3] = "满意";
				}
				else if(strsleepness[3].equals("2")){
					strsleepness[3] = "一般";
				}
				else if(strsleepness[3].equals("3")){
					strsleepness[3] = "不满意";
				}
				else if(strsleepness[3].equals("4")){
					strsleepness[3] = "很不满意";
				}
				
				if(strsleepness[4].equals("0")){
					strsleepness[4] = "没有干扰";
				}
				else if(strsleepness[4].equals("1")){
					strsleepness[4] = "轻微";
				}
				else if(strsleepness[4].equals("2")){
					strsleepness[4] = "有些";
				}
				else if(strsleepness[4].equals("3")){
					strsleepness[4] = "较多";
				}
				else if(strsleepness[4].equals("4")){
					strsleepness[4] = "很多干扰";
				}
				
				if(strsleepness[5].equals("0")){
					strsleepness[5] = "没有";
				}
				else if(strsleepness[5].equals("1")){
					strsleepness[5] = "一点";
				}
				else if(strsleepness[5].equals("2")){
					strsleepness[5] = "有些";
				}
				else if(strsleepness[5].equals("3")){
					strsleepness[5] = "较多";
				}
				else if(strsleepness[5].equals("4")){
					strsleepness[5] = "很多";
				}
				
				if(strsleepness[6].equals("0")){
					strsleepness[6] = "没有";
				}
				else if(strsleepness[6].equals("1")){
					strsleepness[6] = "一点";
				}
				else if(strsleepness[6].equals("2")){
					strsleepness[6] = "有些";
				}
				else if(strsleepness[6].equals("3")){
					strsleepness[6] = "较多";
				}
				else if(strsleepness[6].equals("4")){
					strsleepness[6] = "很多";
				}
				ArrayAdapter<String> arrayAdapter2 = null;// 创建ArrayAdapter对象
				arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strsleepness);
				lv02.setAdapter(arrayAdapter2);
			}
			
			
			String[] strhama = new String[15];
			if(hama == null){
				strhama[1] = "您还没有做测试！！";
				System.out.println("hama没有做测试");
			}else{
				
				for(int i = 0; i < hama.length()-2;i++){
					strhama[i] = hama.substring(i, i+1);
				}
				strhama[14] = hama.substring(hama.length()-2,hama.length());
				for(int j = 0;j < 14;j++){
					if(strhama[j].equals("0")){
						strhama[j] = "无";
					}
					else if(strhama[j].equals("1")){
						strhama[j] = "轻度";
					}
					else if(strhama[j].equals("2")){
						strhama[j] = "中度";
					}
					else if(strhama[j].equals("3")){
						strhama[j] = "重度";
					}
					else if(strhama[j].equals("4")){
						strhama[j] = "极重度";
					}
				}
				ArrayAdapter<String> arrayAdapter3 = null;// 创建ArrayAdapter对象
				arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strhama);
				lv03.setAdapter(arrayAdapter3);
			}
			

			String[] strhamd = new String[18];
			if(hamd == null){
				strhamd[1] = "您还没有做测试！！";
				System.out.println("hamd没有做测试");
			}else{
				for(int i = 0; i < hamd.length()-2;i++){
					strhamd[i] = hamd.substring(i, i+1);
				}
				strhamd[17] = hamd.substring(hamd.length()-2,hamd.length());
				for(int j = 0;j < 17;j++){
					if(strhamd[j].equals("0")){
						strhamd[j] = "无";
					}
					else if(strhamd[j].equals("1")){
						strhamd[j] = "轻度";
					}
					else if(strhamd[j].equals("2")){
						strhamd[j] = "中度";
					}
					else if(strhamd[j].equals("3")){
						strhamd[j] = "重度";
					}
					else if(strhamd[j].equals("4")){
						strhamd[j] = "极重度";
					}
				}
				ArrayAdapter<String> arrayAdapter4 = null;// 创建ArrayAdapter对象
				arrayAdapter4 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strhamd);
				lv04.setAdapter(arrayAdapter4);
			}
			
			String[] strisi = new String[8];
			if(isi == null){
				strisi[1] = "您还没有做测试！！";
				System.out.println("isi没有做测试");
			}else{
				for(int i = 0; i < 7;i++){
					strisi[i] = isi.substring(i, i+1);
				}
				strisi[7] = isi.substring(7,isi.length());
				for(int j = 0;j < 7;j++){
					if(strisi[j].equals("0")){
						strisi[j] = "无";
					}
					else if(strisi[j].equals("1")){
						strisi[j] = "轻度";
					}
					else if(strisi[j].equals("2")){
						strisi[j] = "中度";
					}
					else if(strisi[j].equals("3")){
						strisi[j] = "重度";
					}
					else if(strisi[j].equals("4")){
						strisi[j] = "极重度";
					}
				}
				ArrayAdapter<String> arrayAdapter5 = null;// 创建ArrayAdapter对象
				arrayAdapter5 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strisi);
				lv05.setAdapter(arrayAdapter5);
			}
			
		}

	}


