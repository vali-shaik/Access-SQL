package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import stafflist.Employee;
import stafflist.StaffList;

public class StaffListDao 
{
	public static StaffList getStaffList(Statement statementStaff,String startingDateAsString,String endingDateAsString)
	{
		StaffList staffList=new StaffList();
		//Staff List
		ResultSet resultStaff=null;
		try {
			resultStaff = statementStaff.executeQuery(""
					+ "select e.firstName,e.lastName,ofc.city,count(distinct( c.customerNumber))as activeCustomers,sum(od.priceEach*od.quantityOrdered) as totalSales "
					+ "from employees as e inner join offices as ofc on e.officeCode=ofc.officeCode "
					+ "inner join customers as c on e.employeeNumber=c.salesRepEmployeeNumber "
					+ "inner join orders as o on c.customerNumber=o.customerNumber "
					+ "inner join orderdetails od on od.orderNumber=o.orderNumber "
					+ "where o.orderDate between '"+startingDateAsString+"' and '"+endingDateAsString+"' "
					+ "group by e.employeeNumber;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Employee> employeeList=new ArrayList<>();
		try {
			while(resultStaff.next())
			{
				Employee employee=new Employee();
				employee.setFirstName(resultStaff.getString("firstName"));
				employee.setLastName(resultStaff.getString("lastName"));
				employee.setOfficeCity(resultStaff.getString("city"));
				employee.setActiveCustomers(resultStaff.getInt("activeCustomers"));
				employee.setTotalSales(resultStaff.getDouble("totalSales"));
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		staffList.setEmployee(employeeList);
		return staffList;
	}//get StaffList
}//class
