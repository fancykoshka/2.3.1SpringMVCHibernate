package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User user) {
        User existingUser = entityManager.find(User.class, id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setEmail(user.getEmail());
            entityManager.merge(existingUser);
        }
    }

        @Override
        public void deleteUserById ( int id){
            User userToDelete = entityManager.find(User.class, id);
            if (userToDelete != null) {
                entityManager.remove(userToDelete);}
        }
}


