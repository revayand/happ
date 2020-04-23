


















package co.health.test.corona.screen.main.home.question.dummy

import co.health.test.corona.repository.db.entities.*
import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContentQuestionnaire {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<QuestionnaireWithQuestions> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, QuestionnaireWithQuestions> = HashMap()

    private val COUNT = 2

    init {
        val vasvasTestq = Questionnaire("تست وسواس", QuestionnaireState.FILLED)
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
            )
        )


        val afsordegiTestq = Questionnaire("تست افسردگی", QuestionnaireState.FILLED)
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
                ), 0
            )
        )
        afsordegiql.add(
            Question(
                "به هر سوال مطابق با روحیات خود پاسخ بدهید ",
                Answer(
                    null,
                    AnswerType.RADIO,
                    listOf(

                        "به آِینده امیدوارم ",
                        "به آینده امیدی ندارم",
                        "احساس می کنم آینده امید بخشی در انتظارم نیست",
                        "کمترین روزنه امیدی ندارم"
                    )
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
            )
        )

        val ezterabTestq = Questionnaire("تست اضطراب", QuestionnaireState.FILLED)
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
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
                ), 0
            )
        )

        ITEMS.add(QuestionnaireWithQuestions(vasvasTestq,vasvalql))
        ITEMS.add(QuestionnaireWithQuestions(afsordegiTestq,afsordegiql))
        ITEMS.add(QuestionnaireWithQuestions(ezterabTestq,ezterabql))

    }

    private fun addItem(item: QuestionnaireWithQuestions) {
        ITEMS.add(item)
//        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): DummyItem {
        return DummyItem(position.toString(), "Item " + position, makeDetails(position), "")
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val title: String, val desc: String, val state: String) {
        override fun toString(): String = desc

    }

}
