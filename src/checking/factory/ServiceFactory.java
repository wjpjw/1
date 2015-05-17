package checking.factory;

import checking.service.CheckingService;

public class ServiceFactory {

	public static CheckingService getServiceInstance()
	{
		return CheckingService.getInstance();
	}
	
}
