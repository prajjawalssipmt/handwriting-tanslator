/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.yannismarkou.resources;

/**
 *
 * @author yannis
 */
public class Resources {

    public static String gAPIKey = "ADD HERE";
    public static String cx = "ADD HERE";

    public static String GVISION_TARGET_URL
            = "https://vision.googleapis.com/v1/images:annotate?";
    public static String GTRANSLATE_BASE_URL = 
            "https://www.googleapis.com/language/translate/v2?key=";
    
    public static String GSEARCH_BASE_URL = 
            "https://www.googleapis.com/customsearch/v1?key=";
    
    public static String GSEARCH_BASE_URL_3 = 
            "&cx=" + cx + "&hl=";
    
    public static String GSEARCH_BASE_URL_5 = "&q=";
    
    public static String gVisionJSON_pt1 = "{\n"
            + "  \"requests\":[\n"
            + "    {\n"
            + "      \"image\":{\n"
            + "        \"content\": \"";
    public static String gVisionJSON_pt3 = "\"\n"
            + "      },\n"
            + "      \"features\": [\n"
            + "        {\n"
            + "          \"type\":\"TEXT_DETECTION\",\n"
            + "          \"maxResults\":1\n"
            + "        }\n"
            + "      ]\n"
            + "    }\n"
            + "  ]\n"
            + "}";

    public static String[] languages = {
        "Afrikaans",
        "Albanian",
        "Amharic",
        "Arabic",
        "Armenian",
        "Azeerbaijani",
        "Basque",
        "Belarusian",
        "Bengali",
        "Bosnian",
        "Bulgarian",
        "Catalan",
        "Cebuano",
        "Chichewa",
        "Chinese (Simplified)",
        "Chinese (Traditional)",
        "Corsican",
        "Croatian",
        "Czech",
        "Danish",
        "Dutch",
        "English",
        "Esperanto",
        "Estonian",
        "Filipino",
        "Finnish",
        "French",
        "Frisian",
        "Galician",
        "Georgian",
        "German",
        "Greek",
        "Gujarati",
        "Haitian Creole",
        "Hausa",
        "Hawaiian",
        "Hebrew",
        "Hindi",
        "Hmong",
        "Hungarian",
        "Icelandic",
        "Igbo",
        "Indonesian",
        "Irish",
        "Italian",
        "Japanese",
        "Javanese",
        "Kannada",
        "Kazakh",
        "Khmer",
        "Korean",
        "Kurdish",
        "Kyrgyz",
        "Lao",
        "Latin",
        "Latvian",
        "Lithuanian",
        "Luxembourgish",
        "Macedonian",
        "Malagasy",
        "Malay",
        "Malayalam",
        "Maltese",
        "Maori",
        "Marathi",
        "Mongolian",
        "Burmese",
        "Nepali",
        "Norwegian",
        "Pashto",
        "Persian",
        "Polish",
        "Portuguese",
        "Punjabi",
        "Romanian",
        "Russian",
        "Samoan",
        "Scots Gaelic",
        "Serbian",
        "Sesotho",
        "Shona",
        "Sindhi",
        "Sinhala",
        "Slovak",
        "Slovenian",
        "Somali",
        "Spanish",
        "Sundanese",
        "Swahili",
        "Swedish",
        "Tajik",
        "Tamil",
        "Telugu",
        "Thai",
        "Turkish",
        "Ukrainian",
        "Urdu",
        "Uzbek",
        "Vietnamese",
        "Welsh",
        "Xhosa",
        "Yiddish",
        "Yoruba",
        "Zulu"};

    public static String[] languageCodes = {
        "af",
        "sq",
        "am",
        "ar",
        "hy",
        "az",
        "eu",
        "be",
        "bn",
        "bs",
        "bg",
        "ca",
        "ceb",
        "ny",
        "zh-CN",
        "zh-TW",
        "co",
        "hr",
        "cs",
        "da",
        "nl",
        "en",
        "eo",
        "et",
        "tl",
        "fi",
        "fr",
        "fy",
        "gl",
        "ka",
        "de",
        "el",
        "gu",
        "ht",
        "ha",
        "haw",
        "iw",
        "hi",
        "hmn",
        "hu",
        "is",
        "ig",
        "id",
        "ga",
        "it",
        "ja",
        "jw",
        "kn",
        "kk",
        "km",
        "ko",
        "ku",
        "ky",
        "lo",
        "la",
        "lv",
        "lt",
        "lb",
        "mk",
        "mg",
        "ms",
        "ml",
        "mt",
        "mi",
        "mr",
        "mn",
        "my",
        "ne",
        "no",
        "ps",
        "fa",
        "pl",
        "pt",
        "ma",
        "ro",
        "ru",
        "sm",
        "gd",
        "sr",
        "st",
        "sn",
        "sd",
        "si",
        "sk",
        "sl",
        "so",
        "es",
        "su",
        "sw",
        "sv",
        "tg",
        "ta",
        "te",
        "th",
        "tr",
        "uk",
        "ur",
        "uz",
        "vi",
        "cy",
        "xh",
        "yi",
        "yo",
        "zu"};

}
