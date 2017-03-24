package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Ip_Match {
	@JSONField(name="ip-protocol")
private String ip_protocol;
	@JSONField(name="ip-dscp")
private String ip_dscp;
	@JSONField(name="ip-ecn")
private String ip_ecn;
	@JSONField(name="ip-proto")
private String ip_proto;


public String getIp_protocol() {
		return ip_protocol;
	}

	public void setIp_protocol(String ip_protocol) {
		this.ip_protocol = ip_protocol;
	}

	public String getIp_dscp() {
		return ip_dscp;
	}

	public void setIp_dscp(String ip_dscp) {
		this.ip_dscp = ip_dscp;
	}

	public String getIp_ecn() {
		return ip_ecn;
	}

	public void setIp_ecn(String ip_ecn) {
		this.ip_ecn = ip_ecn;
	}

	public String getIp_proto() {
		return ip_proto;
	}

	public void setIp_proto(String ip_proto) {
		this.ip_proto = ip_proto;
	}

public Ip_Match(){}

/**
 * 
 * @param protocol
 * @param dscp
 * @param ecn
 * @param proto
 */
public Ip_Match(String protocol,String dscp,String ecn,String proto){
	if(protocol!=null)
		this.ip_protocol=protocol;
	
	if(proto!=null)
		this.ip_proto=proto;
	if(dscp!=null)
		this.ip_dscp=dscp;
	if(ecn!=null)
		this.ip_ecn=ecn;
}
}
