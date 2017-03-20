package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;

import junit.framework.Assert;

public class Staff_Test {
	private static ArrayList<Staff> staffList = new ArrayList<Staff>();
	
	@BeforeClass
	public static void setup() throws PersonException {
		staffList.add(
			new Staff("Bob", "Lloyd", "Shieffer",
					new Date(1937, 2, 25), "Shieffer Lane", "2127777777", "RadioMan@shieffer.shief",
					"'Urny time.", 10, 500000.00, new Date(1991 + 1900, 1, 1), eTitle.MR));
		staffList.add(
				new Staff("Bobs", "Lloyd", "Shieffer",
						new Date(1937, 2, 25), "Shieffer Lane", "2127777777", "RadioMan@shieffer.shief",
						"'Urny time.", 10, 450000.00, new Date(1991 + 1900, 1, 1), eTitle.MR));
		staffList.add(
				new Staff("Bob", "Lloyds", "Shieffer",
						new Date(1937, 2, 25), "Shieffer Lane", "2127777777", "RadioMan@shieffer.shief",
						"'Urny time.", 10, 550000.00, new Date(1991 + 1900, 1, 1), eTitle.MR));
		staffList.add(
				new Staff("Bob", "Lloyd", "Shieffers",
						new Date(1937, 2, 25), "Shieffer Lane", "2127777777", "RadioMan@shieffer.shief",
						"'Urny time.", 10, 400000.00, new Date(1991 + 1900, 1, 1), eTitle.MR));
		staffList.add(
				new Staff("Bob", "Lloyd", "Sheeffer",
						new Date(1937, 2, 25), "Shieffer Lane", "2127777777", "RadioMan@shieffer.shief",
						"'Urny time.", 10, 600000.00, new Date(1991 + 1900, 1, 1), eTitle.MR));
	}
	
	@Test
	public void testSalaryAverage() {
		double sum = 0;
		double avg = 0;
		for(Staff shieffer : staffList)
			sum += shieffer.getSalary();
		avg = sum/staffList.size();
		assertEquals(avg,500000.00, 0.01);
	}
	
	@Test(expected = PersonException.class)
	public void testBadShieffer1() throws PersonException {
		//Bad DOB
		Staff wrongShieffer = new Staff("Bob", "Lloyd", "Shnieffer",
				new Date(1737, 2, 25), "Shieffer Lane", "2127777777", "RadioMan@shieffer.shief",
				"Radio time.", 10, 500000.00, new Date(1991 + 1900, 1, 1), eTitle.MR);
    }
	
	@Test(expected = PersonException.class)
	public void testBadShieffer2() throws PersonException {
		//Bad phone #
		Staff wrongShieffer = new Staff("Bob", "Lloyd", "Shnierfiereras",
				new Date(1937, 2, 25), "Shieffer Lane", "21277777778", "RadioMan@shieffer.shief",
				"Radio time.", 10, 500000.00, new Date(1991 + 1900, 1, 1), eTitle.MR);
    }
}