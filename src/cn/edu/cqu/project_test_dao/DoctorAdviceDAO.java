package cn.edu.cqu.project_test_dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.cqu.project_test_model.DoctorAdivce;

public class DoctorAdviceDAO {
	private final static String TABLE_NAME04 = "doctor_advice";
	public final static String PATIENT_HPONE = "patient_phone";
	public final static String DOCTORADVICE = "advice";
	
	private PatientDB helper;
	private SQLiteDatabase db;
	public DoctorAdviceDAO(Context context){
		helper = new PatientDB(context);// 初始化DBOpenHelper对象
	}
	
	//增加医生医嘱数据
	public void insert(DoctorAdivce adivce){
		//创建一个可写的数据库
		db = helper.getWritableDatabase();
		//ContentValues以键值对的形式存放数据 
		ContentValues cv = new ContentValues();
		cv.put(PATIENT_HPONE, adivce.getPatient_phone());
		cv.put(DOCTORADVICE, adivce.getAdvice());
		db.insert(TABLE_NAME04, null, cv);
	}
	public void deletetable(){
//		//创建一个可读的数据库
		db = helper.getReadableDatabase();
		//清空一个表格
		db.execSQL("DELETE FROM doctor_advice");
//		//删除一个表格
//		String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
//		db.execSQL(sql);
	}
	//查找医嘱
	public DoctorAdivce find(String patient_phone){
				db = helper.getWritableDatabase();
				Cursor cursor = db.rawQuery(
	                    "select * from doctor_advice where patient_phone = ?",
	                  new String[] {patient_phone});//根据编号查找到的病人信息，并存储到Cursor中
				//遍历查找到的病人信息
				if(cursor.moveToNext()){
					//将遍历查找到的病人信息存储到PatientInformation类中
					return new DoctorAdivce(
		                    cursor.getString(cursor.getColumnIndex("patient_phone")),
		                    cursor.getString(cursor.getColumnIndex("advice"))
		                    );
				}
				return null;	
			}
	//获取总记录数
		public long getCount() {
			 
	        db = helper.getWritableDatabase();
	        Cursor cursor = db
	                .rawQuery("select count(id) from doctor_advice", null);//获取病人信息记录数
	        if (cursor.moveToNext())//判断Cursor中是否有数据
	        {
	 
	            return cursor.getLong(0);//返回总记录数
	         
	        }
	        return 0;//没有数据则返回0
	     
		}
}
