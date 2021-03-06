package com.upgrade.bean;

import javax.persistence.Version;

public class BaseBean {
	@Version
	protected int version ;

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
