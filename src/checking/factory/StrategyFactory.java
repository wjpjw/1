package checking.factory;

import checking.strategy.*;

public class StrategyFactory {

	public static ExceptionStrategy getExceptionStrategyInstance() {
		return ExceptionStrategy.getInstance();
	}

	public static UnreachableStrategy getUnreachableStrategyInstance() {
		return UnreachableStrategy.getInstance();
	}

	public static InitExceptionStrategy getInitExceptionStrategyInstance() {
		return InitExceptionStrategy.getInstance();
	}

}
