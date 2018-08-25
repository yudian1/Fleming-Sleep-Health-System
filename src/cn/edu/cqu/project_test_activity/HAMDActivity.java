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

public class HAMDActivity extends Activity{
	public static final String action5 = "broadcast.action5";
	public static final String AlarmAction5 = "alarm.action5";
	private RadioButton button00;
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private Button resultButton;
	private RadioGroup radioGroip;
	private RelativeLayout rl;
	private RelativeLayout rl2;
	private String[] questiontitle = {"2.有罪感：（1）责备自己，感到自己已连累他人；（2）认为自己犯了罪，或反复思考以往的过失和错误；（3）认为目前的疾病，是对自己错误的惩罚，或有罪恶妄想；（4）罪恶妄想伴有指责或威胁性幻觉。",
			"3．自杀：（1）觉得活得没有意义；（2）希望自死去，或常想到与死有关的事；（3）消极观念（自杀念头）；（4）有严重杀行为。",
			"4．入睡困难（初段失眠）：（1）主诉有入睡困难，上床半小时后仍不能入睡。（要注意平时病人入睡的时间）；（2）主诉每晚均有入睡困难。",
			"5．睡眠不深（中段失眠）：（1）睡眠浅，多恶梦；（2）半夜（晚12点钟以前）曾醒来（不包括上厕所）。",
			"6．早醒（末段失眠）：（1）有早醒，比平时早醒1小时，但能重新入睡（应排除平时的习惯）；（2）早醒后无法重新入睡。",
			"7．工作和兴趣：（1）提问时才诉述；（2）自发地直接或间接表达对活动、工作或学习失去兴趣，如感到没精打彩，犹豫不决，不能坚持或需强迫自己去工作或活动；（3）活动时间减少或成效下降，住院病人每天参加病房劳动或娱乐不满3小时；（4）因目前的疾病而停止工作，住院者不参加任何活动或者没有他人帮助便不能完成病室日常事务（注意不能凡住院就打4分）。",
			"8．阻滞（指思维和言语缓慢，注意力难以集中，主动性减退）：（1）精神检查中发现轻度阻滞；（2）精神检查中发现明显阻滞；（3）精神检查进行困难；（4）完全不能回答问题（木僵）。",
			"9．激越：（1）检查时有些心神不定；（2）明显心神不定或小动作多；（3）不能静坐，检查中曾起立；（4）搓手、咬手指、扯头发、咬嘴唇。","10．精神性焦虑：（1）问及时诉述；（2）自发地表达；（3）表情和言谈流露出明显忧虑；（4）明显惊恐。",
			"11．躯体性焦虑（指焦虑的生理症状，包括：口干、腹胀、腹泻、打呃、腹绞痛、心悸、头痛、过度换气和叹气，以及尿频和出汗）：（1）轻度；（2）中度，有肯定的上述症状；（3）重度，上述症状严重，影响生活或需要处理；（4）严重影响生活和活动。",
			"12．胃肠道症状：（1）食欲减退，但不需他人鼓励便自行进食；（2）进食需他人催促或请求和需要应用泻药或助消化药。",
			"13．全身症状：（l）四肢，背部或颈部沉重感，背痛、头痛、肌肉疼痛，全身乏力或疲倦；（2）症状明显。",
			"14．性症状（指性欲减退，月经紊乱等）：（1）轻度；（2）重度；（3）不能肯定，或该项对被评者不适合（不计入总分）。","15．疑病：（1）对身体过分关注；（2）反复考虑健康问题；（3）有疑病妄想；（4）伴幻觉的疑病妄想。",
			"16．体重减轻：按病史评定：（1）患者诉述可能有体重减轻；（2）肯定体重减轻。按体重记录评定：（1）一周内体重减轻超过0.5公斤；（2）一周内体重减轻超过1公斤。",
			"17．自知力：（0）知道自己有病，表现为抑郁；（1）知道自己有病，但归咎伙食太差，环境问题，工作过忙，病毒感染或需要休息；（2）完全否认有病。"};
	
	private int i = 0;
	private int j;
	private int score;
	private String buttontext;
	private int resulttext[] = new int[19];
	//private int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hamd);
		
		final TextView questiontext = (TextView) findViewById(R.id.questiontitle);
		radioGroip = (RadioGroup) findViewById(R.id.radiogroup);
		button00 = (RadioButton) findViewById(R.id.button00);
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		resultButton = (Button)findViewById(R.id.resultbutton);
		radioGroip.setOnCheckedChangeListener(new RadioGroupListener());
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
		
		final Button nextButton = (Button) findViewById(R.id.nextbutton);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false && button00.isChecked()==false){
							
							new AlertDialog.Builder(HAMDActivity.this)
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
				System.out.println("选择的结果----》"+i+ ">>>>>>>" + resulttext[i]);
				if (i == 3) {
					button03.setVisibility(View.GONE);
					button04.setVisibility(View.GONE);
				}
				if (i == 6) {
					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
				}
				if (i == 11) {
					button03.setVisibility(View.GONE);
					button04.setVisibility(View.GONE);
				}
				if (i == 13) {
					button03.setVisibility(View.VISIBLE);
//					button04.setVisibility(View.VISIBLE);
				}
				if (i == 14) {
//					button03.setVisibility(View.VISIBLE);
					button04.setVisibility(View.VISIBLE);
				}
				if (i == 15) {
					button03.setVisibility(View.GONE);
					button04.setVisibility(View.GONE);
				}
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
						&& button04.isChecked()==false && button00.isChecked() == false){
							
							new AlertDialog.Builder(HAMDActivity.this)
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
				int array_score[] = new int[17];
				for (int i = 0; i < array_score.length; i++) {
					array_score[i] = j;
				}
				score += j;
				score -= array_score[13];
				tv.setText("您的得分为：" + score);
				resulttext[17] = j;
				System.out.println("选择的结果>>>》"+ resulttext[17]);
				//保存数据
				PInformation();
				resultButton.setVisibility(View.GONE);
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
//			if(checkedId == button05.getId()){
//				j = 4;
//				buttontext = button05.getText().toString();
//				System.out.println("buttontext ---" + buttontext);
//			}
		}
		
	}
	//获得要保存的数据
	public void PInformation(){
		resulttext[18] = score;
		//int hamd_score = score;
		System.out.println("选择的结果>>>》"+ resulttext[17]);
		
		String hamd = "";
		//讲述组转化成字符串
		for(int i = 1;i < resulttext.length;i++){
			hamd += String.valueOf(resulttext[i]);
		}
		System.out.println("HAMD----->" + hamd);
		PatientTest patientTest = new PatientTest();
		patientTest.setHamd(hamd);
		PatientTestDAO ptdao = new PatientTestDAO(HAMDActivity.this);
		PatientInformationDAO pd = new PatientInformationDAO(HAMDActivity.this);
		ptdao.updateHAMD(pd.getMaxId(), patientTest);
		System.out.println("当前ID----" + pd.getMaxId());
		Toast.makeText(HAMDActivity.this, "【心理评估二】数据保存成功！", Toast.LENGTH_SHORT).show();
		
		//发送两个广播
		Intent intent = new Intent(action5);
		//intent.putExtra("data5", "data5");
		sendBroadcast(intent);
		setAlarmTime(HAMDActivity.this);
	}
	
	public void setAlarmTime(Context context) {
		//获取系统的闹钟服务
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		//过滤条件
		Intent intent = new Intent(AlarmAction5);
		//
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		//int interval = 1000 * 60 * 60 * 24;// 24小时
		//24小时太长了，还是先测试1分钟的能不能实现
		int interval = 1000 * 60;// 24小时
		//设置闹钟，System.currentTimeMillis() + interval系统当前时间+24小时
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, sender);
		   
	}
}
