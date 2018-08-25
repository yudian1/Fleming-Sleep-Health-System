package cn.edu.cqu.project_test_activity;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.zghaikun.sleep_client.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MP3ListActivity extends Activity implements OnClickListener{

    private static final String LOG = "MP3ListActivity";
	private ListView lv; 
	private SeekBar seekBar;
	private Button modelButton;
	private Button backButton;
	private Button pauseButton;
	private Button nextButton;
	private TextView nameText;
    private ArrayList<HashMap<String, String>> musicList;
    private ArrayList<String> musicpathlist;
    private String path = Environment.getExternalStorageDirectory().getPath();
    private String musicpath;
    
    private String musicname;
    private MediaPlayer mediaPlayer;
    public int songNum; // ��ǰ���ŵĸ�����List�е��±� 
    private boolean isStartTrackingTouch; 
    private Handler handler = new Handler();  
    private int Sequence = 1;//˳�򲥷�
    private int Shuffle = 2;//�������
    
    private Button fristButton;
    private Button secondButton;
    private String music = "/Music";
    private String mp3 = "/mp3";
    int i = 0;
    //Ĭ������Ϊ�������
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp3_list);  
        
        lv = (ListView) findViewById(R.id.lv);  
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        modelButton = (Button) findViewById(R.id.model);
        backButton = (Button) findViewById(R.id.back);
        pauseButton = (Button) findViewById(R.id.pause);
        nextButton = (Button) findViewById(R.id.next);
        nameText = (TextView) findViewById(R.id.name);
        fristButton = (Button) findViewById(R.id.list1);
        secondButton = (Button) findViewById(R.id.list2);
        
        
        musicList = new ArrayList<HashMap<String, String>>(); 
        musicpathlist=new ArrayList<String>();
        mediaPlayer = new MediaPlayer();
        //�����Ƶ�б�,Ĭ����ʾ��������
        mp3List(music);
        fristButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//����б�ͱ���ĵ�ַ��Ϣ
				musicpathlist.clear();
				musicList.clear();
				//�����Ƶ�б�
		        mp3List(music);
			}
		});
        secondButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//����б�ͱ���ĵ�ַ��Ϣ
				musicpathlist.clear();
				musicList.clear();
				//ڤ���б�
				mp3List(mp3);
			}
		});
        pauseButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        modelButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        //������������   
        seekBar.setOnSeekBarChangeListener(new MySeekBarListener()); 
        nextButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if(arg1.getAction() == MotionEvent.ACTION_DOWN){  
                    arg0.setBackgroundResource(R.drawable.xiayishou_anxia);  
                }  
                else if(arg1.getAction() == MotionEvent.ACTION_UP){  
                    arg0.setBackgroundResource(R.drawable.xiayishou);  
                } 
				return false;
			}
		});
        backButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){  
                    view.setBackgroundResource(R.drawable.shangyishou_anxia);  
                }  
                else if(event.getAction() == MotionEvent.ACTION_UP){  
                    view.setBackgroundResource(R.drawable.shangyishou);  
                } 
				return false;
			}
		});
//        pauseButton.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				// TODO Auto-generated method stub
//				int i = 0;
//				i++;
//				if(i % 2 == 0){
//					
//				}
//				return false;
//			}
//		});
    }   
    //�����Ƶ�б�
    private void mp3List(String music){ 
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {  
           // File path = Environment.getExternalStorageDirectory();// ���SD��·��      
            System.out.println("path-------��" + path);
            //File[] files = path.listFiles();// ��ȡ   
            String musicPath = path + music; 
            getFileName(musicPath);  //��Ƶ�б�
            Log.i(LOG, musicPath);
        }  

        SimpleAdapter adapter = new SimpleAdapter(this, musicList, R.layout.sd_list, new String[] { "name" }, new int[] { R.id.mp4 });  
        lv.setAdapter(adapter);
        for (int i = 0; i < musicList.size(); i++) {  
            Log.i(LOG, "list.  name:  " + musicList.get(i));  
        }
    
       lv.setOnItemClickListener(new ListView.OnItemClickListener(){
           @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {    
//        	   //˳�򲥷�
//        	   if(flag == 0){
//        		   songNum = position;   
//        	   }
//        	   //�������
//        	   else if(flag == 1){
//        		   songNum = (int)(Math.random()*musicList.size());;
//			}
        	   songNum = position;
        	   initMediaPlayer(songNum);
            }           
        }); 
    }
    //��������ǰĿ¼�µ��ļ� 
    private void getFileName(String url) {   
    	File files = new File(url);
    	File[] file = files.listFiles();
    	//���ж�Ŀ¼�Ƿ�Ϊ�գ�����ᱨ��ָ��
    	if (files != null) {  
            for (File f : file) {  
                String fileName = f.getName();  
                    if (fileName.endsWith(".mp3")||fileName.endsWith(".wav")) {   
                        HashMap<String, String> map = new HashMap<String, String>();  
                        String s = fileName.substring(0,fileName.lastIndexOf(".")).toString();  
                        //��ȡ�ļ��ĵ�ַ
                        musicpath = f.getPath();
                        Log.i(LOG, "�ļ���mp3��wav����   " + s);   
                        map.put("name", fileName);
                       // map.put("mp3", f.getName());  
                        musicpathlist.add(musicpath);
                        musicList.add(map);  
                    }  
                }  
            }  
    }
    //���������
    private final class MySeekBarListener implements OnSeekBarChangeListener {  
        //�ƶ�����   
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  
        }  
  
        //��ʼ����   
        public void onStartTrackingTouch(SeekBar seekBar) {  
            isStartTrackingTouch = true;  
        }  
  
        //��������   
        public void onStopTrackingTouch(SeekBar seekBar) {  
            mediaPlayer.seekTo(seekBar.getProgress());  
            isStartTrackingTouch = false;  
        }  
    } 
    //�����˳�򲥷�
    private void initMediaPlayer(int songNum){
    	musicname = musicpathlist.get(songNum);	
    	Log.i(LOG, musicname);
    	if (musicname != null) {
        	try {
        		mediaPlayer.reset(); //���ö�ý��  
    		//ָ����Ƶ�ļ���ַ
			mediaPlayer.setDataSource(musicname);
			//����һ����ַ
			String path = musicpathlist.get(songNum);
			String Text[] = path.split("/");
			Log.i(LOG, Text[5]);
			//���õ�ǰ�����ļ�
			nameText.setText(Text[Text.length - 1]);
			Log.i(LOG, "����");
			//׼������
			mediaPlayer.prepare();
			start();
//			if (!mediaPlayer.isPlaying()) {
//				mediaPlayer.start();
//				System.out.println("��ʼ����");
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.pause:
			pauseButton.setBackgroundResource(R.drawable.jixu);
			Toast.makeText(getApplicationContext(), "��ͣ", Toast.LENGTH_SHORT).show();
			pause();
			break;
		case R.id.next:
			next();
			break;
		case R.id.back:
			back();
			break;
		case R.id.model:
			i++;
			if(i % 2 == 1){
				Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT).show();
				//����һ�������
//				songNum = (int)(Math.random()*musicList.size());
//				System.out.println("song---" + songNum);
//				initMediaPlayer(songNum); 
				//�������falg
				flag = 1;
				modelButton.setBackgroundResource(R.drawable.suiji);
			}
			else{
				Toast.makeText(getApplicationContext(), "ѭ������", Toast.LENGTH_SHORT).show();
				//ѭ������
				flag = 0;
				modelButton.setBackgroundResource(R.drawable.xunhuan);
			}
			break;
		default:
			break;
		}
	}
	public void start() {   
	    try {   
	    	mediaPlayer.start();//��ʼ����   
	    	//���ý���������   
            seekBar.setMax(mediaPlayer.getDuration()); 
	    	//����һ��Runnable, handler�յ�֮��ͻ�ִ��run()����   
            handler.post(new Runnable() {  
                public void run() {  
                    // ���½�����״̬   
                    if (!isStartTrackingTouch) 
                    	//��ȡ��ǰ�������ֵ�λ��
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());  
                    // 1��֮���ٴη���   
                    handler.postDelayed(this, 1000);  
                }  
            });
	        //setOnCompletionListener ����ǰ��ý����󲥷����ʱ�������¼�   
	    	mediaPlayer.setOnCompletionListener(new OnCompletionListener() {   
	            public void onCompletion(MediaPlayer arg0) {   
	                next();//�����ǰ�����������,�Զ�������һ��.   
	            }   
	        });   
	    } catch (Exception e) {   
	        Log.v("MusicService", e.getMessage());   
	    }   
	}   
	  
	public void next() { 
		Toast.makeText(getApplicationContext(), "��һ��", Toast.LENGTH_SHORT).show();
		if(flag == 0){
			songNum = songNum == musicList.size() - 1 ? 0 : songNum + 1; 
		}
		else if(flag == 1){
			songNum = (int)(Math.random()*musicList.size());
		}
	    initMediaPlayer(songNum);  
	}   
	  
	public void back() {   
		Toast.makeText(getApplicationContext(), "��һ��", Toast.LENGTH_SHORT).show();
		if(flag == 0){
			 //songNum = songNum == 0 ? musicList.size() - 1 : songNum - 1; 
		    songNum = songNum - 1 < 0 ? musicList.size() - 1 : songNum - 1; 
		}
		else if(flag == 1){
			songNum = (int)(Math.random()*musicList.size());
		}
	    initMediaPlayer(songNum);  
	}   
	  
	public void pause() {   
	    if (mediaPlayer.isPlaying())  {
	    	Toast.makeText(getApplicationContext(), "����", Toast.LENGTH_SHORT).show();
	    	pauseButton.setBackgroundResource(R.drawable.zanting);
	    	mediaPlayer.pause();   
	    }
	    else  
	    	mediaPlayer.start();   
	}   
	  
	public void stop() {   
	    if (mediaPlayer.isPlaying()) {   
	    	mediaPlayer.stop();   
	    }   
	} 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			//mediaPlayer.release();
		}
	}
}