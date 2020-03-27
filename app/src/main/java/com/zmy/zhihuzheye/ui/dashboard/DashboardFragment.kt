package com.zmy.zhihuzheye.ui.dashboard

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.zmy.zhihuzheye.R
import com.zmy.zhihuzheye.base.BaseApp
import com.zmy.zhihuzheye.bean.DrivingBean
import com.zmy.zhihuzheye.bean.DrivingJsonResultBean
import com.zmy.zhihuzheye.utils.Utils
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    lateinit var root: View


    //科目1题目

    var mDrivingData1: MutableList<DrivingBean> = ArrayList()

    //科目4题目
    var mDrivingData4: MutableList<DrivingBean> = ArrayList()


    lateinit var tv_explains: TextView
    lateinit var scrollView: ScrollView
    lateinit var imageView: ImageView
    lateinit var tv_type: TextView
    lateinit var tv_question: TextView
    lateinit var tv_no_count: TextView
    lateinit var tv_ok_count: TextView
    lateinit var tv_count_index: TextView

    lateinit var btn_next: Button
    lateinit var btn_back: Button

    lateinit var radioGroup: RadioGroup
    lateinit var checkbox1: RadioButton
    lateinit var checkbox2: RadioButton
    lateinit var checkbox3: RadioButton
    lateinit var checkbox4: RadioButton


    //科目当前答题位置
    var subject1Index: Int = 0
    var subject4Index: Int = 0
    var tabLayoutPostion: Int = 0


    //科目1正确数、错误数量
    var subject1OKCount = 0
    var subject1NOCount = 0

    //科目4正确数、错误数量
    var subject4OKCount = 0
    var subject4NOCount = 0

    lateinit var mTabLayout: TabLayout


    companion object {

        fun newInstance(): Fragment {
            val myFragment: DashboardFragment = DashboardFragment()
            val bundle = Bundle()
            myFragment.setArguments(bundle)
            return myFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        initView(root)
        initData(1)
        return root
    }

    private fun initData(subject: Int) {


        //去网络获取100道题目
        //去服务器加载数据
        OkGo.get<String>(Utils.getDrivingApi(subject)).tag(this).execute(object : StringCallback() {
            override fun onSuccess(response: Response<String>?) {
                val data = response?.body()


                val resultBean =
                    Gson().fromJson(data, DrivingJsonResultBean::class.java)

                if (resultBean.result != null) {

                    if (subject == 1) {
                        mDrivingData1 = resultBean.result
                    } else {
                        mDrivingData4 = resultBean.result
                    }

                    updateUI(subject)

                } else {
                    println(resultBean.reason)
                }


            }
        })
    }

    private fun initView(root: View) {

        tv_explains = root.findViewById(R.id.tv_explains)
        scrollView = root.findViewById(R.id.scrollView)
        imageView = root.findViewById(R.id.imageView)
        radioGroup = root.findViewById(R.id.radioGroup)
        mTabLayout = root.findViewById(R.id.tabLayout)
        tv_type = root.findViewById(R.id.tv_type)
        tv_question = root.findViewById(R.id.tv_question)
        checkbox1 = root.findViewById(R.id.checkbox1)
        checkbox2 = root.findViewById(R.id.checkbox2)
        checkbox3 = root.findViewById(R.id.checkbox3)
        checkbox4 = root.findViewById(R.id.checkbox4)
        btn_back = root.findViewById(R.id.btn_back)
        btn_next = root.findViewById(R.id.btn_next)
        tv_ok_count = root.findViewById(R.id.tv_ok_count)
        tv_no_count = root.findViewById(R.id.tv_no_count)
        tv_count_index = root.findViewById(R.id.tv_count_index)


        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                tabLayoutPostion = if (position == 0) 0 else 1

                updateUI(if (position == 0) 1 else 4)


                LogUtils.e("选中了=$position")
            }
        })

        btn_back.setOnClickListener { v ->


            if (tabLayoutPostion == 0) {

                if (subject1Index <= 0) {
                    SnackbarUtils.with(v).setMessage("没有上一题啦").show()
                } else {
                    subject1Index -= 1
                    updateUI(if (tabLayoutPostion == 0) 1 else 4)
                }


            } else {
                if (subject4Index <= 0) {
                    SnackbarUtils.with(v).setMessage("没有上一题啦").show()

                } else {
                    subject4Index -= 1
                    updateUI(if (tabLayoutPostion == 0) 1 else 4)
                }

            }

        }


        btn_next.setOnClickListener { v ->

            if (tabLayoutPostion == 0) {

                if (subject1Index >= 100) {
                    SnackbarUtils.with(v).setMessage("没有下一题啦").show()
                } else {
                    subject1Index += 1
                    updateUI(if (tabLayoutPostion == 0) 1 else 4)
                }


            } else {
                if (subject4Index >= 100) {
                    SnackbarUtils.with(v).setMessage("没有下一题啦").show()
                } else {
                    subject4Index += 1
                    updateUI(if (tabLayoutPostion == 0) 1 else 4)
                }

            }

        }


        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {


                when (checkedId) {
                    R.id.checkbox1 -> answer(1)
                    R.id.checkbox2 -> answer(2)
                    R.id.checkbox3 -> answer(3)
                    R.id.checkbox4 -> answer(4)
                }

            }
        })


    }


    /**
     * 回答题目
     */
    private fun answer(item: Int) {


        var drivingBean: DrivingBean
        //获取当前题目
        if (tabLayoutPostion == 0) {

            drivingBean = mDrivingData1[subject1Index]

        } else {
            drivingBean = mDrivingData4[subject4Index]
        }

        if (drivingBean.answer.toInt() == item) {
            //回答正确
//            ToastUtils.showShort("回答正确")
            //更新对错数目
            if (tabLayoutPostion == 0) {
                subject1OKCount += 1

                tv_ok_count.text = subject1OKCount.toString()
            } else {
                subject4OKCount += 1
                //更新错误
                tv_ok_count.text = subject4OKCount.toString()
            }


            btn_next.callOnClick()
            radioGroup.clearCheck()
        } else {
            //回答错误
//            ToastUtils.showShort("回答错误")

            if (tabLayoutPostion == 0) {
                subject1NOCount += 1
                //更新错误
                tv_no_count.text = subject1NOCount.toString()
            } else {
                subject4OKCount += 1
                //更新错误
                tv_no_count.text = subject4NOCount.toString()
            }
            //显示解释

//            btn_next.callOnClick()

            scrollView.visibility = View.VISIBLE
            tv_explains.text = drivingBean.explains

        }



    }


    //更新UI
    private fun updateUI(subject: Int) {
        //判断当前科目几，答道第几题了 。 显示题目，更新操

        if (subject == 1) {
            if (mDrivingData1.isEmpty()) {
                initData(subject)
            } else {

                //不为空显示数据
                val drivingBean = mDrivingData1[subject1Index]



                showUI(drivingBean, subject1Index)

            }


        } else {
            if (mDrivingData4.isEmpty()) {
                initData(subject)
            } else {
                //不为空显示数据
                val drivingBean = mDrivingData4[subject4Index]
                showUI(drivingBean, subject4Index)

            }
        }


    }

    /**
     * 更新题目
     */
    private fun showUI(drivingBean: DrivingBean, subjectIndex: Int) {

        //判断题目类型


        scrollView.visibility = View.INVISIBLE

        if (!TextUtils.isEmpty(drivingBean.url)) {

            Glide.with(BaseApp.context).clear(imageView)
            imageView.visibility = View.VISIBLE
            Glide.with(BaseApp.context)
                .load(drivingBean.url)
                .into(
                    imageView
                )
        } else {
            imageView.visibility = View.GONE
        }



        tv_question.text = "                 ${drivingBean.question}"

        if (!TextUtils.isEmpty(drivingBean.item3)) {
            //单选题 、多选题
            tv_type.text = "【单选】"
            checkbox1.text = drivingBean.item1
            checkbox2.text = drivingBean.item2
            checkbox3.text = drivingBean.item3
            checkbox4.text = drivingBean.item4
            checkbox3.visibility = View.VISIBLE
            checkbox4.visibility = View.VISIBLE
        } else {
            //判断题
            tv_type.text = "【判断】"
            checkbox1.text = if (!TextUtils.isEmpty(drivingBean.item1)) drivingBean.item1 else "正确"
            checkbox2.text = if (!TextUtils.isEmpty(drivingBean.item2)) drivingBean.item2 else "错误"
            checkbox3.visibility = View.GONE
            checkbox4.visibility = View.GONE
        }


        //设置索引
        tv_count_index.text = "$subjectIndex/100"

        //设置正确错误数量

        if (tabLayoutPostion == 0) {


            tv_ok_count.text = subject1OKCount.toString()
            tv_no_count.text = subject1NOCount.toString()
        } else {

            tv_ok_count.text = subject4OKCount.toString()
            tv_no_count.text = subject4NOCount.toString()
        }


    }
}