package kin;

import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * サイトからデータをとるのクラス
 * 
 * @author jinch
 *
 */

public class GetIjobData {
	/**
	 * サイトからデータを取る、データベースに書き込み
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		Document document;
		Document documentAllPage;
		IjobInfoService ijobInfoService = new IjobInfoService();

		try {
			// データベースの古いデータをクリアする
			ijobInfoService.deleteIjobData();
			// 目標サイトのURLを設定
			document = Jsoup.connect("http://ijob.jp/index.php?pN=5&ctl=Index&act=joblist&type=3&c_hangye=11").get();
			// ページ数を取得
			Elements getPageNum = document.getElementsByClass("fl fybo fy_ym ");
			int pageNum = Integer.valueOf(getPageNum.get(getPageNum.size() - 1).text());
			for (int i = 1; i <= pageNum; i++) {
				documentAllPage = Jsoup
						// 毎ページのデータを取りたいので、ページナンバーをサイトのURLの中に差し替える
						.connect("http://ijob.jp/index.php?pN=" + i + "&ctl=Index&act=joblist&type=3&c_hangye=11")
						.get();

				// 整体のページで、取りたい部分のclass URLを設定
				Elements elment = documentAllPage.getElementsByClass("fl w100per margint10 marginb60");
				// 整体会社の情報を主体として データを取る
				Elements eachCompanyInfo = elment.last()
						.getElementsByClass("fl paddinglr50 bbox w100per bdhuisebottom2 paddingtb45 pos_r ");

				// 整体会社の情報を主体として、その中各情報を取る

				for (Element x : eachCompanyInfo) {
					// 会社名を取得のURLを設定
					Elements companyName = x.getElementsByClass("fl w70per fnt20 hang30   overf fncolorhui");
					// 仕事名を取得のURLを設定
					Elements jobName = x.getElementsByClass("fl w70per fnt20 hang30   overf fncolorhui");
					// 所在地を取得のURLを設定
					Elements address = x.getElementsByClass("fl hang20  marginl5");
					// 駅を取得のURLを設定
					Elements station = x.getElementsByClass("fl hang20 marginl5");
					// 給料と待遇を取得のURLを設定
					Elements salaryLimit = x.getElementsByClass("fr w30per fnt20 hang30 overf fncolorhongse ta_r");
					// 実装クラスを使用、取得のデータを実装クラスの引数を設定
					JobInfo jobInfo = new JobInfo();
					jobInfo.setJobName(jobName.text());
					jobInfo.setCompanyName(companyName.text());
					jobInfo.setAddress(address.text());
					jobInfo.setSalaryLimit(salaryLimit.text());
					jobInfo.setStation(station.text());

					ijobInfoService.AddIjobData(jobInfo);

				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
