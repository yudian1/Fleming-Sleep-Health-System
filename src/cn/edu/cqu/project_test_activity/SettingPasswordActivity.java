package cn.edu.cqu.project_test_activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zghaikun.sleep_client.R;

public class SettingPasswordActivity extends Activity implements OnClickListener{
	
	private Button settingButton;
	private EditText current_pwd,new_pwd1,new_pwd2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_password);
		
		current_pwd = (EditText) findViewById(R.id.pwd_0);
		new_pwd1 = (EditText) findViewById(R.id.pwd_1);
		new_pwd2 = (EditText) findViewById(R.id.pwd_2);
		settingButton = (Button) findViewById(R.id.setting_button);
		settingButton.setOnClickListener(this);
		
		//保存一个初始密码
//		SharedPreferences.Editor editor = getSharedPreferences("password", MODE_PRIVATE).edit();
//		editor.putString("pwd", "12345678");
//		editor.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_button:
			SharedPreferences pref = getSharedPreferences("password", MODE_PRIVATE);
			if (current_pwd.getText().toString().equals(pref.getString("pwd", ""))) {
				if (new_pwd1.getText().toString().equals(new_pwd2.getText().toString()) && new_pwd1.getText().toString().length() != 0) {
					SharedPreferences.Editor editor = getSharedPreferences("password", MODE_PRIVATE).edit();
					editor.putString("pwd", new_pwd1.getText().toString());
					editor.commit();
					Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
					finish();
				}else {
					Toast.makeText(this, "两次密码不匹配", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(this, "原密码错误", Toast.LENGTH_SHORT).show();
			}
			
			
			break;

		default:
			break;
		}
	}

}
