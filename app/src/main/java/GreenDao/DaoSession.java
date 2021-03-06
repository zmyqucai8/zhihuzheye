package GreenDao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zmy.zhihuzheye.utils.dao.NewsTable;

import GreenDao.NewsTableDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig newsTableDaoConfig;

    private final NewsTableDao newsTableDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        newsTableDaoConfig = daoConfigMap.get(NewsTableDao.class).clone();
        newsTableDaoConfig.initIdentityScope(type);

        newsTableDao = new NewsTableDao(newsTableDaoConfig, this);

        registerDao(NewsTable.class, newsTableDao);
    }
    
    public void clear() {
        newsTableDaoConfig.clearIdentityScope();
    }

    public NewsTableDao getNewsTableDao() {
        return newsTableDao;
    }

}
