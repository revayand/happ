package co.health.test.corona.repository.db.entities

import androidx.room.*

@Entity(tableName = "users")
data class Users(
    @Embedded val detail: Detail,
    val history: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "users_id")
    val id: Long = 0
)

data class Detail(val fname: String?, val lname: String?, val gender: String?)



data class UsersWithAnswers(
    @Embedded val users: Users,
    @Relation(parentColumn = "users_id", entityColumn = "parent_users_id")
    val answers: List<Answerr>
)