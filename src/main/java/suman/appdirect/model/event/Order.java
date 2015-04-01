// 

package suman.appdirect.model.event;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

//--------------------------------------------------------------------------------------------------------------------------------
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
	private String editionCode;

	@XmlElement(name = "item")
	private List<Item> items;

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getEditionCode() {
		return editionCode;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setEditionCode(String editionCode) {
		this.editionCode = editionCode;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public List<Item> getItems() {
		return items;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
