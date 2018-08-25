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
	String strType = "";// 创建字符串，记录管理类型
	List<HashMap<String, Object>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_management);
		lvpatient = (ListView) findViewById(R.id.listView);
		
		System.out.println("test......");
		String[] strPatients = null;// 定义字符串数组，用来存储患者信息
		//ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		SimpleAdapter adapter;
		strType = "btnininfo";// 为strType变量赋值
		//strType.
		PatientInformationDAO pi = new PatientInformationDAO(DataManagemente.this);// 创建PatientInformationDAO对象
		// 获取所有患者信息，并存储到List泛型集合中
		List<PatientInformation> listpatient = pi.getScrollData(0,(int) pi.getCount());
		//System.out.println("这是什么----" + listpatient);
		strPatients = new String[listpatient.size()];// 设置字符串数组的长度
		//list<T<String>> listitems = new ArrayList<T<String>>();
		data = new ArrayList<HashMap<String,Object>>();  
		int m = 0;// 定义一个开始标识
		for (PatientInformation patientinformation: listpatient) {// 遍历List泛型集合
			// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
			strPatients[m] = patientinformation.getId() + "| " + patientinformation.getCurrentdate() +", " +
			patientinformation.getPatient_name() + ", " + patientinformation.getPatient_gender() +", " +patientinformation.getPatient_age()
			+", " + patientinformation.getPatient_postion()+", " + patientinformation.getPatient_scde()+
			", " + patientinformation.getPatient_insomnia()+", " + patientinformation.getPatient_mhistory()+", " + patientinformation.getPatient_phone();
			m++;// 标识加1
			//patientid = patientinformation.getId();//这得到的肯定是最大的id号！！！
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
		// 使用字符串数组初始化ArrayAdapter对象
		//arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, strPatients);
		//arrayAdapter = new ArrayAdapter<String>(this,R.layout.simple_list,strPatients);
		adapter = new SimpleAdapter(this,data,R.layout.data_management_item,new String[]{"id","currentdate","name","gender","age","position",
				"scde","insomnia","mhistory","phone"},new int[]{R.id.id,R.id.date,R.id.name,R.id.gender,R.id.age,R.id.position,R.id.scde,R.id.insomnia,R.id.mhistory,R.id.phone});
		lvpatient.setAdapter(adapter);// 为ListView列表设置数据源
		
		
		lvpatient.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				//获得选中的item的信息
				Map<String,Object> getId = new HashMap<String,Object>();
                getId = data.get(position);
                System.out.println("id------" + getId);
                Object[] str = getId.values().toArray();
                //获得选中的id
                String  patientid = str[1].toString();
                System.out.println("patientid------" + patientid);
                //传递到PatientInfoManage去
				Intent intent = new Intent(DataManagemente.this,PatientInfoManage.class);// 创建Intent对象
				intent.putExtra("patientid", patientid);
				startActivity(intent);// 执行Intent操作
			}
		});
	}

}
