package com.example.myapplication.validate

import android.util.Log

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName Validator
 * @Author heqinglin
 * @Date 2019-12-14 15:19
 * @Description ：
 * 校验器
 * 每一个校验实体，每个校验都继承自此类
 */

abstract class Validator<T>(val value:T, val msg:String) {

    companion object{
        val   TAG = Validator.javaClass.simpleName
        const val   VALIDATOR_CODE_DEFAULT = 0
        const val   VALIDATOR_CODE_EMPTY = 1
        const val   VALIDATOR_CODE_ZH = 2  //判断是否中文
        const val   VALIDATOR_CODE_ADDRESS = 4  //判断地址
        const val   VALIDATOR_CODE_MOBILE = 5  //判断电话
        const val   VALIDATOR_CODE_EMAIL = 6  //判断邮箱
    }

    /**
     * 是否参与校验，默认校验
     * 如果重写，并返回false就不校验，用户动态决定是否做这个校验的场景
     */
    open fun accept():Boolean{
       return true
    }

    /**
     * 重写这个方法来校验你的数据
     * 默认未true（通过），就是不拦截
     * 这里写校验方法
     */
    abstract fun doValidate():Boolean

    /**
     * 发生异常的时候的时候做的事情,可以在这里打印log
     * @param {*} value
     */
    open fun onException(msg:String){
         Log.e(TAG, msg)
    }

    abstract fun getCode():Int

}