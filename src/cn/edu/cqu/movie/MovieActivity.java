package cn.edu.cqu.movie;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.zghaikun.sleep_client.R;

public class MovieActivity extends Activity{
	
	public static String tpath;
	private VideoView video;
	private MediaController mController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		
		video = (VideoView)findViewById(R.id.video);
        
        //ʵ����MediaController
        mController = new MediaController(this);
        //��Ҫ���ŵ��ļ���ַͨ��intent������
        Intent intent = getIntent();
        tpath = intent.getStringExtra("moviename");
        File file = new File(tpath);
        if(file.exists()){
        	//���ò�����ƵԴ��λ��
        	video.setVideoPath(file.getAbsolutePath());
        	//ΪVideoָ��Mediacontroller
        	video.setMediaController(mController);
        	//ΪMediacontrollerָ��video
        	mController.setMediaPlayer(video);
        	//�Զ����ţ�����Ҫ���¿�ʼ��
        	video.start();
        	////��Ƶ������֮�󷵻ز����б�
        	video.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer arg0) {
					Intent intent2list = new Intent();
		    		intent2list.setClass(MovieActivity.this, MovieListActivity.class);
		    		startActivity(intent2list);
		    		//�رյ�ǰActivity�����ŵ�ջ����
		    		finish();
				}
			});
  
        	//���Ӽ�����һ������һ�����л��¼���Ĭ����������ť�ǲ���ʾ
        	mController.setPrevNextListeners(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Toast.makeText(MovieActivity.this, "��һ��",0).show();
				}
			}, new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(MovieActivity.this, "��һ��",0).show();
				}
			});
        }
	}
		
}

