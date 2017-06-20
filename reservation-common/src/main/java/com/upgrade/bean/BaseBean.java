package com.upgrade.bean;

import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
public class BaseBean {
	@Version
	private int version ;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BaseBean [version=" + version + "]";
	} 
}
