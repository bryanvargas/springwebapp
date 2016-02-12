package com.shingo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shingo.pojo.Admin;
import com.shingo.service.AdminService;

//Todols los daos deben ser transaccionales, deotra forma daran algunos erroes a la 
//hora de ejecutar algunos poryectos
//Tambien hay que anotar @Repository, para indicarle a Hibernate que se trata de un DAO
@Transactional
@Repository
public class AdminDaoImpl implements AdminDao {
	
	//@Autowired, para que haga la inyeccion de dependencias de spring y nos regrese una instancia
	//lista con las propiedades para ser utilizadas en el dao
	//SessionFactory Es el responsable de crear la sesión a través de
		//la cual Hibernate realizara las transacciones y accesos de datos pertinentes.
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(Admin admin) {
		getSession().save(admin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAll() {
		//se realizara consulta con HQL
		Query query = getSession().createQuery("from Admin"); //MSQL: selecto * from Admin
		return query.list();
	}

	@Override
	public Admin findById(int id) {
		//utilizndo CRITERIAL
		Criteria crit  = getSession().createCriteria(Admin.class);
//		eqProperty: no nos pide la propiedad de la base de datos
//		sino la propiedad del POJO Admin, que esta mapeada con las anotaciones
//		JPA, y como se tiene los nombres identicos no hay muucha relevancia,
//		recuerda que hibarnate no trabaja con la base de datos,
//		trabaja con la clase ANOTADA con las anotacines JPA que se colocarton en la clase Admin.java,
		//debido a mapping ORM
		crit.add(Restrictions.eq("idAd", id));
//		crit.uniqueResult() nos devolvera un solo resultado
		return (Admin) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findByNombre(String nombre) {
		Criteria crit = getSession().createCriteria(Admin.class);
		crit.add(Restrictions.like("nombre", "%" + nombre + "%"));
		return crit.list();
	}

	@Override
	public List<Admin> finByIdNombre(int id, String nombre) {
		return null;
	}

	@Override
	public void update(Admin admin) {
		getSession().update(admin);
	}

	@Override
	public void delete(Admin admin) {
		getSession().delete(admin);
	}

}
