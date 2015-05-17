package checking.factory;

import checking.strategy.*;

public class StrategyFactory {

	public static Strategy getStrategyInstance(String type) {
		if ("exception".equals(type))
			return ExceptionStrategy.getInstance();
		else if ("initException".equals(type))
			return InitExceptionStrategy.getInstance();
		else if ("unreachable".equals(type))
			return UnreachableStrategy.getInstance();
		else
			return null;
	}

}
