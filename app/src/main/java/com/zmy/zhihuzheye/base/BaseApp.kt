package com.zmy.zhihuzheye.base

import GreenDao.DaoMaster
import GreenDao.DaoSession
import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.lzy.okgo.OkGo


/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-26 17:07
 * @Notice 无
 */
class BaseApp : Application() {

    companion object {                          //声明静态变量。
        lateinit var daoSession: DaoSession
        lateinit var context: Context
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        OkGo.getInstance().init(this)
        Utils.init(this)
        initDao()
    }

    private fun initDao() {
        val helper = DaoMaster.DevOpenHelper(this,"zhihuzheye")
        val db = helper.writableDb
        daoSession = DaoMaster(db).newSession()
    }
    open fun getDaoInstant(): DaoSession {
        return daoSession
    }

    open fun getContext():Context{
        return context
    }

}