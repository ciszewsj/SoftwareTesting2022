package com.example.metodywytwarzaniaoprogramowania.data.requests;

import com.example.metodywytwarzaniaoprogramowania.data.Order;
import lombok.Data;

@Data
public class ChangeOrderStatusRequest {
	Order.Status status;
}
