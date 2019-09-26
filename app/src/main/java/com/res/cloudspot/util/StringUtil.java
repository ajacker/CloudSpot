package com.res.cloudspot.util;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ajacker
 */
public class StringUtil {
    public static SparseArray<String> comments = new SparseArray<>();
    public static SparseArray<String> titles = new SparseArray<>();
    public static HashMap<String, ArrayList<String>> imgUrls = new HashMap<>();
    public static ArrayList<String> poems = new ArrayList<>();

    static {
        titles.put(1, "高积云");
        comments.put(1, "简写Ac，属中云族。主要由中云高度上稳定而湿润空气发生波动所形成。" +
                "云体呈块状、片状或球状；云块有时分散孤立，有时聚集成群，排列成行，好象田垄或波浪；" +
                "云块常呈白色或灰色，中部较阴暗；云体各部分的透光程度不同，薄的部分能见日、月轮廓，有时出现华和虹彩现象。薄的高积云稳定少变，" +
                "一般预示天晴；厚的高积云如继续增厚，有时也有零星雨雪。其类别有透光、蔽光、积云性、絮状、荚状、堡状等高积云数种。");
        titles.put(2, "卷云");
        comments.put(2, "卷云，属于高云族。它有时产生在能生成云的最高高度上，云底一般在4500至10000米。" +
                "它由高空的细小冰晶组成，且冰晶比较稀疏，故云比较薄而透光良好，色泽洁白并具有冰晶的亮泽。" +
                "卷云按外形、结构等特征，分为毛卷云和钩卷云、伪卷云、密卷云四类");
        titles.put(3, "层积云");
        comments.put(3, "层积云，属低云族，结构松散的大云块、大云条(滚轴状)组成的云层，" +
                "有时排列成行；颜色灰白或灰色；云块的视角宽度通常大于5°；主要由空气的波动和乱流混合作用形成，" +
                "一般由水滴构成，北方和高原地区严寒季节可由水滴、冰晶、雪花构成，厚者可降间歇性小雨雪，南方有时可有较大降水；" +
                "根据其形状特征可分透光、蔽光、积云性、荚状、堡状等层积云数种。");
        titles.put(4, "积云");
        comments.put(4, "积云（cumulus）是由水滴组成，但有时可伴有冰晶，它主要是由空气对流上升冷却使水汽发生凝结而形成的。因此，积云的外形特征与空气对流运动的特点紧密相联。\n" +
                "积云通常在湿润地区和热带地区出现，但有时也会在干燥地区出现。除非积云变成积雨云，否则不会出现阵雨，尤为正午后形成的云堆和积雨云表示阵雨很可能出现。");
        titles.put(5, "荚状云");
        comments.put(5, "荚状云，又称飞碟云，是指一种状如飞碟云形，以透镜形状出现在高空，通常形成于顺风的右侧。" +
                "常被误认为外星飞船或不明飞行物。它是一种自然天气现象，经常在湿润空气经过山脉上空时出现。如果空气在经过山脉上空时温度下降，空气中的水分就会凝结成荚状云。");
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
