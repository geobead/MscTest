package com.msc.qa.testcases;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class SampleTestClass {

	private String name;
	private int age;
	private Map<String, Integer> marks;

	@Factory(dataProvider = "getData")
	public SampleTestClass(String name, int age, Map<String, Integer> marks) {
		this.name = name;
		this.age = age;
		this.marks = marks;
	}

	@DataProvider(name = "getData")
	public static Object[][] testData() {
		return new Object[][] { { "Amar", 16, marksInSubjects(45, 55, 65) },
				{ "Akbar", 16, marksInSubjects(55, 65, 75) }, { "Antony", 16, marksInSubjects(35, 45, 55) }, };
	}

	@Test
	public void testName() {
		assertTrue(name != null && !name.trim().isEmpty(), "Should have received a valid name");
	}

	@Test
	public void testAge() {
		assertTrue(age > 0 && age < 30, "Should have received a valid age.");
	}

	@Test(dataProvider = "marks")
	public void testMarks(String subject, int marks) {
		boolean validSubject = subject != null && !"sports".equalsIgnoreCase(subject.trim());
		assertTrue(validSubject, "Should have received a valid subject");
		assertTrue(marks >= 40, this.name + " didn't pass in " + subject);
	}

	@DataProvider(name = "marks")
	public Object[][] getMarks() {
		Object[][] marks = new Object[this.marks.size()][1];
		int index = 0;
		for (Map.Entry<String, Integer> mark : this.marks.entrySet()) {
			marks[index++] = new Object[] { mark.getKey(), mark.getValue() };
		}
		return marks;
	}

	private static Map<String, Integer> marksInSubjects(int m1, int m2, int m3) {
		Map<String, Integer> marks = new HashMap<>();
		marks.put("english", m1);
		marks.put("science", m2);
		marks.put("mathematics", m3);
		return marks;
	}
}
