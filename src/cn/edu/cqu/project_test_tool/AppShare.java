package cn.edu.cqu.project_test_tool;

import android.app.Application;
//不同Activity之间传递数据
public class AppShare extends Application{
	private int row;
	private String patientname;
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

}
