package com.shingo.dao;

import java.util.List;

import com.shingo.pojo.Admin;
import com.shingo.pojo.Direccion;

public interface DireccionDao {
	public void save(Direccion direccion);
	public List<Direccion> findAll(Admin admin);
	public void delete(Direccion dir);
	public Direccion finById(int idDir);
}
