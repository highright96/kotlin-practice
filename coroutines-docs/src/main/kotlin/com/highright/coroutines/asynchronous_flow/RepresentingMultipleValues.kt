package com.highright.coroutines.asynchronous_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/*
suspend 함수를 호출하면 메인 스레드는 delay(1000ms) 되는 동안 다른 작업을 할 수 있다. (비동기)

Flow 는 비동기로 동작하면서 여러개의 값을 반환하는 함수를 만들 때 사용하는 Builder 다.
1. flow 타입의 생성은 flow {} 빌더를 사용한다.
2. flow {} 블록 내부 코드는 중단(suspend) 가능하다.
3. 결과 값들은 flow 블록 내에서 emit() 함수를 사용해 방출한다.
4. flow 에서 방출된 값은 collect 함수를 이용해 수집한다.
*/

fun main() = runBlocking {
    launch {
        for (k in 1..3) {
            delay(100)
            println("I'm not blocked $k")
        }
    }

    simpleWithFlow().collect { println(it) }
}

suspend fun simpleWithSuspend(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}

fun simpleWithFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // suspend 함수 사용 가능
        emit(i)
    }
}