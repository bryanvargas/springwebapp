package com.shingo.service;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shingo.dao.AdminDao;
import com.shingo.pojo.Admin;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public boolean save(Admin admin){
		Timestamp ts = new Timestamp(new Date().getTime());
		admin.setFechaCreacion(ts);
		return adminDao.save(admin);
	}

	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	public boolean saveOrUpdate(Admin admin) {
		if(admin.getIdAd() == 0){
			//salvar el requistro
			Timestamp ts = new Timestamp(new Date().getTime());
			admin.setFechaCreacion(ts);
			return adminDao.save(admin);
		}else {
			return adminDao.update(admin);
		}	
	}

	public boolean delete(int id) {
		return adminDao.delete(id);
	}
}
