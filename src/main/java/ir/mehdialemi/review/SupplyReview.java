package ir.mehdialemi.review;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SupplyReview {
	private int commentCount;
	private int rateCount;
	private double avgRate;
	private Set<Comment> comments = new HashSet <>();
}
