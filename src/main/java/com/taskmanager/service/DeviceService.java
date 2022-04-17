package com.taskmanager.service;

import java.util.List;

import com.taskmanager.model.Device;

public interface DeviceService {

Device createDevice(Device device);
	
List<Device>findAll();
}
