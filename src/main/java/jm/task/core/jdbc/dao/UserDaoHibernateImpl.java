package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = Util.getInstance().getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS `katappfirst`.`user` (\n" +
                    "                      `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                      `name` VARCHAR(45) NULL,\n" +
                    "                      `lastname` VARCHAR(45) NULL,\n" +
                    "                      `age` TINYINT NULL,\n" +
                    "                      PRIMARY KEY (`id`));");
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS `katappfirst`.`user`;");
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }


    }

    @Override
    public List<User> getAllUsers() {
        Transaction tx = null;
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            users = session.createQuery("from User").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
