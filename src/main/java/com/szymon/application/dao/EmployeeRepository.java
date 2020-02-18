package com.szymon.application.dao;

import com.szymon.application.model.Address;
import com.szymon.application.model.Employee;
import com.szymon.application.model.MapperClass;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.*;

import static com.szymon.application.dao.utility.UtilityDao.findOrEmpty;

@Repository
public class EmployeeRepository implements IEmployeeDao{

    private static Date date_example;

    public EmployeeRepository() {
    }

    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    public void saveWithoutTransaction() {
        Session session = entityManager.unwrap(Session.class);
        Employee emp = getTestEmployee();
        long id = (Long) session.save(emp);
        System.out.println("1. Employee save called without transaction, id="+id);
//        session.flush(); //address will not get saved without this
        System.out.println("*****");

        final org.hibernate.engine.spi.SessionImplementor session1 = entityManager.unwrap( org.hibernate.engine.spi.SessionImplementor.class );
        final org.hibernate.engine.spi.PersistenceContext pc = session1.getPersistenceContext();
        final Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = pc.reentrantSafeEntityEntries();

    }

    public void saveWithTransaction() {
        Session session = entityManager.unwrap(Session.class);

        Employee emp6 = session.load(Employee.class, new Long(3));

        System.out.println("Employee Details="+emp6);
        emp6.setName("New Name");
        emp6.getAddress().setCity("New City");

        long id6 = (Long) session.save(emp6);
        emp6.setName("New Name1"); // will get updated in database
        System.out.println("5. Employee save called with transaction, id="+id6);
        System.out.println("*****");

        final org.hibernate.engine.spi.SessionImplementor session1 = entityManager.unwrap( org.hibernate.engine.spi.SessionImplementor.class );
        final org.hibernate.engine.spi.PersistenceContext pc = session1.getPersistenceContext();
        final Map.Entry<Object,org.hibernate.engine.spi.EntityEntry>[] entityEntries = pc.reentrantSafeEntityEntries();
    }

    public Employee getByIdCriteria(Long id) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery criteria = cb.createQuery(Employee.class);
        Root<Employee> i = criteria.from(Employee.class);

        ParameterExpression<Long> idParameter = cb.parameter(Long.class);

        TypedQuery<Employee> query = entityManager.createQuery(criteria.select(i).where(
                cb.equal(
                        i.get("id"),
                        idParameter
                )
        )).setParameter(idParameter, id);

        return query.getSingleResult();
    }

    public Employee getByTimestampCriteria(Date date) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery criteria = cb.createQuery(Employee.class);
        Root<Employee> i = criteria.from(Employee.class);

        ParameterExpression<Date> dateParameter = cb.parameter(Date.class);

        TypedQuery<Employee> query = entityManager.createQuery(criteria.select(i).where(
                cb.notEqual(
                        i.get("date"),
                        dateParameter
                )
        )).setParameter(dateParameter, date, TemporalType.TIMESTAMP);

        return query.getSingleResult();
    }

    // Lepiej zawsze dodawac blok try_catch bo wyrzuca wyjatek jak nie znajdzie (tylko przy getSingleresult())
    public Optional<Employee> getSingleResult(Long id) {
//        try{
//            TypedQuery<Employee> query = entityManager.createQuery("select i from Employee i where i.id = :id", Employee.class)
//                    .setParameter("id", id);
//
//            return Optional.ofNullable(query.setMaxResults(1).getSingleResult());
//        }catch (NoResultException e) {
//            System.out.println("Nie znaleziono pojedynczego wyniku");
//            return Optional.empty();
//        }

        // LUB
            return entityManager.createQuery("from Employee i where i.id = :id", Employee.class)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .getResultList()
                    .stream()
                    .findFirst();
    }

    public Optional<Employee> findBySingleResult(Date date) {
        return findOrEmpty(() ->
                entityManager.createQuery("select e from com.szymon.application.model.Employee e where e.date = :date", Employee.class)
                        .setParameter("date", date, TemporalType.TIMESTAMP)
                        .setMaxResults(1)
                        .getSingleResult());
    }

    public Map<String, String> getCityAndEmployeeNameByJoin(Long id) {
        Session session = entityManager.unwrap(Session.class);

        String sql = "SELECT a.city, e.name FROM Employee e inner JOIN e.address a WHERE e.id = :id";

        Query<Object[]> query = session.createQuery(sql);
        query.setParameter("id", id);

        Map<String,String> result = new HashMap<>();
        for (Object[] data: query.list()) {
            result.put((String) data[0],(String) data[1]);
        }

        return result;
    }

    public List<MapperClass> getMapperClass() {
        Session session = entityManager.unwrap(Session.class);
        Long id = 3L;
        String sql = "SELECT new com.szymon.application.model.MapperClass(a.zipcode, a.city, e.id) FROM Employee e inner JOIN e.address a WHERE e.id = :id";

        return session.createQuery(sql, MapperClass.class).setParameter("id", id).list();
    }

//    public List<MapperClassExample> getMapperClassInnerClass() {
//        Session session = entityManager.unwrap(Session.class);
//        Long id = 3L;
//        String sql = "SELECT new MapperClassExample(a.zipcode, a.city, e.id) FROM Employee e inner JOIN e.address a WHERE e.id = :id";
//
////        List<IEmployeeDao.MapperClassExample> f = new ArrayList<>();
////        f.add(new MapperClassExample());
//
//
//
//
//        return session.createQuery(sql, IEmployeeDao.MapperClassExample.class).setParameter("id", id).list();
//    }

    public static Employee getTestEmployee() {
        date_example = new Date();
        Employee emp = new Employee();
        Address add = new Address();
        add.setId(3L);
        emp.setName("Test Emp");
        emp.setSalary(1000);
        emp.setDate(date_example);
        add.setAddressLine1("Test address1");
        add.setCity("Test City");
        add.setZipcode("12121");
        emp.setAddress(add);
        add.setEmployee(emp);
        return emp;
    }
}
