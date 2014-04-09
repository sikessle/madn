package de.htwg.madn.model.db4o;

import com.google.inject.name.Names;

import de.htwg.madn.model.AbstractDefaultModelModule;
import de.htwg.madn.model.IModelDao;

public class Db4oModelModule extends AbstractDefaultModelModule {

	@Override
	protected void configure() {
		super.configure();
		bindConstant().annotatedWith(Names.named("db4oPath")).to(
				"/tmp/madnDb4o");
		bind(IModelDao.class).to(Db4oModelDao.class);
	}
}
