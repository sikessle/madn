package de.htwg.madn.model.hibernate;

import de.htwg.madn.model.AbstractDefaultModelModule;
import de.htwg.madn.model.IModelDao;

public class HibernateModelModule extends AbstractDefaultModelModule {

	@Override
	protected void configure() {
		super.configure();
		bind(IModelDao.class).to(HibernateModelDao.class);
	}
}
