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
        
        
        //获得视频列表
       vedio();
        //progressDialog = ProgressDialog.show(MovieListActivity.this, "请稍等", "正在扫描...", true);
        // 开始动态加载线程
        //mThreadLoadApps.start();
    }
    
    class UpdatevedioButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			updataView();
		}
    	
    }
    //获得视频列表
    private void vedio(){ 
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
           // File path = Environment.getExternalStorageDirectory();// 获得SD卡路径      
            System.out.println("path-------》" + path);
            //File[] files = path.listFiles();// 读取   
            getFileName(path);  //视频列表
           // sHandler = new ScaningHandler();
            //st = new ScaningThread();
            //st.start();
        }  

        SimpleAdapter adapter = new SimpleAdapter(this, name, R.layout.sd_list, new String[] { "视频名称" }, new int[] { R.id.mp4 });  
        lv.setAdapter(adapter);
        for (int i = 0; i < name.size(); i++) {  
            Log.i(LOG, "list.  name:  " + name.get(i));  
        }
    
       lv.setOnItemClickListener(new ListView.OnItemClickListener(){
           @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {                
                //String str = lv.getItemAtPosition(position).toString();        
                //file = str.substring("{视频名称=".length(), str.lastIndexOf("}"));
                //String moviename = moviepath ;
                //System.out.println("视频文件地址-----》" + moviepath);
        	   String moviename = moviepathlist.get(position);
                Intent intent = new Intent(MovieListActivity.this,MovieActivity.class);
                //把文件地址传给下一个activity
                intent.putExtra("moviename", moviename);
                startActivity(intent);
                //关闭当前Activity，不放到栈里面
                //finish();
            }           
        }); 
    }
    //仅搜索当前目录下的文件 
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
                    //获得视频名,去掉扩展名
                    String s = fileName.substring(0,fileName.lastIndexOf(".")).toString();  
                    Log.i(LOG, "文件名mp4或3gp：：   " + s); 
                    //获取视频文件地址
                    moviepath = f.getPath();
                    System.out.println("filename-----" + fileName);
                    map.put("视频名称", fileName);
                    moviepathlist.add(moviepath);
                    name.add(map);  
                }  
    		}
    	}
    	
    }
    	//先判断目录是否为空，否则会报空指针
    	/*if (files != null) {  
            for (File file : files) {  
                String fileName = file.getName();  
                    if (fileName.endsWith(".3gp")||fileName.endsWith(".mp4")) {   
                        HashMap<String, String> map = new HashMap<String, String>();  
                        String s = fileName.substring(0,fileName.lastIndexOf(".")).toString();  
                        Log.i(LOG, "文件名mp4或3gp：：   " + s);                         
                        map.put("视频名称", fileName);
                        name.add(map);  
                    }  
                }  
            }  */
  
   //更新视频 
    private void updataView(){
    	name.clear();
    	//File path = Environment.getExternalStorageDirectory();// 获得SD卡路径            
       // File[] files = path.listFiles();// 读取   
        getFileName(path);  //视频列表 
        SimpleAdapter adapter = new SimpleAdapter(this, name, R.layout.sd_list, new String[] { "视频名称" }, new int[] { R.id.mp4 });  
        //lv.removeAllViews();
        lv.setAdapter(adapter);
    }
//    class ScaningHandler extends Handler{
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch(msg.what){
//            case 0:
//            	//SimpleAdapter adapter = new SimpleAdapter(this, name, R.layout.sd_list, new String[] { "视频名称" }, new int[] { R.id.mp4 });
//                adapter.notifyDataSetChanged(); //发送消息通知ListView更新
//                lv.setAdapter(adapter); // 重新设置ListView的数据适配器
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
//        	progressDialog = ProgressDialog.show(MovieListActivity.this, "请稍等", "正在扫描...", true);
//        	getFileName(path);   // 数据（耗时的操作）
//            sHandler.sendEmptyMessage(0); // 下载完成后发送处理消息
//            progressDialog.dismiss();   // 关闭进度条对话框
//            Looper.loop();
//        }
// };
}