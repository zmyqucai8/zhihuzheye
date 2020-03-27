package com.zmy.zhihuzheye.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zmy.zhihuzheye.R
import com.zmy.zhihuzheye.bean.NewsBean
import com.zmy.zhihuzheye.utils.dao.NewsTable

/**
 *
 * @Description: java类作用描述
 * @Author: zhangmengyun
 * @CreateDate: 2020-03-26 16:23
 * @Notice 无
 */
class  NewsAadpter(layoutResId: Int, data: MutableList<NewsTable>?) :

    BaseQuickAdapter<NewsTable, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder, item: NewsTable) {


        helper
            .setText(R.id.tv_title,item.title)
            .setText(R.id.tv_date,item.date)
            .setText(R.id.tv_author,item.author_name)



    }
}