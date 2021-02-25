package com.example.myapplication.validate

import java.lang.Exception

/**
 * @PackageName com.example.myapplication.validate
 * @ClassName ValidatorManager
 * @Author heqinglin
 * @Date 2019-12-14 15:38
 * @Description TODO
 * 一个用于校验的管理器
 * 用了责任链模式构建的数据校验模块
 */

class ValidatorManager{

    //校验器集合
    private val validatorList = mutableListOf<Validator<*>>()

    //添加校验器
   fun add(validator:Validator<*>):ValidatorManager {
        this.validatorList.add(validator)
        return this
    }

    //执行校验
   fun doValidate():Result {
        var result = Result(true,"验证通过")
        while (this.validatorList.isNotEmpty()){
            val validator = this.validatorList.removeAt(0)
            try {
                if(!validator.accept())
                    continue
                if(!validator.doValidate()) {
                    return Result(false,validator.msg)
                }
            }catch (e:Exception){
                validator.onException("校验发生异常Exception：$e")
            }
        }
        return result
    }

}