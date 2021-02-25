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
class EmailValidator(value: String, msg: String) : Validator<String>(value, msg) {

    override fun doValidate(): Boolean {
        val regExp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}\$"
        val p = Pattern.compile(regExp)
        val m = p.matcher(value)
        return m.matches()
    }

    override fun getCode(): Int {
        return VALIDATOR_CODE_EMAIL
    }
}