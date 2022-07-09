package objects;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name="country")
public class Country {
	int id;
	String name;
	String capital;
	int population;

}
