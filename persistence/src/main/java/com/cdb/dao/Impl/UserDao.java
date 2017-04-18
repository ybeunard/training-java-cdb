package com.cdb.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cdb.dao.InterfaceUserDao;
import com.cdb.model.entities.QUser;
import com.cdb.model.entities.User;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDao.
 */
public class UserDao implements InterfaceUserDao {

  private static QUser qUser = QUser.user;

  private SessionFactory sessionFactory;

  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional
  public User findByUserName(String username) {
    HibernateQueryFactory queryFactory = new HibernateQueryFactory(sessionFactory.openSession());
    List<User> users = new ArrayList<User>();

    users = queryFactory.select(qUser).from(qUser).where(qUser.username.eq(username)).fetch();

    if (users.size() > 0) {
      return users.get(0);
    } else {
      return null;
    }

  }

}
