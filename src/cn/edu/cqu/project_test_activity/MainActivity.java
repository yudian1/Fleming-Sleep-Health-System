package cn.edu.cqu.project_test_activity;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.cqu.movie.MovieListActivity;
import cn.edu.cqu.project_test_chart.CurativeEffectActivity;
import cn.edu.cqu.project_test_chart.SelectChartActivity;
import cn.edu.cqu.project_test_dao.DoctorAdviceDAO;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_model.DoctorAdivce;
import cn.edu.cqu.project_test_model.PatientInformation;

import com.zghaikun.sleep_client.R;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private ImageButton_define button7;
	private Button databtn;
	private Button isiButton;
	private ImageButton_define weixinButton;
	private String input;
	private Button updateButton,downloadButton;
	private ImageButton_define vedioButton;
	private ImageButton_define voicebutton;
	private ImageButton_define button8;
	private TextView patinetInfo;
	private String patient_phone;
	private Button setting;
	private Button chakanButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		patinetInfo = (TextView) findViewById(R.id.patientInfo);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		
		button7 = (ImageButton_define) findViewById(R.id.button7);
		weixinButton = (ImageButton_define) findViewById(R.id.Weixinbutton);
		updateButton = (Button) findViewById(R.id.updateButton);
		vedioButton = (ImageButton_define) findViewById(R.id.vediobutton);
		voicebutton = (ImageButton_define) findViewById(R.id.voicebutton);
		//控制主界面按钮
		setButton();
		
		setting = (Button) findViewById(R.id.setting);
		setting.setOnClickListener(this);
		downloadButton = (Button) findViewById(R.id.downloadButton);
		downloadButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				UpdateDoctorAdviceThread ut = new UpdateDoctorAdviceThread();
				ut.start();
				
				if (isNetworkAvailable(MainActivity.this) == true )
				{
					/*if (patient_phone != null) {
						Intent intent = new Intent(MainActivity.this, DoctorAdviceActivity.class);
						startActivity(intent);
					}
					else {
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("温馨提示")
						.setMessage("请注册！")
						.setPositiveButton("确定", null)
						.show();
						return;
					}*/
				}
				else{
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("温馨提示")
					.setMessage("请连接网络！")
					.setPositiveButton("确定", null)
					.show();
					return;
				}
					/*if (patient_phone != null) {
						Intent intent = new Intent(MainActivity.this, DoctorAdviceActivity.class);
						//intent.putExtra("patient_phone", patient_phone);
						startActivity(intent);
					}else {
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("温馨提示")
						.setMessage("请注册！")
						.setPositiveButton("确定", null)
						.show();
						return;
					}*/
				}	
				
		});
		chakanButton= (Button) findViewById(R.id.chakanButton);
        chakanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

					if (patient_phone != null) {
						Intent intent = new Intent(MainActivity.this, DoctorAdviceActivity.class);
						intent.putExtra("patient_phone", patient_phone);
						startActivity(intent);
					}
					else {
						new AlertDialog.Builder(MainActivity.this)
						.setTitle("温馨提示")
						.setMessage("请注册！")
						.setPositiveButton("确定", null)
						.show();
						return;
					}
				}

		});
		button8 = (ImageButton_define) findViewById(R.id.button8);
		patinetInfo();
		button8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, SelectChartActivity.class);
				startActivity(intent);
			}
		});
		voicebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, MP3ListActivity.class);
				startActivity(intent);
			}
		});
		vedioButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
				startActivity(intent);
			}
		});
		updateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isNetworkAvailable(MainActivity.this) == true )
				{
					Intent intent = new Intent(MainActivity.this, UpdateInfomation.class);
					startActivity(intent);
				}
				else{
					new AlertDialog.Builder(MainActivity.this)
					.setTitle("温馨提示")
					.setMessage("请连接网络！")
					.setPositiveButton("确定", null)
					.show();
					return;
				}
			}
		});
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final SharedPreferences pref = getSharedPreferences("password", MODE_PRIVATE);
				//第一次打开,保存一个初始密码
//				SharedPreferences.Editor editor = getSharedPreferences("password", MODE_PRIVATE).edit();
				int count = pref.getInt("count", 0);
				System.out.println("count---" + count);
				Editor editor = pref.edit();
				//第一次打开应用程序，设置默认密码为12345678
				if (count == 0) {
					editor.putString("pwd", "12345678");
				}
				editor.putInt("count", ++count);
				editor.commit();
				
				LayoutInflater factory = getLayoutInflater();
	            final View textEntryView = factory.inflate(R.layout.dialog, null);
	            final EditText editTextName = (EditText) textEntryView.findViewById(R.id.editTextName);
	            AlertDialog.Builder ad1 = new AlertDialog.Builder(MainActivity.this);
	            ad1.setTitle("请输入密码");
	            ad1.setView(textEntryView);
	            ad1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int i) {
	                	String input = editTextName.getText().toString();
	                	
	                	pref.getString("pwd", "");
	                	System.out.println("密码----" + pref.getString("pwd", ""));
	                	//密码正确，清空
	                	if (input.equals(pref.getString("pwd", ""))) {
	                		//每次注册都清空SharedPreferences中的时间数据和按钮的按下次数
	                		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
	                		Editor editor = pref.edit();
	                		editor.putInt("count", 0);
	                		editor.putString("date", "");
	                		editor.commit();
	                		
	                		Intent intent = new Intent();
	        				intent.setClass(MainActivity.this, SleepHealthyActivity.class);
	        				startActivity(intent);
	        				//重新注册后按键重新可用
	        				button2.setEnabled(true);
	        				button3.setEnabled(true);
	        				button4.setEnabled(true);
	        				button5.setEnabled(true);
						}else {
							new AlertDialog.Builder(MainActivity.this)
							.setTitle("请输入密码")
							.setMessage("密码错误")
							.setPositiveButton("确定", null)
							.show();
							return;
						}
	                }
	            });
	            ad1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int i) {

	                }
	            });
	            ad1.show();
//				Intent intent = new Intent();
//				intent.setClass(MainActivity.this, SleepHealthyActivity.class);
//				startActivity(intent);
				//这样就有缺陷，患者没有做完测试推出之后就不能再次测试了
				//button1.setClickable(false);
			}
		});
//		//过滤条件
//		IntentFilter filter = new IntentFilter(SleepHealthyActivity.action);
//		//注册广播
//		 registerReceiver(broadcastReceiver, filter);
//		//过滤条件
//		IntentFilter filter2 = new IntentFilter(SleepHealthyActivity.AlarmAction);
//		//注册广播
//		registerReceiver(AlarmRecevier, filter2);
//		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, PSQIActivity.class);
				startActivity(intent);
			}
		});
//		//过滤条件
//		IntentFilter button2filter = new IntentFilter(PSQIActivity.action2);
//		//注册广播
//				registerReceiver(broadcastReceiver2, button2filter);
//				//过滤条件
//				IntentFilter button2filter2 = new IntentFilter(PSQIActivity.AlarmAction2);
//				//注册广播
//				registerReceiver(AlarmRecevier2, button2filter2);
				
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SleepnessActivity.class);
				startActivity(intent);
			}
		});
//		//过滤条件
//				IntentFilter button3filter = new IntentFilter(SleepnessActivity.action3);
//				//注册广播
//						registerReceiver(broadcastReceiver3, button3filter);
//						//过滤条件
//						IntentFilter button3filter2 = new IntentFilter(SleepnessActivity.AlarmAction3);
//						//注册广播
//						registerReceiver(AlarmRecevier3, button3filter2);
//						
//		button4.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				Intent intent = new Intent();
//				intent.setClass(MainActivity.this, HAMAActivity.class);
//				startActivity(intent);
//			}
//		});
//		//过滤条件
//		IntentFilter button4filter = new IntentFilter(HAMAActivity.action4);
//		//注册广播
//				registerReceiver(broadcastReceiver4, button4filter);
//				//过滤条件
//				IntentFilter button4filter2 = new IntentFilter(HAMAActivity.AlarmAction4);
//				//注册广播
//				registerReceiver(AlarmRecevier4, button4filter2);
				
		button5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, HAMDActivity.class);
				startActivity(intent);
			}
		});
//		//过滤条件
//				IntentFilter button5filter = new IntentFilter(HAMDActivity.action5);
//				//注册广播
//						registerReceiver(broadcastReceiver5, button5filter);
//						//过滤条件
//						IntentFilter button5filter2 = new IntentFilter(HAMDActivity.AlarmAction5);
//						//注册广播
//						registerReceiver(AlarmRecevier5, button5filter2);
						
		button6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, SleepDiaryMain.class);
				startActivity(intent);
			}
		});
		button7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input = "mobileqq";
				openApp(input);
			}
		});
		weixinButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				input = "tencent.mm";
				openApp(input);
			}
		});
//		isiButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				intent.setClass(MainActivity.this, ISIActivity.class);
//				startActivity(intent);
//			}
//		});
//		databtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
		
//			public void onClick(View arg0) {
//				Intent intent = new Intent();
//				System.out.println("打开。。。。。");
//				intent.setClass(MainActivity.this, DataManagementeActivity.class);
//				startActivity(intent);
//			}
//		});
		
	}
//	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			System.out.println("更新控件");
//			if("broadcast.action".equals(intent.getAction())){
//				button1.setClickable(false);
//			}
//		}
//	};
//
//	BroadcastReceiver AlarmRecevier = new BroadcastReceiver(){
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			if ("alarm.action".equals(intent.getAction())) {
//				//在这里更新UI
//				//设置按键使能;
//				button1.setClickable(true);
//				System.out.println("打印一句话");
//				Toast.makeText(MainActivity.this, "打印一句话！！", Toast.LENGTH_LONG).show();
//				return;
//				}
//			}
//			
//		};
		
//		BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() {
//
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				// TODO Auto-generated method stub
//				//System.out.println("更新控件");
////				intent.getExtras().getString("data2");
////				button2.setClickable(false);
//				if("broadcast.action2".equals(intent.getAction())){
//					button2.setClickable(false);
//				}
//				}
//			};
//		BroadcastReceiver AlarmRecevier2 = new BroadcastReceiver(){
//
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				// TODO Auto-generated method stub
//				if ("alarm.action2".equals(intent.getAction())) {
//					//在这里更新UI
//					//设置按键使能;
//					button2.setClickable(true);
//					//System.out.println("打印一句话");
//					//Toast.makeText(MainActivity.this, "打印一句话！！", Toast.LENGTH_LONG).show();
//					return;
//					}
//				}
//						
//			};
//			
//			BroadcastReceiver broadcastReceiver3 = new BroadcastReceiver() {
//
//				@Override
//				public void onReceive(Context context, Intent intent) {
//					// TODO Auto-generated method stub
//					//System.out.println("更新控件");
////					intent.getExtras().getString("data3");
////					button3.setClickable(false);
//					if("broadcast.action3".equals(intent.getAction())){
//						button3.setClickable(false);
//					}
//					}
//				};
//				BroadcastReceiver AlarmRecevier3 = new BroadcastReceiver(){
//
//				@Override
//				public void onReceive(Context context, Intent intent) {
//					// TODO Auto-generated method stub
//					if ("alarm.action3".equals(intent.getAction())) {
//						//在这里更新UI
//						//设置按键使能;
//						button3.setClickable(true);
//						//System.out.println("打印一句话");
//						//Toast.makeText(MainActivity.this, "打印一句话！！", Toast.LENGTH_LONG).show();
//						return;
//						}
//					}
//							
//				};
//				BroadcastReceiver broadcastReceiver4 = new BroadcastReceiver() {
//
//					@Override
//					public void onReceive(Context context, Intent intent) {
//						// TODO Auto-generated method stub
//						//System.out.println("更新控件");
////						intent.getExtras().getString("data4");
////						button4.setClickable(false);
//						if("broadcast.action4".equals(intent.getAction())){
//							button4.setClickable(false);
//						}
//						}
//					};
//					BroadcastReceiver AlarmRecevier4 = new BroadcastReceiver(){
//
//					@Override
//					public void onReceive(Context context, Intent intent) {
//						// TODO Auto-generated method stub
//						if ("alarm.action4".equals(intent.getAction())) {
//							//在这里更新UI
//							//设置按键使能;
//							button4.setClickable(true);
//							//System.out.println("打印一句话");
//							//Toast.makeText(MainActivity.this, "打印一句话！！", Toast.LENGTH_LONG).show();
//							return;
//							}
//						}
//								
//					};			
//					BroadcastReceiver broadcastReceiver5 = new BroadcastReceiver() {
//
//						@Override
//						public void onReceive(Context context, Intent intent) {
//							// TODO Auto-generated method stub
//							//System.out.println("更新控件");
////							intent.getExtras().getString("data5");
////							button5.setClickable(false);
//							if("broadcast.action5".equals(intent.getAction())){
//								button5.setClickable(false);
//							}
//							}
//						};
//						BroadcastReceiver AlarmRecevier5 = new BroadcastReceiver(){
//
//						@Override
//						public void onReceive(Context context, Intent intent) {
//							// TODO Auto-generated method stub
//							if ("alarm.action5".equals(intent.getAction())) {
//								//在这里更新UI
//								//设置按键使能;
//								button5.setClickable(true);
//								//System.out.println("打印一句话");
//								//Toast.makeText(MainActivity.this, "打印一句话！！", Toast.LENGTH_LONG).show();
//								return;
//								}
//							}
//									
//						};	
		private String wsdlURL,webMethod,namespace,soapAction;
		private SoapObject soapObject;
		private SoapSerializationEnvelope  envelope;
		private HttpTransportSE se ;
		private String data;
		class UpdateDoctorAdviceThread extends Thread{
							@Override
							public void run() {
								Looper.prepare();
								
								
								//步骤1： 设置Webservice 的调用参数必须静态的IP地址localhost不认？必须静态IP地址
								 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
								 webMethod = "getgetAllDoctor_Advice";
								 namespace = "http://service.cloud.chinasoft.com";
								 soapAction = namespace + webMethod;
								
								//步骤2:创建一个对象SoapObject
							    soapObject = new SoapObject(namespace, webMethod);
								//步骤3：传递参数
								
								//步骤4：创建一个SoapSer
								SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

								//步骤5:信封设置一下传回对象
								envelope.bodyOut = soapObject;
							
								//步骤6：创建邮递员
								 se = new HttpTransportSE(wsdlURL);
								//步骤7发送请求：
								try {
									se.call(soapAction, envelope);
									//步骤8：获取从互联网返回的结果
									if(envelope.getResponse() != null)
									{
										Object resout = envelope.getResponse();
										System.out.println("result----" + resout.toString());
										System.out.println("result----" + resout.getClass());
										System.out.println(resout instanceof Vector);
										//解析集合resoult
										@SuppressWarnings("unchecked")
										Vector<SoapObject> vec = (Vector<SoapObject>) resout;
										System.out.println("vec--" + vec);
										//循环显示
										StringBuffer buffer = new StringBuffer();
										for(SoapObject soapObject : vec)
										{
											buffer.append(soapObject.getProperty("patient_phone")+"_");
											buffer.append(soapObject.getProperty("advice")+"#");
										}
										
										buffer.deleteCharAt(buffer.length()-1);
										data = new String(buffer);
										jiexiDoctorAdvice(data);
										
										//测试：显示结果
										Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_LONG).show();
										System.out.println("结果----" + data);
									}
								} catch (IOException e) {
									e.printStackTrace();
								} catch (XmlPullParserException e) {
									e.printStackTrace();
								}
								
								Looper.loop();}
						}
					    DoctorAdviceDAO dadao;
					    public void jiexiDoctorAdvice(String str){
							String patient[] = null;
							String patientAdvice[] = null;
							//按照“#”分割,获得所有的医嘱
							patient = str.split("#");
							dadao = new DoctorAdviceDAO(MainActivity.this);
							//清空表格
							dadao.deletetable();
							for(int i = 0;i < patient.length;i++){
								//获得病人信息
								System.out.println("patient23----" + patient[i]);
								patientAdvice = patient[i].split("_");							
								DoctorAdivce da = new DoctorAdivce(patientAdvice[0], patientAdvice[1]);
								//插入数据
								dadao.insert(da);
								
							}
							
						}
	private void setButton(){
		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
		//第一次打开,保存一个初始密码
//		SharedPreferences.Editor editor = getSharedPreferences("password", MODE_PRIVATE).edit();
		String currentDate = pref.getString("date", "");
		
		if (!currentDate.equals("")) {
			String[] preftime =  currentDate.split(",");
			for (int i = 0; i < preftime.length; i++) {
				System.out.println("数组---" + preftime[i]);
			}
			System.out.println("currentDate---" + currentDate);
			System.out.println("数据长度------" + preftime.length);
			String systemdate = Current_date();
			String [] time = systemdate.split(",");
			for (int i = 0; i < time.length; i++) {
				System.out.println("数组---" + time[i]);
			}
			//一天之后之后禁用
			if (Integer.parseInt(time[4]) > (Integer.parseInt(preftime[4]) + 1)) {
				button2.setEnabled(false);
				button3.setEnabled(false);
				button4.setEnabled(false);
				button5.setEnabled(false);
				System.out.println("不可用");
			}
			//四周之后重新可用
			if (Integer.parseInt(time[4]) > (Integer.parseInt(preftime[4]) + 5)) {
				button2.setEnabled(true);
				button3.setEnabled(true);
				button4.setEnabled(true);
				button5.setEnabled(true);
				System.out.println("重新可用");
			}
		}
	}
	//获取当前日期
		public String Current_date(){
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DAY_OF_MONTH);
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int munite = c.get(Calendar.MINUTE);
			String date = year + "," + month + "," + day + "," + hour + "," + munite;
			//text.setText("记录日期：" + date);
			System.out.println(date);
			return date;
		}
	protected void onDestroy() {
		super.onDestroy();
	};
	
	
	private List<Map<String, Object>> list = null;
    private PackageManager mPackageManager;
    private List<ResolveInfo> mAllApps;
    /**
     * 检查系统应用程序，并打开
     */
    private void openApp(String str){
        //应用过滤条件
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        System.out.println("testrrr");
        mPackageManager = this.getPackageManager();
        System.out.println("tesr");
        mAllApps = mPackageManager.queryIntentActivities(mainIntent, 0);
        //按报名排序
        Collections.sort(mAllApps, new ResolveInfo.DisplayNameComparator(mPackageManager));

        for(ResolveInfo res : mAllApps){
            //该应用的包名和主Activity
            String pkg = res.activityInfo.packageName;
            String cls = res.activityInfo.name;
            //System.out.println("pkg---" +pkg);
            System.out.println("打印出来的----" + str);

            // 打开QQ pkg中包含"qq"，打开微信，pkg中包含"mm"
            if(pkg.contains(str)){
                ComponentName componet = new ComponentName(pkg, cls);
                Intent intent = new Intent();
                intent.setComponent(componet);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(intent);
            }
        }
    
} 
    public static boolean isNetworkAvailable(Context context){
    	ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	if(cm == null){
    		
    	}
    	else {
    		NetworkInfo[] info = cm.getAllNetworkInfo();
    		if(info != null){
    			for(int i = 0 ; i < info.length;i ++){
    				if(info[i].getState() == NetworkInfo.State.CONNECTED){
    					return true;
    				}
    					
    			}
    		}
    	}
    	return false;
    }
    
    public void patinetInfo(){
    	PatientInformationDAO pi = new PatientInformationDAO(this);
    	PatientInformation patient = pi.find((int)pi.getCount());
    	if (patient != null) {
			String name = patient.getPatient_name();
			String gender = patient.getPatient_gender();
			String age = patient.getPatient_age();
			patient_phone = patient.getPatient_phone();
			patinetInfo.setText("当前用户：" + name + ", " + gender + ", " + age);
		}
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting:
			startActivity(new Intent(MainActivity.this, SettingPasswordActivity.class));
			break;
		default:
			break;
		}
		
	}
}
