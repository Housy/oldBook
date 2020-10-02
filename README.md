# 简介
项目由店铺注册、店铺模块、商品类别模块、商品模块以及用户模块五大模块，利用SSM框架搭建，数据库使用mysql，为给用户良好的体验，引入redis缓存技术，店铺注册模块使用图片处理功能以及kaptcha验证码功能，在用户信息安全方面使用了DES加密，由于项目涉及卖家和买家两种类型用户，因此加入了拦截器技术，本平台主要服务于微信公众号，用户可直接通过微信授权登录本系统，为实现数据的定期备份，在服务器上对crontab进行了配置。前台直接引入SUI Mobile实现简单页面展示，后台引入Logback便于后台异常排查。

项目目前线上还做得不是特别完善，日后我会再做补充。


# 预览图
本项目分为两种用户类型，商家和买家，买家可以通过对接微信端口直接进入如下图页面：
![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/8.jpg)  

通过拦截器对登录进行拦截，卖家进入后是如下界面：  

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/9.png)  
![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/4.png)  

用户修改密码界面：  

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/1.png)  

店铺信息：  

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/5.jpg)  

微信绑定：  

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/6.png)  

商店管理：  

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/2.png)  
![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/3.png)  

商品管理  

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/7.png)  

类别展示   

![这里随便写文字](https://github.com/Housy/oldBook/blob/master/o2opicture/10.png)

