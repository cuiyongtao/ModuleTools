package com.victory.basemodule.tools

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.view.isVisible
import com.victory.basemodule.R

/**
 * @author  Victory
 * @date 2019/5/24
 * TitleBar : 顶部导航栏
 */
class TitleBar : Toolbar {
    /**
     * 点击事件接口
     */
    private var mTxtLeftInterface: TxtLeftInterface? = null
    private var mIconLeftInterface: IconLeftInterface? = null
    private var mIconRightInterface: IconRightInterface? = null
    private var mEditCenterSearchInterface: EditCenterSearchInterface? = null

    /**
     * 控件
     */
    lateinit var titleBarLayout: LinearLayout
    lateinit var txtLeft: TextView
    lateinit var leftIB: ImageButton
    lateinit var txtTitleName: TextView
    lateinit var etSearch: EditText
    lateinit var rightIB: ImageButton


    /**
     * 左侧textview接口回调方法
     */
    fun setTxtLeftInterFace(txtLeftInterface: TxtLeftInterface) {
        mTxtLeftInterface = txtLeftInterface;
    }

    /**
     * 左侧ImageButton接口回调方法
     */
    fun setIconLeftInterFace(iconLeftInterface: IconLeftInterface) {
        mIconLeftInterface = iconLeftInterface;
    }

    /**
     *
     */
    fun setEditCanterSearchInterface(editCenterSearchInterface: EditCenterSearchInterface) {
        mEditCenterSearchInterface = editCenterSearchInterface
    }

    /**
     * 右侧ImageButton接口回调
     */
    fun setIconRightInterFace(iconRightInterface: IconRightInterface) {
        mIconRightInterface = iconRightInterface
    }

    constructor(context: Context) : super(context) {
        getView()
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet, 0) {
        getView()
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, 0) {
        getView()
        init()
    }

    /**
     * 使用静态方法获取当前页面的顶部样式
     */
    companion object {
        /**
         * mStyle 0=普通样式；1搜索样式；2主页样式
         */
        var mStyle = 0

        fun getStyle(style: Int) {
            mStyle = style
        }
    }

    /**
     * 根据mStyle修改顶部样式
     * 0=普通样式；1搜索样式；2主页样式
     */
    fun setTitleView() {
        if (mStyle == 2) {
            txtTitleName.isVisible = true
        } else if (mStyle == 1) {
            etSearch.isVisible = true
        } else {
            leftIB.isVisible = true
            txtTitleName.isVisible = true
        }
    }

    /**
     * 获取顶部导航栏
     */
    private fun getView(): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this)
        return view
    }

    /**
     * 初始化
     */
    private fun init() {
        titleBarLayout = getView().findViewById<LinearLayout>(R.id.titleBarLayout)
        txtLeft = getView().findViewById<TextView>(R.id.txtLeft)
        leftIB = getView().findViewById<ImageButton>(R.id.leftIB)
        txtTitleName = getView().findViewById<TextView>(R.id.txtTitleName)
        etSearch = getView().findViewById<EditText>(R.id.etSearch)
        rightIB = getView().findViewById<ImageButton>(R.id.rightIB)
        //点击事件
        mViewsOnClickListener()
        //设置样式
        setTitleView()
    }

    /**
     * 导航栏整体颜色
     */
    fun setTitleBarBg(color: Int) {
        titleBarLayout.setBackgroundResource(color)
    }

    /**
     * 控件点击事件
     */
    private fun mViewsOnClickListener() {
        //左边TextView
        txtLeft.setOnClickListener {
            if (mTxtLeftInterface != null) {
                mTxtLeftInterface!!.txtLeftOnclick();
            }
        }
        //左边图标
        leftIB.setOnClickListener {
            if (mIconLeftInterface != null) {
                mIconLeftInterface!!.iconLeftOnclick()
            }
        }
        //中间搜索
        etSearch.setOnEditorActionListener { v, actionId, event ->
            //键盘上下一步和回车键事件，点击回车或者下一步进行搜索
            if (actionId == EditorInfo.IME_ACTION_SEND || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                if (mEditCenterSearchInterface != null) {
                    mEditCenterSearchInterface!!.editCenterSearchOnClick()
                }
            }
            false
        }
        //右边图标
        rightIB.setOnClickListener {
            if (mIconRightInterface != null) {
                mIconRightInterface!!.iconRightOnClick()
            }
        }
    }

    /**
     * 设置当前页面名称
     */
    override fun setTitle(titleName: CharSequence) {
        txtTitleName.setText(titleName)
    }

    /**
     * 左侧TxtView接口
     */
    interface TxtLeftInterface {
        public fun txtLeftOnclick()
    }

    /**
     * 左侧图标接口
     */
    interface IconLeftInterface {
        public fun iconLeftOnclick()
    }

    /**
     * 中间搜索
     */
    interface EditCenterSearchInterface {
        public fun editCenterSearchOnClick()
    }

    /**
     * 右侧ImageButton按钮
     */
    interface IconRightInterface {
        public fun iconRightOnClick();
    }
}
