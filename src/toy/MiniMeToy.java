package toy;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import customerlist.CustomerList;
import dao.CustomerListDao;
import dao.DBConnection;
import dao.ProductListDao;
import dao.StaffListDao;
import productlist.ProductList;
import stafflist.StaffList;
import year.Year;

//Main class for accepting inputs from user and populating the output into file and console
public class MiniMeToy 
{
	//Main method
	public static void main(String args[])
	{
		//Scanner to read inputs from user
		Scanner scanner=new Scanner(System.in);
		//Regular expression for YYYY-MM-DD format
		String regexDate = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";
		boolean flag;
		String startingDateAsString,endingDateAsString;
		do
		{
			//Reading starting date from user
			System.out.println("Please enter the STARTING DATE (YYYY-MM-DD) for the period to summarize");
			startingDateAsString=scanner.nextLine();  
			//Creating a pattern object
			flag = Pattern.compile(regexDate).matcher(startingDateAsString).matches();
		}while(!flag);

		do
		{
			//Reading ending date from user
			System.out.println("Please enter the ENDING DATE (YYYY-MM-DD) for the period to summarize");
			endingDateAsString=scanner.nextLine();  
			//Creating a pattern object
			flag = Pattern.compile(regexDate).matcher(endingDateAsString).matches();
		}while(!flag);
		//Read file name
		System.out.println("Please enter name of the file for the output");
		String outputFileName=scanner.next();
		//Establishing connection to Database
		Connection connection=DBConnection.getDBConnection();
		//Creating statement objects for all queries
		Statement statementCustomer=null;
		Statement statementProduct=null;
		Statement statementStaff=null;
		try 
		{
			statementCustomer = connection.createStatement();
			statementProduct=connection.createStatement();
			statementStaff=connection.createStatement();
		}//try 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}//catch
		scanner.close();
		//XMl tags conversion
		YearEndSummary yearEndSummary=new YearEndSummary();
		Year year=new Year();
		year.setEndDate(endingDateAsString);
		year.setStartDate(startingDateAsString);
		//Assigning starting and ending date to XML tags
		yearEndSummary.setYear(year);
		CustomerList customerList=new CustomerList();
		//Executing query and fetching data from DB related to customers
		customerList=CustomerListDao.getCustomerList(statementCustomer, startingDateAsString, endingDateAsString);
		yearEndSummary.setCustomerList(customerList);
		ProductList productList=new ProductList();
		//Executing query and fetching data from DB related to product
		productList=ProductListDao.getProductList(statementProduct, startingDateAsString, endingDateAsString);
		yearEndSummary.setProductList(productList);
		StaffList staffList=new StaffList();
		//Executing query and fetching data from DB related to employee
		staffList=StaffListDao.getStaffList(statementStaff, startingDateAsString, endingDateAsString);
		yearEndSummary.setStaffList(staffList);
		//Conversion to XML
		Marshaller marshaller=null;
		//JAXB convets java pojo Class data to Xml tags 
		try 
		{
			JAXBContext jaxbContext = JAXBContext.newInstance(YearEndSummary.class);
			marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//Writing Xml tags into a file
			marshaller.marshal(yearEndSummary, new File(""+outputFileName+".xml"));
			marshaller.marshal(yearEndSummary, System.out);
		}//try 
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}//catch

	}//main

}//end
