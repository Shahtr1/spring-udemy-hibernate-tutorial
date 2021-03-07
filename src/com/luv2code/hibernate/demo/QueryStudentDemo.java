package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student")
							.getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			System.out.println("\n\n");
			
			// query student: lastName='Duck'
			theStudents = 
					session.createQuery("from Student s where s.lastName='Duck'")
					.getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			System.out.println("\n\n");

			// query students: lastName='Duck' OR firstName='Paul'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Duck' OR s.firstName='Paul'")
					.getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			System.out.println("\n\n");

			// query students where email LIKE '%luv2code.com'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.email LIKE '%luv2code.com'")
					.getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("\n\nDone!\n");
			
			
		}finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
