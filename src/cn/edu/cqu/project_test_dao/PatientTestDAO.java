package cn.edu.cqu.project_test_dao;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cqu.project_test_model.PatientInformation;
import cn.edu.cqu.project_test_model.PatientTest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class PatientTestDAO {
	private final static String TABLE_NAME02 = "test_table";
	private String PATIENT_ID = "id";
	private String PATIENT_PQSI = "pqsi";
	private String PATIENT_Sleepness = "sleepness";
	private String PATIENT_HAMA = "hama";
	private String PATIENT_HAMD = "hamd";
	private String PATIENT_ISI = "isi";
	private PatientDB helper;
	private SQLiteDatabase db;
	public PatientTestDAO(Context context){
		helper = new PatientDB(context);// ��ʼ��DBOpenHelper����
	}
	//���Ӳ��˲�������
	public void insert(PatientTest patienttest){
		//����һ����д�����ݿ�
		db = helper.getWritableDatabase();
		//ContentValues�Լ�ֵ�Ե���ʽ������� 
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_PQSI,patienttest.getPqsi());
		cv.put(PATIENT_Sleepness, patienttest.getSleepness());
		cv.put(PATIENT_HAMA, patienttest.getHama());
		cv.put(PATIENT_HAMD, patienttest.getHamd());
		System.out.println("����ISI");
		cv.put(PATIENT_ISI, patienttest.getIsi());
		System.out.println("����ISI�ɹ�����");
		db.insert(TABLE_NAME02, null, cv);
		//return row;	
		
	}
	//�޸Ĳ�����Ϣ
	public void updatePQSI(int id ,PatientTest patienttest){
		//���һ����д�����ݿ�
		SQLiteDatabase db = helper.getWritableDatabase();
		//�޸�����
		String where = PATIENT_ID + " = ?";
		//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
		String[] whereValue = { Integer.toString(id) };
				 
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_PQSI,patienttest.getPqsi());
		System.out.println("ʧ���ˣ���������");
		db.update(TABLE_NAME02, cv, where, whereValue);
		}
	//�޸Ĳ�����Ϣ
		public void updateHAMD(int id ,PatientTest patienttest){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(PATIENT_HAMD, patienttest.getHamd());
			db.update(TABLE_NAME02, cv, where, whereValue);
			}
		//�޸Ĳ�����Ϣ
		public void updateSleepness(int id ,PatientTest patienttest){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(PATIENT_Sleepness, patienttest.getSleepness());
			db.update(TABLE_NAME02, cv, where, whereValue);
			}
		//�޸Ĳ�����Ϣ
		public void updateHAMA(int id ,PatientTest patienttest){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(PATIENT_HAMA, patienttest.getHama());
			db.update(TABLE_NAME02, cv, where, whereValue);
			}
		//�޸Ĳ�����Ϣ
		public void updateISI(int id ,PatientTest patienttest){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
							 
			ContentValues cv = new ContentValues();
			cv.put(PATIENT_ISI, patienttest.getIsi());
			db.update(TABLE_NAME02, cv, where, whereValue);
			}
	//���Ҳ�����Ϣ
	public PatientTest find(int id){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
                        "select id,pqsi,sleepness,hama,hamd,isi from test_table where id = ?",
                        new String[] {String.valueOf(id)});//���ݱ�Ų��ҵ��Ĳ�����Ϣ�����洢��Cursor��
		//�������ҵ��Ĳ�����Ϣ
		if(cursor.moveToNext()){
			//���������ҵ��Ĳ�����Ϣ�洢��PatientInformation����
			return new PatientTest(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("pqsi")),
                    cursor.getString(cursor.getColumnIndex("sleepness")),
                    cursor.getString(cursor.getColumnIndex("hama")),
                    cursor.getString(cursor.getColumnIndex("hamd")),
                    cursor.getString(cursor.getColumnIndex("isi"))
                    );
		}
		return null;	
	}
	//ɾ������
	public void delete(Integer... ids){
	/*db = helper.getWritableDatabase();
	String where = PATIENT_ID + " = ?";
	String[] whereValue ={ Integer.toString(id) };
	db.delete(TABLE_NAME, where, whereValue);*/
		if(ids.length > 0){ //�ж��Ƿ����Ҫɾ����id
			StringBuffer sb = new StringBuffer();
			for(int i = 0;i < ids.length;i++){
				sb.append('?').append(','); //��ɾ��������ӵ�StringBuffer��
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			db.execSQL("delete from patient where _id in (" + sb + ")",
                    (Object[]) ids);
		}
	}
	/**
	 * �÷�����Ҫ�ǴӲ�����Ϣ�е�ָ����������ò�����Ϣ
	 * **/
	public List<PatientInformation> getScrollData(int start, int count) {
		 
        List<PatientInformation> patient = new ArrayList<PatientInformation>();// �������϶���
        db = helper.getWritableDatabase();
        //��ȡ���в�����Ϣ
        Cursor cursor = db.rawQuery("select * from patient_table limit ?,?",
                new String[] {String.valueOf(start), String.valueOf(count)});
        while (cursor.moveToNext())//�������в�����Ϣ
        {
            //�������������в�����Ϣ��ӵ�������
        	patient.add(new PatientInformation(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("current_date")),
                    cursor.getString(cursor.getColumnIndex("patient_name")),
                    cursor.getString(cursor.getColumnIndex("patient_gender")),
                    cursor.getString(cursor.getColumnIndex("patient_age")),
                    cursor.getString(cursor.getColumnIndex("patient_postion")),
                    cursor.getString(cursor.getColumnIndex("patient_scde")),
                    cursor.getString(cursor.getColumnIndex("patient_insomnia")),
                    cursor.getString(cursor.getColumnIndex("patient_mhistory")),
                    cursor.getString(cursor.getColumnIndex("patient_phone"))
                    ));
        	}
        return patient;//���ؼ���
     
	}
	//�õ����ݿ��е�����
		public Cursor export(){
			db = helper.getWritableDatabase();
			Cursor cursor = db.rawQuery( "select * from test_table",null);
			return cursor;
		}
	//��ȡ�ܼ�¼��
	public long getCount() {
		 
        db = helper.getWritableDatabase();
        Cursor cursor = db
                .rawQuery("select count(id) from patient_table", null);//��ȡ������Ϣ��¼��
        if (cursor.moveToNext())//�ж�Cursor���Ƿ�������
        {
 
            return cursor.getLong(0);//�����ܼ�¼��
         
        }
        return 0;//û�������򷵻�0
     
	}
	//��ȡ���˵������
	public int getMaxId() {
		 
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from patient_table", null);//��ȡ������Ϣ�е������
        //����Cursor�е����һ������
        while (cursor.moveToLast()) {
            return cursor.getInt(0);//��ȡ���ʵ������ݣ��������
         
}
        return 0;//���û�������򷵻�0
     
}

}
