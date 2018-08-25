package cn.edu.cqu.project_test_dao;

import java.util.ArrayList;
import java.util.List;

import cn.edu.cqu.project_test_model.PatientInformation;
import cn.edu.cqu.project_test_model.PatientTest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class PatientInformationDAO {
	private final static String TABLE_NAME = "patient_table";
	public final static String PATIENT_ID = "id";
	public final static String CURRENT_DATE = "current_dat";
	public final static String PATIENT_NAME = "patient_name";
	//private final static String TABLE_NAME02 = "test_table";
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
	
	
	private PatientDB helper;
	private SQLiteDatabase db;
	public PatientInformationDAO(Context context){
		helper = new PatientDB(context);// ��ʼ��DBOpenHelper����
	}
	//���Ӳ�������
	public void insert(PatientInformation patient){
		//����һ����д�����ݿ�
		db = helper.getWritableDatabase();
		//ContentValues�Լ�ֵ�Ե���ʽ������� 
		ContentValues cv = new ContentValues();
		cv.put(CURRENT_DATE, patient.getCurrentdate());
		cv.put(PATIENT_NAME, patient.getPatient_name());
		cv.put(PATIENT_GENDER, patient.getPatient_gender());
		cv.put(PATIENT_AGE, patient.getPatient_age());
		cv.put(PATIENT_POSITION, patient.getPatient_postion());
		cv.put(PATIENT_SCDE, patient.getPatient_scde());
		cv.put(PATIENT_INSOMNIA, patient.getPatient_insomnia());
		cv.put(PATIENT_MHISTORY, patient.getPatient_mhistory());
		cv.put(PATIENT_HPONE, patient.getPatient_phone());
		//cv.put(PATIENT_HAMD, patient.getPatient_hamd());
		db.insert(TABLE_NAME, null, cv);
		//return row;	
		
//		PatientTestDAO ptdao = new PatientTestDAO(con);
//		PatientTest patienttest = new PatientTest();
//		ptdao.insert(patienttest);
		
		//�������Ա��
//		String sql02 = "CREATE TABLE " + TABLE_NAME02 + " (" + PATIENT_ID + INTEGER primary key autoincrement,  PQSI varchar(30), )";
//				db.execSQL(sql02);
//		db.execSQL("CREATE TABLE  test_table(id integer primary key autoincrement, pqsi varchar(30), sleepness varchar(30),hama varchar(60), hamd varchar(60))");
//		Ӧ����������������ݲŶԡ�������
//				System.out.println("�������Ա�ɹ�����");
				
	}
	//�޸Ĳ�����Ϣ
	public void updatePatientInformation(int id ,PatientInformation patient){
		//���һ����д�����ݿ�
		SQLiteDatabase db = helper.getWritableDatabase();
		//�޸�����
		String where = PATIENT_ID + " = ?";
		//�޸������Ĳ���  Integer.toString(id)�����������ַ�����ʾ
		String[] whereValue = { Integer.toString(id) };
				 
		ContentValues cv = new ContentValues();
		cv.put(CURRENT_DATE, patient.getCurrentdate());
		cv.put(PATIENT_NAME, patient.getPatient_name());
		cv.put(PATIENT_GENDER, patient.getPatient_gender());
		cv.put(PATIENT_POSITION, patient.getPatient_postion());
		cv.put(PATIENT_SCDE, patient.getPatient_scde());
		cv.put(PATIENT_INSOMNIA, patient.getPatient_insomnia());
		cv.put(PATIENT_MHISTORY, patient.getPatient_mhistory());
		cv.put(PATIENT_HPONE, patient.getPatient_phone());
		//cv.put(PATIENT_HAMD, patient.getPatient_hamd());
		db.update(TABLE_NAME, cv, where, whereValue);
		}
	//���Ҳ�����Ϣ
	public PatientInformation find(int id){
		db = helper.getWritableDatabase();
//		Cursor cursor = db.rawQuery(
//                        "select id,patient_name,patient_gender,patient_age,patient_postion,patient_scde,patient_insomnia,patient_mhistory,patient_phone,current_date from patient_table where id = ?",
//                        new String[] {String.valueOf(id)});//���ݱ�Ų��ҵ��Ĳ�����Ϣ�����洢��Cursor��
		Cursor cursor = db.rawQuery(
                "select * from patient_table where id = ?",
              new String[] {String.valueOf(id)});//���ݱ�Ų��ҵ��Ĳ�����Ϣ�����洢��Cursor��
		//�������ҵ��Ĳ�����Ϣ
		if(cursor.moveToNext()){
			System.out.println("1111111111111111111111----" + cursor.getString(cursor.getColumnIndex("current_dat")));
			//���������ҵ��Ĳ�����Ϣ�洢��PatientInformation����
			return new PatientInformation(
					cursor.getString(cursor.getColumnIndex("current_dat")),
                    cursor.getString(cursor.getColumnIndex("patient_name")),
                    cursor.getString(cursor.getColumnIndex("patient_gender")),
                    cursor.getString(cursor.getColumnIndex("patient_age")),
                    cursor.getString(cursor.getColumnIndex("patient_postion")),
                    cursor.getString(cursor.getColumnIndex("patient_scde")),
                    cursor.getString(cursor.getColumnIndex("patient_insomnia")),
                    cursor.getString(cursor.getColumnIndex("patient_mhistory")),
                    cursor.getString(cursor.getColumnIndex("patient_phone"))
                    );
		}
		
		return null;	
	}
	//ѡ��
	public Cursor select(){
	//����һ���ɶ������ݿ�
	db = helper.getReadableDatabase();
	Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
	return cursor;	
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
                    cursor.getString(cursor.getColumnIndex("current_dat")),
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
		Cursor cursor = db.rawQuery( "select * from patient_table",null);
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
