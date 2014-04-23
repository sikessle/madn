package de.htwg.madn.model.couchdb;

import de.htwg.madn.model.AbstractDefaultModelModule;
import de.htwg.madn.model.IModelDao;

public class CouchDbModelModule extends AbstractDefaultModelModule {
	
	@Override
	protected void configure() {
		super.configure();
		bind(IModelDao.class).to(CouchDbModelDao.class);
	}
	
}
