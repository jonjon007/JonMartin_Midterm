package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {
	private static ArrayList<Course> courseList = new ArrayList<Course>();
	private static ArrayList<Semester> semesterList = new ArrayList<Semester>();
	private static ArrayList<Section> sectionList = new ArrayList<Section>();
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private static ArrayList<Enrollment> enrollmentList = new ArrayList<Enrollment>();
	private static ArrayList<Double> studentTotals = new ArrayList<Double>();
	private static ArrayList<Double> sectionTotals = new ArrayList<Double>();
	public final static double GPA_RATIO = 23.26;
	
	@BeforeClass
	public static void setup() {
		Date fallDate = new Date();
		fallDate.setMonth(9);
		Date springDate = new Date();
		springDate.setMonth(2);
		springDate.setYear(springDate.getYear()+1);
		courseList.add(new Course("CS181", 100, eMajor.COMPSI));
		courseList.add(new Course("CS108", 99, eMajor.COMPSI));
		courseList.add(new Course("MATH210", 98, eMajor.BUSINESS));
		semesterList.add(new Semester(fallDate, fallDate));
		semesterList.add(new Semester(springDate, springDate));
		for(int i = 0; i < courseList.size(); i++)
			for(int j = 0; j < semesterList.size(); j++)
				sectionList.add(new Section(courseList.get(i).getCourseID(), semesterList.get(j).getSemesterID()));
		for(int i = 0; i < 10; i++){
			studentList.add(new Student(eMajor.values()[i%eMajor.values().length]));
		}
	//Build the enrollment list. First ten indexes are for one section, then the next ten for the next, and so on.
		for(Section sec : sectionList){
			for(Student std : studentList){
				enrollmentList.add(new Enrollment(sec.getSectionID(), std.getStudentID()));
			}
		}
		for(int i = 1; i <= enrollmentList.size(); i++){
			enrollmentList.get(i-1).setGrade((i%studentList.size())*10);
		}
	//Student 0's point total should be 10. //Student 1's, 20, and so on.
		for(int i = 0; i < studentList.size(); i++){
			double currentStudentTotal = 0;
			for(int j = i; j < enrollmentList.size(); j += studentList.size()){
				currentStudentTotal += enrollmentList.get(j).getGrade();
			}
			studentTotals.add(currentStudentTotal);
		}
	//Each section's total should be the same.
		for(int i = 0; i < enrollmentList.size(); i += studentList.size()){
			double currentSectionTotal = 0;
			for(int j = i; j < i + studentList.size(); j++){
				currentSectionTotal += enrollmentList.get(j).getGrade();
			}
			sectionTotals.add(currentSectionTotal);
		}
	}
	
	public double getGPA(double totalGrades, int numGrades){
		//ArrayList<Double> convertedGrades = new ArrayList<Double>();
		double convertedGrades = 0;
		convertedGrades = totalGrades/GPA_RATIO;
		return convertedGrades/numGrades;
	}

	@Test
	public void gpaTest() {
		assertEquals(0.43, getGPA(studentTotals.get(0), sectionList.size()), 0.01);
		assertEquals(0.86, getGPA(studentTotals.get(1), sectionList.size()), 0.01);
		assertEquals(1.29, getGPA(studentTotals.get(2), sectionList.size()), 0.01);
		assertEquals(1.72, getGPA(studentTotals.get(3), sectionList.size()), 0.01);
		assertEquals(2.15, getGPA(studentTotals.get(4), sectionList.size()), 0.01);
		assertEquals(2.58, getGPA(studentTotals.get(5), sectionList.size()), 0.01);
		assertEquals(3.01, getGPA(studentTotals.get(6), sectionList.size()), 0.01);
		assertEquals(3.44, getGPA(studentTotals.get(7), sectionList.size()), 0.01);
		assertEquals(3.87, getGPA(studentTotals.get(8), sectionList.size()), 0.01);
		assertEquals(0, getGPA(studentTotals.get(9), sectionList.size()), 0.01);
	}
	
	@Test
	public void sectionAverageTest() {
		//Each student gets the same grades in each of their sections, so the averages should all be the same.
		for(int i = 0; i < sectionTotals.size(); i++)
			assertEquals(45, sectionTotals.get(i)/studentList.size(), 0.01);
	}
	
	@Test
	public void changeMajor(){
		Student studZero = studentList.get(0);
		assertTrue(studentList.get(0).getMajor() == eMajor.BUSINESS);
		studZero.setMajor(eMajor.NURSING);
		assertFalse(studentList.get(0).getMajor() == eMajor.BUSINESS);
		assertTrue(studentList.get(0).getMajor() == eMajor.NURSING);
	}
}