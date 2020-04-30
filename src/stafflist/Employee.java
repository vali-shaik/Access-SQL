package stafflist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee 
{
	@XmlElement(name="first_name")
	String firstName;
	@XmlElement(name="last_name")
	String lastName;
	@XmlElement(name="office_city")
	String officeCity;
	@XmlElement(name="active_customers")
	int activeCustomers;
	@XmlElement(name="total_sales")
	double totalSales;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	public int getActiveCustomers() {
		return activeCustomers;
	}
	public void setActiveCustomers(int activeCustomers) {
		this.activeCustomers = activeCustomers;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
}//class
