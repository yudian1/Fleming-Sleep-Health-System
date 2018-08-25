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
//		progressDialog.setTitle("�����������ݣ����Ժ�");
//		progressDialog.setMessage("������");
//		progressDialog.setCancelable(false);
//		progressDialog.show();
		UpdateInfo();
//		progressDialog.cancel();
		finish();
	}

	public void UpdateInfo(){
		//�ϴ�����һ����Ϣ
		MyThread1 mt1 = new MyThread1();
		mt1.start();
	
	}
	class MyThread1 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Looper.prepare();
			
			PatientInformationDAO pi = new PatientInformationDAO(UpdateInfomation.this);// ����PatientInformationDAO����
			PatientInformation patient = pi.find((int)pi.getCount());
			//PatientInformation patient = pi.find(1);
			System.out.println("patient---" + patient);
			//��ò��˵绰����
			patient_phone = patient.getPatient_phone();
			System.out.println("patient---" + patient);
			System.out.println("phone----" + patient_phone);
			
			//��ò��˵绰����
			String date = patient.getCurrentdate();
			System.out.println("date----" + date);
			
			//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
			
			 //wsdlURL= "http://haikunfuwuqi.nat123.net/CloudServer/services/UserService";
			wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "add";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//����2:����һ������SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//����3�����ݲ���
			soapObject.addProperty("patientInfo", patient);
			
			//����4������һ��SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//�Զ���Ķ���������envelop
			//�����ص㣺ָ���������˵ķ����л������
			//u.getClass�ǻ�ö���ķ��䡣
			envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "PatientInfo", patient.getClass());
			//����5:�ŷ�����һ�´��ض���
			envelope.bodyOut = soapObject;
			//����6�������ʵ�Ա
			 se = new HttpTransportSE(wsdlURL);
			//����7��������
			try {
				se.call(soapAction, envelope);
				//����8����ȡ�ӻ��������صĽ��
				if(envelope.getResponse() != null)
				{
					Object resout = envelope.getResponse();
					//���ԣ���ʾ���
					Toast.makeText(UpdateInfomation.this, "�����ϴ��ɹ�", Toast.LENGTH_LONG).show();
					System.out.println("���----" + resout.toString());
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
			//���ص�����û�е绰����
			pateint = pt.find((int)pt.getCount());
			//���õ绰����
			pateint.setPatient_phone(patient_phone);
			//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
			 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "addTest_Table";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//����2:����һ������SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//����3�����ݲ���
			soapObject.addProperty("patientTest", pateint);
			
			//����4������һ��SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//�Զ���Ķ���������envelop
			//�����ص㣺ָ���������˵ķ����л������
			//u.getClass�ǻ�ö���ķ��䡣
			envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "PatientTest", pateint.getClass());
			//����5:�ŷ�����һ�´��ض���
			envelope.bodyOut = soapObject;
			//����6�������ʵ�Ա
			 se = new HttpTransportSE(wsdlURL);
			//����7��������
			try {
				se.call(soapAction, envelope);
				//����8����ȡ�ӻ��������صĽ��
				if(envelope.getResponse() != null)
				{
					Object resout = envelope.getResponse();
					//���ԣ���ʾ���
					Toast.makeText(UpdateInfomation.this, "�����ϴ��ɹ�", Toast.LENGTH_LONG).show();
					System.out.println("���----" + resout.toString());
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
			//����һ�����绰�����sd
			sd.setPatient_phone(patient_phone);
			
			//����1�� ����Webservice �ĵ��ò������뾲̬��IP��ַlocalhost���ϣ����뾲̬IP��ַ
			 wsdlURL= "http://m167x89974.iask.in:52327/CloudServer/services/UserService";
			 webMethod = "addSleepDiary_Day";
			 namespace = "http://service.cloud.chinasoft.com";
			 soapAction = namespace + webMethod;
			
			//����2:����һ������SoapObject
		    soapObject = new SoapObject(namespace, webMethod);
			//����3�����ݲ���
			soapObject.addProperty("sleepDiary", sd);
			
			//����4������һ��SoapSer
			SoapSerializationEnvelope  envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			//�Զ���Ķ���������envelop
			//�����ص㣺ָ���������˵ķ����л������
			//u.getClass�ǻ�ö���ķ��䡣
			envelope.addMapping("http://po.cloud.chinasoft.com/xsd", "SleepDiary", sd.getClass());
			//����5:�ŷ�����һ�´��ض���
			envelope.bodyOut = soapObject;
			//����6�������ʵ�Ա
			 se = new HttpTransportSE(wsdlURL);
			//����7��������
			try {
				se.call(soapAction, envelope);
				//����8����ȡ�ӻ��������صĽ��
				if(envelope.getResponse() != null)
				{
					Object resout = envelope.getResponse();
					//���ԣ���ʾ���
					Toast.makeText(UpdateInfomation.this, "�����ϴ��ɹ�", Toast.LENGTH_LONG).show();
					System.out.println("���----" + resout.toString());
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

