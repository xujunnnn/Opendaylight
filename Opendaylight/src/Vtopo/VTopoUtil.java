package Vtopo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VTopoUtil {
	private static Map<String, Integer> vlan_idMap=new ConcurrentHashMap<String, Integer>();
	public static int get_Vlan(String vtopo){
		return vlan_idMap.get(vtopo);
	}
	
	
	public static void initVlan(String vtopo){
		vlan_idMap.put(vtopo, 1);
	}

	
}
