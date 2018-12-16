---
home: true
heroImage: /logo.png
actionText:  起步 →
actionLink: /views/personalInfo/
features:
- title: 易用
  details: Easy Spring 是一个很容易上手的扩展框架, 如果你用过 SpringBoot 相信你会很快上手.
- title: 高效
  details: Easy Spring 几乎封装了所有单表操作可能的情况, 配合 Common Generator 代码生成器, 几乎可以实现 0 编码.
- title: 自由度高
  details: Easy Spring 是为了便捷的开发做了功能的封装, 但并不会限制你使用扩展框架.
footer: 'GPL-3.0 Licensed | Copyright © 2018-present EasySpring'
---

### 主要功能
#### 基础的 CRUD 

项目 `base-data-mybatis` 整合了 `mybatis` `通用mapper` `page-help` `druid` 等数据库相关的技术, 
创建了基础的 `entity` `mapper` 和 `service`, 并实现了基础的 `CRUD`. 

如果你的所需要的是单表操作的接口, 你会惊奇的发现, 使用 `EasySpring` 后就不需要些代码了.

#### 代码生成器

代码生成也是项目开发中必不可少的一项功能, 创建数据库后, 执行一下代码生成器, 项目就开发完成了. 听起来是不是很爽?

项目 `common-generator` 会生成 `entity` `mapper` `mapper.xml` `service` `serviceImp` `controller` 这些项目中必要的类.
另外, 还提供了另一个更强大的生成器 `common-generator-extension` , 除了上述的内容外, `common-generator-extension` 还会生成 
`service` 层和 `controller` 层常用方法的测试类. 同时还会实现常用的接口.