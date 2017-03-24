package NetWork_Topology;

import java.util.HashSet;
import java.util.HashSet;
import java.util.Set;

public class TopoUtil {
	private static Topology topology=new Topology().update();

	public static HashSet<Node> get_access_node(){
			topology.update();
			HashSet<Node> Accessnodes=new HashSet<>();
			HashSet<String> AccessStringnodes=new HashSet<String>();
			HashSet<Node> nodes=topology.getNodes();
			for(Node node:nodes){
				if(node.getNode_id().startsWith("host")){
					String node_connector=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
					String []nodeinfo=node_connector.split(":");
					String nodeid=nodeinfo[0]+":"+nodeinfo[1];
					
					
					if(!AccessStringnodes.contains(nodeid)){
						
						AccessStringnodes.add(nodeid);
						
					}
				}
				
			}
			for(String nodeid:AccessStringnodes){
				Node node2=new Node();
				node2.setNode_id(nodeid);
				Accessnodes.add(node2);
				
			}
			return Accessnodes;
		}
		/**
		 * get inner links
		 * @return
		 */
		public static HashSet<Link> get_inner_link(){
			topology.update();
			HashSet<Link> inner_links=new HashSet<>();
			for(Link link:topology.getLinks()){
					if((link.getSource().getSource_node().startsWith("openflow")) && (link.getDestination().getDest_node().startsWith("openflow"))){
					inner_links.add(link);
				}
			}
			return inner_links;
		}
		/**
		 * get node which is a openflow switch
		 * @return
		 */
		public static HashSet<Node> get_switch(){
			topology.update();
			HashSet<Node> switches=new HashSet<>();
			for(Node node:topology.getNodes()){
				if(node.getNode_id().startsWith("openflow")){
					switches.add(node);
				}
			}
			return switches;
		}
		/**
		 * get ports linked with host
		 * @return
		 */
		public static HashSet<Termination_point> get_ports_to_host(){
			
			HashSet<Termination_point>  ports_to_host=new HashSet<>();
			HashSet<Host> hosts=TopoUtil.get_hosts();
			for(Host host:hosts){
				Termination_point termination_point=new Termination_point();
				termination_point.setTp_id(host.getAccess_port());
				ports_to_host.add(termination_point);
			}
			return ports_to_host;
		}
		/**
		 * get ports linked with the switch
		 * @return
		 */
		public static HashSet<Termination_point> get_ports_to_switch(){
			HashSet<Termination_point> ports_to_switch=new HashSet<>();
			for(Link link:TopoUtil.get_inner_link()){
				Termination_point termination_point=new Termination_point();
				termination_point.setTp_id(link.getSource().getSource_tp());
			    ports_to_switch.add(termination_point);
			    Termination_point termination_point2=new Termination_point();
			    termination_point2.setTp_id(link.getDestination().getDest_node());
			}
			return ports_to_switch;
		}
		/**
		 * get ports links with the host of the specified node
		 * @param thenode
		 * @return
		 */
		public static HashSet<Termination_point> get_ports_to_host(Node thenode){
			HashSet<Termination_point>  ports_to_host=new HashSet<>();
			HashSet<Host> hosts=TopoUtil.get_hosts(thenode);
			for(Host host:hosts){
				Termination_point termination_point=new Termination_point();
				termination_point.setTp_id(host.getAccess_port());
				ports_to_host.add(termination_point);
			}
			return ports_to_host;
			
			
		}
		/**
		 * get  all hosts
		 * @return
		 */
	    public static HashSet<Host> get_hosts(){
	    	topology.update();
	    	HashSet<Host> hosts=new HashSet<Host>();
	    	for(Node node:topology.getNodes()){
	    		if(node.getNode_id().startsWith("host")){
	    			Host host=new Host();
	    			String port=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
	    			String []portinfo=port.split(":");
	    			String nodename=portinfo[0]+":"+portinfo[1];
	    			host.setMac(node.getHost_tracker_service_addresses().get(0).getMac())
	    				.setIp(node.getHost_tracker_service_addresses().get(0).getIp())
	    				.setAccess_node(nodename)
	    				.setAccess_port(port)
	    				.setHost_name(node.getNode_id());
	    			hosts.add(host);
	    		}
	    	}
	    	return hosts;
	    }
	    /**
	     * get host connected to this node
	     * @param node
	     * @return
	     */
	    public static HashSet<Host> get_hosts(Node thenode){
	    	topology.update();
	    	HashSet<Host> hosts=new HashSet<Host>();
	    	for(Node node:topology.getNodes()){
	    		if(node.getNode_id().startsWith("host")){
	    			Host host=new Host();
	    			String port=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
	    			String []portinfo=port.split(":");
	    			String nodename=portinfo[0]+":"+portinfo[1];
	    			host.setMac(node.getHost_tracker_service_addresses().get(0).getMac())
	    				.setIp(node.getHost_tracker_service_addresses().get(0).getIp())
	    				.setAccess_node(nodename)
	    				.setAccess_port(port)
	    				.setHost_name(node.getNode_id());
	    			if(thenode.getNode_id().equals(nodename))
	    			hosts.add(host);
	    		}
	    	}
	    	return hosts;
	    }
	    /**
	     * get host with the hostname
	     * @param hostA
	     * @return
	     */
	  public static Host get_host_from_name(String hostA){
		  topology.update();
	    	Host host=new Host();
	    	for(Node node:topology.getNodes()){
	    		if(node.getNode_id().equals(hostA)){
	    			String port=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
	    			String []portinfo=port.split(":");
	    			String nodename=portinfo[0]+":"+portinfo[1];
	    			host.setMac(node.getHost_tracker_service_addresses().get(0).getMac())
	    				.setIp(node.getHost_tracker_service_addresses().get(0).getIp())
	    				.setAccess_node(nodename)
	    				.setAccess_port(port)
	    				.setHost_name(node.getNode_id());
	    			break;	    		
	    		}
	    	}
	    	return host;
	  }

}
