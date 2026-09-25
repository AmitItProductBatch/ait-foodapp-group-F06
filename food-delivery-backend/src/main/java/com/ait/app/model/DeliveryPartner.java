package com.ait.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class DeliveryPartner {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String name;
private long mobileNo;
private String status;
private String email;
private String vechicleType;
private boolean active;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(long mobileNo) {
	this.mobileNo = mobileNo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getVechicleType() {
	return vechicleType;
}
public void setVechicleType(String vechicleType) {
	this.vechicleType = vechicleType;
}
public boolean isActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public LocalDateTime getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}
public LocalDateTime getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}


}
