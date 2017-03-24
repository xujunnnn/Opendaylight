package Vtopo;

import java.util.HashSet;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import Exception.Mac_MapFailException;
import Exception.VbridgeFailExpection;
import NetWork_Topology.Host;
import NetWork_Topology.TopoUtil;
import Vtn.Allowed_Hosts;
import Vtn.Mac_Map;
import Vtn.Mac_Vlan;
import Vtn.OperationType;
import Vtn.UpDate_Mode;
import Vtn.Vbridge;

public class VGroup {
	private HashSet<String> host_names=new HashSet<String>();
	private String group_id;
	private String vtoponame;
	public VGroup(){}
	
	public String getVtoponame() {
		return vtoponame;
	}

	public VGroup setVtoponame(String vtoponame) {
		this.vtoponame = vtoponame;
		return this;
	}

	/**
	 * constructor
	 * @param host_names
	 */
	public VGroup(HashSet<String> host_names){
		this.host_names=host_names;
	}
	/**
	 * add a host to this group
	 * @param host_name
	 * @return
	 */
	public VGroup addHost(String host_name){
		this.host_names.add(host_name);
		return this;
	}
	public VGroup addGroup(VGroup group){
		this.host_names.addAll(group.getHost_names());
		return this;
	}
	
	public HashSet<String> getHost_names() {
		return host_names;
	}

	public VGroup setHost_names(HashSet<String> host_names) {
		this.host_names = host_names;
		return this;
	}

	public String getGroup_id() {
		return group_id;
	}

	public VGroup setGroup_id(String group_id) {
		this.group_id = group_id;
		return this;
	}
    /**
     * create the VGroup
     * @throws VbridgeFailExpection
     * @throws Mac_MapFailException
     */
	public void create() throws VbridgeFailExpection, Mac_MapFailException{
		Vbridge vbridge=new Vbridge();
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		for(String name:host_names){
			allowed_Hosts.addHost(TopoUtil.get_host_from_name(name).getMac()+"@"+"0");
			}
		vbridge.setTenant_name(vtoponame)
			   .setBridge_name(group_id)
			   .setUpdate_mode(UpDate_Mode.CREATE)
			   .setOperation(OperationType.SET)
			   .Set_Mac_Map(allowed_Hosts, null)
			   .send();
				
			   
	}
	
	
	public static void main(String args[]){
		VGroup vGroup=new VGroup().setGroup_id("g1");
		vGroup.addHost("host1")
			  .addHost("host2")
			  .addHost("host3");
		JSONObject json=(JSONObject)JSONObject.toJSON(vGroup);
		System.out.println(json.toJSONString());
		
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.host_names,this.group_id);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj==null)
			return false;
		if(this==obj)
			return true;
		if(this.getClass()!=obj.getClass())
			return false;
		VGroup other=(VGroup) obj;
		return Objects.equals(this.host_names, other.getHost_names()) && Objects.equals(this.group_id, other.getGroup_id());
	}
}
