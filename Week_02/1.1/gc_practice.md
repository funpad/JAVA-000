# GC的实弹演习
- 串行、并行、CMS、G1基于java8
- ZGC基于java15

## 1、串行GC

执行：
```shell
$ java -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseSerialGC GCLogAnalysis 
```
输出：
```console
正在执行...
2020-10-27T23:23:14.506-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.507-0800: [DefNew: 139776K->17472K(157248K), 0.0396217 secs] 139776K->46186K(506816K), 0.0400630 secs] [Times: user=0.02 sys=0.01, real=0.04 secs]
2020-10-27T23:23:14.578-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.578-0800: [DefNew: 157248K->17469K(157248K), 0.0465562 secs] 185962K->88581K(506816K), 0.0466043 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
2020-10-27T23:23:14.646-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.646-0800: [DefNew: 156825K->17472K(157248K), 0.0427170 secs] 227937K->137218K(506816K), 0.0427676 secs] [Times: user=0.02 sys=0.02, real=0.04 secs]
2020-10-27T23:23:14.718-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.719-0800: [DefNew: 157248K->17472K(157248K), 0.0308499 secs] 276994K->175580K(506816K), 0.0308994 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2020-10-27T23:23:14.772-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.772-0800: [DefNew: 157248K->17471K(157248K), 0.0504960 secs] 315356K->224132K(506816K), 0.0505392 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
2020-10-27T23:23:14.847-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.847-0800: [DefNew: 157247K->17471K(157248K), 0.0452273 secs] 363908K->270668K(506816K), 0.0452930 secs] [Times: user=0.02 sys=0.02, real=0.05 secs]
2020-10-27T23:23:14.913-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.913-0800: [DefNew: 157247K->17469K(157248K), 0.0476242 secs] 410444K->315638K(506816K), 0.0476724 secs] [Times: user=0.02 sys=0.02, real=0.05 secs]
2020-10-27T23:23:14.986-0800: [GC (Allocation Failure) 2020-10-27T23:23:14.986-0800: [DefNew: 157245K->157245K(157248K), 0.0000189 secs]2020-10-27T23:23:14.986-0800: [Tenured: 298168K->265832K(349568K), 0.0667445 secs] 455414K->265832K(506816K), [Metaspace: 2696K->2696K(1056768K)], 0.0668146 secs] [Times: user=0.06 sys=0.00, real=0.07 secs]
2020-10-27T23:23:15.075-0800: [GC (Allocation Failure) 2020-10-27T23:23:15.075-0800: [DefNew: 139776K->17471K(157248K), 0.0153869 secs] 405608K->313748K(506816K), 0.0154300 secs] [Times: user=0.01 sys=0.00, real=0.02 secs]
2020-10-27T23:23:15.115-0800: [GC (Allocation Failure) 2020-10-27T23:23:15.115-0800: [DefNew: 157080K->17471K(157248K), 0.0391998 secs] 453357K->360042K(506816K), 0.0392705 secs] [Times: user=0.02 sys=0.01, real=0.04 secs]
2020-10-27T23:23:15.178-0800: [GC (Allocation Failure) 2020-10-27T23:23:15.178-0800: [DefNew: 157247K->157247K(157248K), 0.0000204 secs]2020-10-27T23:23:15.178-0800: [Tenured: 342570K->314230K(349568K), 0.0546816 secs] 499818K->314230K(506816K), [Metaspace: 2697K->2697K(1056768K)], 0.0547592 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
2020-10-27T23:23:15.255-0800: [GC (Allocation Failure) 2020-10-27T23:23:15.255-0800: [DefNew: 139776K->139776K(157248K), 0.0000196 secs]2020-10-27T23:23:15.255-0800: [Tenured: 314230K->320459K(349568K), 0.0573620 secs] 454006K->320459K(506816K), [Metaspace: 2697K->2697K(1056768K)], 0.0574355 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
2020-10-27T23:23:15.334-0800: [GC (Allocation Failure) 2020-10-27T23:23:15.334-0800: [DefNew: 139776K->139776K(157248K), 0.0000222 secs]2020-10-27T23:23:15.335-0800: [Tenured: 320459K->308423K(349568K), 0.0606217 secs] 460235K->308423K(506816K), [Metaspace: 2697K->2697K(1056768K)], 0.0607031 secs] [Times: user=0.06 sys=0.01, real=0.06 secs]
执行结束!共生成对象次数:6879
Heap
 def new generation   total 157248K, used 46507K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,  33% used [0x00000007a0000000, 0x00000007a2d6adf8, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
  to   space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
 tenured generation   total 349568K, used 308423K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  88% used [0x00000007aaaa0000, 0x00000007bd7d1e40, 0x00000007bd7d2000, 0x00000007c0000000)
 Metaspace       used 2703K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```

## 2、并行GC

执行：
```shell
$ java -XX:+UseParallelGC \
-Xms512m -Xmx512m \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps GCLogAnalysis
```

输出：
```console
正在执行...
2020-10-27T23:29:02.519-0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21486K(153088K)] 131584K->40888K(502784K), 0.0160453 secs] [Times: user=0.02 sys=0.07, real=0.02 secs]
2020-10-27T23:29:02.563-0800: [GC (Allocation Failure) [PSYoungGen: 153070K->21498K(153088K)] 172472K->84224K(502784K), 0.0312856 secs] [Times: user=0.03 sys=0.13, real=0.03 secs]
2020-10-27T23:29:02.618-0800: [GC (Allocation Failure) [PSYoungGen: 152594K->21501K(153088K)] 215320K->126841K(502784K), 0.0266110 secs] [Times: user=0.04 sys=0.10, real=0.03 secs]
2020-10-27T23:29:02.667-0800: [GC (Allocation Failure) [PSYoungGen: 153085K->21500K(153088K)] 258425K->172009K(502784K), 0.0269507 secs] [Times: user=0.06 sys=0.09, real=0.03 secs]
2020-10-27T23:29:02.727-0800: [GC (Allocation Failure) [PSYoungGen: 153058K->21492K(153088K)] 303567K->215035K(502784K), 0.0305574 secs] [Times: user=0.06 sys=0.08, real=0.03 secs]
2020-10-27T23:29:02.788-0800: [GC (Allocation Failure) [PSYoungGen: 153076K->21503K(80384K)] 346619K->261673K(430080K), 0.0319223 secs] [Times: user=0.05 sys=0.09, real=0.04 secs]
2020-10-27T23:29:02.836-0800: [GC (Allocation Failure) [PSYoungGen: 80118K->33669K(116736K)] 320288K->278394K(466432K), 0.0094426 secs] [Times: user=0.04 sys=0.01, real=0.01 secs]
2020-10-27T23:29:02.855-0800: [GC (Allocation Failure) [PSYoungGen: 92524K->48417K(116736K)] 337249K->299646K(466432K), 0.0084360 secs] [Times: user=0.03 sys=0.01, real=0.01 secs]
2020-10-27T23:29:02.880-0800: [GC (Allocation Failure) [PSYoungGen: 107297K->55982K(116736K)] 358526K->316094K(466432K), 0.0099300 secs] [Times: user=0.05 sys=0.02, real=0.01 secs]
2020-10-27T23:29:02.905-0800: [GC (Allocation Failure) [PSYoungGen: 114862K->38865K(116736K)] 374974K->334731K(466432K), 0.0177427 secs] [Times: user=0.04 sys=0.07, real=0.02 secs]
2020-10-27T23:29:02.922-0800: [Full GC (Ergonomics) [PSYoungGen: 38865K->0K(116736K)] [ParOldGen: 295865K->248481K(349696K)] 334731K->248481K(466432K), [Metaspace: 2697K->2697K(1056768K)], 0.0569376 secs] [Times: user=0.23 sys=0.01, real=0.05 secs]
2020-10-27T23:29:02.993-0800: [GC (Allocation Failure) [PSYoungGen: 58880K->20469K(116736K)] 307361K->268950K(466432K), 0.0075076 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-27T23:29:03.018-0800: [GC (Allocation Failure) [PSYoungGen: 79349K->22125K(116736K)] 327830K->289362K(466432K), 0.0055070 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
2020-10-27T23:29:03.040-0800: [GC (Allocation Failure) [PSYoungGen: 81005K->18888K(116736K)] 348242K->308011K(466432K), 0.0067250 secs] [Times: user=0.05 sys=0.00, real=0.00 secs]
2020-10-27T23:29:03.056-0800: [GC (Allocation Failure) [PSYoungGen: 77677K->20609K(116736K)] 366801K->327969K(466432K), 0.0090553 secs] [Times: user=0.03 sys=0.03, real=0.01 secs]
2020-10-27T23:29:03.065-0800: [Full GC (Ergonomics) [PSYoungGen: 20609K->0K(116736K)] [ParOldGen: 307360K->277566K(349696K)] 327969K->277566K(466432K), [Metaspace: 2697K->2697K(1056768K)], 0.0517565 secs] [Times: user=0.25 sys=0.00, real=0.05 secs]
2020-10-27T23:29:03.128-0800: [GC (Allocation Failure) [PSYoungGen: 58880K->17808K(116736K)] 336446K->295375K(466432K), 0.0063724 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-27T23:29:03.147-0800: [GC (Allocation Failure) [PSYoungGen: 76688K->18980K(116736K)] 354255K->313480K(466432K), 0.0059069 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-27T23:29:03.164-0800: [GC (Allocation Failure) [PSYoungGen: 77421K->21250K(116736K)] 371921K->332692K(466432K), 0.0116685 secs] [Times: user=0.05 sys=0.02, real=0.01 secs]
2020-10-27T23:29:03.175-0800: [Full GC (Ergonomics) [PSYoungGen: 21250K->0K(116736K)] [ParOldGen: 311442K->293616K(349696K)] 332692K->293616K(466432K), [Metaspace: 2697K->2697K(1056768K)], 0.0452155 secs] [Times: user=0.23 sys=0.00, real=0.05 secs]
2020-10-27T23:29:03.238-0800: [GC (Allocation Failure) [PSYoungGen: 58684K->20221K(116736K)] 352300K->313838K(466432K), 0.0054711 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-27T23:29:03.254-0800: [GC (Allocation Failure) [PSYoungGen: 79101K->19499K(116736K)] 372718K->331161K(466432K), 0.0048523 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
2020-10-27T23:29:03.259-0800: [Full GC (Ergonomics) [PSYoungGen: 19499K->0K(116736K)] [ParOldGen: 311661K->301197K(349696K)] 331161K->301197K(466432K), [Metaspace: 2697K->2697K(1056768K)], 0.0537626 secs] [Times: user=0.26 sys=0.01, real=0.06 secs]
2020-10-27T23:29:03.325-0800: [GC (Allocation Failure) [PSYoungGen: 58879K->18215K(116736K)] 360076K->319412K(466432K), 0.0031977 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
2020-10-27T23:29:03.342-0800: [GC (Allocation Failure) [PSYoungGen: 77041K->19468K(116736K)] 378238K->337723K(466432K), 0.0092352 secs] [Times: user=0.03 sys=0.01, real=0.01 secs]
2020-10-27T23:29:03.351-0800: [Full GC (Ergonomics) [PSYoungGen: 19468K->0K(116736K)] [ParOldGen: 318255K->305972K(349696K)] 337723K->305972K(466432K), [Metaspace: 2697K->2697K(1056768K)], 0.0511282 secs] [Times: user=0.26 sys=0.01, real=0.05 secs]
2020-10-27T23:29:03.415-0800: [GC (Allocation Failure) [PSYoungGen: 58741K->20346K(116736K)] 364713K->326319K(466432K), 0.0044478 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
执行结束!共生成对象次数:6491
Heap
 PSYoungGen      total 116736K, used 22855K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b57f3280,0x00000007b8f00000)
  from space 57856K, 35% used [0x00000007bc780000,0x00000007bdb5ebc8,0x00000007c0000000)
  to   space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
 ParOldGen       total 349696K, used 305972K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 87% used [0x00000007a0000000,0x00000007b2acd200,0x00000007b5580000)
 Metaspace       used 2703K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
```

## 3、CMS GC

执行：
```shell
$ java -XX:+UseConcMarkSweepGC \
-Xms512m -Xmx512m \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps GCLogAnalysis
```

输出：
```console
正在执行...
2020-10-27T23:32:37.188-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.188-0800: [ParNew: 139776K->17471K(157248K), 0.0236604 secs] 139776K->48962K(506816K), 0.0237266 secs] [Times: user=0.03 sys=0.08, real=0.03 secs]
2020-10-27T23:32:37.244-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.245-0800: [ParNew: 157247K->17464K(157248K), 0.0323187 secs] 188738K->91992K(506816K), 0.0323896 secs] [Times: user=0.06 sys=0.10, real=0.03 secs]
2020-10-27T23:32:37.307-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.307-0800: [ParNew: 157240K->17470K(157248K), 0.0404520 secs] 231768K->137906K(506816K), 0.0405015 secs] [Times: user=0.22 sys=0.02, real=0.04 secs]
2020-10-27T23:32:37.366-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.367-0800: [ParNew: 157246K->17470K(157248K), 0.0405542 secs] 277682K->182165K(506816K), 0.0405969 secs] [Times: user=0.21 sys=0.02, real=0.04 secs]
2020-10-27T23:32:37.428-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.428-0800: [ParNew: 157246K->17471K(157248K), 0.0359476 secs] 321941K->229942K(506816K), 0.0359945 secs] [Times: user=0.21 sys=0.02, real=0.04 secs]
2020-10-27T23:32:37.465-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 212471K(349568K)] 232758K(506816K), 0.0005624 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.465-0800: [CMS-concurrent-mark-start]
2020-10-27T23:32:37.469-0800: [CMS-concurrent-mark: 0.004/0.004 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2020-10-27T23:32:37.470-0800: [CMS-concurrent-preclean-start]
2020-10-27T23:32:37.471-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.471-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-27T23:32:37.491-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.491-0800: [ParNew: 157247K->17471K(157248K), 0.0312408 secs] 369718K->270934K(506816K), 0.0312986 secs] [Times: user=0.20 sys=0.02, real=0.03 secs]
2020-10-27T23:32:37.542-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.542-0800: [ParNew: 156823K->17471K(157248K), 0.0357991 secs] 410286K->316200K(506816K), 0.0358403 secs] [Times: user=0.22 sys=0.02, real=0.03 secs]
2020-10-27T23:32:37.605-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.605-0800: [ParNew: 157247K->17467K(157248K), 0.0397158 secs] 455976K->357943K(506816K), 0.0397587 secs] [Times: user=0.17 sys=0.02, real=0.04 secs]
2020-10-27T23:32:37.644-0800: [CMS-concurrent-abortable-preclean: 0.004/0.173 secs] [Times: user=0.66 sys=0.06, real=0.17 secs]
2020-10-27T23:32:37.644-0800: [GC (CMS Final Remark) [YG occupancy: 18264 K (157248 K)]2020-10-27T23:32:37.644-0800: [Rescan (parallel) , 0.0003931 secs]2020-10-27T23:32:37.645-0800: [weak refs processing, 0.0000322 secs]2020-10-27T23:32:37.645-0800: [class unloading, 0.0003008 secs]2020-10-27T23:32:37.645-0800: [scrub symbol table, 0.0004079 secs]2020-10-27T23:32:37.646-0800: [scrub string table, 0.0002504 secs][1 CMS-remark: 340475K(349568K)] 358740K(506816K), 0.0015133 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.646-0800: [CMS-concurrent-sweep-start]
2020-10-27T23:32:37.647-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.647-0800: [CMS-concurrent-reset-start]
2020-10-27T23:32:37.649-0800: [CMS-concurrent-reset: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.679-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.679-0800: [ParNew: 157243K->17470K(157248K), 0.0130930 secs] 451319K->358302K(506816K), 0.0131415 secs] [Times: user=0.07 sys=0.01, real=0.02 secs]
2020-10-27T23:32:37.692-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 340831K(349568K)] 359050K(506816K), 0.0001897 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.692-0800: [CMS-concurrent-mark-start]
2020-10-27T23:32:37.694-0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.694-0800: [CMS-concurrent-preclean-start]
2020-10-27T23:32:37.695-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.695-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-27T23:32:37.695-0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.695-0800: [GC (CMS Final Remark) [YG occupancy: 30443 K (157248 K)]2020-10-27T23:32:37.695-0800: [Rescan (parallel) , 0.0010273 secs]2020-10-27T23:32:37.696-0800: [weak refs processing, 0.0000117 secs]2020-10-27T23:32:37.696-0800: [class unloading, 0.0013563 secs]2020-10-27T23:32:37.697-0800: [scrub symbol table, 0.0003518 secs]2020-10-27T23:32:37.697-0800: [scrub string table, 0.0001878 secs][1 CMS-remark: 340831K(349568K)] 371275K(506816K), 0.0029820 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.698-0800: [CMS-concurrent-sweep-start]
2020-10-27T23:32:37.699-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.699-0800: [CMS-concurrent-reset-start]
2020-10-27T23:32:37.699-0800: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.725-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.725-0800: [ParNew: 157224K->17470K(157248K), 0.0125319 secs] 399308K->300407K(506816K), 0.0125853 secs] [Times: user=0.07 sys=0.00, real=0.01 secs]
2020-10-27T23:32:37.738-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 282936K(349568K)] 300731K(506816K), 0.0001523 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.738-0800: [CMS-concurrent-mark-start]
2020-10-27T23:32:37.740-0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2020-10-27T23:32:37.740-0800: [CMS-concurrent-preclean-start]
2020-10-27T23:32:37.741-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.741-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-27T23:32:37.764-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.764-0800: [ParNew: 157246K->17471K(157248K), 0.0144290 secs] 440183K->351595K(506816K), 0.0144738 secs] [Times: user=0.09 sys=0.00, real=0.01 secs]
2020-10-27T23:32:37.779-0800: [CMS-concurrent-abortable-preclean: 0.001/0.038 secs] [Times: user=0.11 sys=0.00, real=0.03 secs]
2020-10-27T23:32:37.779-0800: [GC (CMS Final Remark) [YG occupancy: 17852 K (157248 K)]2020-10-27T23:32:37.779-0800: [Rescan (parallel) , 0.0003759 secs]2020-10-27T23:32:37.779-0800: [weak refs processing, 0.0000128 secs]2020-10-27T23:32:37.779-0800: [class unloading, 0.0002219 secs]2020-10-27T23:32:37.780-0800: [scrub symbol table, 0.0003419 secs]2020-10-27T23:32:37.780-0800: [scrub string table, 0.0001948 secs][1 CMS-remark: 334124K(349568K)] 351976K(506816K), 0.0012014 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2020-10-27T23:32:37.780-0800: [CMS-concurrent-sweep-start]
2020-10-27T23:32:37.782-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.782-0800: [CMS-concurrent-reset-start]
2020-10-27T23:32:37.783-0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.808-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.808-0800: [ParNew: 157247K->157247K(157248K), 0.0000221 secs]2020-10-27T23:32:37.808-0800: [CMS: 307274K->307323K(349568K), 0.0729799 secs] 464522K->307323K(506816K), [Metaspace: 2697K->2697K(1056768K)], 0.0730606 secs] [Times: user=0.08 sys=0.00, real=0.08 secs]
2020-10-27T23:32:37.882-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 307323K(349568K)] 307848K(506816K), 0.0002128 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.882-0800: [CMS-concurrent-mark-start]
2020-10-27T23:32:37.883-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.883-0800: [CMS-concurrent-preclean-start]
2020-10-27T23:32:37.884-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.884-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-27T23:32:37.902-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.902-0800: [ParNew: 139776K->17470K(157248K), 0.0093632 secs] 447099K->354979K(506816K), 0.0094148 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
2020-10-27T23:32:37.911-0800: [CMS-concurrent-abortable-preclean: 0.001/0.027 secs] [Times: user=0.07 sys=0.00, real=0.03 secs]
2020-10-27T23:32:37.911-0800: [GC (CMS Final Remark) [YG occupancy: 17768 K (157248 K)]2020-10-27T23:32:37.911-0800: [Rescan (parallel) , 0.0011272 secs]2020-10-27T23:32:37.912-0800: [weak refs processing, 0.0000158 secs]2020-10-27T23:32:37.912-0800: [class unloading, 0.0002582 secs]2020-10-27T23:32:37.913-0800: [scrub symbol table, 0.0003966 secs]2020-10-27T23:32:37.913-0800: [scrub string table, 0.0002155 secs][1 CMS-remark: 337508K(349568K)] 355277K(506816K), 0.0020907 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.914-0800: [CMS-concurrent-sweep-start]
2020-10-27T23:32:37.914-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.914-0800: [CMS-concurrent-reset-start]
2020-10-27T23:32:37.915-0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:37.941-0800: [GC (Allocation Failure) 2020-10-27T23:32:37.941-0800: [ParNew: 157246K->157246K(157248K), 0.0000230 secs]2020-10-27T23:32:37.941-0800: [CMS: 337326K->326812K(349568K), 0.0595547 secs] 494572K->326812K(506816K), [Metaspace: 2697K->2697K(1056768K)], 0.0596666 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
2020-10-27T23:32:38.001-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 326812K(349568K)] 327364K(506816K), 0.0002398 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:38.002-0800: [CMS-concurrent-mark-start]
2020-10-27T23:32:38.005-0800: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-27T23:32:38.005-0800: [CMS-concurrent-preclean-start]
2020-10-27T23:32:38.006-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-27T23:32:38.006-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-27T23:32:38.025-0800: [GC (Allocation Failure) 2020-10-27T23:32:38.025-0800: [ParNew: 139661K->139661K(157248K), 0.0000265 secs]2020-10-27T23:32:38.025-0800: [CMS2020-10-27T23:32:38.025-0800: [CMS-concurrent-abortable-preclean: 0.001/0.020 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
 (concurrent mode failure): 326812K->338384K(349568K), 0.0647764 secs] 466474K->338384K(506816K), [Metaspace: 2697K->2697K(1056768K)], 0.0648743 secs] [Times: user=0.06 sys=0.00, real=0.07 secs]
执行结束!共生成对象次数:7947
Heap
 par new generation   total 157248K, used 5851K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   4% used [0x00000007a0000000, 0x00000007a05b6c68, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 concurrent mark-sweep generation total 349568K, used 338384K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2703K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 295K, capacity 386K, committed 512K, reserved 1048576K
  ```

## 4、G1 GC

执行：
```shell
$ java -XX:+UseG1GC \
-Xloggc:g1_gc.log \
-Xms512m -Xmx512m \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps \
GCLogAnalysis
```

输出：
```console
正在执行...
执行结束!共生成对象次数:9852
```
日志文件：./g1_gc.log

## 5、ZGC

执行：
```shell
$ java -XX:+UseZGC \
-verbose:gc \
-Xlog:gc=debug:file=zgc_gc.log \
-Xms512m -Xmx512m \
GCLogAnalysis
```

输出：
```console
正在执行...
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:48)
	at GCLogAnalysis.main(GCLogAnalysis.java:25)
```
日志文件：./zgc_gc.log ./zgc_gc.log.*

# 小结
通过测试，发现 ZGC 果然是不适合小堆，在 512M 堆内存时，很容易发生 OOM 现象。
出了 ZGC，其它几个 GC 在同样的 512M 堆下，暂时 G1 胜出。但是这是非科学的测试，不构成参考结论。
具体，我还做了不同 JDK 版本下，同样的 GC 的性能测试，以及各个版本下不同 GC 的性能对比。
总体，我觉得即使是相同的 GC，但是堆大小不一样，导致最终测试结果也是不一样的。所以 GC 调优还需要深入了解 JVM 和各个 GC 算法，从而做到调优知道从哪里下手，不然一同乱试很浪费时间也不科学。

