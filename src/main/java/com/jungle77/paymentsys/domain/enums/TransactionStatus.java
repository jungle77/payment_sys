package com.jungle77.paymentsys.domain.enums;
public enum TransactionStatus {

    APPROVED("APPROVED"), 
    REVERSED("REVERSED"), 
    REFUNDED("REFUNDED"), 
    ERROR("ERROR");

	private String name;

	private TransactionStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static TransactionStatus fromName(String shortName) {
		for (TransactionStatus m : values()) {
			if (m.name.equals(shortName)) {
				return m;
			}
		}
		throw new IllegalArgumentException(
				"Enum value is not found  " + shortName);
	}
}

