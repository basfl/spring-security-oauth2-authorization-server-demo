package objects;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement(name="countries")
public class Countries {
	List<Country> country;

}
