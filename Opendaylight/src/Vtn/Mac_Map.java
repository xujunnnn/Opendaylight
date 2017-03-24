package Vtn;

import java.util.HashSet;

import com.alibaba.fastjson.annotation.JSONField;

public class Mac_Map {
	@JSONField(name="tenant-name")
	private String tenant_name;
	@JSONField(name="bridge-name")
	private String bridge_name;
	@JSONField(name="allowed-hosts")
	private HashSet<String> allowed_hosts;
	@JSONField(name="denied-hosts")
	private HashSet<String> denyed_Hosts;
	public HashSet<String> getAllowed_hosts() {
		return allowed_hosts;
	}
	public Mac_Map setAllowed_hosts(HashSet<String> allowed_hosts) {
		this.allowed_hosts = allowed_hosts;
		return this;
	}
	public HashSet<String> getDenyed_Hosts() {
		return denyed_Hosts;
	}
	public Mac_Map setDenyed_Hosts(HashSet<String> denyed_Hosts) {
		this.denyed_Hosts = denyed_Hosts;
		return this;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public Mac_Map setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public String getBridge_name() {
		return bridge_name;
	}
	public Mac_Map setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
		return this;
	}
	

}