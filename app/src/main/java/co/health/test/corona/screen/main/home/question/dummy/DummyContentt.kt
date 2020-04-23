package co.health.test.corona.screen.main.home.question.dummy

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContentt {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<DummyItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, DummyItem> = HashMap()

    private val COUNT = 2

    init {
        addItem(
            DummyItem(
                0.toString(),
                "تب داشتن",
                "سلام من مدتی است تب دارم آیا نیاز است به پزشک مراجعه کنم",
                "پاسخ داده نشده"
            )
        )
        addItem(
            DummyItem(
                1.toString(),
                "سرفه خشک",
                "من سرفه خشک دارم آیا کرونا گرفته ام؟",
                "پاسخ داده نشده"
            )
        )

    }

    private fun addItem(item: DummyItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem(position.toString(), "Item " + position, makeDetails(position),"")
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val title: String, val desc: String,val state:String) {
        override fun toString(): String = desc

    }

}













