package com.dino.library.kakao

sealed class AsyncType {
    object NORMAL : AsyncType()
    object RX : AsyncType()
    object COROUTINE : AsyncType()
}