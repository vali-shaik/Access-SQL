package toy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import customerlist.CustomerList;
import productlist.ProductList;
import stafflist.StaffList;
import year.Year;

@XmlRootElement(name="year_end_summary")
@XmlAccessorType(XmlAccessType.FIELD)
public class YearEndSummary 
{
	@XmlElement
	Year year;
	@XmlElement(name="customer_list")
	CustomerList customerList;
	@XmlElement(name="product_list")
	ProductList productList;
	@XmlElement(name="staff_list")
	StaffList staffList;
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	public CustomerList getCustomerList() {
		return customerList;
	}
	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}
	public ProductList getProductList() {
		return productList;
	}
	public void setProductList(ProductList productList) {
		this.productList = productList;
	}
	public StaffList getStaffList() {
		return staffList;
	}
	public void setStaffList(StaffList staffList) {
		this.staffList = staffList;
	}
	
	
}//class
