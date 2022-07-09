package com.feign;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;




@XmlRootElement(name="country")
@Data
@Builder
public class Country {
	int id;
	String name;
	String capital;
	int population;

}
