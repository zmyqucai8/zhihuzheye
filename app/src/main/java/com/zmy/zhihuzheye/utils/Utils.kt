package com.zmy.zhihuzheye.utils

import com.zmy.zhihuzheye.base.AppContact

/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-26 16:58
 * @Notice 无
 */
class Utils {


    companion object {
        fun getNewsApi(type: String = "top"): String {
            return AppContact.API_NEWS + "?type=$type&key=" + AppContact.API_KEY_NEWS
        }

        fun getDrivingApi(subject: Int = 1): String {
            //随机获取100道c1测试题目， 科目类型根据传入数字 1或4
            return AppContact.API_DRIVING + "?subject=$subject&model=c1&testType=rand&key=" + AppContact.API_KEY_DRIVING
        }
    }

}