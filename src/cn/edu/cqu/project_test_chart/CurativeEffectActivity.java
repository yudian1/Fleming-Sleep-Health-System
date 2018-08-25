package cn.edu.cqu.project_test_chart;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.edu.cqu.project_test_dao.SleepDiaryDAO;
import cn.edu.cqu.project_test_model.SleepDiary;

import com.zghaikun.sleep_client.R;

public class CurativeEffectActivity extends Activity {
	
	private int[] atBed = new int[7];
	private int[] sleepTime = new int[7];
	private int[] SE = new int[7];
	private int flag = 0;
	private SleepDiary sleepDiary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curative_effect_layout);
        
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 0);
        
        SleepDiaryDAO sleepDao = new SleepDiaryDAO(this);
    	sleepDiary = sleepDao.find((int)sleepDao.getCount());
//    	System.out.println(sleepDiary.getClass());
    	if (sleepDiary.getDay01() != null && sleepDiary.getDay02() != null && sleepDiary.getDay03() != null && 
    			sleepDiary.getDay04() != null && sleepDiary.getDay05() != null && sleepDiary.getDay06() != null && 
    			sleepDiary.getDay07() != null) {
    		//��ȡ����
            setdata();
        	for (int i = 0; i < SE.length; i++) {
        		SE[i] = 100 * sleepTime[i] / atBed[i];
        	}
            if (flag == 0) {
            	showChar("�ڴ�ʱ��",atBed);
    		}else if (flag == 1) {
    			showChar("˯��ʱ��",sleepTime);
    		}else if (flag == 2) {
    			showChar("˯��Ч��",SE);
    		}
    	}else {
    		Toast.makeText(this, "��û��˯�����ݣ�", Toast.LENGTH_SHORT).show();
    	}
        
    }

	public void showChar(String title,int data[]) {
		//1��������ʾ����Ⱦͼ
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        //���ñ���ͱ��������С
        renderer.setChartTitleTextSize(30);
        renderer.setChartTitle("˯���ռ�����");
        //XY���ߵ������С
        renderer.setLabelsTextSize(20);
        renderer.setLegendTextSize(20);
        
        renderer.setYTitle("����");
        renderer.setXTitle("��");
        //��ʾ����
        renderer.setShowGrid(true);
        //���������ڵı���
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.BLACK);
        //�ⲿ�����޸�
        renderer.setMarginsColor(Color.DKGRAY);
        //2��������ʾ
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        
        for (int i = 0; i < data.length; i++) {
   			System.out.println("data---" + data[i]);
   		}
           XYSeries series = new XYSeries(title);
         //�������
   		for (int j = 0; j < data.length; j++) {
   			//���XY��ֵ
   			series.add((j+1), data[j]);
   		}
   		//��Ҫ���Ƶ����ݷ���dataset��
   		dataset.addSeries(series);
        // 3, �Ե�Ļ��ƽ�������
        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        // 3.1������ɫ
        xyRenderer.setColor(Color.RED);
        // 3.2���õ����ʽ
        xyRenderer.setPointStyle(PointStyle.SQUARE);
        // 3.3, ��Ҫ���Ƶĵ���ӵ����������
        renderer.addSeriesRenderer(xyRenderer);
        // 3.4,�ظ� 1~3�Ĳ�����Ƶڶ���ϵ�е�
  
        LinearLayout barchart = (LinearLayout) findViewById(R.id.barchart);
        //����ͼ
        GraphicalView mChartView = ChartFactory.getLineChartView(this, dataset, renderer);
        mChartView.repaint();// ˢ������ͼ
		barchart.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}
    
private void setdata(){
	String day01 = sleepDiary.getDay01();
	String day02 = sleepDiary.getDay02();
	String day03 = sleepDiary.getDay03();
	String day04 = sleepDiary.getDay04();
	String day05 = sleepDiary.getDay05();
	String day06 = sleepDiary.getDay06();
	String day07 = sleepDiary.getDay07();
	System.out.println(day01 + "---" + day02 + "---" + day03 + "---" + day04 + "---" + day05 + "---" + day06 + "---" + day07);
	//���е��ڴ�ʱ��
	atBed[0] = 24*60 - (goBedTime(day01) - getUpTime(day01));
	atBed[1] = 24*60 - (goBedTime(day02) - getUpTime(day02));
	atBed[2] = 24*60 - (goBedTime(day03) - getUpTime(day03));
	atBed[3] = 24*60 - (goBedTime(day04) - getUpTime(day04));
	atBed[4] = 24*60 - (goBedTime(day05) - getUpTime(day05));
	atBed[5] = 24*60 - (goBedTime(day06) - getUpTime(day06));
	atBed[6] = 24*60 - (goBedTime(day07) - getUpTime(day07));
	//���е�˯��ʱ��
	sleepTime[0] = 24*60 + (wakeTime(day01) - SleepTime(day01));
	sleepTime[1] = 24*60 + (wakeTime(day02) - SleepTime(day02));
	sleepTime[2] = 24*60 + (wakeTime(day03) - SleepTime(day03));
	sleepTime[3] = 24*60 + (wakeTime(day04) - SleepTime(day04));
	sleepTime[4] = 24*60 + (wakeTime(day05) - SleepTime(day05));
	sleepTime[5] = 24*60 + (wakeTime(day06) - SleepTime(day06));
	sleepTime[6] = 24*60 + (wakeTime(day07) - SleepTime(day07));
	
	for (int i = 0; i < sleepTime.length; i++) {
		if (sleepTime[i] > 1440) {
			sleepTime[i] = sleepTime[i] - 1440;
		}
	}
//	for (int i = 0; i < SE.length; i++) {
//		SE[i] = sleepTime[i] / atBed[i] * 100;
//	}
	
//	SE[0] = 100 *sleepTime[0] / sleepTime[0];
}
//��ȡ�ϴ�ʱ��
private int goBedTime(String day){
	String gobentime = day.substring(4, 9);
	int hour = Integer.parseInt(gobentime.substring(0, 2));
	int minute = Integer.parseInt(gobentime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
//��ȡ��ʱ��
private int getUpTime(String day){
	String getUptime = day.substring(20, 25);
	int hour = Integer.parseInt(getUptime.substring(0, 2));
	int minute = Integer.parseInt(getUptime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
//��ȡ˯��ʱ��
private int SleepTime(String day){
	String sleeptime = day.substring(9, 14);
	//Stringת����int�� String.valueof();ת����String���͵�
	int hour = Integer.parseInt(sleeptime.substring(0, 2));
	int minute = Integer.parseInt(sleeptime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
//��ȡ����ʱ��
private int wakeTime(String day){
	String waketime = day.substring(15, 20);
	int hour = Integer.parseInt(waketime.substring(0, 2));
	int minute = Integer.parseInt(waketime.substring(3, 5));
	int time = hour * 60 + minute;
	return time;
}
}
