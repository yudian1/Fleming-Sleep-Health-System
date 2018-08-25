package cn.edu.cqu.project_test_dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cn.edu.cqu.project_test_model.PatientInformation;

import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

public class ExportToCSV {
	
	private String patient_name;
	public void setName(String name){
		patient_name = name;
		System.out.println("name---" + patient_name);
	}
	//�������ķ���
	public void exportToCSV(Cursor c, String fileName) {

		int rowCount = 0;
		int colCount = 0;
		FileWriter fw;
		BufferedWriter bfw;
		//��ȡsd����Ŀ¼
		File sdCardDir = Environment.getExternalStorageDirectory();
		
		//�����ļ��У�Ҫ���еĻ�������,���ݻ��ߵ����������ض�������
		String filedir = sdCardDir +"/HKMedical_" + patient_name;
		//String filedir = sdCardDir +"/HKMedical";
		File file = new File(filedir);
		if(!file.exists()){
			 try {  
	                //����ָ����·�������ļ���  
	                file.mkdirs(); 
	                System.out.println("����HKMedical�ɹ���");
	            } catch (Exception e) {  
	                // TODO: handle exception  
	            } 
		}else{
			System.out.println("HKMedical�ļ����Ѵ��ڣ�");
		}
		//�����ļ�Ŀ¼
		File saveFile = new File(filedir, fileName);
		try {

			rowCount = c.getCount();
			colCount = c.getColumnCount();
			fw = new FileWriter(saveFile);
			bfw = new BufferedWriter(fw);
			if (rowCount > 0) {
				c.moveToFirst();
				// д���ͷ
				for (int i = 0; i < colCount; i++) {
					if (i != colCount - 1)
					   bfw.write(c.getColumnName(i) + ',');
					else
					   bfw.write(c.getColumnName(i));
				}
				// д�ñ�ͷ����
				bfw.newLine();
				// д������
				for (int i = 0; i < rowCount; i++) {
					c.moveToPosition(i);
					// Toast.makeText(mContext, "���ڵ�����"+(i+1)+"��",
					// Toast.LENGTH_SHORT).show();
					Log.v("��������", "���ڵ�����" + (i + 1) + "��");
					for (int j = 0; j < colCount; j++) {
						if (j != colCount - 1)
						    bfw.write(c.getString(j) + ',');
						else
						   bfw.write(c.getString(j));
					}
					// д��ÿ����¼����
					bfw.newLine();
				}
			}
			// ����������д���ļ�
			bfw.flush();
			// �ͷŻ���
			bfw.close();
			// Toast.makeText(mContext, "������ϣ�", Toast.LENGTH_SHORT).show();
			Log.v("��������", "������ϣ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			c.close();
		}
	}

}
