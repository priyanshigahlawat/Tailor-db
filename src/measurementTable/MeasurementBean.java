package measurementTable;

public class MeasurementBean {
	String custname;
	String custmobile;
	String dress;
	String spl;
	String measurement;
	String doo;
	String dod;
	int amount;
	String wish;
	MeasurementBean(){}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getCustmobile() {
		return custmobile;
	}
	public void setCustmobile(String custmobile) {
		this.custmobile = custmobile;
	}
	public String getDress() {
		return dress;
	}
	public void setDress(String dress) {
		this.dress = dress;
	}
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getDoo() {
		return doo;
	}
	public void setDoo(String doo) {
		this.doo = doo;
	}
	public String getDod() {
		return dod;
	}
	public void setDod(String dod) {
		this.dod = dod;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getWish() {
		return wish;
	}
	public void setWish(String wish) {
		this.wish = wish;
	}
	public MeasurementBean(String custname, String custmobile, String dress, String spl,
			String doo, String dod, int amount) {
		super();
		this.custname = custname; 
		this.custmobile = custmobile;
		this.dress = dress;
		this.spl = spl;
//		this.measurement = measurement;
		this.doo = doo;
		this.dod = dod;
		this.amount = amount;
//		this.wish = wish;
	};   
}