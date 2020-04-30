package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import productlist.Product;
import productlist.ProductList;
import productlist.ProductSet;
//ProductListDao class executes a query and stores result into ProductList class 
public class ProductListDao 
{
	/*
	 * Executes a sql query and assign ouput record to ProductList pojo class
	 * 
	 * input: Statement object for executing query,start date,end date
	 * output: ProductList
	 * */
	public static ProductList getProductList(Statement statementProduct,String startingDateAsString,String endingDateAsString)
	{
		//Product List
		ResultSet resultProduct=null;
		try {
			//Executing a sql query
			resultProduct = statementProduct.executeQuery("select pl.productline,p.productName,p.productVendor,od.quantityOrdered as unitsSold,sum(od.quantityOrdered*od.priceEach) as totalSales "
					+ "from productlines as pl "
					+ "inner join products as p on pl.productline=p.productline "
					+ "inner join orderdetails as od on p.productcode=od.productcode "
					+ "inner join orders as o on od.ordernumber=o.ordernumber "
					+ "where "
					+ "o.orderDate between '"+startingDateAsString+"' and '"+endingDateAsString+"' "
					+ "group by p.productName "
					+ "order by pl.productline");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductList productList=new ProductList();
		ArrayList<ProductSet> productSetList=new ArrayList<>();
		ProductSet productSet=new ProductSet();
		ArrayList<Product> productListProduct=new ArrayList<>();
		String productLineName=null;
		List<String> productLineFlag=new ArrayList<String>();
		try {
			//Traversing each row
			while(resultProduct.next())
			{
				//Fetching all records from result set
				productLineName=resultProduct.getString("productline");
				if(productLineFlag.contains(productLineName))
				{
					//If product line name is already  present then a
					Product product=new Product();
					product.setProductName(resultProduct.getString("productName"));
					product.setProductVendor(resultProduct.getString("productVendor"));
					product.setUnitsSold(resultProduct.getInt("unitsSold"));
					product.setTotalSales(resultProduct.getDouble("totalSales"));
					productListProduct.add(product);
					productSet.setProduct(productListProduct);
				}//if
				else
				{
					//For new product line name, create a new product set
					Product product=new Product();
					productSet=new ProductSet();
					product.setProductName(resultProduct.getString("productName"));
					product.setProductVendor(resultProduct.getString("productVendor"));
					product.setUnitsSold(resultProduct.getInt("unitsSold"));
					product.setTotalSales(resultProduct.getDouble("totalSales"));
					productListProduct=new ArrayList<>();
					productListProduct.add(product);
					productSet.setProduct(productListProduct);
					productSet.setProductLineName(productLineName);
					productSetList.add(productSet);
					productLineFlag.add(productLineName);
				}//else
			}//while
		}//try 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}//while
		productList.setProductSets(productSetList);
		return productList;
	}
	
}//class
