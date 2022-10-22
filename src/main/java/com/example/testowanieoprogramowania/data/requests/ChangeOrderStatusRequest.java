package com.example.testowanieoprogramowania.data.requests;

import com.example.testowanieoprogramowania.data.Order;
import lombok.Data;

@Data
public class ChangeOrderStatusRequest {
	Order.Status status;
}
