package productlist;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductList 
{
	@XmlElement(name="product_set")
	ArrayList<ProductSet> productSets;

	/**
	 * @return the productSets
	 */
	public ArrayList<ProductSet> getProductSets() {
		return productSets;
	}

	/**
	 * @param productSets the productSets to set
	 */
	public void setProductSets(ArrayList<ProductSet> productSets) {
		this.productSets = productSets;
	}

	
}//class
