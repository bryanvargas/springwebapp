package com.shingo.dao;

import java.util.List;

import com.shingo.pojo.Admin;

public interface AdminDao {
	public boolean save(Admin admim);
	public List<Admin> findAll();
	public Admin findById(int id);
	public List<Admin> findByNombre(String nombre);
	//metodo creado por shingo para ve como manejar varias variables de busqueda
	public List<Admin> finByIdNombre(int id, String nombre);
	
	public boolean update(Admin admin);
	public boolean delete(int idAd);
	public int[] saveAll(List<Admin> admins);
}
