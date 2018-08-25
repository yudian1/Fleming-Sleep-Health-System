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
	//String strType = "";// �����ַ�������¼��������
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
		//��ô��ݹ�����id
		Intent intent = getIntent();
		patientid = intent.getStringExtra("patientid");
		//��String�͵�idֵת����int��
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
		System.out.println("�õ���id---" + i);
		PatientTestDAO ptdao = new PatientTestDAO(PatientInfoManage.this);
		PatientTest patienttest = ptdao.find(i);
		pqsi = patienttest.getPqsi();
		sleepness = patienttest.getSleepness();
		hama = patienttest.getHama();
		hamd = patienttest.getHamd();
		isi = patienttest.getIsi();
		System.out.println("�õ��Ľ��----" + pqsi + "----" + sleepness + "-----" + hama + "-----" + hamd + "---------" + isi);
		ShowInfo();// ��ʾ˯����������

	}
	
	private void ShowInfo() {// �������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
		//�ж�pqsi�Ƿ�Ϊ��
		String strpqsi[] = new String[19];
			if(pqsi == null){
				strpqsi[1] = "����û�������ԣ�";
			}
			else{
				
				for(int i = 0; i < pqsi.length();i++){
					strpqsi[i] = pqsi.substring(i, i+1);
				}
				System.out.println("22222222222" + strpqsi[0]);
				if(strpqsi[0].equals("0")){
					strpqsi[0] = "20����ǰ";
				}
				else if(strpqsi[0].equals("1")){
					strpqsi[0] = "20-21��";
				}
				else if(strpqsi[0].equals("2")){
					strpqsi[0] = "21-22��";
				}
				else if(strpqsi[0].equals("3")){
					strpqsi[0] = "22-23��";
				}
				else if(strpqsi[0].equals("4")){
					strpqsi[0] = "23-24��";
				}
				System.out.println("pqsi11111111--" + strpqsi[0]);
				
				if(strpqsi[1].equals("0")){
					strpqsi[1] = "С��15����";
				}
				else if(strpqsi[1].equals("1")){
					strpqsi[1] = "16-30����";
				}
				else if(strpqsi[1].equals("2")){
					strpqsi[1] = "30-45����";
				}
				else if(strpqsi[1].equals("3")){
					strpqsi[1] = "45-60����";
				}
				else if(strpqsi[1].equals("4")){
					strpqsi[1] = "1Сʱ����";
				}
				
				if(strpqsi[2].equals("0")){
					strpqsi[2] = "5����ǰ";
				}
				else if(strpqsi[2].equals("1")){
					strpqsi[2] = "5-6��";
				}
				else if(strpqsi[2].equals("2")){
					strpqsi[2] = "6-7��";
				}
				else if(strpqsi[2].equals("3")){
					strpqsi[2] = "7-8��";
				}
				else if(strpqsi[2].equals("4")){
					strpqsi[2] = "8-9��";
				}
				
				if(strpqsi[3].equals("0")){
					strpqsi[3] = "С��4Сʱ";
				}
				else if(strpqsi[3].equals("1")){
					strpqsi[3] = "4-6Сʱ";
				}
				else if(strpqsi[3].equals("2")){
					strpqsi[3] = "6-8Сʱ";
				}
				else if(strpqsi[3].equals("3")){
					strpqsi[3] = "8-10Сʱ";
				}
				else if(strpqsi[3].equals("4")){
					strpqsi[3] = "10Сʱ����";
				}
				
				for(int j = 0;j < 14;j++){
					if(strpqsi[4+j].equals("0")){
						strpqsi[4+j] = "1��/��";
					}
					else if(strpqsi[4+j].equals("1")){
						strpqsi[4+j] = "2��/��";
					}
					else if(strpqsi[4+j].equals("2")){
						strpqsi[4+j] = "3��/��";
					}
					else if(strpqsi[4+j].equals("3")){
						strpqsi[4+j] = "4��/��";
					}
					else if(strpqsi[4+j].equals("4")){
						strpqsi[4+j] = "5��/��";
					}
					else if(strpqsi[4+j].equals("5")){
						strpqsi[4+j] = "6��/��";
					}
					else if(strpqsi[4+j].equals("6")){
						strpqsi[4+j] = "7��/��";
					}
				}
				if(strpqsi[18].equals("0")){
					strpqsi[18] = "�ܺ�";
				}
				else if(strpqsi[18].equals("1")){
					strpqsi[18] = "�Ϻ�";
				}
				else if(strpqsi[18].equals("2")){
					strpqsi[18] = "�ϲ�";
				}
				else if(strpqsi[18].equals("3")){
					strpqsi[18] = "�ܲ�";
				}
				ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
				arrayAdapter = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strpqsi);
				lv01.setAdapter(arrayAdapter);
			}
			
			
			
			
			String[] strsleepness = new String[7];
			if(sleepness == null){
				strsleepness[1] = "����û�������ԣ�";
				System.out.println("sleepnessû��������");
			}
			else{
				for(int i = 0; i < sleepness.length();i++){
					strsleepness[i] = sleepness.substring(i, i+1);
				}
				for(int j = 0;j < 3;j++){
					if(strsleepness[j].equals("0")){
						strsleepness[j] = "��";
					}
					else if(strsleepness[j].equals("1")){
						strsleepness[j] = "���";
					}
					else if(strsleepness[j].equals("2")){
						strsleepness[j] = "�ж�";
					}
					else if(strsleepness[j].equals("3")){
						strsleepness[j] = "�ض�";
					}
					else if(strsleepness[j].equals("4")){
						strsleepness[j] = "���ض�";
					}
				}
				if(strsleepness[3].equals("0")){
					strsleepness[3] = "������";
				}
				else if(strsleepness[3].equals("1")){
					strsleepness[3] = "����";
				}
				else if(strsleepness[3].equals("2")){
					strsleepness[3] = "һ��";
				}
				else if(strsleepness[3].equals("3")){
					strsleepness[3] = "������";
				}
				else if(strsleepness[3].equals("4")){
					strsleepness[3] = "�ܲ�����";
				}
				
				if(strsleepness[4].equals("0")){
					strsleepness[4] = "û�и���";
				}
				else if(strsleepness[4].equals("1")){
					strsleepness[4] = "��΢";
				}
				else if(strsleepness[4].equals("2")){
					strsleepness[4] = "��Щ";
				}
				else if(strsleepness[4].equals("3")){
					strsleepness[4] = "�϶�";
				}
				else if(strsleepness[4].equals("4")){
					strsleepness[4] = "�ܶ����";
				}
				
				if(strsleepness[5].equals("0")){
					strsleepness[5] = "û��";
				}
				else if(strsleepness[5].equals("1")){
					strsleepness[5] = "һ��";
				}
				else if(strsleepness[5].equals("2")){
					strsleepness[5] = "��Щ";
				}
				else if(strsleepness[5].equals("3")){
					strsleepness[5] = "�϶�";
				}
				else if(strsleepness[5].equals("4")){
					strsleepness[5] = "�ܶ�";
				}
				
				if(strsleepness[6].equals("0")){
					strsleepness[6] = "û��";
				}
				else if(strsleepness[6].equals("1")){
					strsleepness[6] = "һ��";
				}
				else if(strsleepness[6].equals("2")){
					strsleepness[6] = "��Щ";
				}
				else if(strsleepness[6].equals("3")){
					strsleepness[6] = "�϶�";
				}
				else if(strsleepness[6].equals("4")){
					strsleepness[6] = "�ܶ�";
				}
				ArrayAdapter<String> arrayAdapter2 = null;// ����ArrayAdapter����
				arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strsleepness);
				lv02.setAdapter(arrayAdapter2);
			}
			
			
			String[] strhama = new String[15];
			if(hama == null){
				strhama[1] = "����û�������ԣ���";
				System.out.println("hamaû��������");
			}else{
				
				for(int i = 0; i < hama.length()-2;i++){
					strhama[i] = hama.substring(i, i+1);
				}
				strhama[14] = hama.substring(hama.length()-2,hama.length());
				for(int j = 0;j < 14;j++){
					if(strhama[j].equals("0")){
						strhama[j] = "��";
					}
					else if(strhama[j].equals("1")){
						strhama[j] = "���";
					}
					else if(strhama[j].equals("2")){
						strhama[j] = "�ж�";
					}
					else if(strhama[j].equals("3")){
						strhama[j] = "�ض�";
					}
					else if(strhama[j].equals("4")){
						strhama[j] = "���ض�";
					}
				}
				ArrayAdapter<String> arrayAdapter3 = null;// ����ArrayAdapter����
				arrayAdapter3 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strhama);
				lv03.setAdapter(arrayAdapter3);
			}
			

			String[] strhamd = new String[18];
			if(hamd == null){
				strhamd[1] = "����û�������ԣ���";
				System.out.println("hamdû��������");
			}else{
				for(int i = 0; i < hamd.length()-2;i++){
					strhamd[i] = hamd.substring(i, i+1);
				}
				strhamd[17] = hamd.substring(hamd.length()-2,hamd.length());
				for(int j = 0;j < 17;j++){
					if(strhamd[j].equals("0")){
						strhamd[j] = "��";
					}
					else if(strhamd[j].equals("1")){
						strhamd[j] = "���";
					}
					else if(strhamd[j].equals("2")){
						strhamd[j] = "�ж�";
					}
					else if(strhamd[j].equals("3")){
						strhamd[j] = "�ض�";
					}
					else if(strhamd[j].equals("4")){
						strhamd[j] = "���ض�";
					}
				}
				ArrayAdapter<String> arrayAdapter4 = null;// ����ArrayAdapter����
				arrayAdapter4 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strhamd);
				lv04.setAdapter(arrayAdapter4);
			}
			
			String[] strisi = new String[8];
			if(isi == null){
				strisi[1] = "����û�������ԣ���";
				System.out.println("isiû��������");
			}else{
				for(int i = 0; i < 7;i++){
					strisi[i] = isi.substring(i, i+1);
				}
				strisi[7] = isi.substring(7,isi.length());
				for(int j = 0;j < 7;j++){
					if(strisi[j].equals("0")){
						strisi[j] = "��";
					}
					else if(strisi[j].equals("1")){
						strisi[j] = "���";
					}
					else if(strisi[j].equals("2")){
						strisi[j] = "�ж�";
					}
					else if(strisi[j].equals("3")){
						strisi[j] = "�ض�";
					}
					else if(strisi[j].equals("4")){
						strisi[j] = "���ض�";
					}
				}
				ArrayAdapter<String> arrayAdapter5 = null;// ����ArrayAdapter����
				arrayAdapter5 = new ArrayAdapter<String>(this, R.layout.patient_infomanage_item,strisi);
				lv05.setAdapter(arrayAdapter5);
			}
			
		}

	}


