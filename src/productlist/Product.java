package productlist;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product 
{
	@XmlElement(name="product_name")
	String productName;
	@XmlElement(name="product_vendor")
	String productVendor;
	@XmlElement(name="units_sold")
	int unitsSold;
	@XmlElement(name="total_sales")
	double totalSales;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public int getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
}//class
