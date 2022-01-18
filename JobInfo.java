package kin;

/**
 * 実装クラス
 * 
 * @author jinch
 *
 */
public class JobInfo {
	public JobInfo() {

	}

	/**
	 * 社名
	 */
	private String companyName;

	/**
	 * 仕事名
	 */
	private String jobName;

	/**
	 * 所在地
	 */
	private String address;

	/**
	 * 駅
	 */
	private String station;

	/**
	 * 給料
	 */
	private String salaryLimit;

	public String getSalaryLimit() {
		return salaryLimit;
	}

	public void setSalaryLimit(String salaryLimit) {
		this.salaryLimit = salaryLimit;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
