# AndroidAop
aop 小栗子

# 为什么要用Aop
简化我们的代码，对于公用的逻辑我们可以抽取出来统一处理。比如对于很多地方我们
都需要判断网络是否连接，如果连接，那么页面上的一些按钮点击之后是可以成功发出
请求与后台交互的，现在的做法是每个这样地方我们都要把网络是否连接的代码写一遍，
是不是显得很啰嗦，那么有没有一种办法可以省去每个地方都判断网络连接，其实那就
要用到Aop编程，可以把横跨每个模块的功能统一进行处理。这里我用到了AspectJ
工具，相关的资料可以自己搜索。这里推一篇老外的[文章](https://fernandocejas.com/2014/08/03/aspect-oriented-programming-in-android/)，之前只了解过Aop，但是并
没有在Android上继承进来，貌似也很麻烦，另外JakeWharton的[Hugo](https://github.com/JakeWharton/hugo)库也是基于此。