package com.example.myapplication.validate

import com.example.myapplication.validate.Validator.Companion.VALIDATOR_CODE_DEFAULT

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName Result
 * @Author heqinglin
 * @Date 2019-12-14 15:34
 * @Description TODO
 */

data class Result(var isSuccess:Boolean,var code:Int = VALIDATOR_CODE_DEFAULT,val msg:String){
    constructor(isSuccess:Boolean,msg:String) : this(isSuccess,VALIDATOR_CODE_DEFAULT,msg)
}