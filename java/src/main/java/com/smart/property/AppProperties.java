package com.smart.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

	private boolean isProd;
	private String prodHost;
	private String uiHost;
	private String serverHost;
	private String domain;
	
	
	public boolean isProd() {
		return isProd;
	}
	public void setProd(boolean isProd) {
		this.isProd = isProd;
	}
	public String getProdHost() {
		return prodHost;
	}
	public void setProdHost(String prodHost) {
		this.prodHost = prodHost;
	}
	public String getUiHost() {
		return uiHost;
	}
	public void setUiHost(String uiHost) {
		this.uiHost = uiHost;
	}
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}
