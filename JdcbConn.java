package kin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * SQLを連結のクラス
 * @author jinch
 *
 */
public class JdcbConn {

	// 連結
	Connection conn = null;
	// 駆動
	Statement stmt = null;
	// 結果回収
	ResultSet rs = null;

	String DB_URL = "jdbc:postgresql://localhost:5432/kin";
	String USER = "postgres";
	String PASS = "postgres";
	/**
	 * SQLをログイン
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Connection getDbcom() throws ClassNotFoundException {
		if (conn == null) {
			Class.forName("org.postgresql.Driver");
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
			} catch (SQLException ex) {
			}
		}
		return conn;
	}

	/**
	 * SQLにコマンドを入力するのメソッド
	 * 
	 * @param sql
	 * @throws SQLException
	 */
	public void upData(String sql) throws SQLException {

		System.out.println(sql);
		stmt.executeUpdate(sql);

	}
	/**
	 * SQLをクロス
	 * @throws SQLException
	 */
	public void closeDbcom() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}
