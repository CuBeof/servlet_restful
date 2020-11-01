package nissan.procurement.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;

import nissan.procurement.model.UserModel;
import nissan.procurement.utils.Hibernate;

/**
 * CRUD database operations
 *
 */
public class UserDAO {

    /**
     * Save User
     * @param user
     */
    public boolean validate(String username, String password) {
        Transaction transaction = null;
        List<UserModel> listUser = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            String SQL_QUERY ="from user as o where o.userName=? and o.userPassword=?";
            Query query = session.createQuery(SQL_QUERY);
			query.setParameter(0,username);
			query.setParameter(1,password);
			listUser = query.list();
			
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return (listUser!=null && listUser.size() == 1)?true:false;
    }
    /**
     * Save User
     * @param user
     */
    public void saveUser(UserModel user) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Update User
     * @param user
     */
    public void updateUser(UserModel user) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Delete User
     * @param id
     */
    public void deleteUser(int id) {

        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a user object
            UserModel user = session.get(UserModel.class, id);
            if (user != null) {
                session.delete(user);
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get User By ID
     * @param id
     * @return
     */
    public UserModel getUser(int id) {

        Transaction transaction = null;
        UserModel user = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.get(UserModel.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get all Users
     * @return
     */
    public String  getAllUser() {

        Transaction transaction = null;
        String listOfUser = "";
        Gson gson = new Gson();
		try (Session session = Hibernate.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            
            listOfUser = gson.toJson(session.createCriteria(UserModel.class).list());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfUser;
    }
}