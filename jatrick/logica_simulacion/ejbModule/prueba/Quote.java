package prueba;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity 
@XmlRootElement 
@Indexed 
@Proxy(lazy = false) 
public class Quote { 
	@Id 
//	@GeneratedValue(generator = "uuid") 
	@GenericGenerator(name = "uuid", strategy = "uuid2") 
	private String id; 
	
	@Field 
	private String quoteNumber; 
	
	@Field private String quoteVers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuoteNumber() {
		return quoteNumber;
	}

	public void setQuoteNumber(String quoteNumber) {
		this.quoteNumber = quoteNumber;
	}

	public String getQuoteVers() {
		return quoteVers;
	}

	public void setQuoteVers(String quoteVers) {
		this.quoteVers = quoteVers;
	} 
	
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
//	private Set<String> lineItems;
}
