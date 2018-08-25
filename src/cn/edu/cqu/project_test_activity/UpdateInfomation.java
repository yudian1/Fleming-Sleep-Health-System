package cn.edu.cqu.project_test_activity;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_dao.PatientTestDAO;
import cn.edu.cqu.project_test_dao.SleepDiaryDAO;
import cn.edu.cqu.project_test_model.PatientInformation;
import cn.edu.cqu.project_test_model.PatientTest;
import cn.edu.cqu.project_test_model.SleepDiary;

import com.zghaikun.sleep_client.R;

public class UpdateInfomation extends Activity{
	private String wsdlURL,webMethod,namespace,soapAction;
	private SoapObject soapObject;
	private SoapSerializationEnvelope  envelope;
	private HttpTransportSE se ;
	private String patient_phone;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updata_info);
//		ProgressDialog progressDialog = new ProgressDialog(UpdateInfomation.this);
//		progressDialog.setTitle("正在下载数据，请稍后");
//		progressDialog.setMessage("下载中");
//		progressDialog.setCancelable(false);
//		progressDialog.show();
		UpdateInfo();
//		progressDialog.cancel();
		finish();
	}

	public void UpdateInfo(){
		//上传患者一般信息
		MyThread1 mt1 = new MyThread1();
		mt1.start();
	
	}
	class MyThread1 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			
			PatientInformationDAO pi = new PatientInformationDAO(UpdateInfomation.this);// 创建PatientInformationDAO对象
			PatientInformation patient = pi.find((int)pi.getCount());
			//PatientInformation patient = pi.find(1);
			System.out.println("patient---" + patient);
			//获得病人电话号码
			patient_phone = patient.getPatient_phone();
			System.out.println("patient---" + patient);
			System.out.println("phone----" + patient_phone);
			
			//获得病人电话号码
			String date = patient.getCurrentdate();
			System.out.println("date----" + date);
			
			//步骤1： 设置Webservice 的调用参数必须静态的IP地址localhost不认？必须静态IP地址
			
			 //wsdlURL= "http://haikunfuwuqi.nat123.net/CloudServer/services/UserService";
			wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "add";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//步骤2:创建一个对象SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//步骤3：传递参数
			soapObject.addProperty("patientInfo", patient);
			
			//步骤4：创建一个SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//自定义的对象必须告诉envelop
			//核心重点：指定服务器端的反序列化类对象：
			//u.getClass是获得对象的反射。
			envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "PatientInfo", patient.getClass());
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
					//测试：显示结果
					Toast.makeText(UpdateInfomation.this, "数据上传成功", Toast.LENGTH_LONG).show();
					System.out.println("结果----" + resout.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SleepDiaryRun();
			PatientTestRun();
			Looper.loop();
		}
	}


		private void PatientTestRun() {
			PatientTest pateint = new PatientTest();
			PatientTestDAO pt = new PatientTestDAO(UpdateInfomation.this);
			//返回的数据没有电话号码
			pateint = pt.find((int)pt.getCount());
			//设置电话号码
			pateint.setPatient_phone(patient_phone);
			//步骤1： 设置Webservice 的调用参数必须静态的IP地址localhost不认？必须静态IP地址
			 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "addTest_Table";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//步骤2:创建一个对象SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//步骤3：传递参数
			soapObject.addProperty("patientTest", pateint);
			
			//步骤4：创建一个SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//自定义的对象必须告诉envelop
			//核心重点：指定服务器端的反序列化类对象：
			//u.getClass是获得对象的反射。
			envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "PatientTest", pateint.getClass());
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
					//测试：显示结果
					Toast.makeText(UpdateInfomation.this, "数据上传成功", Toast.LENGTH_LONG).show();
					System.out.println("结果----" + resout.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			private void SleepDiaryRun() {
			SleepDiary sd = new SleepDiary();
			SleepDiaryDAO sddao = new SleepDiaryDAO(UpdateInfomation.this);
			sd = sddao.find((int)sddao.getCount());
			//设置一个带电话号码的sd
			sd.setPatient_phone(patient_phone);
			
			//步骤1： 设置Webservice 的调用参数必须静态的IP地址localhost不认？必须静态IP地址
			 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "addSleepDiary_Day";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//步骤2:创建一个对象SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//步骤3：传递参数
			soapObject.addProperty("sleepDiary", sd);
			
			//步骤4：创建一个SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//自定义的对象必须告诉envelop
			//核心重点：指定服务器端的反序列化类对象：
			//u.getClass是获得对象的反射。
			envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "SleepDiary", sd.getClass());
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
					//测试：显示结果
					Toast.makeText(UpdateInfomation.this, "数据上传成功", Toast.LENGTH_LONG).show();
					System.out.println("结果----" + resout.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

