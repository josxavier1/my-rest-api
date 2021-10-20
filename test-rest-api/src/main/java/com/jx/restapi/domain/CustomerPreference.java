package com.jx.restapi.domain;

public class CustomerPreference {

	private boolean taxBlockIndicator;
	
	private String preferredShipMode;

	public boolean isTaxBlockIndicator() {
		return taxBlockIndicator;
	}

	public void setTaxBlockIndicator(boolean taxBlockIndicator) {
		this.taxBlockIndicator = taxBlockIndicator;
	}

	public String getPreferredShipMode() {
		return preferredShipMode;
	}

	public void setPreferredShipMode(String preferredShipMode) {
		this.preferredShipMode = preferredShipMode;
	}
	
	
}
