package com.picpay.desafio.android

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<com.desafio.picpay.core.user_list.model.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}