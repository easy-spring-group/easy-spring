module.exports = {
  /**
   * 基本配置
   */
  // 部署站点的基础路径，如果你想让你的网站部署到一个子路径下，你将需要设置它。
  base: '/',
  // 网站的标题，它将会被用作所有页面标题的前缀，同时，默认主题下，它将显示在导航栏（navbar）上。
  title: 'EasySpring',
  // 网站的描述，它将会以 <meta> 标签渲染到当前页面的 HTML 中。
  description: 'Just playing around',
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
      { text: '首页', link: '/' },
      { text: '指南', link: '/' },
      { text: 'Categories',
        items: [
          { text: '前端', link: '/categories/frontEnd' },
          { text: '后台', link: '/categories/backEnd' },
          { text: 'Essay', link: '/categories/essay' },
          { text: 'Other', link: '/categories/other' }
        ]
      },
      { text: '标签', link: '/tags/' },
      { text: 'Git', link: 'https://gitlab.com/lixian13149999无背景-256-23253A.png' },
    ],
    logo: '/head.png',
    sidebar: 'auto',
    //搜索
    search: true,
    searchMaxSuggestions: 10,
    sidebarDepth : 2,
    // 最后更新时间
    lastUpdated: 'Last Updated', // string | boolean
  },

  /**
   * markdown 配置
   */
  markdown: {
    // 是否显示行号
    lineNumbers: true
  }
}
