package com.example.myapplication.validate.validates

import android.text.TextUtils
import com.example.myapplication.validate.Validator

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName StringEmptyValidator
 * @Author heqinglin
 * @Date 2019-12-14 17:26
 * @Description TODO
 * 字符串判断是否为空
 */
class StringEmptyValidator(value: String?, msg: String) :
    Validator<String?>(value, msg){

    override fun getCode(): Int {
       return VALIDATOR_CODE_EMPTY
    }

    override fun doValidate(): Boolean {

        return !TextUtils.isEmpty(value)

    }


    override fun onException(msg: String) {
        super.onException(msg)
        TODO("do something!")
    }
}