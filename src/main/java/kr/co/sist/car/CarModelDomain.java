package kr.co.sist.car;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class CarModelDomain {
	private String model,maker,car_year,car_option,car_img;			
	private int price,cc;
	private Date input_date;
}
