package com.zmy.zhihuzheye.bean

/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-26 16:23
 * @Notice 无
 */


data class DrivingJsonResultBean(
    val reason: String,
    val error_code: Int,
    val result: MutableList<DrivingBean>
)


data class DrivingBean(
    var id: Int,
    var question: String,
    var answer: String,
    var item1: String,//选项，当内容为空时表示判断题正确选项
    var item2: String,//选项，当内容为空时表示判断题错误选项
    var item3: String,
    var item4: String,
    var url: String,
    var explains: String,//答案解释

    var isComplete: Boolean//自定义数据：当前题目是否答题完成

)