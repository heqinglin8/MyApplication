package com.example.myapplication.validate

import com.example.myapplication.validate.validates.StringEmptyValidator
import com.example.myapplication.validate.validates.ZHValidator

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName ValidatorUtils
 * @Author heqinglin
 * @Date 2019-12-16 13:33
 * @Description TODO
 * 一些校验方法集中写到这个工具类，利于归纳和查找
 */


class ValidatorUtils{

    /**
     * 校验名称
     */
    fun checkName(name:String?):Result{

       return ValidatorManager().
            add(StringEmptyValidator(name, "用户名为空"))
            .add(ZHValidator(name, "必须包含中文！"))
            .doValidate()

    }

}