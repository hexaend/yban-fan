package com.ybanfan.app.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class ContentRepository(context: Context) {

    private val assets = context.applicationContext.assets

    val portrait: Portrait by lazy {
        parsePortrait(JSONObject(readAsset("portrait.json")))
    }

    val videos: List<Video> by lazy {
        JSONArray(readAsset("videos.json"))
            .mapObjects(::parseVideo)
            .sortedWith(compareByDescending<Video> { it.rating }.thenByDescending { it.views })
    }

    fun videoById(id: String): Video = videos.first { it.id == id }

    private fun readAsset(name: String): String =
        assets.open(name).bufferedReader().use { it.readText() }

    private fun parsePortrait(json: JSONObject) = Portrait(
        name = json.getString("name"),
        handle = json.getString("handle"),
        tagline = json.getString("tagline"),
        about = json.getString("about"),
        stats = json.getJSONArray("stats").mapObjects { Stat(it.getString("label"), it.getString("value")) },
        facts = json.stringList("facts"),
        memes = json.stringList("memes"),
        fansSay = json.stringList("fansSay"),
        othersSay = json.stringList("othersSay"),
    )

    private fun parseVideo(json: JSONObject) = Video(
        id = json.getString("id"),
        url = json.optString("url"),
        date = json.optString("date"),
        caption = json.optString("caption"),
        views = json.optInt("views"),
        likes = json.optInt("likes"),
        commentCount = json.optInt("commentCount"),
        rating = json.optInt("rating"),
        whyFunny = json.optString("whyFunny"),
        kind = json.optString("kind"),
        summary = json.optString("summary"),
        quotes = json.stringList("quotes"),
        comments = json.optJSONArray("comments")
            ?.mapObjects { Comment(it.optString("author"), it.optString("text"), it.optInt("likes")) }
            .orEmpty(),
    )
}

private fun JSONObject.stringList(key: String): List<String> {
    val array = optJSONArray(key) ?: return emptyList()
    return List(array.length()) { array.getString(it) }
}

private inline fun <T> JSONArray.mapObjects(transform: (JSONObject) -> T): List<T> =
    List(length()) { transform(getJSONObject(it)) }
