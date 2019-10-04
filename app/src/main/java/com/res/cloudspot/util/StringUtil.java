package com.res.cloudspot.util;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ajacker
 */
public class StringUtil {
    public static SparseArray<String> titles = new SparseArray<>();
    public static HashMap<String, ArrayList<String>> imgUrls = new HashMap<>();
    public static ArrayList<String> poems = new ArrayList<>();

    static {
        titles.put(1, "高积云");
        titles.put(2, "卷云");
        titles.put(3, "层积云");
        titles.put(4, "积云");
        titles.put(5, "荚状云");
    }

    static {
        poems.add("—《关山月》\n明月出天山，\n苍茫云海间");
        poems.add("—《独坐敬亭山》\n众鸟高飞尽 ，\n孤云独去闲。");
        poems.add("—《寻隐者不遇》\n只在此山中，\n云深不知处。");
        poems.add("—《春夜喜雨》\n野径云俱黑，\n江船火独明。");
        poems.add("—《渡荆门送别》\n月下飞天镜，\n云生结海楼。");
        poems.add("—《别董大》\n千里黄云白日曛，\n北风吹雁雪纷纷。");
        poems.add("—《早发白帝城》\n朝辞白帝彩云间，\n千里江陵一日还。");
        poems.add("—《山行》\n远上寒山石径斜，\n白云深处有人家。");
        poems.add("—《凉州词》\n黄河远上白云间，\n一片孤城万仞山。");
        poems.add("—《白雪歌送武判官归京》\n瀚海阑干百丈冰，\n愁云惨淡万里凝。");
        poems.add("—《立春日晓望三素云》\n晴晓初春日，\n高心望素云。\n彩光浮玉辇，\n紫气隐元君。");
        poems.add("—《东峰亭各赋一物得岭上云》\n伫立增远意，\n中峰见孤云。");
        poems.add("—《咏云》\n帝乡白云起，\n飞盖上天衢。");
        poems.add("—《云》\n龙似瞿唐会，\n江依白帝深。");
        poems.add("—《云》\n渡江随鸟影，\n拥树隔猿吟。\n莫隐高唐去，\n枯苗待作霖。");
    }

    static {
        imgUrls.put("高积云", new ArrayList<>(Arrays.asList(
                "https://i.loli.net/2019/09/23/gF6dTfuGk1DNCBi.jpg",
                "https://i.loli.net/2019/09/23/HK2SNmEIsOy6Xea.jpg",
                "https://i.loli.net/2019/09/23/TCi9GrFb6AnMXad.jpg",
                "https://i.loli.net/2019/09/23/Zb57LrH2JDNYURd.jpg",
                "https://i.loli.net/2019/09/23/2tgGjauK85WFlrM.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/GVY4T8XTE4qxo0jFtRjoZTgJikJf2kSJN*S*2ei68.U!/b/dDcBAAAAAAAA&bo=ngKeAgAAAAARFyA!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/0hirm*g.lXjbJKpnIQZAKrtrAoJNEK5kvonfeOsnJCA!/b/dLYAAAAAAAAA&bo=sAEgAQAAAAARF7A!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/bgj9fXXZ8hiJetAo7khRhJZc0gmNdM5xh86L.5wiND8!/b/dLYAAAAAAAAA&bo=wgEsAQAAAAARF84!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/541V6O.lFVzpHvcet7bTx.iS8NZJ8yiVsU*VATAoL4U!/b/dLYAAAAAAAAA&bo=sgN0AgAAAAARF.c!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/yMTHI7D9M*Bfg0DyIovI6g8kHmAZiRxIfrRIssMIgeo!/b/dMMAAAAAAAAA&bo=AASrAgAAAAARF40!&rf=viewer_4.jpg"
        )));

        imgUrls.put("卷云", new ArrayList<>(Arrays.asList(
                "https://i.loli.net/2019/09/23/bYg8QqUndG9zw5D.jpg",
                "https://i.loli.net/2019/09/23/PGsxCcrgF6tWdjY.jpg",
                "https://i.loli.net/2019/09/23/DLpSGRrJ7Uogq5t.jpg",
                "https://m.qpic.cn/psb?/V11qpDYW2MBfNr/4LmzMxyMMpdqviWRmn*mpyYd6MfL0Kn9d.AN17*wplI!/b/dLYAAAAAAAAA&bo=AATJAgAAAAARB*8!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/UL5FJpLDMmtBEpq60i7zVActy9FbVC8OZ*J0Ddn4SB8!/b/dL8AAAAAAAAA&bo=2AKKAQAAAAARF3E!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/MYyYizRp1tgx3OVumb54SoY33ACWpdJp6MLVM7d5iAw!/b/dLYAAAAAAAAA&bo=gAKQAQAAAAARFzM!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/JeFksUEMVhzVd66LyGy6fIVvOGbWGObfGLCCnFnp5Ew!/b/dFMBAAAAAAAA&bo=gALgAQAAAAARF0M!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/HtfyD48ki92xV79ZfRb9TVPhpr9NHgIsZg.XKZAlkHM!/b/dFMBAAAAAAAA&bo=6AObAgAAAAARF1I!&rf=viewer_4.jpg"
        )));

        imgUrls.put("层积云", new ArrayList<>(Arrays.asList(
                "https://i.loli.net/2019/09/23/eqkoQ28sY4MRBjx.jpg",
                "https://i.loli.net/2019/09/23/eBCbcjILaAWQGOZ.jpg",
                "https://i.loli.net/2019/09/23/b4lMf6OU5Ydek9G.jpg",
                "https://i.loli.net/2019/09/23/MWmxXUz6G7IRCTv.jpg",
                "https://i.loli.net/2019/09/23/JkFz15HjyXBxmt9.jpg",
                "https://i.loli.net/2019/09/23/CSIbp9mza5DsWuG.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/yhBl086uwEEJNtnZlXQf4LwXCYYHQkaZ2nZAWvKVQD8!/b/dFMBAAAAAAAA&bo=6AObAgAAAAARF1I!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/8Ki19DFggrQQOJH558GvzeGUF2MERax3hPJ.wbCa8bo!/b/dLYAAAAAAAAA&bo=hANZAgAAAAARF*w!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/dTZQ9Rhk0IOP6o4WC3I0erpQaqsp*ak7dJvewMyADpE!/b/dL4AAAAAAAAA&bo=.QE*AQAAAAARF.Y!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/xZBn*4bgqZDj3i7UvDJFiE7j4e3eLur9bp1w*rREKJE!/b/dMMAAAAAAAAA&bo=lAK4AQAAAAARFw8!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/KAc9039e4wSbW*lAKCfQ51iZP7RyJ0ekY*SAdf.w4S8!/b/dMUAAAAAAAAA&bo=1AN6AgAAAAARF48!&rf=viewer_4.jpg"
        )));
        imgUrls.put("积云", new ArrayList<>(Arrays.asList(
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/c3Rva*.ttG5rGZmiTdyNnky2qMlXQKsL1pS23pJF8*I!/b/dLYAAAAAAAAA&bo=LAGiAQAAAAARF64!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/N16N1WSl*1zlVrG.C7ShNpdCaaj0TTth4sxt5VaeDqo!/b/dMQAAAAAAAAA&bo=FAW8AwAAAAARF44!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/ZI.N.ufnNCiloMbDdvLQKgJSiiXnCii6i.c.1DtW66U!/b/dFIBAAAAAAAA&bo=*wOqAgAAAAARF3Q!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/Oi.dqlm0*sOQVBv*5GENoJJrYrUov2h0uSvo3HeiVZU!/b/dE4BAAAAAAAA&bo=lwFkAgAAAAARF9A!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/gBTNMm3W0sSMvy3m83zPTGd7JUj5h8q6.2M8u1xdsJA!/b/dIMAAAAAAAAA&bo=*wOqAgAAAAARF3Q!&rf=viewer_4.jpg"
        )));

        imgUrls.put("荚状云", new ArrayList<>(Arrays.asList(
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/6eTmbmKMm7CQzKENnrho*wiVqJ0BJ1XCU3lS.rz19fo!/b/dMMAAAAAAAAA&bo=4AFoAQAAAAARF6g!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/eFjwT5u..hr8LiW6oNABUsWtFeHrrK4mpyfUSVL3MtQ!/b/dDUBAAAAAAAA&bo=6AOmAgAAAAARF28!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/nd9k.jNCNjJVNGlGEAUKvym9WYM*dEw09PRqI4jWcIo!/b/dIMAAAAAAAAA&bo=wgOBAgAAAAARF2I!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/mDAAYpKlayMy6cwyJNgDV8cHB7QUI5wFWT.slKLN4pI!/b/dLgAAAAAAAAA&bo=gALgAQAAAAARF0M!&rf=viewer_4.jpg",
                "http://m.qpic.cn/psb?/V11qpDYW2MBfNr/UdpCv2fOnhn.VWU1IRYmn2XcgiCLDh0DAbWTfEy9hMI!/b/dLgAAAAAAAAA&bo=AAXQAgAAAAARF*c!&rf=viewer_4.jpg"
        )));

    }

    public static String addSpaceBetweenWords(String str) {
        return str.replace("", " ").trim();
    }
}
