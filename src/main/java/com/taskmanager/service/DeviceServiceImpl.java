package com.taskmanager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.model.Category;
import com.taskmanager.model.Device;
import com.taskmanager.repository.CategoryRepository;
import com.taskmanager.repository.DeviceRepository;


@Service
public class DeviceServiceImpl implements DeviceService{

	private DeviceRepository deviceRepository;
	private CategoryRepository categRepo;

	 @Autowired
	    public DeviceServiceImpl(DeviceRepository deviceRepository,CategoryRepository categRepo) {
	        this.deviceRepository= deviceRepository;
	        this.categRepo=categRepo;
	    }
	 
	 @Override
	    public Device createDevice(Device device) {
		 Category categ = categRepo.findByCategory("porolto");
		 device.setCategory(new ArrayList<>(Collections.singletonList(categ)));
	        return deviceRepository.save(device);
	    } 
	 
	 @Override
	    public List<Device> findAll() {
	        return deviceRepository.findAll();
	 }
	
}
