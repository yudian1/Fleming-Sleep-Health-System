package cn.edu.cqu.project_test_model;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class SleepDiary implements KvmSerializable{
	private int id;
	private String day01;
	private String day02;
	private String day03;
	private String day04;
	private String day05;
	private String day06;
	private String day07;
	private String patient_phone;
	public SleepDiary(String day01, String day02, String day03, String day04,
			String day05, String day06, String day07) {
		super();
		this.day01 = day01;
		this.day02 = day02;
		this.day03 = day03;
		this.day04 = day04;
		this.day05 = day05;
		this.day06 = day06;
		this.day07 = day07;
	}
	public SleepDiary(String day01, String day02, String day03, String day04,
			String day05, String day06, String day07, String patient_phone) {
		super();
		this.day01 = day01;
		this.day02 = day02;
		this.day03 = day03;
		this.day04 = day04;
		this.day05 = day05;
		this.day06 = day06;
		this.day07 = day07;
		this.patient_phone = patient_phone;
	}
	public String getPatient_phone() {
		return patient_phone;
	}
	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}
	public SleepDiary(int id, String day01, String day02, String day03,
			String day04, String day05, String day06, String day07) {
		super();
		this.id = id;
		this.day01 = day01;
		this.day02 = day02;
		this.day03 = day03;
		this.day04 = day04;
		this.day05 = day05;
		this.day06 = day06;
		this.day07 = day07;
	}
	public SleepDiary() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDay01() {
		return day01;
	}
	public void setDay01(String day01) {
		this.day01 = day01;
	}
	public String getDay02() {
		return day02;
	}
	public void setDay02(String day02) {
		this.day02 = day02;
	}
	public String getDay03() {
		return day03;
	}
	public void setDay03(String day03) {
		this.day03 = day03;
	}
	public String getDay04() {
		return day04;
	}
	public void setDay04(String day04) {
		this.day04 = day04;
	}
	public String getDay05() {
		return day05;
	}
	public void setDay05(String day05) {
		this.day05 = day05;
	}
	public String getDay06() {
		return day06;
	}
	public void setDay06(String day06) {
		this.day06 = day06;
	}
	public String getDay07() {
		return day07;
	}
	public void setDay07(String day07) {
		this.day07 = day07;
	}
	
	@Override
	public Object getProperty(int index) {

		// TODO Auto-generated method stub
		Object obj = null;
		switch (index) {
		case 0:
			obj = this.patient_phone;
			break;
		case 1:
			obj = this.day01;
			break;
		case 2 :
			obj = this.day02;
			break;
		case 3:
			obj = this.day03;
			break;
		case 4:
			obj = this.day04;
			break;
		case 5 :
			obj = this.day05;
			break;
		case 6 :
			obj = this.day06;
			break;
		case 7:
			obj = this.day07;
			break;
		default:
			break;
		}
		return obj;
	
	}
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 8;
	}
	@Override
	public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			info.name = "patient_phone";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 1:
			info.name = "day01";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 2:
			info.name = "day02";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 3:
			info.name = "day03";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 4:
			info.name = "day04";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 5:
			info.name = "day05";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 6:
			info.name = "day06";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		case 7:
			info.name = "day07";
			info.type = PropertyInfo.STRING_CLASS;
			break;
		default:
			break;
		}
	}
	@Override
	public void setProperty(int index, Object obj) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			this.patient_phone = obj.toString();
			break;
		case 1:
			this.day01 = obj.toString();
			break;	
		case 2:
			this.day02 = obj.toString();
			break;
		case 3:
			this.day03 = obj.toString();
			break;	
		case 4:
			this.day04= obj.toString();
			break;
		case 5:
			this.day05 = obj.toString();
			break;	
		case 6:
			this.day06 = obj.toString();
			break;
		case 7:
			this.day07 = obj.toString();
			break;
		default:
			break;
		}
	}
}
