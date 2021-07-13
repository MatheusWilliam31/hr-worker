package com.loose.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.loose.hrpayroll.entities.Payment;
import com.loose.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTamplate;

	public Payment getPayment(long workerId, int days) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", "" + workerId);

		Worker worker = restTamplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
