package com.example.myapplication.validate.validates

import com.example.myapplication.validate.Validator
import java.util.regex.Pattern

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName ZHValidator
 * @Author heqinglin
 * @Date 2019-12-16 12:44
 * @Description TODO
 * 是否是中文
 */

class ZHValidator(value: String?, msg: String) :
    Validator<String?>(value, msg){
    override fun getCode(): Int {
       return VALIDATOR_CODE_ZH
    }

    override fun doValidate(): Boolean {
        val format = "[\u4e00-\u9fa5]"
        val pattern = Pattern.compile(format)
        val matcher = pattern.matcher(value)
        return matcher.find()

    }

}