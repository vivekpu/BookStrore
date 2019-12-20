package com.coursecube.spring.daoimpl;

import java.math.BigInteger;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coursecube.spring.dao.BookDAO;
import com.coursecube.spring.entity.Book;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {
	@Autowired
	HibernateTemplate hibernateTemp;

	@Override
	public List<Book> getAllBooks() {
		System.out.println("-----BookDAOImpl--getAllBooks()------");
		List<Book> list = hibernateTemp.loadAll(Book.class);
		// System.out.println("BookDAOImpl=="+list);
		return list;
	}

	// @SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks(int start, int total) {
		System.out.println("-----BookDAOImpl--getAllBooks(start,total)------");
		DetachedCriteria dc = DetachedCriteria.forClass(Book.class);
		/*
		 * List<Book> list=(List<Book>)hibernateTemp.findByCriteria(dc, start, total);
		 * System.out.println("BookDAOImpl=="+list);
		 */
		return (List<Book>) hibernateTemp.findByCriteria(dc, start, total);
		// return list;
	}

	@Override
	public Book getBookById(Integer bid) {
		System.out.println("-----BookDAOImpl--getAllBooks()------");
		return hibernateTemp.load(Book.class, bid, LockMode.READ);
	}

	@Override
	public void addBook(Book book) {
		System.out.println("-----BookDAOImpl--addBook()------");
		hibernateTemp.save(book);
	}

	@Override
	public void updateBook(Book book) {
		System.out.println("-----BookDAOImpl--updateBook()------");
		hibernateTemp.update(book);
	}

	@Override
	public void deleteBook(Integer bid) {
		System.out.println("-----BookDAOImpl--deleteBook()------");
		Book book = hibernateTemp.get(Book.class, bid);
		if (book != null) {
			hibernateTemp.delete(book);
		}
	}

	public int getBookCount() {
		SessionFactory sessionFactory = hibernateTemp.getSessionFactory();
		Session session = sessionFactory.openSession();
		String sql = "select count(*) from mybooks";
		BigInteger big = (BigInteger) session.createNativeQuery(sql).uniqueResult();
		return big.intValue();
	}

}
