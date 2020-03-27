package com.zmy.zhihuzheye.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.TimeUtils
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson

import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.zmy.zhihuzheye.R
import com.zmy.zhihuzheye.utils.Utils
import com.zmy.zhihuzheye.adapter.NewsAadpter
import com.zmy.zhihuzheye.bean.NewsJsonResultBean
import com.zmy.zhihuzheye.utils.dao.DaoUtils
import com.zmy.zhihuzheye.utils.dao.NewsTable


/**
 * 新闻页面
 */
class NewsFragment(type: String) : Fragment() {

    lateinit var root: View
    lateinit var mNewsAadpter: NewsAadpter
    lateinit var mRecyclerView: RecyclerView
    lateinit var mTabLayout: TabLayout

    val mType: String = type

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_news, container, false)

        initView()
        initData()
        return root
    }


    /**
     * 初始化VIEW
     */
    private fun initView() {
        mRecyclerView = root.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        mRecyclerView.layoutManager = layoutManager
        mNewsAadpter = NewsAadpter(R.layout.item_news, null)
        mRecyclerView.adapter = mNewsAadpter


    }


    /**
     * 初始化数据
     */
    private fun initData() {


        //默认去数据库获取，如果没有才从服务器获取， 下拉刷新时从服务器获取


        val queryNews = DaoUtils.queryNews(mType)

        if (queryNews != null && !queryNews.isEmpty()) {
            //使用缓存数据
            mNewsAadpter.setNewData(queryNews)

        } else {
            //去服务器加载数据
            OkGo.get<String>(Utils.getNewsApi(mType)).tag(this).execute(object : StringCallback() {
                override fun onSuccess(response: Response<String>?) {
                    val data = response?.body()

                    println("请求成功")

                    val resultBean =
                        Gson().fromJson(data, NewsJsonResultBean::class.java)

                    if (resultBean.result != null && resultBean.result.data != null) {

                        var newsTableList = mutableListOf<NewsTable>()

                        for (bean in resultBean.result.data) {
                            var newsTable = NewsTable()
                            newsTable.title = bean.title
                            newsTable.date = bean.date
                            newsTable.category = bean.category
                            newsTable.author_name = bean.author_name
                            newsTable.thumbnail_pic_s = bean.thumbnail_pic_s
                            newsTable.url = bean.url
                            newsTable.time = TimeUtils.string2Millis(bean.date)
                            newsTableList.add(newsTable)
                        }

                        DaoUtils.insertNews(newsTableList)
                        initData()
                    } else {
                        println(resultBean.reason)
                    }


                }

                override fun onError(response: Response<String>?) {
                    super.onError(response)
                    println("请求错误")
                }
            })

        }


    }

}