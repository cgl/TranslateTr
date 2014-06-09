package com.analyser.Lexicons;

import java.util.HashMap;

/**
 * Created by cagil on 08/06/14.
 */
public class Lexicon {
    private static final HashMap<String,String> verb = new HashMap<String,String>();
    static
    {
        verb.put("adjust", "ayarlamak");
        verb.put("set", "kurmak");
        verb.put("insert", "sokmak");
        verb.put("position", "yerleStirmek");
        verb.put("situate", "yerleStirmek");
        verb.put("raise", "yUkseltmek");
        verb.put("put", "koymak");
        verb.put("lift", "kaldIrmak");
        verb.put("open", "aCmak");
        verb.put("close", "kapatmak");
        verb.put("clip", "klipslenmek");
        verb.put("unlock", "aCmak");
        verb.put("lock", "kitlemek");
        verb.put("slide", "kaydIrmak");
        verb.put("space", "konumlandIrmak");
        verb.put("draw", "cizmek");
        verb.put("show", "gOstermek");
        verb.put("select", "seCmek");
        verb.put("choose", "seCmek");
        verb.put("be", "olmak");
        verb.put("become", "olmak");
        verb.put("place", "yerleStirmek");
        verb.put("mark", "iSaretlemek");
        verb.put("push", "itmek");
        verb.put("pull", "cekmek");
        verb.put("see", "bakmak");
        verb.put("buy", "almak");
        verb.put("sell", "satmak");
        verb.put("draw", "cizmek");
        verb.put("leave", "bIrakmak");
        verb.put("fold", "katlamak");
        verb.put("wait", "beklemek");
        verb.put("go", "gitmek");
        verb.put("make", "yapmak");
        verb.put("do", "yapmak");
        verb.put("use", "kullanmak");
        verb.put("call", "aramak");
        verb.put("try", "denemek");
        verb.put("provide", "saGlamak");
        verb.put("change", "deGiStirmek");
        verb.put("update", "yenilemek");
        verb.put("start", "baSlatmak");
        verb.put("shift", "kaydIrmak");
    }

    private static final HashMap<String,String> noun = new HashMap<String,String>();
    static {
        noun.put("sprocket","diSli Cark");
        noun.put("paper", "kaGIt");
        noun.put("chute", "oluk");
        noun.put("guide", "kIlavuz");
        noun.put("printer", "yazIcI");
        noun.put("support", "destek");
        noun.put("bar", "bar");
        noun.put("rear", "arka");
        noun.put("back", "arka");
        noun.put("position", "pozisyon");
        noun.put("situation", "durum");
        noun.put("tractor", "cekici");
        noun.put("cover", "kapak");
        noun.put("place", "yer");
        noun.put("width", "geniSlik");
        noun.put("height", "yUkseklik");
        noun.put("slider", "silindir");
        noun.put("roller", "makara");
        noun.put("lever", "lOvye");
        noun.put("display", "ekran");
        noun.put("user", "kullanIcI");
        noun.put("right", "saG");
        noun.put("left", "sol");
        noun.put("hole", "delik");
        noun.put("drawing", "cizim");
        noun.put("load", "yUk");
        noun.put("loading", "yUkleme");
        noun.put("section", "bOlUm");
        noun.put("setup", "kurum");
        noun.put("box", "kutu");
        noun.put("manual", "kitapcIk");
        noun.put("usage", "kullanIm");
        noun.put("button", "dUGme");
        noun.put("sign", "iSaret");
        noun.put("error", "hata");
        noun.put("message", "mesaj");
        noun.put("noise", "gUrUltU");
        noun.put("second", "saniye");
        noun.put("minute", "dakika");
        noun.put("hour", "saat");
        noun.put("day", "gUn");
        noun.put("power", "gUC");
        noun.put("selection", "seCim");
        noun.put("you", "sen");
        noun.put("us", "biz");
        noun.put("it", "o");
        noun.put("cartridge", "kartuS");
        noun.put("document", "dOkUman");
        noun.put("color", "renk");
        noun.put("colorless", "renksiz");
        noun.put("manufacturer", "uretici");
        noun.put("ink", "mUrekkep");
        noun.put("toner", "toner");
        noun.put("font", "font");
        noun.put("command", "komut");
        noun.put("setting", "ayar");
        noun.put("style", "sitil");
        noun.put("status", "durum");
        noun.put("help", "yardIm");
        noun.put("control", "kontrol");
        noun.put("panel", "panel");
        noun.put("update", "gUncelleme");
        noun.put("inserting", "yerleStirme");
    }
    HashMap<String,String> adj;
    HashMap<String,String> adv;
    HashMap<String,String> pp;
    //private static Lexicon instance = new Lexicon();

    private Lexicon(){}

    //public static Lexicon getInstance() { return instance;}
    public static String getVerbs(String engVerb){
         return verb.get(engVerb.toLowerCase());
    }
    public void getVerbs(){
        //this.verb = new HashMap<String,String>();
        verb.put("is","dir");
        verb.put("are","dirlar");
        verb.put("adjust", "ayarlamak");
        verb.put("set", "kurmak");
        verb.put("insert", "sokmak");
        verb.put("position", "yerleStirmek");
        verb.put("situate", "yerleStirmek");
        verb.put("raise", "yUkseltmek");
        verb.put("put", "koymak");
        verb.put("lift", "kaldIrmak");
        verb.put("open", "aCmak");
        verb.put("close", "kapatmak");
        verb.put("clip", "klipslenmek");
        verb.put("unlock", "aCmak");
        verb.put("lock", "kitlemek");
        verb.put("slide", "kaydIrmak");
        verb.put("space", "konumlandIrmak");
        verb.put("draw", "cizmek");
        verb.put("show", "gOstermek");
        verb.put("select", "seCmek");
        verb.put("choose", "seCmek");
        verb.put("be", "olmak");
        verb.put("become", "olmak");
        verb.put("place", "yerleStirmek");
        verb.put("mark", "iSaretlemek");
        verb.put("push", "itmek");
        verb.put("pull", "cekmek");
        verb.put("see", "bakmak");
        verb.put("buy", "almak");
        verb.put("sell", "satmak");
        verb.put("draw", "cizmek");
        verb.put("leave", "bIrakmak");
        verb.put("fold", "katlamak");
        verb.put("wait", "beklemek");
        verb.put("go", "gitmek");
        verb.put("make", "yapmak");
        verb.put("do", "yapmak");
        verb.put("use", "kullanmak");
        verb.put("call", "aramak");
        verb.put("try", "denemek");
        verb.put("provide", "saGlamak");
        verb.put("change", "deGiStirmek");
        verb.put("update", "yenilemek");
        verb.put("start", "baSlatmak");
        verb.put("shift", "kaydIrmak");
    }

    private static final HashMap<String,String> adverb = new HashMap<String,String>();
    static {
        adverb.put("firmly", "sIkIca");
        adverb.put("gently", "nazikce");
        adverb.put("evenly", "eSitce");
        adverb.put("again", "tekrar");
        adverb.put("slowly", "yavaSca");
        adverb.put("quickly", "hIzlIca");
        adverb.put("now", "simdi");
        adverb.put("later", "sonra");
        adverb.put("also", "ayrIca");
        adverb.put("already", "onceden");
        adverb.put("only", "sadece");
    }

    private static final HashMap<String,String> adjective = new HashMap<String,String>();
    static {
        adjective.put("right", "saG");
        adjective.put("left", "sol");
        adjective.put("down", "aSaGI");
        adjective.put("up", "yukarI");
        adjective.put("different", "farklI");
        adjective.put("alternative", "alternatif");
        adjective.put("other", "diGer");
        adjective.put("same", "aynI");
        adjective.put("closed", "kapalI");
        adjective.put("available", "kullanIlabilir");
        adjective.put("unavailable", "kullanIlamaz");
        adjective.put("colored", "renkli");
        adjective.put("ready", "hazIr");
        adjective.put("clear", "temiz");
        adjective.put("dirty", "kirli");
        adjective.put("broken", "bozulmuS");
        adjective.put("next", "sonraki");
        adjective.put("previous", "onceki");
        adjective.put("last", "son");
        adjective.put("default", "ontanImlI");
        adjective.put("technical", "teknik");
    }

    private static final HashMap<String,String> collocation = new HashMap<String,String>();
    static {
        collocation.put("make sure" , "emin olmak" );
    }
    public static String getNoun(String lemma) {
        return noun.get(lemma.toLowerCase());
    }

    public static String getRB(String advp) {
        return adverb.get(advp.toLowerCase());    }

    public static String getJJ(String adj) {
        return adjective.get(adj.toLowerCase());    }

    public static String getCollocation(String col) {
        return collocation.get(col.toLowerCase());    }
}
