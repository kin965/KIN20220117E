package kin;

import java.sql.SQLException;
import java.sql.Timestamp;

public class IjobInfoService {

	/**
	 * サイトから取ったデータをデータベースに入れるのメソッド
	 * 
	 * @param jobInfo
	 * @throws ClassNotFoundException
	 */
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

	/**
	 * 元のデータtableをクリア、新データtable作りメソッド
	 * 
	 * @throws SQLException
	 */
	public void deleteAndRebuildTheTable() throws SQLException {
		JdcbConn con = new JdcbConn();
		try {
			con.getDbcom();
			String sql = "DELETE  FROM  ijobinfoformal";
			con.insertIjobInfoData(sql);
			System.out.println(sql);
			con.closeDbcom();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}
