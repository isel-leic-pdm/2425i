package com.imaginationoverflow.com.revenuemesh

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform