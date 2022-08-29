package com.example.alquran;

public class SurahInfo {

    private String SurahNumber;
    private String SurahEnglishName;
    private String SurahUrduName;
    private String AyatCount;

    public SurahInfo(String surahNumber, String surahEnglishName, String surahUrduName, String ayatCount) {
        SurahNumber = surahNumber;
        SurahEnglishName = surahEnglishName;
        SurahUrduName = surahUrduName;
        AyatCount = ayatCount;
    }

    public String getSurahNumber() {
        return SurahNumber;
    }

    public void setSurahNumber(String surahNumber) {
        SurahNumber = surahNumber;
    }

    public String getSurahEnglishName() {
        return SurahEnglishName;
    }

    public void setSurahEnglishName(String surahEnglishName) {
        SurahEnglishName = surahEnglishName;
    }

    public String getSurahUrduName() {
        return SurahUrduName;
    }

    public void setSurahUrduName(String surahUrduName) {
        SurahUrduName = surahUrduName;
    }

    public String getAyatCount() {
        return AyatCount;
    }

    public void setAyatCount(String ayatCount) {
        AyatCount = ayatCount;
    }
}
