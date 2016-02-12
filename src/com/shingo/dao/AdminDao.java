package com.shingo.dao;

import java.util.List;

import com.shingo.pojo.Admin;

public interface AdminDao {
	public void save(Admin admim);
	public List<Admin> findAll();
	public Admin findById(int id);
	public List<Admin> findByNombre(String nombre);
	//metodo creado por shingo para ve como manejar varias variables de busqueda
	public List<Admin> finByIdNombre(int id, String nombre);
	
	public void update(Admin admin);
	public void delete(Admin admin);
}
