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
        
        //实例化MediaController
        mController = new MediaController(this);
        //把要播放的文件地址通过intent传过来
        Intent intent = getIntent();
        tpath = intent.getStringExtra("moviename");
        File file = new File(tpath);
        if(file.exists()){
        	//设置播放视频源的位置
        	video.setVideoPath(file.getAbsolutePath());
        	//为Video指定Mediacontroller
        	video.setMediaController(mController);
        	//为Mediacontroller指定video
        	mController.setMediaPlayer(video);
        	//自动播放，不需要按下开始键
        	video.start();
        	////视频播放完之后返回播放列表
        	video.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer arg0) {
					Intent intent2list = new Intent();
		    		intent2list.setClass(MovieActivity.this, MovieListActivity.class);
		    		startActivity(intent2list);
		    		//关闭当前Activity，不放到栈里面
		    		finish();
				}
			});
  
        	//增加监听上一个和下一个的切换事件，默认这两个按钮是不显示
        	mController.setPrevNextListeners(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 Toast.makeText(MovieActivity.this, "下一个",0).show();
				}
			}, new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(MovieActivity.this, "上一个",0).show();
				}
			});
        }
	}
		
}

