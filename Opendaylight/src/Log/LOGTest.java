package Log;

import org.apache.log4j.Logger;

public class LOGTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger=Logger.getLogger(LOGTest.class.getName());
		
        logger.debug("debug");
        logger.error("error");
	}

}
