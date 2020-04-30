package stafflist;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="staff_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class StaffList 
{
	@XmlElement(name="employee")
	ArrayList<Employee> employee;

	public ArrayList<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(ArrayList<Employee> employee) {
		this.employee = employee;
	}
	
}//class
