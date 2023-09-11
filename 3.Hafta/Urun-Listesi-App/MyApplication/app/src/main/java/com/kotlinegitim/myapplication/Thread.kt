package com.kotlinegitim.myapplication
import kotlin.concurrent.thread

fun main() {
    val thread1 = thread(start = true) {
        println("Thread 1: Uzun süren işlem 1 başladı.")
        Thread.sleep(5000) // 5 saniye uyku
        println("Thread 1: Uzun süren işlem 1 tamamlandı.")
    }

    val thread2 = thread(start = true) {
        println("Thread 2: Uzun süren işlem 2 başladı.")
        Thread.sleep(3000) // 3 saniye uyku
        println("Thread 2: Uzun süren işlem 2 tamamlandı.")
    }

    println("Ana thread: Diğer threadlerin tamamlanmasını bekliyor.")
    Thread.sleep(6000) // 6 saniye uyku
    println("Ana thread: Tüm threadler tamamlandı.")
}