package ir.mehdialemi.review;

import lombok.Data;

@Data
public class Review {
	private String username;
	private long supplyId;
	private String text;
	private int rate;
}
