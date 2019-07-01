# 概述
有时候我们遇到获取用户的手机型号时，只能获取 XX-XXX 这样的型号，我们无法直接得知用户的手机名称，在网上各大数据接口上搜寻了一番也未找到一种可以直接将手机型号（例如：SM901）转换为人类可直接阅读的名称（例如：锤子 M1），于是我和另外一个小伙伴（51587124@github）发起了该项目。

该项目的主旨为提供一个统一、公开、人人可参与的手机型号数据库，采用 github 进行托管和维护，同时我个人提供了基于该数据库实现的在线 API ，大家也可以基于该数据库文件实现私有的 API，定期同步数据库文件即可。

若在使用的过程中有缺失的机型信息，欢迎提交 issues 

# 存储格式
model:name:brand

# 在线 API
正常状态返回 0，name 为手机名称，brand 为手机品牌
```
https://model-lib.4kb.cn/api/model/{model}
```
example:
```
https://model-lib.4kb.cn/api/model/SM901
```
# 版权信息
原作者 [weibo@@KHwang9883](https://weibo.com/huangyf9883)
 
修改者 [10086@xiaoi.me](https://xiaoi.me)，整合了原作者的数据为标准格式，并提供在线 API

## 许可

<a rel="license" href="https://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="知识共享许可协议" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />本作品采用 <a rel="license" href="https://creativecommons.org/licenses/by-nc-sa/4.0/">知识共享署名-非商业性使用-相同方式共享 4.0 国际许可协议</a> 进行许可。

文档版的分发和传播亦适用于该协议。

# 参考文献
 > [手机品牌型号汇总](https://github.com/KHwang9883/MobileModels)
