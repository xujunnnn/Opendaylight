package Vtn;

public class VtnUtilTest {
	public static void main(String args[]){
		for(Mac_Mapped_Host m:VTNUtil.readMac_Map("Tenant3", "vBridge2")){
			System.out.println(m.getMac_address());
		}
		
	}

}
