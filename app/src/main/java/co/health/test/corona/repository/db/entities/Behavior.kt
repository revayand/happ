package co.health.test.corona.repository.db.entities

import androidx.room.*

@Entity(tableName = "behavior")
data class Behavior(
    val point: Int,
    val time:Long,
    @ColumnInfo(name = "parent_users_id") val usersId: Long,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "behavior_id")
    val id: Long = 0
)
