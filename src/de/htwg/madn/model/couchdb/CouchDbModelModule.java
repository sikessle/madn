package de.htwg.madn.model.couchdb;

import com.google.inject.name.Names;

import de.htwg.madn.model.AbstractDefaultModelModule;
import de.htwg.madn.model.IModelDao;

public class CouchDbModelModule extends AbstractDefaultModelModule {

	@Override
	protected void configure() {
		super.configure();
		bindConstant().annotatedWith(Names.named("couchDBServerUrl")).to(
				"http://lenny2.in.htwg-konstanz.de:5984");
		bind(IModelDao.class).to(CouchDbModelDao.class);
	}

}
