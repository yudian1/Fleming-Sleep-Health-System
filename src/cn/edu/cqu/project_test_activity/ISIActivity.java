package cn.edu.cqu.project_test_activity;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
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
import cn.edu.cqu.project_test_dao.ExportToCSV;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_dao.PatientTestDAO;
import cn.edu.cqu.project_test_model.PatientTest;

public class ISIActivity extends Activity{

	private RelativeLayout rl;
	private RelativeLayout rl2;
	private int i = 0;
	private String[] question = {"1、入睡困难：","2、维持睡眠困难：","3、早醒：","4、您对目前的睡眠模式满意/不满意程度如何？：",
			"5、您认为您的失眠在多大程度上影响了您的日常功能：","6、您的失眠问题影响了您的生活质量，您觉得在别人眼中你的失眠程度如何？",
			"7、您对目前的睡眠问题的担心/痛苦程度：","您的得分："};
	private Button resultbutton;
	private int j;
	private String buttontext;
	private int resulttext[] = new int[10];
	private RadioButton button01;
	private RadioButton button02;
	private RadioButton button03;
	private RadioButton button04;
	private RadioButton button05;
	private RadioGroup radioGroup;
	private TextView questiontext;
	private int score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.isi);
		
		questiontext = (TextView) findViewById(R.id.questiontitle);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		button01 = (RadioButton) findViewById(R.id.button01);
		button02 = (RadioButton) findViewById(R.id.button02);
		button03 = (RadioButton) findViewById(R.id.button03);
		button04 = (RadioButton) findViewById(R.id.button04);
		button05 = (RadioButton) findViewById(R.id.button05);
		resultbutton = (Button) findViewById(R.id.resultbutton);
		rl = (RelativeLayout) findViewById(R.id.relativeLayout);
		rl2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
		radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
		rl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				rl.setVisibility(View.INVISIBLE);
				rl2.setVisibility(View.VISIBLE);
				return false;
			}
		});
		
		final Button nextbutton = (Button) findViewById(R.id.nextbutton);
		nextbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//questiontext.setText(question[i]);	
				//i ++;
				if(button01.isChecked()==false && button02.isChecked()==false && button03.isChecked()==false
						&& button04.isChecked()==false&& button05.isChecked()==false && i != 0){
							
							new AlertDialog.Builder(ISIActivity.this)
							.setTitle("温馨提示")
							.setMessage("您还没有选择任何一个选项！")
							.setPositiveButton("确定", null)
							.show();
							return;
						}
				else{
					questiontext.setText(question[i]);	
					if(i < question.length +1){
						i++;
					}
				if(i == 1){
					radioGroup.setVisibility(View.VISIBLE);
				}
					//i++;
					radioGroup.clearCheck();
				}
				score += j;
				System.out.println("result----->" + score);
				resulttext[i]= j;
				System.out.println("选择的结果----》" + resulttext[i]);
				//PInformation();
				//question[7] = "您的得分: " + score;
				if(i == question.length - 1){
					nextbutton.setText("确定");
					//score += j;
					int total = score +j;
					question[7] = "您的得分: " + total;
					System.out.println("您的得分---" + total);
					//PInformation();
					//finish();
				}
				
				if(i == question.length ){
					//finish();
					PInformation();
					radioGroup.setVisibility(View.INVISIBLE);
					nextbutton.setVisibility(View.INVISIBLE);
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
		}
		
	}
	//获得要保存的数据
			public void PInformation(){
				int[] result = new int[8];
				result[0] = resulttext[2];
				result[1] = resulttext[3];
				result[2] = resulttext[4];
				result[3] = resulttext[5];
				result[4] = resulttext[6];
				result[5] = resulttext[7];
				result[6] = resulttext[8];
				result[7] = score;
				String isi = "";
				for(int i = 0;i < result.length;i++){
					isi += String.valueOf(result[i]);
				}
				System.out.println("严重失眠指数----->" + isi);
				PatientTest patientTest = new PatientTest();
				patientTest.setIsi(isi);
				PatientTestDAO ptdao = new PatientTestDAO(ISIActivity.this);
				//获得最大的id
				PatientInformationDAO pd = new PatientInformationDAO(ISIActivity.this);
				ptdao.updateISI(pd.getMaxId(), patientTest);
				System.out.println("当前ID----" + pd.getMaxId());
				Toast.makeText(ISIActivity.this, "【失眠程度评估】数据保存成功！", Toast.LENGTH_SHORT).show();
				
				Cursor c = pd.export();
				ExportToCSV export2CSV = new ExportToCSV();
				export2CSV.exportToCSV(c, "test_table.csv");
			}


}
