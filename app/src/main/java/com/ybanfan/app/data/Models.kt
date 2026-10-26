package com.ybanfan.app.data

data class Comment(
    val author: String,
    val text: String,
    val likes: Int,
)

data class Video(
    val id: String,
    val url: String,
    val date: String,
    val caption: String,
    val views: Int,
    val likes: Int,
    val commentCount: Int,
    val rating: Int,
    val whyFunny: String,
    val kind: String,
    val summary: String,
    val quotes: List<String>,
    val comments: List<Comment>,
)

data class Stat(
    val label: String,
    val value: String,
)

data class Portrait(
    val name: String,
    val handle: String,
    val tagline: String,
    val about: String,
    val stats: List<Stat>,
    val facts: List<String>,
    val memes: List<String>,
    val fansSay: List<String>,
    val othersSay: List<String>,
)
