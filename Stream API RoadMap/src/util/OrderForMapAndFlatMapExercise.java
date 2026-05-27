package util;

import java.util.List;

public class OrderForMapAndFlatMapExercise {
	private String orderId;
	private List<String> items;

	public OrderForMapAndFlatMapExercise(String orderId, List<String> items) {
        this.orderId = orderId;
        this.items = items;
    }

	public List<String> getItems() {
		return items;
	}
}
