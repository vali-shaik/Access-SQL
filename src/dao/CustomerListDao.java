package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import customerlist.Address;
import customerlist.Customer;
import customerlist.CustomerList;
/*
 * CustomerListDao Class executes sql query to fetch customer information between given period 
 * and assigns result set rows to java pojo class 
 * */
public class CustomerListDao 
{
	/*
	 * Executes sql query and assign records to CustomerList fields
	 * 
	 * input: Statement Object for executing query,start date, end date
	 * output: CustomerList
	 */
	public static CustomerList getCustomerList(Statement statementCustomer,String startingDateAsString,String endingDateAsString)
	{

		//Customer List
		ResultSet resultCustomer=null;
		try {
			//Executing sql query
			resultCustomer = statementCustomer.executeQuery(""
					+ "select "
					+ "c.customerName,c.addressLine1,c.city,c.postalCode,c.country,count(distinct o.orderNumber) as NoOfOrders,sum(od.priceEach*quantityOrdered) as OrderValue from orders as o "
					+" inner join customers as c on o.customerNumber=c.customerNumber "
					+ "inner join orderdetails as od on o.orderNumber=od.orderNumber "
					+ "where o.orderDate between '"+startingDateAsString+"' and '"+endingDateAsString+"';"
					+ "");
		}//try 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}//catch
		CustomerList customerList=new CustomerList();
		ArrayList<Customer> customers=new ArrayList<Customer>();
		try 
		{
			//Result Set object is used to pull all the data and assign to java pojo class
			while(resultCustomer.next())
			{
				Customer customer=new Customer();
				customer.setNumberOfOrders(resultCustomer.getInt("NoOfOrders"));
				customer.setOrderValue(resultCustomer.getDouble("OrderValue"));
				customer.setCustomerName(resultCustomer.getString("customerName"));
				//Fetcing address data from result
				Address address=new Address();
				address.setStreetAddress(resultCustomer.getString("addressLine1"));
				address.setCity(resultCustomer.getString("city"));
				address.setPostalCode(resultCustomer.getString("postalCode"));
				address.setCountry(resultCustomer.getString("country"));
				customer.setAddress(address);
				customers.add(customer);

			}//while
		}//try
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//catch
		customerList.setCustomers(customers);
		return customerList;

	}//get Customer List

}//class
