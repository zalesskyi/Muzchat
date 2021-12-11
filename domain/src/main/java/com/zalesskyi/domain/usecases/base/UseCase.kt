package com.zalesskyi.domain.usecases.base

abstract class UseCase<Param, T> {

    abstract fun execute(param: Param): T
}