package checking.factory;

import checking.service.Service;

public class ServiceFactory {

	public static Service getServiceInstance()
	{
		return Service.getInstance();
	}
	
}
