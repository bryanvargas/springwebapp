package com.shingo.dao;
/*
 * Características de HQL

Estas son algunas de las características más importantes que nos proporciona HQL:

   * Soporte completo para operaciones relacionales: HQL permite representar consultas SQL en 
     forma de objetos. HQL usa clases y atributos o propiedades en vez de tablas y columnas.
   * Regresa sus resultados en forma de objetos: Las consultas realizadas usando HQL regresan los 
     resultados de las mismas en la forma de objetos o listas de objetos,
     que son más fáciles de usar, ya que eliminan la necesidad de crear un objeto y
     llenarlo con los datos obtenidos de un ResultSet (como hacemos normalmente cuando 
     trabajamos con JDBC).
   * Consultas Polimórficas: Podemos declarar el resultado usando el tipo de la superclase
     y Hibernate se encargara de crear los objetos adecuados d
     e las subclases correctas de forma automática.
   * Fácil de Aprender: Es muy similar a SQL estándar, así que si has trabajado con SQL,
     HQL te resultará muy fácil.
   * Soporte para características avanzadas: HQL contiene muchas características avanzadas
     que son muy útiles y que no siempre están presentes en todas las bases de datos,
     o no es fácil usarlas, como paginación, fetch joins con perfiles dinámicos, inner
     y outer joins, etc. Además soporta proyecciones, funciones de agregación (max, avg), y agrupamientos, ordenamientos, y subconsultas.
   * Independiente del manejador de base de datos: Las consultas escritas en HQL son 
    independientes de la base de datos (siempre que la base de dats soporte la característica 
    que estamos intentando utilizar ^-^).
 */


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shingo.pojo.Admin;
import com.shingo.pojo.Direccion;

@Transactional
@Repository
public class DireccionDaoImpl implements DireccionDao {

	// SessionFactory Es el responsable de crear la sesión a través de
	// la cual Hibernate realizara las transacciones y accesos de datos
	// pertinentes.
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Direccion direccion) {
		getSession().save(direccion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Direccion> findAll(Admin admin) {
		// setResultTransformer devuelve la lista sin repetidos
		Criteria crit = getSession().createCriteria(Direccion.class)
				// esto lo que realiza es una reunion natural
				.setFetchMode("admin", FetchMode.JOIN).add(Restrictions.eq("admin.idAd", admin.getIdAd()))
				.setResultTransformer(Criteria.ROOT_ENTITY);
		return crit.list();
	}



	@Override
	public Direccion finById(int idDir) {
		Query query = getSession().createQuery("from Direccion d where d.idDir=:id");
		query.setParameter("id", idDir);
		return (Direccion) query.uniqueResult();
	}

	@Override
	public void delete(Direccion dir) {		
		getSession().delete(dir);
	}

}
