package day1226;
import java.sql.Date;

import day1229.CarCountryDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class CarModelDomain extends CarMakerDomain{
	private String model,maker,car_year,car_option,car_img;		
	private int price,cc;
	private Date input_date;
}
