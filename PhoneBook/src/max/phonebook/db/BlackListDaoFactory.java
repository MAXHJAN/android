package max.phonebook.db;

import android.content.Context;

public class BlackListDaoFactory {

	private static BlackListDao mBlackListDao;

	public static synchronized BlackListDao getBlackListDao(Context context) {

		if (mBlackListDao == null)
			mBlackListDao = new BlackListDaoImpl(context);
		return mBlackListDao;
	}
}
