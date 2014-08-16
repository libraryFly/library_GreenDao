package com.example.test_android_green_dao.dao;

import com.example.test_android_green_dao.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table STUDENTS.
 */
public class Student {

    private Long id;
    private String name;
    private java.util.Date birthday;
    private Long teacher_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient StudentDao myDao;

    private Techer techer;
    private Long techer__resolvedKey;


    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String name, java.util.Date birthday, Long teacher_id) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.teacher_id = teacher_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    /** To-one relationship, resolved on first access. */
    public Techer getTecher() {
        Long __key = this.teacher_id;
        if (techer__resolvedKey == null || !techer__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TecherDao targetDao = daoSession.getTecherDao();
            Techer techerNew = targetDao.load(__key);
            synchronized (this) {
                techer = techerNew;
            	techer__resolvedKey = __key;
            }
        }
        return techer;
    }

    public void setTecher(Techer techer) {
        synchronized (this) {
            this.techer = techer;
            teacher_id = techer == null ? null : techer.getId();
            techer__resolvedKey = teacher_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
