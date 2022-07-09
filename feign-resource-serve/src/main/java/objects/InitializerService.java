package objects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class InitializerService implements CommandLineRunner{
	private List<Country> countryList=new ArrayList<Country>();

	@Override
	public void run(String... args) throws Exception {
		 Country usa = Country.builder().id(1).capital("washington").name("usa").population(331893745).build();
		 Country uk = Country.builder().id(2).capital("london").name("UK").population(67326569).build();
		  countryList.add(uk);
		 countryList.add(usa);
		
	}

}
