package Exception;

import java.io.IOException;

public class FlowFailException extends Exception {
	public FlowFailException(){}
	
	public FlowFailException(String gripe){
		super(gripe);
	}
}
