package NetWork_Topology;

import java.util.HashSet;
import java.util.Set;

public class TopoUtilTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Set<Host> hosts=TopoUtil.get_hosts();
	for(Host h:hosts){
		System.out.println(h.getHost_name());
	}
	HashSet<Node> nodes=TopoUtil.get_access_node();
	for(Node n:nodes){
		System.out.println(n.getNode_id());
	}
	HashSet<Termination_point> ts=TopoUtil.get_ports_to_host();
	for(Termination_point t:ts){
		System.out.println(t.getTp_id());
	}
	Node node=new Node();node.setNode_id("openflow:1");
	HashSet<Termination_point> tsnode=TopoUtil.get_ports_to_host(node);
	for(Termination_point t:tsnode){
		System.out.println(t.getTp_id());
	}
	}

}
