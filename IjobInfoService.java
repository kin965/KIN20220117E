package kin;

import java.sql.SQLException;
import java.sql.Timestamp;

public class IjobInfoService {

	// テストの方法、今は使わず
	public void forAddingDataToTheDatabase(JobInfo jobInfo) throws ClassNotFoundException {
		JdcbConn con = new JdcbConn();
		try {
			con.getDbcom();
			String sql = null;
			sql = "insert into ijobinfo ";
			sql += "values('" + jobInfo.getCompanyName() + "',";
			sql += "'" + jobInfo.getJobName() + "',";
			sql += "'" + jobInfo.getAddress() + "',";
			sql += "'" + jobInfo.getSalaryLimit() + "',";
			sql += "'" + new Timestamp(System.currentTimeMillis()) + "')";
			con.insertIjobInfoData(sql);
			con.closeDbcom();
			// System.out.println(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

//サイトから取ったデータをデータベースに入れるの方法
	public void forAddingDataToTheDatabaseFormal(JobInfo jobInfo) throws ClassNotFoundException {
		JdcbConn con = new JdcbConn();
		try {
			con.getDbcom();
			String sql = null;
			sql = "insert into ijobinfoformal  ";
			sql += "values('" + jobInfo.getCompanyName() + "',";
			sql += "'" + jobInfo.getJobName() + "',";
			sql += "'" + jobInfo.getAddress() + "',";
			sql += "'" + jobInfo.getStation() + "',";
			sql += "'" + jobInfo.getSalaryLimit() + "',";
			sql += "'" + new Timestamp(System.currentTimeMillis()) + "')";
			con.insertIjobInfoData(sql);
			con.closeDbcom();
			// System.out.println(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	//元のデータをクリア、
	public void deleteAndRebuildTheTable() throws SQLException {
		JdcbConn con = new JdcbConn();
		try {
	    con.getDbcom();
		String sql=" DROP TABLE ijobinfoformal;\n"
				+ " create table ijobinfoformal(\n"
				+ " companyName varchar,\n"
				+ " jobName varchar,\n"
				+ " address varchar,\n"
				+ " station varchar,\n"
				+ " salaryLimit varchar,\n"
				+ " updatetime    timestamp(6) without time zone)";
		  con.insertIjobInfoData(sql);
		  System.out.println(sql);
	      con.closeDbcom();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
