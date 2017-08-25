/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpacontroller;

import entity.Book;
import entity.EBook;
import entity.PaperBook;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Dixie
 */
public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        
        
        em.getTransaction().begin();
        
        
        // Adding to the DB and showcasing polymorphism
        
        PaperBook paperBook = new PaperBook();
        paperBook.setInStock(10);
        paperBook.setAuthor("Jens");  //POLY !!! WHATEVER I GIVE CLASS IT TRANSFORMS EITHER TO THE BOOK OR THE PAPERBOOK!!!  magic...
        
        em.persist(paperBook);
        
        
        // Removing from the DB
        
        PaperBook paperBook1 = em.find(PaperBook.class, 1);   // im also finding stuff here
        em.remove(paperBook1);
        
        //merge
        
        EBook eBook = new EBook();
        
        eBook.setAuthor("The Rock");
        em.merge(eBook);
        eBook.setAuthor("Zukee");
        em.merge(eBook);
        
        // Find
        
        Book book = em.find(Book.class, 1);
        System.out.println(book.getAuthor());
        
        
        
        
        
        
        
        em.getTransaction().commit();
        em.close();
        
    }
}
