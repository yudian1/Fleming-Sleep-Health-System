package cn.edu.cqu.project_test_activity;

import cn.edu.cqu.project_test_dao.DoctorAdviceDAO;
import cn.edu.cqu.project_test_model.DoctorAdivce;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorAdviceActivity extends Activity{
	
	private TextView sleep_latency,sleep_latency_state,sleep_time,sleep_time_state,sleep_efficient,sleep_efficient_state
	,today_sleep,today_sleep_state,thisweek_sleep_latency,thisweek_sleep_time,add_doctor_advice;
	private String patient_phone2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_advice);
		
		sleep_latency = (TextView) findViewById(R.id.sleep_latency);
		sleep_latency_state = (TextView) findViewById(R.id.sleep_latency_state);
		sleep_time = (TextView) findViewById(R.id.sleep_time);
		sleep_time_state = (TextView) findViewById(R.id.sleep_time_state);
		sleep_efficient = (TextView) findViewById(R.id.sleep_efficient);
		sleep_efficient_state = (TextView) findViewById(R.id.sleep_efficient_state);
		today_sleep = (TextView) findViewById(R.id.today_sleep);
		today_sleep_state = (TextView) findViewById(R.id.today_sleep_state);
		thisweek_sleep_latency = (TextView) findViewById(R.id.thisweek_sleep_latency);
		thisweek_sleep_time = (TextView) findViewById(R.id.thisweek_sleep_time);
		add_doctor_advice = (TextView) findViewById(R.id.add_doctor_advice);
		
		Intent intent = getIntent();
		String patient_phone = intent.getStringExtra("patient_phone");
		System.out.println("patient_phone---" + patient_phone);
		
		DoctorAdviceDAO dadao = new DoctorAdviceDAO(this);
		DoctorAdivce advice = new DoctorAdivce();
		advice = dadao.find(patient_phone);
		
		System.out.println("advice---" + advice);
		
		patient_phone2 = advice.getPatient_phone();
		System.out.println("yizhuphone----" + patient_phone2);
		
		if (advice == null) {
			new AlertDialog.Builder(DoctorAdviceActivity.this)
			.setTitle("温馨提示")
			.setMessage("您还没有医嘱")
			.setPositiveButton("确定", null)
			.show();
			return;
		}else {
			String advices = advice.getAdvice();
			System.out.println("advices----" + advices);
			
			String[] doctorAdvice = advices.split(",");
			for (int i = 0; i < doctorAdvice.length; i++) {
				System.out.println("doctorAdvice---" + doctorAdvice[i]);
			}
			sleep_latency.setText(doctorAdvice[0] + " 分钟   较前：");
			sleep_latency_state.setText(transform(doctorAdvice[1]));
			sleep_time.setText(doctorAdvice[2] + " 分钟   较前：");
			sleep_time_state.setText(transform(doctorAdvice[3]));
			sleep_efficient.setText(doctorAdvice[4] + " 分钟   较前：");
			sleep_efficient_state.setText(transform(doctorAdvice[5]));
			today_sleep.setText(doctorAdvice[6] + " 分钟   较前：");
			today_sleep_state.setText(transform(doctorAdvice[7]));
			thisweek_sleep_latency.setText(doctorAdvice[8] + " 分钟");
			thisweek_sleep_time.setText(doctorAdvice[9] + " 分钟 ");
			add_doctor_advice.setText(doctorAdvice[10]);
		}
	}
	private String transform(String str){
		if (str.equals("0")) {
			str = "减少";
		}else if (str.equals("1")) {
			str = "相同";
		}else if (str.equals("2")) {
			str = "增加";
		}
		return str;
	}
}
