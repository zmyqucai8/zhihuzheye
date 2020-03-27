package com.zmy.zhihuzheye.utils.dao

import GreenDao.NewsTableDao
import com.zmy.zhihuzheye.base.BaseApp

/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-27 09:59
 * @Notice 无
 */
class DaoUtils {


    companion object {


        /**
         * 插入一条新闻
         */
        open fun insertNews(newsTable: NewsTable) {
            BaseApp().getDaoInstant().newsTableDao.insertOrReplace(newsTable)
        }

        /**
         * 更新新闻数据
         */
        open fun insertNews(newsTable: List<NewsTable>) {
            BaseApp().getDaoInstant().newsTableDao.insertOrReplaceInTx(newsTable)
        }

        /**
         * 根据新闻类型查询
         * 根据时间进行排序
         * 直接查询所有数据，因为是demo懒得考虑那么多
         */
        open fun queryNews(type: String): MutableList<NewsTable> {

            return BaseApp().getDaoInstant().newsTableDao.queryBuilder()
                .where(NewsTableDao.Properties.Category.eq(type))
                .orderDesc(NewsTableDao.Properties.Time)
                .build().list()
        }
    }

}