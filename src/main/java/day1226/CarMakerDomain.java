package day1226;
import day1229.CarCountryDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class CarMakerDomain extends CarCountryDomain{
	private String maker,model;
}
