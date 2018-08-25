package cn.edu.cqu.movie;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zghaikun.sleep_client.R;

public class MovieListActivity extends Activity{

    private static final String LOG = "main";
	private ListView lv; 
	private Button updatevedioButton;
    private ArrayList<HashMap<String, String>> name;
    private ArrayList<String> moviepathlist;
    String path = Environment.getExternalStorageDirectory().getPath() + "/Movies";
    String file;
    String moviepath;
    SimpleAdapter adapter;
    ProgressDialog progressDialog ;
//    ScaningHandler sHandler;
//    ScaningThread st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);  
        
        lv = (ListView) findViewById(R.id.lv);  
        name = new ArrayList<HashMap<String, String>>(); 
        moviepathlist=new ArrayList<String>();
        updatevedioButton = (Button)findViewById(R.id.updatevedioButton);
        updatevedioButton.setOnClickListener(new UpdatevedioButtonListener());
        
        
        //�����Ƶ�б�
       vedio();
        //progressDialog = ProgressDialog.show(MovieListActivity.this, "���Ե�", "����ɨ��...", true);
        // ��ʼ��̬�����߳�
        //mThreadLoadApps.start();
    }
    
    class UpdatevedioButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			updataView();
		}
    	
    }
    //�����Ƶ�б�
    private void vedio(){ 
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
           // File path = Environment.getExternalStorageDirectory();// ���SD��·��      
            System.out.println("path-------��" + path);
            //File[] files = path.listFiles();// ��ȡ   
            getFileName(path);  //��Ƶ�б�
           // sHandler = new ScaningHandler();
            //st = new ScaningThread();
            //st.start();
        }  

        SimpleAdapter adapter = new SimpleAdapter(this, name, R.layout.sd_list, new String[] { "��Ƶ����" }, new int[] { R.id.mp4 });  
        lv.setAdapter(adapter);
        for (int i = 0; i < name.size(); i++) {  
            Log.i(LOG, "list.  name:  " + name.get(i));  
        }
    
       lv.setOnItemClickListener(new ListView.OnItemClickListener(){
           @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {                
                //String str = lv.getItemAtPosition(position).toString();        
                //file = str.substring("{��Ƶ����=".length(), str.lastIndexOf("}"));
                //String moviename = moviepath ;
                //System.out.println("��Ƶ�ļ���ַ-----��" + moviepath);
        	   String moviename = moviepathlist.get(position);
                Intent intent = new Intent(MovieListActivity.this,MovieActivity.class);
                //���ļ���ַ������һ��activity
                intent.putExtra("moviename", moviename);
                startActivity(intent);
                //�رյ�ǰActivity�����ŵ�ջ����
                //finish();
            }           
        }); 
    }
    //��������ǰĿ¼�µ��ļ� 
    private void getFileName(String url) {   
    	File files = new File(url);
    	File[] file = files.listFiles();
    	for(File f : file){
    		if(f.isDirectory()){
    			getFileName(f.getAbsolutePath());
    		}
    		else{
    			String fileName = f.getName();  
                if (fileName.endsWith(".3gp")||fileName.endsWith(".mp4")) {   
                    HashMap<String, String> map = new HashMap<String, String>();  
                    //�����Ƶ��,ȥ����չ��
                    String s = fileName.substring(0,fileName.lastIndexOf(".")).toString();  
                    Log.i(LOG, "�ļ���mp4��3gp����   " + s); 
                    //��ȡ��Ƶ�ļ���ַ
                    moviepath = f.getPath();
                    System.out.println("filename-----" + fileName);
                    map.put("��Ƶ����", fileName);
                    moviepathlist.add(moviepath);
                    name.add(map);  
                }  
    		}
    	}
    	
    }
    	//���ж�Ŀ¼�Ƿ�Ϊ�գ�����ᱨ��ָ��
    	/*if (files != null) {  
            for (File file : files) {  
                String fileName = file.getName();  
                    if (fileName.endsWith(".3gp")||fileName.endsWith(".mp4")) {   
                        HashMap<String, String> map = new HashMap<String, String>();  
                        String s = fileName.substring(0,fileName.lastIndexOf(".")).toString();  
                        Log.i(LOG, "�ļ���mp4��3gp����   " + s);                         
                        map.put("��Ƶ����", fileName);
                        name.add(map);  
                    }  
                }  
            }  */
  
   //������Ƶ 
    private void updataView(){
    	name.clear();
    	//File path = Environment.getExternalStorageDirectory();// ���SD��·��            
       // File[] files = path.listFiles();// ��ȡ   
        getFileName(path);  //��Ƶ�б� 
        SimpleAdapter adapter = new SimpleAdapter(this, name, R.layout.sd_list, new String[] { "��Ƶ����" }, new int[] { R.id.mp4 });  
        //lv.removeAllViews();
        lv.setAdapter(adapter);
    }
//    class ScaningHandler extends Handler{
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch(msg.what){
//            case 0:
//            	//SimpleAdapter adapter = new SimpleAdapter(this, name, R.layout.sd_list, new String[] { "��Ƶ����" }, new int[] { R.id.mp4 });
//                adapter.notifyDataSetChanged(); //������Ϣ֪ͨListView����
//                lv.setAdapter(adapter); // ��������ListView������������
//                break;
//            default:
//                //do something
//                break;
//            }
//        }
//    };
//    class ScaningThread extends Thread{ 
//        @Override 
//        public void run() {   
//        	Looper.prepare();
//        	progressDialog = ProgressDialog.show(MovieListActivity.this, "���Ե�", "����ɨ��...", true);
//        	getFileName(path);   // ���ݣ���ʱ�Ĳ�����
//            sHandler.sendEmptyMessage(0); // ������ɺ��ʹ�����Ϣ
//            progressDialog.dismiss();   // �رս������Ի���
//            Looper.loop();
//        }
// };
}