package com.zmy.zhihuzheye.bean

/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-26 16:23
 * @Notice 无
 */


data class NewsJsonResultBean(
        val reason:String,
        val error_code:Int,
        val result:NewsResultBean
)

data class NewsResultBean(
    val stat:String,
    val data:MutableList<NewsBean>

)

data class NewsBean(var title:String ,
               var date:String,
               var category:String,
               var author_name:String,
               var url:String,
               var thumbnail_pic_s:String
               ){

}