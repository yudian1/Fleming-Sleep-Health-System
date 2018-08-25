package cn.edu.cqu.project_test_dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PatientDB extends SQLiteOpenHelper{
	private final static String DATABASE_NAME = "patientdata.db";
	private final static int DATABASE_VERSION = 1;
	//�����ı����
	private final static String TABLE_NAME = "patient_table";
	private final static String TABLE_NAME02 = "test_table";
	private final static String TABLE_NAME03 = "sleepdiary_day";
	private final static String TABLE_NAME04 = "doctor_advice";
//	private final static String TABLE_NAME05 = "hamd_table";
//	private final static String TABLE_NAME06 = "sleepdiary_table";
	
	//һ����Ϣ����
	public final static String PATIENT_ID = "id";
	public final static String CURRENT_DATE = "current_dat";
	public final static String PATIENT_NAME = "patient_name";
	public final static String PATIENT_GENDER = "patient_gender";
	public final static String PATIENT_AGE = "patient_age";
	public final static String PATIENT_POSITION = "patient_postion";
	//�Ļ��̶�
	public final static String PATIENT_SCDE = "patient_scde";
	//ʧ��ʱ��
	public final static String PATIENT_INSOMNIA = "patient_insomnia";
	//��ʷ
	public final static String PATIENT_MHISTORY = "patient_mhistory";
	public final static String PATIENT_HPONE = "patient_phone";
	public final static String PATIENT_HAMD = "patient_hamd";
	
	//���Բ���
	public final static String PQSI = "pqsi";
	public final static String Sleepness = "sleepness";
	public final static String HAMA = "hama";
	public final static String HAMD = "hamd";
	public final static String ISI = "isi";
	
	//˯���ռǲ���
	public final static String DAY01 = "day01";
	public final static String DAY02 = "day02";
	public final static String DAY03 = "day03";
	public final static String DAY04 = "day04";
	public final static String DAY05 = "day05";
	public final static String DAY06 = "day06";
	public final static String DAY07 = "day07";
	
	//ҽ����Ϣ
	public final static String DOCTORADVICE = "advice";

	
	public PatientDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	//�������
	@Override
	public void onCreate(SQLiteDatabase db) {
		//����������Ϣ��
		String sql = "CREATE TABLE " + TABLE_NAME + " (" + PATIENT_ID + " integer primary key autoincrement, " + CURRENT_DATE + " text, "+ PATIENT_NAME + " text, " + PATIENT_GENDER + " text, " 
				+ PATIENT_AGE + " text, "+ PATIENT_POSITION + " text, "
				+ PATIENT_SCDE + " text, " + PATIENT_INSOMNIA + " text, " + PATIENT_MHISTORY + " text, " +PATIENT_HPONE +  " text);";
		Log.v("sql", sql);
		System.out.println("sql---" + sql);
		//�������Ա�
	//	db.execSQL("CREATE TABLE  %1(id integer primary key autoincrement, pqsi varchar(30), sleepness varchar(30),hama varchar(30), hamd varchar(30))");
		db.execSQL(sql);
//		//�������Ա�
		String sql02 = "CREATE TABLE " + TABLE_NAME02 + " (" + PATIENT_ID + " integer primary key autoincrement, "  + PQSI + " text, "+ Sleepness + " text, " + HAMA + " text, " 
				+ HAMD +" text, " + ISI +  " text);";
		db.execSQL(sql02);
		//����˯���ռ�������
		String sql03 = "CREATE TABLE " + TABLE_NAME03 + " (" + PATIENT_ID + " integer primary key autoincrement, " + DAY01 + " text, "+ DAY02 + " text, " + DAY03 + " text, " 
				+ DAY04 + " text, "+ DAY05 + " text, "+ DAY06 + " text, " + DAY07 +" text);";
		db.execSQL(sql03);
		//����˯���ռ�������
		String sql04 = "CREATE TABLE " + TABLE_NAME04 + " (" + PATIENT_ID + " integer primary key autoincrement, " + DOCTORADVICE + " text, " + PATIENT_HPONE +" text);";
		db.execSQL(sql04);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//ɾ���ɰ���
		//String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
		//db.execSQL(sql);
		//onCreate(db);
	}
}
