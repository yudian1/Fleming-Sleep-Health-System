package cn.edu.cqu.project_test_dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.cqu.project_test_model.PatientInformation;
import cn.edu.cqu.project_test_model.PatientTest;
import cn.edu.cqu.project_test_model.SleepDiary;

public class SleepDiaryDAO {

	private final static String TABLE_NAME03 = "sleepdiary_day";
	private String PATIENT_ID = "id";
	//˯���ռǲ���
	public final static String DAY01 = "day01";
	public final static String DAY02 = "day02";
	public final static String DAY03 = "day03";
	public final static String DAY04 = "day04";
	public final static String DAY05 = "day05";
	public final static String DAY06 = "day06";
	public final static String DAY07 = "day07";
	private PatientDB helper;
	private SQLiteDatabase db;
	public SleepDiaryDAO(Context context){
		helper = new PatientDB(context);// ��ʼ��DBOpenHelper����
	}
	//���Ӳ��˲�������
	public void insert(SleepDiary sleepdiary){
		//����һ����д�����ݿ�
		db = helper.getWritableDatabase();
		//ContentValues�Լ�ֵ�Ե���ʽ������� 
		ContentValues cv = new ContentValues();
		cv.put(DAY01, sleepdiary.getDay01());
		cv.put(DAY02, sleepdiary.getDay02());
		cv.put(DAY03, sleepdiary.getDay03());
		cv.put(DAY04, sleepdiary.getDay04());
		cv.put(DAY05, sleepdiary.getDay05());
		cv.put(DAY06, sleepdiary.getDay06());
		cv.put(DAY07, sleepdiary.getDay07());
		db.insert(TABLE_NAME03, null, cv);
		//return row;	
		
	}
	//�޸Ĳ�����Ϣ
	public void updateDAY01(int id ,SleepDiary sleepdiary){
		//���һ����д�����ݿ�
		SQLiteDatabase db = helper.getWritableDatabase();
		//�޸�����
		String where = PATIENT_ID + " = ?";
		//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
		String[] whereValue = { Integer.toString(id) };
				 
		ContentValues cv = new ContentValues();
		cv.put(DAY01,sleepdiary.getDay01());
		System.out.println("ʧ���ˣ���������");
		db.update(TABLE_NAME03, cv, where, whereValue);
		}
	//�޸Ĳ�����Ϣ
		public void updateDAY02(int id ,SleepDiary sleepdiary){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(DAY02, sleepdiary.getDay02());
			db.update(TABLE_NAME03, cv, where, whereValue);
			}
		//�޸Ĳ�����Ϣ
		public void updateDAY03(int id ,SleepDiary sleepdiary){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(DAY03, sleepdiary.getDay03());
			db.update(TABLE_NAME03, cv, where, whereValue);
			}
		//�޸Ĳ�����Ϣ
		public void updateDAY04(int id ,SleepDiary sleepdiary){
			//���һ����д�����ݿ�
			SQLiteDatabase db = helper.getWritableDatabase();
			//�޸�����
			String where = PATIENT_ID + " = ?";
			//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
			String[] whereValue = { Integer.toString(id) };
					 
			ContentValues cv = new ContentValues();
			cv.put(DAY04, sleepdiary.getDay04());
			db.update(TABLE_NAME03, cv, where, whereValue);
			}
		//�޸Ĳ�����Ϣ
				public void updateDAY05(int id ,SleepDiary sleepdiary){
					//���һ����д�����ݿ�
					SQLiteDatabase db = helper.getWritableDatabase();
					//�޸�����
					String where = PATIENT_ID + " = ?";
					//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
					String[] whereValue = { Integer.toString(id) };
							 
					ContentValues cv = new ContentValues();
					cv.put(DAY05, sleepdiary.getDay05());
					db.update(TABLE_NAME03, cv, where, whereValue);
					}
				//�޸Ĳ�����Ϣ
				public void updateDAY06(int id ,SleepDiary sleepdiary){
					//���һ����д�����ݿ�
					SQLiteDatabase db = helper.getWritableDatabase();
					//�޸�����
					String where = PATIENT_ID + " = ?";
					//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
					String[] whereValue = { Integer.toString(id) };
							 
					ContentValues cv = new ContentValues();
					cv.put(DAY06, sleepdiary.getDay06());
					db.update(TABLE_NAME03, cv, where, whereValue);
					}
				//�޸Ĳ�����Ϣ
				public void updateDAY07(int id ,SleepDiary sleepdiary){
					//���һ����д�����ݿ�
					SQLiteDatabase db = helper.getWritableDatabase();
					//�޸�����
					String where = PATIENT_ID + " = ?";
					//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
					String[] whereValue = { Integer.toString(id) };
							 
					ContentValues cv = new ContentValues();
					cv.put(DAY07, sleepdiary.getDay07());
					db.update(TABLE_NAME03, cv, where, whereValue);
					}
	//���Ҳ�����Ϣ
	public SleepDiary find(int id){
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
                        "select id,day01,day02,day03,day04,day05,day06,day07 from sleepdiary_day where id = ?",
                        new String[] {String.valueOf(id)});//���ݱ�Ų��ҵ��Ĳ�����Ϣ�����洢��Cursor��
		//�������ҵ��Ĳ�����Ϣ
		if(cursor.moveToNext()){
			//���������ҵ��Ĳ�����Ϣ�洢��PatientInformation����
			return new SleepDiary(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("day01")),
                    cursor.getString(cursor.getColumnIndex("day02")),
                    cursor.getString(cursor.getColumnIndex("day03")),
                    cursor.getString(cursor.getColumnIndex("day04")),
                    cursor.getString(cursor.getColumnIndex("day05")),
                    cursor.getString(cursor.getColumnIndex("day06")),
                    cursor.getString(cursor.getColumnIndex("day07"))
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
        Cursor cursor = db.rawQuery("select * from sleepdiary_day limit ?,?",
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
		Cursor cursor = db.rawQuery( "select * from sleepdiary_day",null);
		return cursor;
		}
	//��ȡ�ܼ�¼��
	public long getCount() {
		 
        db = helper.getWritableDatabase();
        Cursor cursor = db
                .rawQuery("select count(id) from sleepdiary_day", null);//��ȡ������Ϣ��¼��
        if (cursor.moveToNext())//�ж�Cursor���Ƿ�������
        {
 
            return cursor.getLong(0);//�����ܼ�¼��
         
        }
        return 0;//û�������򷵻�0
     
	}
	//��ȡ���˵������
	public int getMaxId() {
		 
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select max(id) from sleepdiary_day", null);//��ȡ������Ϣ�е������
        //����Cursor�е����һ������
        while (cursor.moveToLast()) {
            return cursor.getInt(0);//��ȡ���ʵ������ݣ��������
         
}
        return 0;//���û�������򷵻�0
     
}



}
