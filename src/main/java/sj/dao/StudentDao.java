package sj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import sj.dto.Student;

public class StudentDao {

	public List<Student> getAllStudents(){
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("pooji");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("Select u from Student u");
		return query.getResultList();
	}

	public void signupStudent(Student student) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("pooji");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(student);
		entityTransaction.commit();
		
	}

	public void deleteStudentById(int id) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("pooji");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Student student=entityManager.find(Student.class, id);
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(student);
		entityTransaction.commit();
		
	}

	public Student getStudentById(int id) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("pooji");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Student student=entityManager.find(Student.class, id);
		return student;
	}

	public void UpdateStudent(Student student) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("pooji");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.merge(student);
		entityTransaction.commit();
		
	}
	
}
