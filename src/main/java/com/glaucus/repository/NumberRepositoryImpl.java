package com.glaucus.repository;

import com.glaucus.model.Numbers;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class NumberRepositoryImpl implements NumberRepository{
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public boolean incrementNumber(int id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            // Using locking on table row so that no other resource can even read the value of the row
            Numbers numbers=session.get(Numbers.class,id, LockMode.PESSIMISTIC_WRITE);
            if(numbers!=null){
                //Incrementing the value of number and updating it into Table
                numbers.setValue(numbers.getValue()+1);
                session.saveOrUpdate(numbers);
                return true;
            }else{
                return false;
            }
        }catch (JDBCException sql){
            System.out.println("SQL Exception Occurred" + sql.getSQLException());
            return false;
        }
    }

    @Transactional
    public Numbers getNumber(int id) {
     try{
         Session session = sessionFactory.getCurrentSession();
         Numbers numbers=session.get(Numbers.class,id,LockMode.PESSIMISTIC_WRITE);
         if(numbers!=null){
             System.out.println("Numbers: " + numbers.getValue());
             return numbers;
         }else{
             //Adding a row in database if no row is present
             Numbers new_numbers = new Numbers();
             new_numbers.setValue(0);
             addNumberRow(new_numbers);
             return new_numbers;
         }
    }catch (JDBCException sql){
         System.out.println("SQL Exception Occurred" + sql.getSQLException());
         return null;
     }
    }

    @Override
    public void addNumberRow(Numbers numbers) {
        sessionFactory.getCurrentSession().save(numbers);
    }
}
