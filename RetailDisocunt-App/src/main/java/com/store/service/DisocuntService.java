package com.store.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DisocuntService {

	public boolean isEligibleFor2yrsOldDiscount(Date userRegistrationDate) {
		LocalDate now = LocalDate.now();
		LocalDate userRegDate = userRegistrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		boolean flag = false;

		int period = Period.between(userRegDate, now).getYears();
		if (period < 2)
			flag = false;
		else
			flag = true;

		return flag;
	}

	public double applyDiscount(double totalAmount, double discountPercentage) {
		double disocuntedAmt = 0;
		double s = 0;

		s = 100 - discountPercentage;

		disocuntedAmt = (s * totalAmount) / 100;

		return disocuntedAmt;

	}

}
