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
	private String[] questiontitle = {"2、紧张：紧张感、易疲劳、不能放松，情绪反应，易哭、颤抖、感到不安。","3、害怕：害怕黑暗、陌生人、一人独处、动物、乘车或旅行及人多的场合。",
			"4、失眠：难以入睡、易醒、睡得不深、多梦、梦魇、夜惊、醒后感疲倦。","5、认知功能：或称记忆、注意障碍。注意力不能集中，记忆力差。",
			"6、抑郁心境：丧失兴趣、对以往爱好缺乏快感、忧郁、早醒、昼重夜轻。","7、躯体性焦虑：肌肉系统：肌肉酸痛、活动不灵活、肌肉抽动、肢体抽动、牙齿打颤、声音发抖。",
			"8、感觉系统：视物糊模，发冷发热，软弱无力感，浑身刺痛。","9、心血管系统症状：心动过速、心悸、胸痛、血管跳动感、昏倒感、心博脱漏。",
			"10、呼吸系统症状：胸闷、窒息感、叹息、呼吸困难。","11、胃肠道症状：吞咽困难、嗳气、消化不良(进食后腹痛、胃部烧灼痛、腹胀、恶心、胃部饱感)、肠鸣、腹泻、体重减轻、便秘。",
			"12、生殖泌尿系统症状：尿意频数、尿急、停经、性冷淡、过早射精、勃起不能、阳萎","13、植物神经症状：口干、潮红、苍白、易出汗、易起“鸡起疙瘩”、紧张性头痛、毛发竖起。",
			"14、会谈时行为表现：(1)一般表现：紧张不安、咬手指、紧紧握拳、摸弄手帕、面肌抽动、不停顿足、表情僵硬、面色苍白；(2)生理表现：吞咽、打呃、安静时心率呼吸快、震颤、瞳孔放大、眼睑跳动、出汗、眼球突出。",
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
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
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
				System.out.println("选择的结果----》" + i + "----" + resulttext[i]);
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
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
							.show();
							return;
						}
				RelativeLayout ll = (RelativeLayout) findViewById(R.id.linearLayout);
				ll.setVisibility(View.GONE);
				TextView tv = (TextView) findViewById(R.id.resulttitle);
				tv.setVisibility(View.VISIBLE);
				score +=j;
				tv.setText("您的得分为：" + score);
				resulttext[14] = j;
				//保存数据
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
	//获得要保存的数据
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
				System.out.println("当前ID----" + pd.getMaxId());
				Toast.makeText(HAMAActivity.this, "【心理评估一】数据保存成功！", Toast.LENGTH_SHORT).show();
				
				//发送两个广播
				Intent intent = new Intent(action4);
				//intent.putExtra("data4", "data4");
				sendBroadcast(intent);
				setAlarmTime(HAMAActivity.this);
			}
			
			public void setAlarmTime(Context context) {
				//获取系统的闹钟服务
				AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				//过滤条件
				Intent intent = new Intent(AlarmAction4);
				PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
				//int interval = 1000 * 60 * 60 * 24;// 24小时
				//24小时太长了，还是先测试1分钟的能不能实现
				int interval = 1000 * 60;// 24小时
				//设置闹钟，System.currentTimeMillis() + interval系统当前时间+24小时
				am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);			   
			}

}
