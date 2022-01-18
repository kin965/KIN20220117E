package kin;

import java.sql.SQLException;
import java.sql.Timestamp;
/**
 * SQLにデータを処理のコマンドを作成のクラス
 * @author jinch
 *
 */
public class IjobInfoService {

	/**
	 * サイトから取ったデータをデータベースに入れるのメソッド
	 * 
	 * @param jobInfo
	 * @throws ClassNotFoundException
	 */
	public void AddIjobData(JobInfo jobInfo) throws ClassNotFoundException {
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
			con.upData(sql);
			con.closeDbcom();
			// System.out.println(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 元のデータをクリア
	 * 
	 * @throws SQLException
	 */
	public void deleteIjobData() throws SQLException {
		JdcbConn con = new JdcbConn();
		try {
			con.getDbcom();
			String sql = "DELETE  FROM  ijobinfoformal";
			con.upData(sql);
			System.out.println(sql);
			con.closeDbcom();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}
}
