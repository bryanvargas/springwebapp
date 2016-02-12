package com.shingo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shingo.dao.AdminDao;
import com.shingo.dao.DireccionDao;
import com.shingo.pojo.Admin;
import com.shingo.pojo.Direccion;

@Service
public class DireccionService {
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private DireccionDao direccionDao;

	public void save(Admin admin, Direccion direccion) {
		direccion.setAdmin(admin);
		direccionDao.save(direccion);
	}

	
	//Hibernate no trabaja con id, sino con instacias de la clase mapeada
	public List<Direccion> findAll(int id) {
		Admin admin = adminDao.findById(id);
		return direccionDao.findAll(admin);
	}


	public void delete(int idDir) {
		Direccion dir = direccionDao.finById(idDir);
		direccionDao.delete(dir);
		
	}


}
