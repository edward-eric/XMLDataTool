package org.data.xml.support.db;

public enum CredentialType {
	
	PRODUCTION, DEVELOPER, CUSTOMER;
	
	public String toString() {
		switch(this) {
		case PRODUCTION: return "Production";
		case DEVELOPER: return "Developer / QA";
		case CUSTOMER: return "Custom";
		default: return this.name();
		}
	}

}
