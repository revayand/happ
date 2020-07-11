package co.health.test.corona.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import co.health.test.corona.R
import co.health.test.corona.repository.db.daos.*
import co.health.test.corona.repository.db.entities.*
import co.health.test.corona.screen.utils.Constants
import io.reactivex.observers.DisposableSingleObserver
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


@TypeConverters(Converters::class)
@Database(
    entities = [Questionnaire::class, Question::class, Users::class, Answerr::class, Behavior::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao
    abstract fun questionnaireDao(): QuestionnaireDao
    abstract fun usersDao(): UsersDao
    abstract fun answerDao(): AnswerDao
    abstract fun behaviorDao(): BehaviorDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }

        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Corona.db"
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    //pre-populate data
                    Executors.newSingleThreadExecutor().execute {
                        INSTANCE?.let {
                            saveData(it)
                        }
                    }
                }
            })

                .build()

        private fun saveData(appDatabase: AppDatabase) {


            val vasvasTestq =
                Questionnaire(143.29, 54.08,
                   Constants.guideVasvas, R.drawable.vasvas, 0,null, null, "تست وسواس", QuestionnaireState.FILLED)
            appDatabase.questionnaireDao().insert(vasvasTestq)
                .subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {


                        val vasvalql: MutableList<Question> = ArrayList()
                        vasvalql.add(
                            Question(
                                "افکار وسواسی چه مقدار از وقت شما را اشغال می کند؟ فراوانی وقوع افکار وسواسی چقدر است؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "هیچ ",
                                        "روزانه کمتر از 1 ساعت ، نه بیش از 8 بار در روز",
                                        "روزانه 1 تا 3 ساعت ، بیش از 8 بار در روز",
                                        "بیش از 3 ساعت و تا حدود 8 ساعت در روز ",
                                        "بیش از 8 بار در روز و یا مزاحمت های تقریبا مداوم "
                                    )
                                ), t
                            )
                        )

                        vasvalql.add(
                            Question(
                                "افکار وسواسی تا چه حد در کارکرد شغلی یا اجتماعی شما اختلال ایجاد کرده اند ؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "هیچ اختلالی",
                                        "اختلال خفیف- لطمه نزده",
                                        "اختلال متوسط- قابل کنترل",
                                        "اختلال شدید – لطمه زده",
                                        "اختلال شدید- ناتوان کرده"
                                    )
                                ), t
                            )
                        )


                        vasvalql.add(
                            Question(
                                "افکار وسواسی چه مقدار پریشانی برای شما ایجاد می کند؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "هیچ",
                                        "خفیف- مختل کننده نیست",
                                        "پریشانی متوسط- قابل کنترل",
                                        "پریشانی شدید- مختل کننده",
                                        "پریشانی بسیار شدید- ناتوان کننده"
                                    )
                                ), t
                            )
                        )


                        vasvalql.add(
                            Question(
                                "چقدر سعی میکنید تا در برابر افکار وسواسی مقاومت کنید ؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "همیشه سعی میکنم در برابر آنها مقاومت کنم",
                                        "اکثر اوقات سعی می کنم مقاومت کنم",
                                        "برخی اوقات ",
                                        "کنترل بر آن ندارم",
                                        "به طور کامل تسلیم می شوم  "
                                    )
                                ), t
                            )
                        )



                        vasvalql.add(
                            Question(
                                "چقدر بر افکار وسواسی خود کنترل دارید؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "کنترل کامل ",
                                        "کنترل زیاد",
                                        "کنترل متوسط",
                                        "کنترل کم ",
                                        "بدون کنترل "
                                    )
                                ), t
                            )
                        )


//6

                        vasvalql.add(
                            Question(
                                "برای انجام رفتار های اجبار گونه چقدر وقت صرف می کنید؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "هیچ",
                                        "روزانه کمتر از 1 ساعت – کمتر 8 بار در روز",
                                        "روزانه بین 1 تا3 ساعت – بیش از 8 باز در روز ",
                                        "بیش از 3 ساعت و تا حدود 8 بار در روز",
                                        "شمارش دفعات بسیار مشکل است"
                                    )
                                ), t
                            )
                        )

                        //7


                        vasvalql.add(
                            Question(
                                "رفتارهای وسواسی و اجبار گونه تا چه حد در کارکرد شغلی یا اجتماعی شما اختلال ایجادکرده اند؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "هیچ اختلالی",
                                        "اختلال خفیف- لطمه نزده",
                                        "اختلال متوسط- قابل کنترل",
                                        "اختلال شدید – لطمه زده",
                                        "اختلال شدید- ناتوان کرده"
                                    )
                                ), t
                            )
                        )


                        //8
                        vasvalql.add(
                            Question(
                                "اگر از انجام اجبارهای خود منع شوید ، چه احساسی پیدا می کنید؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "به هیچ وجه مضطرب نمی شوم ",
                                        "تا حدودی مضطرب می شوم ",
                                        "اضطرابم بالا می رود",
                                        "اضطرابم بسیار افزایش می یابد- مختل کننده می شود.",
                                        "اضطراب شدید و ناتوان کننده"
                                    )
                                ), t
                            )
                        )


                        //9
                        vasvalql.add(
                            Question(
                                "چقدر سعی می کنید در برابر اجبار ها مقاومت کنید ؟",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "همیشه سعی می کنم مقاومت کنم  ",
                                        "اکثر اوقات سعی می کنم مقاومت کنم ",
                                        "برخی اوقات سعی میکنم مقاومت کنم  ",
                                        "تلاش برای کنترل آنها ندارم ",
                                        "کاملا تسلیم می شوم"
                                    )
                                ), t
                            )
                        )


                        //10
                        vasvalql.add(
                            Question(
                                "چقدربر رفتار های اجبار گونه خود کنترل دارید؟ متوقف کردن",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(
                                        "کنترل کامل ",
                                        "کنترل زیاد",
                                        "کنترل متوسط",
                                        "کنترل کم ",
                                        "بدون کنترل "
                                    )
                                ), t
                            )
                        )
                        appDatabase.questionDao().insert(vasvalql)
                            .subscribeWith(object : DisposableSingleObserver<List<Long>>() {
                                override fun onSuccess(t: List<Long>) {


                                }

                                override fun onError(e: Throwable) {
                                }


                            })


                    }

                    override fun onError(e: Throwable) {

                    }
                })


            val afsordegiTestq =
                Questionnaire(19.4, 11.89,Constants.guideAfsordegi,R.drawable.afsordegi,0, null, null, "تست افسردگی", QuestionnaireState.FILLED)

            appDatabase.questionnaireDao().insert(afsordegiTestq)
                .subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {


                        val afsordegiql: MutableList<Question> = ArrayList()

                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "غمگین نیستم ",
                                        "غمگین هستم",
                                        "غم دست بردار نیست",
                                        "تحملم را از دست داده ام"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "به آینده امیدوارم ",
                                        "به آینده امیدی ندارم",
                                        "احساس می کنم آینده امید بخشی در انتظارم نیست",
                                        "کمترین روزنه امیدی ندارم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "ناکام نیستم ",
                                        "ناکام تر از دیگرانم ",
                                        "احساس می کنم آینده امید بخشی در انتظارم نیست",
                                        "آدم کاملا شکست خورده ای هستم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "مثل گذشته از زندگی ام راضی هستم ",
                                        "مثل سابق از زندگی لذت نمی برم ",
                                        "از زندگی رضایت واقعی ندارم",
                                        "از هرکس و هر چیز بگویی ناراضی هستم "
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "احساس تقصیر نمی کنم ",
                                        "گاهی اوقات احساس تقصیر می کنم",
                                        "اغلب احساس تقصیر می کنم",
                                        "همیشه احساس تقصیر می کنم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "انتظار مجازات ندارم",
                                        "احساس می کنم ممکن است مجازات شوم",
                                        "انتظار مجازات دارم ",
                                        "احساس می کنم مجازات میشوم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "از خودم راضی هستم",
                                        "از خودم ناراضی هستم",
                                        "ازخودم بدم می آید",
                                        "از خودم متنفرم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "بدتر از سایرین نیستم ",
                                        "ازخودم به خاطر خطاهایم انتقاد می کنم",
                                        "همیشه خودم را بخاطر خطاهایم سرزنش می کنم",
                                        "برای هر اتفاقی بدی خود را سرزنش می کنم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "هرگز به فکر خودکشی نمی افتم ",
                                        "فکر خودکشی به سرم زده اما اقدامی نکرده ام ",
                                        "به فکر خودکشی هستم ",
                                        "اگر بتوانم خودکشی می کنم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "بیش از حد معمول گریه نمی کنم ",
                                        "بیش از گذشته گریه می کنم",
                                        "همیشه گریانم ",
                                        "قبلا گریه می کردم اما حالا با اینکه دلم هم می خواهد نمی توانم گریه کنم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "کم حوصله تر از گذشته نیستم ",
                                        "کم حوصله تر از گذشته هستم ",
                                        "اغلب کم حوصله هستم ",
                                        "همیشه کم حوصله هستم "
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "مثل همیشع مردم را دوست دارم ",
                                        "به نسبت گذشته کمتر از مردم خوشم می آید",
                                        "تا حدودی زیادی علاقه ام را به مردم از دست داده ام",
                                        "از مردم قطع امید کرده ام به آنها علاقه ای ندارم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "مانند گذشته تصمیم می گیرم ",
                                        "کمتر از گذشته تصمیم می گیرم",
                                        "نسبت به گذشته تصمیم گیری برایم دشوار تر شده است ",
                                        "قدرت تصمیم گیریم را از دست داده ام"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "جذابیت گذشته ها را ندارم",
                                        "نگران هستم که جذابیتم را از دست بدهم",
                                        "احساس می کنم هر روز که می گذرد جذابیتم را بیشتر از دست می دهم ",
                                        "زشت هستم "
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "به خوبی گذشته کار می کنم ",
                                        "به خوبی گذشته کار نمی کنم ",
                                        "برای اینکه کاری بکنم به خودم فشار زیادی می آورم",
                                        "دستم به هیچ کاری نمی رود"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "مثل همیشه خوب می خوابم",
                                        "مثل گذشته خوابم نمی برد",
                                        "یکی دو ساعتی زودتر از معمول از خواب بیدار می شوم خوابیدن دوباره برایم مشکل است",
                                        "چند ساعت زودتر از معمول از خواب بیدار می شوم ودیگر خوابم نمی برد"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "بیشتر گذشته خسته نمی شوم ",
                                        "بیش از گذشته خسته می شوم ",
                                        "انجام هر کاری خسته ام می کند",
                                        "از شدت خستگی هیچ کاری از عهده ام ساخته نیست"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اشتهایم تغییری نکرده است",
                                        "اشتهایم به خوبی گذشته نیست",
                                        "اشتهایم خیلی کم شده است",
                                        "به هیچ چیز اشتها ندارم"
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اخیرا وزن کم نکرده ام ",
                                        "بیش از دو کیلو و نیم وزن کم نکرده ام ",
                                        "بیش از پنج کیلو از وزن بدنم کم شده است ",
                                        "بیش از هفت کیلو وزن کم کرده ام "
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "بیش از گذشته بیمار نمی شوم ",
                                        "از سر درد و دل درد یبوست کمی ناراحتم",
                                        "به شدت نگران سلامتی خود هستم ",
                                        "انقدر نگران سلامتی خودم هستم که دستم به هیچ کاری نمی رود."
                                    )
                                ), t
                            )
                        )
                        afsordegiql.add(
                            Question(
                                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "میل جنسی ام تغییری نکرده است ",
                                        "میل جنسی ام کمتر شده است",
                                        "میل جنسی ام خیلی کم شده است ",
                                        "کمترین میل جنسی در من نیست"
                                    )
                                ), t
                            )
                        )

                        appDatabase.questionDao().insert(afsordegiql)
                            .subscribeWith(object : DisposableSingleObserver<List<Long>>() {
                                override fun onSuccess(t: List<Long>) {


                                }

                                override fun onError(e: Throwable) {

                                }
                            })

                    }

                    override fun onError(e: Throwable) {

                    }
                })


            val ezterabTestq =
                Questionnaire(23.33, 7.89,Constants.guideEzterab,R.drawable.ezterab,0, null, null, "تست اضطراب", QuestionnaireState.FILLED)
            appDatabase.questionnaireDao().insert(ezterabTestq)
                .subscribeWith(object : DisposableSingleObserver<Long>() {
                    override fun onSuccess(t: Long) {

                        val ezterabql: MutableList<Question> = ArrayList()

                        ezterabql.add(
                            Question(
                                "کرخی و گزگز شدن (مور مورشدن)",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "احساس داغ(گرما)",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "لرزش در پاها",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "ناتوانی در آرامش ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "ترس از وقوع حادثه بد",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "سرگیجه و منگی",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "تپش قلب و نفس نفس زدن",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "حالت متغییر ( بی ثبات)",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "وحشت زده ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "عصبی ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "احساس خفگی ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "لرزش دست ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "لرزش بدن",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "ترس از دست دادن کنترل",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "به سختی نفس کشیدن ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "ترس از مردن",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "ترسیده ( حالت ترس)",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "سوء هاضمه و ناراحتی در شکم ",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "غش کردن ( از حال رفتن )",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        ezterabql.add(
                            Question(
                                "سرخ شدن صورت",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )


                        ezterabql.add(
                            Question(
                                "عرق کردن (نه در اثر گرما)",
                                Answer(
                                    null,
                                    AnswerType.RADIO,
                                    listOf(

                                        "اصلا",
                                        "خفیف",
                                        "متوسط",
                                        "شدید"
                                    )
                                ), t
                            )
                        )

                        appDatabase.questionDao().insert(ezterabql)
                            .subscribeWith(object : DisposableSingleObserver<List<Long>>() {
                                override fun onSuccess(t: List<Long>) {


                                }

                                override fun onError(e: Throwable) {
                                }
                            })

                    }

                    override fun onError(e: Throwable) {

                    }
                })


            val nazmTestq = Questionnaire(
                0.0, 1.0,Constants.guideNazm,R.drawable.nazm,1,
                "ملامت خویش-پذیرش-تمرکز روی افکار-تمرکز مجدد مثبت-تمرکز مجدد بر برنامه ریزی-ارزیابی مجدد مثبت-دیدگاه گیری-فاجعه سازی-ملامت دیگران" +
                        "=1-2,3-4,5-6,7-8,9-10,11-12,13-14,15-16,17-18"
                , null, "تست نظم جویی شناخت هیجان", QuestionnaireState.FILLED
            )
            appDatabase.questionnaireDao().insert(nazmTestq).subscribe { t ->

                val nazmql: MutableList<Question> = ArrayList()


                nazmql.add(
                    Question(
                        " احساس می کنم که من برای آنچه که اتفاق افتاده است، مسئولم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  فکر می کنم مسبب اتفاق های  پیش آمده خودم هستم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  فکر می کنم من مجبورم که این اتفاق را بپذیرم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  فکر می کنم من مجبورم که این  موقعیت را بپذیرم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من اغلب  در مورد احساسی که نسبت به این تجربه دارم، فکر میکنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  این مساله که من در مورد تجربه کسب کرده چه احساس و فکری دارم، ذهن من را به خود مشغول کرده است."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  به چیزهای خوشایندی فکر میکنم که ربطی به این اتفاق ندارند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من به جای این اتفاق به چیزهای خوب میاندیشم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من در مورد اینکه چگونه این موقعیت را تغییر دهم، فکر می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  برای بهترین کار ممکن برنامه ریزی میکنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من فکر می کنم می توانم از این موقعیت چیزی را یاد بگیرم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من فکر می کنم در نتیجه چیزی که برای من  اتفاق افتاده است، قوی تر خواهم شد."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  فکر میکنم چیزی را که من تجربه کردهام در مقایسه با چیزهای دیگر خیلی بد نبوده است."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من به خودم میگویم که در زندگی چیزهای بدتری هم وجود دارد."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من به فکر کردن در مورد وحشتناک بودن تجربهام ادامه میدهم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من عمیقاً به  وحشتناک بودن این موقعیت فکر می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من احساس می کنم که دیگران برای انچه که اتفاق می افتد، مسئولند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        ), t
                    )
                )

                nazmql.add(
                    Question(
                        "  من احساس می کنم که علت اصلی این مشکل دیگران هستند."
                        ,
                        Answer(
                            null, AnswerType.RADIO, listOf("هرگز", "گاهی", "مرتبا", "اغلب", "همیشه")
                        )
                        , t
                    )
                )

                appDatabase.questionDao().insert(nazmql).subscribe { _ -> }

            }

            val socialTestq = Questionnaire(
                0.0,
                1.0,Constants.guideMasaleEjtemai,R.drawable.ejtemai,1,
                "سبک منطقی حل مسئله-سبک اجتنابی حل مسئله-سبک تکانشی حل مسئله" +
                        "=1-2-3-4-5-6,7-8-9-10-11,12-13-14-15-16",
                null,
                "تست حل مسئله اجتماعی",
                QuestionnaireState.FILLED
            )
            appDatabase.questionnaireDao().insert(socialTestq).subscribe { t ->

                val socialql: MutableList<Question> = ArrayList()

                socialql.add(
                    Question(
                        "به راه حل های مختلف فکر می کنم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "اگر تلاش اولم برای حل مسئله با شکست مواجه شود، مایوس می شوم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "ارزیابی می کنم که بعد از تغییر شرایط، چه روحیه ای خواهم داشت "
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "ارزیابی می کنم که آیا تا به حال وضعیت بهتر شده است، یا نه"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "نتایج هر انتخاب را سبک سنگین و مقایسه می کنم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "برای مقایسه انتخاب ها روش منظمی را به کار می برم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "زمانی که با مسئله ای مواجه می شوم، احساس ترس می کنم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "برای ارزیابی دقیق نتایج کارم، وقت صرف نمی کنم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "یک مسئله سخت مرا آشفته می سازد"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "حل مسائل را تا جایی به تعویق می اندازم که دیر نشود"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "بیشتر وقتم را به اجتناب از حل مسائل می گذرانم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "به اعتقاد من هر مسئله ای قابل حل است"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "برای اجتناب از مواجهه با مسائل، از مسیر خود منحرف می شوم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "از تفکر درباره مسائل اجتناب می کنم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "زمانی را برای توجه به اظهار نظرهای موافقان یا مخالفان صرف نمی کنم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                socialql.add(
                    Question(
                        "بدون تفکر با احساس درونی خود درگیر می شوم"
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("به هیچ وجه", "تا حدودی", "متوسط", "زیاد", "خیلی زیاد")
                        )
                        ,
                        t
                    )
                )

                appDatabase.questionDao().insert(socialql).subscribe { _ -> }

            }

            val marriageTestq = Questionnaire(
                0.0,
                1.0,Constants.guideZanashoi,R.drawable.zanashoi,1,
                "ﮐﺎﻫﺶ ﻫﻤﮑﺎري-ﮐﺎﻫﺶ راﺑﻄﻪ ﺟﻨﺴﯽ-اﻓﺰاﯾﺶ واﮐﻨﺶ ﻫﺎي ﻫﯿﺠﺎﻧﯽ-اﻓﺰاﯾﺶ ﺟﻠﺐ ﺣﻤﺎﯾﺖ ﻓﺮزﻧﺪان-اﻓﺰاﯾﺶ راﺑﻄﻪ ﻓﺮدي ﺑﺎ ﺧﻮﯾﺸﺎوﻧﺪان ﺧﻮد-ﮐﺎﻫﺶ راﺑﻄﻪ ﺧﺎﻧﻮادﮔﯽ ﺑﺎ ﺧﻮﯾﺸﺎوﻧﺪان ﻫﻤﺴﺮ و دوﺳﺘﺎن-ﺟﺪا ﮐﺮدن اﻣﻮر ﻣﺎﻟﯽ از ﯾﮑﺪﯾﮕﺮ-ﮐﺎﻫﺶ ارﺗﺒﺎط ﻣﻮﺛﺮ" +
                        "=4-12-18-25-34,5-13-19-35-40,6-14-20-27-36-42-49-51,9-22-31-38-44,8-15-21-29-37-43,1-23-32-46-50-53,2-10-17-24-33-39-48,3-7-11-16-26-28-30-41-45-47-52-54",
                null,
                "تست تعارضات زناشویی",
                QuestionnaireState.FILLED
            )
            appDatabase.questionnaireDao().insert(marriageTestq).subscribe { t ->
                val marriageql: MutableList<Question> = ArrayList()

                marriageql.add(
                    Question(
                        "    هنگام دعوا با همسرم، رابطه من با خانواده پدر و مادري او قطع می شود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    من و همسرم حساب هاي مالی جداگانه داریم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    حرف هاي من و همسرم بدون کنایه و بی پرده گفته می شود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم هرکاري از من بخواهد فراموش می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دلخوري من و همسرم، هیچ کدام براي رابطه جنسی پیش قدم نمی شویم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    من و همسرم کتک کاري می کنیم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم با اظهار نظرهاي خود مرا تحقیر می کند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دلخوري، رابطه من با خانواده پدرو مادري خودم قطع می شود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    براي جلب فرزند (یا فرزندانم) حاضرم انتظارات نامعقول او(یا آنان) را برآورده کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    بدون اطلاع همسرم، براي خود پس انداز شخصی دارم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هیچ وقت احساس بدي را که نسبت به او دارم به او نمی گویم، چون می ترسم عصبانی شود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    وقتی همسرم از من تقاضایی دارد، خودم را به کارهاي دیگر مشغول می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    رابطه جنسی من با همسرم ارضا کننده نیست."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    وقتی با همسرم دعوا می کنم، اتاق یا خانه را براي مدتی ترك می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    به تنهایی با خانواده پدر و مادري خود و خواهر و برادرانم رابطه دارم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگامی که مشکل داریم، غالبا همسرم در جواب من سکوت اختیار می کند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    درآمد همسرم را به بهانه هاي مختلف صرف خواسته هاي شخصی خود می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم را با ناقص انجام دادن کارهایی که از من می خواهد تنبیه می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم رابطه جنسی را به من تحمیل می کند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم را به رفتارهاي غیر اخلاقی متهم می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    از اعضاي خانواده پدري و مادري ام، براي حل اختلافات خود با همسرم، کمک می گیرم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    فرزند (یا فرزندانم)، اسرار همسرم را به من می گویند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    خانواده پدر و مادري همسرم، به من بی احترامی می کنند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    خرج خانواده، در هرصورت (آشتی یا دعوا) در اختیار من است."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم را با بی نظمی و نامرتب بودن، آزار می دهم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    من و همسرم بدون واسطه و مستقیم با هم صحبت می کنیم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    وقتی با همسرم دعوا می کنم، فریاد می زنم و ناسزا می گویم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    آرزو دارم همسرم احساساتش را با من در میان بگذارد."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    خانواده پدر و مادري من، همسرم را با دیگران مقایسه و به حال من دلسوزي می کنند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    از نحوه گفتگو با همسرم بسیار راضی هستم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    فرزند یا فرزندانم یکی از عوامل مهم حفظ و تداوم رابطه زناشویی من هستند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    دوست یا دوستانم در جریان اختلاف من و همسرم هستند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    من و همسرم با مشارکت و توافق یکدیگر پول و درآمد خانواده را خرج می-کنیم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    حوصله ام از همسرم و خواسته هایش سر می رود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دعوا، تنها رابطه من با همسرم رابطه جنسی است."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام نزاع، از همسرم درخواست طلاق می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    خانواده پدري و مادري من، تمام گناه ها را به گردن همسرم می اندازند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دعوا بین من و همسرم، فرزند یا فرزندانم از من حمایت می کنند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    حاضر نیستم براي بدهی هاي همسرم از دیگران پول قرض کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دعوا، رابطه جنسی من و همسرم قطع می شود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    می ترسم خواسته هاي خود را با همسرم مطرح کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    با وجود داشتن همسر، از تنهایی رنج می برم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم را در حضور خانواده پدري و مادري خودم، تحقیر می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دعواي من و همسرم، یکی از فرزندانم زیاد مریض می شود و نیاز به مراقبت دارد."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم شنونده خوبی است."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    همسرم و خانواده اش را در مقابل سایرین تحقیر می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    در ابراز هرگونه احساس واقعی خود براي همسرم، راحتم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    بدون اطلاع همسرم، به والدین خود پول می دهم و یا از آن ها پول می گیرم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    دعوا با همسرم، مرا نسبت به فعالیت هاي خودم بی علاقه می کند."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    با آن دسته از برنامه هاي مهمانی که با حضور خانواده همسرم باشد، مخالفت می کنم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    هنگام دعوا با همسرم نسبت به غذا بی اشتها می شوم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    باورکردن تمام حرف هاي همسرم، برایم دشوار است."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    در هنگام دعوا با همسرم، رابطه من و او با دوستان مشترکمان قطع می شود."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه")
                        ),
                        t
                    )
                )

                marriageql.add(
                    Question(
                        "    من و همسرم با یکدیگر روراست و بی پرده هستیم."
                        ,
                        Answer(
                            null,
                            AnswerType.RADIO,
                            listOf("هرگز", "به ندرت", "گاهی", "اکثرا", "همیشه").reversed()
                        ),
                        t
                    )
                )


                appDatabase.questionDao().insert(marriageql).subscribe { _ -> }

            }


            val killTestq = Questionnaire(
                0.0, 1.0,null,R.drawable.afsordegi,0,
                "آمادگی جهت خودکشی-قصد اقدام به خودکشی" +
                        "=1-2-3-4-5-6-7,8-9-10-11-12-13-14"
                , null, "تست مکمل افسردگی", QuestionnaireState.FILLED
            )
            appDatabase.questionnaireDao().insert(killTestq).subscribe { t ->
                val killql: MutableList<Question> = ArrayList()


                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "زمان کوتاهی به فکر خودکشی می افتم که به سرعت از ذهنم می گذرد."
                                , "گاهی کم و بیش به فکر خودکشی می افتم."
                                , "مدت های طولانی فکر خودکشی را در ذهنم دارم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "بندرت درباره ی خودکشی فکر می کنم."
                                , "گهگاهی درباره ی خودکشی فکر می کنم."
                                , "تقریباً همیشه درباره ی خودکشی فکر می کنم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "فکر درباره ی خودکشی را قبول ندارم."
                                , "فکر درباره ی خودکشی را نه قبول دارم و نه رد می کنم."
                                , "فکر درباره ی خودکشی را قبول دارم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "اگر قصد خودکشی داشته باشم قادر به کنترل خودم هستم."
                                , "مطمئن نیستم که بتوانم خودم را از ارتکاب به خودکشی کنترل کنم."
                                , "اگر قصد خودکشی داشته باشم قادر به کنترل خودم نیستم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "به خاطر خانواده، دوستان، مذهب و مجروحیت ناشی از خودکشی ناموفق قصد خودکشی ندارم."
                                ,
                                "به خاطر خانواده، دوستان، مذهب و مجروحیت ناشی از خودکشی ناموفق، نسبت به اقدام خودکشی تا اندازه ای نگران هستم."
                                ,
                                "نسبت به اقدام به خودکشی نگران خانواده، دوستان، مذهب و مجروحیت ناشی از خودکشی ناموفق نیستم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "دلیل اصلی من از اقدام به خودکشی تأثیر گذاشتن بر دیگران است به طوری که به من توجه شود."
                                ,
                                "منظور من از اقدام به خودکشی فقط تأثیر گذاشتن بر افراد نیست، بلکه راه حلی برای حل مشکلاتم می باشد. "
                                ,
                                "منظور اصلی من از اقدام به خودکی فرار کردن از مشکلات است."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "طرح و برنامه خاصی برای اینکه چطور خودکشی کنم ندارم."
                                , "راه های خودکی را بررسی کرده ام، اما روی جزئیات آن فکر نکرده ام."
                                , "طرح و برنامه خاصی برای اینکه چطور خودکشی کنم در ذهنم دارم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "به یک روش و یا فرصت مناسبی که خودکشی کنم دسترسی ندارم."
                                ,
                                "روشی را که قصد دارم برای خودکشی به کار ببرم وقت زیادی می خواهد و من فرصت بکارگیری این روش را ندارم."
                                ,
                                "روشی را برای خودکشی انتخاب کرده ام و برای عملی کردن آن منتظر فرصت مناسب هستم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "جرأت یا توانایی اقدام به خودکشی را ندارم."
                                , "مطمئن نیستم که جرأت و توانایی ارتکاب به خودکشی را داشته باشم."
                                , "جرأت یا توانایی اقدام به خودکشی را دارم."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "گمان نمی کنم که قصد خودکشی داشته باشم."
                                , "مطمئن نیستم که بخواهم خودکشی کنم."
                                , "مطمئنم که خودکشی خواهم کرد."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "هیچ وسیله ای برای اقدام به خودکشی آماده نکرده ام."
                                , "اندکی وسایل برای اقدام به خودکی آماده کرده ام."
                                , "تقریباً برای اقدام به خودکشی وسایل لازم را آماده کرده ام."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "درباره ی خودکشی ام تا کنون مطلبی ننوشته ام."
                                ,
                                "درباره یادداشت خودکشی ام فکر کرده و یا شروع به نوشتن آن کرده ام، اما کامل نیست."
                                ,
                                "یادداشت خودکشی ام را کامل کرده ام."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "در مورد حوادث و مسایل پس از خودکشی هیچ برنام مشخصی ندارم."
                                ,
                                "در مورد حوادث و مسایل پس از خودکشی تا حدودی برنامه ها را مشخص کرده ام."
                                ,
                                "در مورد حوادث و مسایل پس از خودکشی بطور دقیق برنامه ریزی کرده ام."
                            )
                        ), t
                    )
                )

                killql.add(
                    Question(
                        "یکی از گزینه های زیر را انتخاب کنید.", Answer(
                            null, AnswerType.RADIO, listOf(

                                "دیگران از قصد خودکشی من آگاه هستند"
                                , "در مورد قصد خودکشی خود به دیگران چیزی نگفته ام."
                                , "دیگران از قصد خودکی من آگاه نیستند."
                            )
                        ), t
                    )
                )


                appDatabase.questionDao().insert(killql).subscribe { _ -> }
            }

        }
    }
}