package com.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.store.pojo.Bill;
import com.store.pojo.Item;
import com.store.service.DisocuntService;

@RestController
public class DisocuntController {

	@Autowired
	DisocuntService service;

	@PostMapping("/getDiscountedBill")
	public String getPayableAmount(@RequestBody Bill billData) {
		double finalBill = 0;
		double totalAmountForDisocunt = 0;
		double totalAmountNonDisocunt = 0;

		for (Item item : billData.getItems()) {
			if (!item.getItemType().equalsIgnoreCase("GROCERIES"))
				totalAmountForDisocunt += item.getUnitPrice() * item.getQuantity();
			else
				totalAmountNonDisocunt += item.getUnitPrice() * item.getQuantity();
		}

		if (billData.getUserType().equalsIgnoreCase("EMPLOYEE")) {
			finalBill = this.service.applyDiscount(totalAmountForDisocunt, 30);
		} else if (billData.getUserType().equalsIgnoreCase("AFFILIATE")) {
			finalBill = this.service.applyDiscount(totalAmountForDisocunt, 10);
		} else {
			if (this.service.isEligibleFor2yrsOldDiscount(billData.getUserRegistrationDate())) {
				finalBill = this.service.applyDiscount(totalAmountForDisocunt, 5);
				billData.setUserType("OTHER above 2 years user");

			} else {
				finalBill = totalAmountForDisocunt;
				billData.setUserType("OTHER below 2 years user");

			}
		}

		finalBill += totalAmountNonDisocunt;

		if (finalBill > 100) {
			int finalBillHu = (int) (finalBill / 100) * 5;
			finalBill -= finalBillHu;
		}

		double total = totalAmountForDisocunt + totalAmountNonDisocunt;
		return "Total Bill Amount = $" + total + "\nUser Type Discount Applied = " + billData.getUserType()
				+ "\nDiscounted Bill Amount = $" + Double.toString(finalBill);

	}

}
