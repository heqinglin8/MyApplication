package com.example.myapplication.validate.validates

import com.example.myapplication.validate.Validator
import java.util.regex.Pattern

/**
 * @PackageName com.example.myapplication.validate.validates
 * @ClassName MobileValidator
 * @Author heqinglin
 * @Date 2019-12-16 14:22
 * @Description TODO
 * 判断是否是电话号码
 */
class MobileValidator(value: String, msg: String) : Validator<String>(value, msg) {

    override fun doValidate(): Boolean {
        val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(value)
        return m.matches()
    }

    override fun getCode(): Int {
        return VALIDATOR_CODE_MOBILE
    }
}