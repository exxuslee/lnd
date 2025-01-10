package org.example

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils
import lnrpc.LightningGrpc

fun main() {
    println("Hello World!")
    val host = "192.168.1.114" // IP или хост LND ноды
    val port = 10009 // Порт LND ноды
    val macaroonHex = "0201036C6E6402F801030A10CA1209542D999BC35287882B53A804DD1201301A160A076164647265737312..." // Ваш macaroon

    // Создаем канал
    val channel: ManagedChannel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext() // Для использования без TLS
        .build()

    // Добавляем macaroon в Metadata
    val macaroon = Metadata()
    val macaroonKey = Metadata.Key.of("Grpc-Metadata-macaroon", Metadata.ASCII_STRING_MARSHALLER)
    macaroon.put(macaroonKey, macaroonHex)

    // Создаем клиента с метаданными
    val blockingStub = MetadataUtils.attachHeaders(LightningGrpc.newBlockingStub(channel), macaroon)

    // Создаем запрос
    val getInfoRequest = GetInfoRequest.newBuilder().build()

    // Выполняем запрос
    try {
        val response = blockingStub.getInfo(getInfoRequest)
        println("Node Info: ${response}")
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Закрываем канал
        channel.shutdown()
    }
}