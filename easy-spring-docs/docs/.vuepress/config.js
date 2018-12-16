module.exports = {
  /**
   * 基本配置
   */
  // 部署站点的基础路径，如果你想让你的网站部署到一个子路径下，你将需要设置它。
  base: '/',
  // 网站的标题，它将会被用作所有页面标题的前缀，同时，默认主题下，它将显示在导航栏（navbar）上。
  title: 'EasySpring',
  // 网站的描述，它将会以 <meta> 标签渲染到当前页面的 HTML 中。
  description: '简洁编码, 从现在开始.',
  // 指定 vuepress build 的输出目录。
  // dest: '.vuepress/dist',

  head: [
    ['link', { rel: 'icon', href: '/favicon.ico' }],
    ['meta', { name: 'viewport', content: 'width=device-width,initial-scale=1,user-scalable=no' }]
  ],

  /**
   * 主题样式的配置
   */
  themeConfig : {
    nav: [
      { text: '指南', link: '/guide/' },
      { text: '整体架构', link: '/framework/' },
      { text: 'Framework',
        items: [
          { text: 'Base', link: '/framework/base/' },
          { text: 'Common', link: '/framework/common/' },
        ]
      },
      { text: 'Gitee', link: 'https://gitee.com/easyspring/spring-book'},
    ],
    logo: '/head.png',
    // sidebar: [
    //     '/',
    //     '/framework/common/',
    //     ['/framework/base/', 'Explicit link text']
    // ],
    sidebar: 'auto',
    displayAllHeaders: true,
    //搜索
    search: true,
    searchMaxSuggestions: 10,
    sidebarDepth : 3,
    // 最后更新时间
    lastUpdated: '上次更新时间', // string | boolean
  },

  /**
   * markdown 配置
   */
  markdown: {
    // 是否显示行号
    lineNumbers: true
  }
}
