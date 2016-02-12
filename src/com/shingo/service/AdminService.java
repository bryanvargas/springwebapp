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
	
	public void save(Admin admin){
		Timestamp ts = new Timestamp(new Date().getTime());
		admin.setFechaCreacion(ts);
		adminDao.save(admin);
	}

	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	public void saveOrUpdate(Admin admin) {
		if(admin.getIdAd() == 0){
			//salvar el requistro
			Timestamp ts = new Timestamp(new Date().getTime());
			admin.setFechaCreacion(ts);
			adminDao.save(admin);
		}else {
			adminDao.update(admin);
		}	
	}

	public void delete(int id) {
		Admin admin = adminDao.findById(id);
		 adminDao.delete(admin);
	}
}
