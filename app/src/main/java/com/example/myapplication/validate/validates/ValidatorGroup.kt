package com.example.myapplication.validate.validates

import com.example.myapplication.validate.Validator

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName ValidatorGroup
 * @Author heqinglin
 * @Date 2019-12-16 13:39
 * 这是一个校验器的集合类
 * 这个类用来定义多个校验实体集群，一般自定义的业务性，不可重用的校验实体在此定义，
 * 一般重用性比较高的，与业务比较隔离的验证写成单个文件，利于重用
 */

class AddressDetailValidator(value: String?, msg: String) :
    Validator<String?>(value, msg){

    override fun getCode(): Int {
        return VALIDATOR_CODE_ADDRESS
    }

    /**
     * 校验地址长度
     */
    override fun doValidate(): Boolean {

        return value?.length in 4..100

    }


}