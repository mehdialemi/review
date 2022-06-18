package ir.mehdialemi.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Value("${admin.comment.shows}")
	private int commentCount;

	@PostMapping("/comment")
	private void addComment(@RequestBody Review review) {
		reviewService.addComment(review.getUsername(), review.getSupplyId(), review.getText());
	}

	@PostMapping("/rate")
	private void addRate(@RequestBody Review review) {
		reviewService.addRate(review.getUsername(), review.getSupplyId(), review.getRate());
	}

	@GetMapping("/rate/avg/{id}")
	public void getAvgRate(@PathVariable("id") Long id) {
		reviewService.avgRate(id);
	}

	@GetMapping("/rate/count/{id}")
	public long getRateCount(@PathVariable("id") Long id) {
		return reviewService.rateCount(id);
	}

	@GetMapping("/comment/count/{id}")
	public long getCommentCount(@PathVariable("id") Long id) {
		return reviewService.commentCount(id);
	}

	@GetMapping("/comment/{id}")
	public Set<Comment> getComments(@PathVariable("id") Long id) {
		return reviewService.getLastComments(id, commentCount);
	}

}
