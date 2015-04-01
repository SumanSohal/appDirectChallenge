// 

package suman.appdirect.model.event;

import javax.xml.bind.annotation.XmlRootElement;

//--------------------------------------------------------------------------------------------------------------------------------
@XmlRootElement
public class Event {
	private String type;
	private Marketplace marketplace;
	private Creator creator; // only applicable for 'create', 'change' and
								// 'cancel' events
	private Payload payload;

	// --------------------------------------------------------------------------------------------------------------------------------
	public String getType() {
		return type;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setType(String type) {
		this.type = type;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Marketplace getMarketplace() {
		return marketplace;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Creator getCreator() {
		return creator;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setCreator(Creator creator) {
		this.creator = creator;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Payload getPayload() {
		return payload;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}
