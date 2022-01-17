package kin;

import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//サイトからデータをとるのクラス
//サイトからデータを取る、データベースに書き込み
public class GetIjobDataTest {
	public static void main(String[] args) throws ClassNotFoundException {
		Document document;
		Document documentAllPage;
		IjobInfoService ijobInfoService = new IjobInfoService();
		

		try {
		
			ijobInfoService.deleteAndRebuildTheTable();
			// 目標サイトのURLを入力
			document = Jsoup.connect("http://ijob.jp/index.php?pN=5&ctl=Index&act=joblist&type=3&c_hangye=11").get();
			// ページナンバーをget
			Elements getPageNum = document.getElementsByClass("fl fybo fy_ym ");
			int pageNum = Integer.valueOf(getPageNum.get(getPageNum.size() - 1).text());
			for (int i = 1; i <= pageNum; i++) {
				documentAllPage = Jsoup
						// 毎ページのデータを取りたいので、ページナンバーをサイトのURLの中に差し替える
						.connect("http://ijob.jp/index.php?pN=" + i + "&ctl=Index&act=joblist&type=3&c_hangye=11")
						.get();

//          もし目標のURLですーぺすがあるの場合には URLの文を差し替える、今は使わず
//			document.html().replace("fl paddinglr50 bbox w100per bdhuisebottom2 paddingtb45 pos_r", "fl paddinglr50 bbox w100per bdhuisebottom2 paddingtb45 pos_r ");
//			Elements elment = document.getElementsByClass("fl paddinglr50 bbox w100per bdhuisebottom2 paddingtb45 pos_r ");

				// 整体のページで、取りたい部分のclass URLを入力
				Elements elment = documentAllPage.getElementsByClass("fl w100per margint10 marginb60");
				// 整体会社の情報を主体として データを取る
				Elements eachCompanyInfo = elment.last()
						.getElementsByClass("fl paddinglr50 bbox w100per bdhuisebottom2 paddingtb45 pos_r ");

				// 整体会社の情報を主体として、その中各情報を取る
				for (int j = 0; j < eachCompanyInfo.size(); j++) {
					JobInfo jobInfo = new JobInfo();
					Elements companyName = eachCompanyInfo.get(j)
							.getElementsByClass("fl w70per fnt20 hang30   overf fncolorhui");
					Elements jobName = eachCompanyInfo.get(j)
							.getElementsByClass("fl w70per fnt20 hang30   overf fncolorhui");
					Elements address = eachCompanyInfo.get(j).getElementsByClass("fl hang20  marginl5");
					Elements station = eachCompanyInfo.get(j).getElementsByClass("fl hang20 marginl5");
					Elements salaryLimit = eachCompanyInfo.get(j)
							.getElementsByClass("fr w30per fnt20 hang30 overf fncolorhongse ta_r");

					jobInfo.setJobName(jobName.text());
					jobInfo.setCompanyName(companyName.text());
					jobInfo.setAddress(address.text());
					jobInfo.setSalaryLimit(salaryLimit.text());
					jobInfo.setStation(station.text());

					ijobInfoService.forAddingDataToTheDatabaseFormal(jobInfo);
                    
				

				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
