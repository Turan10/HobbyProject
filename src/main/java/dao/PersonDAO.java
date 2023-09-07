package dao;

import config.HibernateConfig;
import dto.PersonInfoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import model.Person;

import java.util.List;

public class PersonDAO {

    private static PersonDAO instance;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }


    public List<Person> getPersonsByHobby(String hobbyName) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobbyName", Person.class);
            query.setParameter("hobbyName", hobbyName);
            return query.getResultList();
        }
    }

    public Person getPersonInfoByPhoneNumber(String number) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :number", Person.class);
            query.setParameter("number", number);
            return query.getSingleResult();
        }
    }


    public void addPerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
    }

    public void addPersonAddress(Address address) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        }
    }

    public void deleteByPersonId(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
        }
    }



    public Person updatePerson(Person person) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Person person1 = em.merge(person);
            em.getTransaction().commit();
            return person1;
        }
    }

    public Person getPersonById(int Id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Person.class, Id);
        }
    }

    public Person getPersonByName(String name) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.name = :name", Person.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }
    }

        public List<Person> findPersonsByCityZip(int zip) {
            try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.address.city.zip = :zip", Person.class);
            query.setParameter("zip", zip);
            return query.getResultList();
        }
    }

    public void showAllPersonInfo(String name) {
        Person person = this.getPersonByName(name);

        if (person != null) {
            System.out.println("Person Details:");
            System.out.println("ID: " + person.getId());
            System.out.println("First Name: " + person.getFirstName());
            System.out.println("Last Name: " + person.getLastName());
            System.out.println("Age: " + person.getAge());
            System.out.println("Last Edited: " + person.getLastEdited());


            Address address = person.getAddress();
            if (address != null) {
                System.out.println("Address: " + address.getStreet());
                System.out.println("City: " + address.getCity().getCityName());
            }


            Set<Hobby> hobbies = person.getHobbies();
            if (!hobbies.isEmpty()) {
                System.out.println("Hobbies:");
                for (Hobby hobby : hobbies) {
                    System.out.println(hobby.getName());
                }
            }

            Set<Phone> phones = person.getPhones();
            if (!phones.isEmpty()) {
                System.out.println("Phones:");
                for (Phone phone : phones) {
                    System.out.println(phone.getNumber());
                }
            }
        } else {
            System.out.println("Person not found.");
        }
    }

}



















