package com.shingo.dao;
/*
 * Caracter�sticas de HQL

Estas son algunas de las caracter�sticas m�s importantes que nos proporciona HQL:

   * Soporte completo para operaciones relacionales: HQL permite representar consultas SQL en 
     forma de objetos. HQL usa clases y atributos o propiedades en vez de tablas y columnas.
   * Regresa sus resultados en forma de objetos: Las consultas realizadas usando HQL regresan los 
     resultados de las mismas en la forma de objetos o listas de objetos,
     que son m�s f�ciles de usar, ya que eliminan la necesidad de crear un objeto y
     llenarlo con los datos obtenidos de un ResultSet (como hacemos normalmente cuando 
     trabajamos con JDBC).
   * Consultas Polim�rficas: Podemos declarar el resultado usando el tipo de la superclase
     y Hibernate se encargara de crear los objetos adecuados d
     e las subclases correctas de forma autom�tica.
   * F�cil de Aprender: Es muy similar a SQL est�ndar, as� que si has trabajado con SQL,
     HQL te resultar� muy f�cil.
   * Soporte para caracter�sticas avanzadas: HQL contiene muchas caracter�sticas avanzadas
     que son muy �tiles y que no siempre est�n presentes en todas las bases de datos,
     o no es f�cil usarlas, como paginaci�n, fetch joins con perfiles din�micos, inner
     y outer joins, etc. Adem�s soporta proyecciones, funciones de agregaci�n (max, avg), y agrupamientos, ordenamientos, y subconsultas.
   * Independiente del manejador de base de datos: Las consultas escritas en HQL son 
    independientes de la base de datos (siempre que la base de dats soporte la caracter�stica 
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

	// SessionFactory Es el responsable de crear la sesi�n a trav�s de
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
