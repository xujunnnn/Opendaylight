package Flow_Filter;

import java.util.ArrayList;

public class filter_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Vtn_flow_match vtn_flow_match1=new Vtn_flow_match();
       vtn_flow_match1.setIndex(1).setSrcip("10.0.0.1/32").setDestip("10.0.0.2/32");
       ArrayList<Vtn_flow_match> flow_matchs=new ArrayList<>();
       flow_matchs.add(vtn_flow_match1);
       Flow_match flow_filter=new Flow_match();
       System.out.println(flow_filter.setName("cond1").setFlow_matchs(flow_matchs).build());
	}

}
