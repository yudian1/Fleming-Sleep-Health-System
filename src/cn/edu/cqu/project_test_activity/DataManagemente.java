package cn.edu.cqu.project_test_activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.edu.cqu.project_test_dao.PatientInformationDAO;
import cn.edu.cqu.project_test_model.PatientInformation;

import com.zghaikun.sleep_client.R;

public class DataManagemente extends Activity{
	private ListView lvpatient;
	String strType = "";// �����ַ�������¼��������
	List<HashMap<String, Object>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_management);
		lvpatient = (ListView) findViewById(R.id.listView);
		
		System.out.println("test......");
		String[] strPatients = null;// �����ַ������飬�����洢������Ϣ
		//ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
		SimpleAdapter adapter;
		strType = "btnininfo";// ΪstrType������ֵ
		//strType.
		PatientInformationDAO pi = new PatientInformationDAO(DataManagemente.this);// ����PatientInformationDAO����
		// ��ȡ���л�����Ϣ�����洢��List���ͼ�����
		List<PatientInformation> listpatient = pi.getScrollData(0,(int) pi.getCount());
		//System.out.println("����ʲô----" + listpatient);
		strPatients = new String[listpatient.size()];// �����ַ�������ĳ���
		//list<T<String>> listitems = new ArrayList<T<String>>();
		data = new ArrayList<HashMap<String,Object>>();  
		int m = 0;// ����һ����ʼ��ʶ
		for (PatientInformation patientinformation: listpatient) {// ����List���ͼ���
			// �����������Ϣ��ϳ�һ���ַ������洢���ַ����������Ӧλ��
			strPatients[m] = patientinformation.getId() + "| " + patientinformation.getCurrentdate() +", " +
			patientinformation.getPatient_name() + ", " + patientinformation.getPatient_gender() +", " +patientinformation.getPatient_age()
			+", " + patientinformation.getPatient_postion()+", " + patientinformation.getPatient_scde()+
			", " + patientinformation.getPatient_insomnia()+", " + patientinformation.getPatient_mhistory()+", " + patientinformation.getPatient_phone();
			m++;// ��ʶ��1
			//patientid = patientinformation.getId();//��õ��Ŀ϶�������id�ţ�����
			HashMap<String, Object> item = new HashMap<String, Object>(); 
			item.put("id", patientinformation.getId());
			item.put("currentdate", patientinformation.getCurrentdate());
			item.put("name", patientinformation.getPatient_name());
			item.put("gender", patientinformation.getPatient_gender());
			item.put("age", patientinformation.getPatient_age());
			item.put("position", patientinformation.getPatient_postion());
			item.put("scde", patientinformation.getPatient_scde());
			item.put("insomnia", patientinformation.getPatient_insomnia());
			item.put("mhistory", patientinformation.getPatient_mhistory());
			item.put("phone", patientinformation.getPatient_phone());
			data.add(item);
		}
		// ʹ���ַ��������ʼ��ArrayAdapter����
		//arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strPatients);
		//arrayAdapter = new ArrayAdapter<String>(this,R.layout.simple_list,strPatients);
		adapter = new SimpleAdapter(this,data,R.layout.data_management_item,new String[]{"id","currentdate","name","gender","age","position",
				"scde","insomnia","mhistory","phone"},new int[]{R.id.id,R.id.date,R.id.name,R.id.gender,R.id.age,R.id.position,R.id.scde,R.id.insomnia,R.id.mhistory,R.id.phone});
		lvpatient.setAdapter(adapter);// ΪListView�б���������Դ
		
		
		lvpatient.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//���ѡ�е�item����Ϣ
				Map<String,Object> getId = new HashMap<String,Object>();
                getId = data.get(position);
                System.out.println("id------" + getId);
                Object[] str = getId.values().toArray();
                //���ѡ�е�id
                String  patientid = str[1].toString();
                System.out.println("patientid------" + patientid);
                //���ݵ�PatientInfoManageȥ
				Intent intent = new Intent(DataManagemente.this,PatientInfoManage.class);// ����Intent����
				intent.putExtra("patientid", patientid);
				startActivity(intent);// ִ��Intent����
			}
		});
	}

}
