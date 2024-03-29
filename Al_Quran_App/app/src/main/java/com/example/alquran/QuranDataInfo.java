package com.example.alquran;

import java.util.ArrayList;
import java.util.List;

public class QuranDataInfo {

    // Starting parahs pointer
    public int[] PSP = {
            1,
            150,
            261,
            387,
            520,
            644,
            756,
            905,
            1048,
            1208,
            1336,
            1488,
            1659,
            1817,
            2044,
            2231,
            2502,
            2694,
            2899,
            3244,
            3412,
            3595,
            3761,
            4127,
            4304,
            4554,
            4755,
            5160,
            5306,
            5748,
    };

    // Starting ayats pointer
    public int[] SSP = {
            0,
            7,
            293,
            493,
            669,
            789,
            954,
            1160,
            1235,
            1364,
            1473,
            1596,
            1707,
            1750,
            1802,
            1901,
            2029,
            2140,
            2250,
            2348,
            2483,
            2595,
            2673,
            2791,
            2855,
            2931,
            3159,
            3252,
            3340,
            3409,
            3471,
            3503,
            3533,
            3606,
            3660,
            3705,
            3788,
            3970,
            4058,
            4133,
            4218,
            4272,
            4325,
            4414,
            4473,
            4510,
            4545,
            4583,
            4612,
            4630,
            4675,
            4735,
            4784,
            4846,
            4901,
            4979,
            5075,
            5104,
            5126,
            5150,
            5163,
            5176,
            5188,
            5199,
            5217,
            5229,
            5241,
            5271,
            5323,
            5375,
            5419,
            5447,
            5475,
            5495,
            5551,
            5591,
            5622,
            5672,
            5712,
            5758,
            5800,
            5829,
            5848,
            5884,
            5909,
            5931,
            5948,
            5967,
            5993,
            6023,
            6043,
            6058,
            6079,
            6090,
            6098,
            6106,
            6125,
            6130,
            6148,
            6146,
            6157,
            6168,
            6176,
            6179,
            6188,
            6193,
            6197,
            6204,
            6207,
            6213,
            6216,
            6221,
            6225,
            6230,
    };

    public int[] surahAyatCount = {
            7,
            286,
            200,
            176,
            120,
            165,
            206,
            75,
            129,
            109,
            123,
            111,
            43,
            52,
            99,
            128,
            111,
            110,
            98,
            135,
            112,
            78,
            118,
            64,
            77,
            227,
            93,
            88,
            69,
            60,
            34,
            30,
            73,
            54,
            45,
            83,
            182,
            88,
            75,
            85,
            54,
            53,
            89,
            59,
            37,
            35,
            38,
            29,
            18,
            45,
            60,
            49,
            62,
            55,
            78,
            96,
            29,
            22,
            24,
            13,
            14,
            11,
            11,
            18,
            12,
            12,
            30,
            52,
            52,
            44,
            28,
            28,
            20,
            56,
            40,
            31,
            50,
            40,
            46,
            42,
            29,
            19,
            36,
            25,
            22,
            17,
            19,
            26,
            30,
            20,
            15,
            21,
            11,
            8,
            8,
            19,
            5,
            8,
            8,
            11,
            11,
            8,
            3,
            9,
            5,
            4,
            7,
            3,
            6,
            3,
            5,
            4,
            5,
            6
    };

    public String[] englishParahName = {"Alif Lam Meem",
            "Sayaqool ",
            "Tilkal Rusull",
            "Lan Tana Loo",
            "Wal Mohsanat",
            "La Yuhibbullah",
            "Wa Iza Samiu",
            "Wa Lau Annana",
            "Qalal Malao",
            "Wa A'lamu",
            "Yatazeroon ",
            "Wa Mamin Da'abat",
            "Wa Ma Ubrioo",
            "Rubama",
            "Subhanallazi",
            "Qal Alam",
            "Aqtarabo",
            "Qadd Aflaha",
            "Wa Qalallazina",
            "A'man Khalaq",
            "Utlu Ma Oohi",
            "Wa Manyaqnut",
            "Wa Mali",
            "Faman Azlam",
            "Elahe Yuruddo",
            "Ha'a Meem",
            "Qala Fama Khatbukum",
            "Qadd Sami Allah",
            "Tabarakallazi",
            "Amma Yatasa'aloon",
    };

    public String[] ParahName = {"الم ",
            "سیقول ",
            "تلک الرسل ",
            "لن تنالوالبر",
            "والمحصنت",
            "لایحب اللہ ",
            "واذاسمعوا",
            "ولواننا",
            "قال الملاء",
            "واعلموا",
            "یعتذرون ",
            "ومامن دآبۃ",
            "وماابرئ",
            "ربما",
            "سبحن الذی ",
            "قال الم ",
            "اقترب للناس",
            "قد افلح",
            "وقال الذین ",
            "امن خلق",
            "اتل مااوحی",
            "ومن یقنت ",
            "ومالی ",
            "فمن اظلم ",
            "الیہ یرد",
            "حم ",
            "قال فماخطبکم ",
            "قدسمع اللہ ",
            "تبارک الذی ",
            "عم یتسآءلون ",
    };

    public String[] englishSurahNames = {"Al-Fatihah",
            "Al-Baqara ",
            "Al-i'Imran ",
            "An-Nisaa ",
            "Al-Maidah ",
            "Al-An'am ",
            "Al-A'raf ",
            "Al-Anfal ",
            "At-Tauba ",
            "Yunus ",
            "Hud ",
            "Yusuf ",
            "Ar-Ra'd ",
            "Ibrahim ",
            "Al-Hijr ",
            "An-Nahl ",
            "Al-Israa ",
            "Al-Kahf ",
            "Maryam ",
            "Ta-ha ",
            "Al-Anbiyaa ",
            "Al-Hajj ",
            "Al-Muminun ",
            "An-Nur ",
            "Al-Furqan ",
            "Ash-Shu'araa ",
            "An-Naml ",
            "Al-Qasas ",
            "Al-Ankabut ",
            "Ar-Rum ",
            "Luqman ",
            "As-Sajda ",
            "Al-Ahzab ",
            "Saba ",
            "Fatir ",
            "Ya-Sin ",
            "As-Saffat ",
            "Sad ",
            "Az-Zumar ",
            "Al-Mu'min ",
            "Ha-Mim ",
            "Ash-Shura ",
            "Az-Zukhruf ",
            "Ad-Dukhan ",
            "Al-Jathiya ",
            "Al-Ahqaf ",
            "Muhammad ",
            "Al-Fat-h ",
            "Al-Hujurat ",
            "Qaf ",
            "Az-Zariyat ",
            "At-Tur ",
            "An-Najm ",
            "Al-Qamar ",
            "Ar-Rahman ",
            "Al-Waqi'a ",
            "Al-Hadid ",
            "Al-Mujadila ",
            "Al-Hashr ",
            "Al-Mumtahana ",
            "As-Saff ",
            "Al-Jumu'a ",
            "Al-Munafiqun ",
            "At-Tagabun ",
            "At-Talaq ",
            "At-Tahrim ",
            "Al-Mulk ",
            "Al-Qalam ",
            "Al-Haqqa ",
            "Al-Ma'arij ",
            "Nuh ",
            "Al-Jinn ",
            "Al-Muzzammil ",
            "Al-Muddathth ",
            "Al-Qiyamat ",
            "Ad-Dahr ",
            "Al-Mursalat ",
            "An-Nabaa ",
            "An-Nazi'at ",
            "Abasa ",
            "At-Takwir ",
            "Al-Infitar ",
            "Al-Mutaffife ",
            "Al-Inshiqaq ",
            "Al-Buruj ",
            "At-Tariq ",
            "Al-A'la ",
            "Al-Gashiya ",
            "Al-Fajr ",
            "Al-Balad ",
            "Ash-Shams ",
            "Al-Lail ",
            "Adh-Dhuha ",
            "Al-Sharh ",
            "At-Tin ",
            "Al-Alaq ",
            "Al-Qadr ",
            "Al-Baiyina ",
            "Al-Zalzalah ",
            "Al-Adiyat ",
            "Al-Qari'a ",
            "At-Takathur ",
            "Al-Asr ",
            "Al-Humaza ",
            "Al-Fil ",
            "Quraish ",
            "Al-Ma'un ",
            "Al-Kauthar ",
            "Al-Kafirun ",
            "An-Nasr ",
            "Al-Lahab ",
            "Al-Ikhlas ",
            "Al-Falaq ",
            "Al-Nas ",
    };

    public String[] urduSurahNames = {
            "الفاتحۃ",
            "البقرۃ",
            "آل عمران",
            "النسآء",
            "المآئدۃ",
            "الانعام",
            "الاعراف",
            "الانفال",
            "التوبۃ",
            "یونس",
            "ھود",
            "یوسف",
            "الرعد",
            "ابراھیم",
            "الحجر",
            "النحل",
            "الاسراء",
            "الکہف",
            "مریم",
            "طٰہٰ",
            "الانبیآء",
            "الحج",
            "المؤمنون",
            "النور",
            "الفرقان",
            "الشعراء",
            "النمل",
            "القصص",
            "العنکبوت",
            "الروم",
            "لقمٰن",
            "السجدۃ",
            "الاحزاب",
            "سبا",
            "فاطر",
            "یٰسٓ",
            "الصّٰفّٰت",
            "صٓ",
            "الزمر",
            "الغافر",
            "فصلت",
            "الشُّورٰی",
            "الزُّخرُف",
            "الدُّخَان",
            "الجاثیہ",
            "الاحقاف",
            "محمد",
            "الفتح",
            "الحجرات",
            "قٓ",
            "الذّٰریٰت",
            "الطّور",
            "النجم",
            "القمر",
            "الرحمٰن",
            "الواقعۃ",
            " الحدید",
            "المجادلۃ",
            "الحشر",
            " الممتحنۃ",
            "الصّف",
            "الجُمعۃ",
            "المُنٰفِقُون",
            " التّغابن",
            "الطّلاق",
            "التحریم",
            "الملک",
            "القلم",
            " الحاقہ",
            "المعارج",
            "نُوح",
            "الجن",
            "المزّمّل",
            "المدّثّر",
            "القیٰمۃ",
            "الانسان",
            "المرسلٰت",
            "النَّبَاِ",
            "النّٰزِعٰتِ",
            "عَبَسَ",
            " التَّکوِیر",
            " الاِنفِطَار",
            "المُطَفِّفِین",
            "الاِنشِقَاق",
            "البُرُوج",
            "الطَّارق",
            "الاَعلیٰ",
            "الغَاشِیَۃ",
            "الفجر",
            "البلد",
            " الشَّمس",
            "الَّیل",
            "الضُّحٰی",
            "الم نشرح",
            "التّین",
            "العَلَق",
            " القدر",
            "البیّنۃ",
            "الزّلزال",
            "العٰدیٰت",
            " القارعۃ",
            "التّکاثُر",
            " العصر",
            "الھُمَزَۃ",
            "الفِیل",
            "قُرَیش",
            "المَاعُون",
            "الکوثر",
            "الکٰفرون",
            "النَّصَر",
            "اللَّھب",
            "الاخلاص",
            "الفَلَق",
            " النَّاس"
    };


    // Utilities Functions

    public int getSurahVerses(int surahNumber) {
        return surahAyatCount[surahNumber];
    }

    public List<String> getUrduSurahNames() {
        List<String> urdulist = new ArrayList<>();
        for (int index = 0; index < urduSurahNames.length; ++index)
            urdulist.add(urduSurahNames[index]);
        return urdulist;
    }

    public List<String> getEnglishSurahNames() {
        List<String> englishlist = new ArrayList<>();
        for (int index = 0; index < englishSurahNames.length; ++index)
            englishlist.add(englishSurahNames[index]);
        return englishlist;
    }

    public int getSurahStart(int surahNumber) {
        return SSP[surahNumber];
    }

    public int getParahStart(int parahNumber) {
        return PSP[parahNumber];
    }

}
