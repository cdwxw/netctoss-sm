1、项目背景
	电信公司有UNIX服务器的出租业务，需要使用一套系统来解决这个出租业务，实现自动化办公的目的。

2、解决方案
	1)NETCTOSS
		NET China Telecom Operation Support System
		中国电信运营支持系统(sm版)
		a、作用
			可以创建账号，维护资费标准，以及查询用户的消费情况。
		b、使用者
			营业员
		c、部署
			应用服务器
	2)DMS
		a、作用
			采集用户登录UNIX服务器的相关信息
		b、使用者
			自动
		c、部署
			DMS Client部署在UNIX服务器上
			DMS Server部署在应用服务器上
	3)PL/SQL
		a、作用
			月底自动计算出每个客户本月的消费数据
		b、使用者
			自动
		c、部署
			数据库
   
3、技术架构
	1)NETCTOSS采用的技术
		Java+MyBatis+Spring+JSP+JSTL+EL+JQuery
	2)开发环境
		Linux+Eclipse Luna+Tomcat7+JDK1.7
	3)项目计划
		Unit01:资费模块
		Unit02:账务账号
		Unit03:角色、管理员、登陆模块
		Unit04:AOP
		Unit05:事务
	4)设计思想
		采用MVC分层思想
		视图层(V):JSP+JQuery+Ajax
		控制层(C):DispatcherServlet+Controller
		业务层(M):Service
		持久层(M):DAO
		
4、权限管理
	1)权限管理由权限设置和权限认证两部分构成，其中权限设置是用户登录系统之前，已经预置好的该用户可以访问的模块，
	它包含角色和管理员2部分；其中权限认证是用户登录时，系统自动判读其身份并控制该用户可以访问的模块；权限设置是
	权限认证的前提，权限认证必须依赖权限设置时所预置的内容。
	2)权限设置
		a.角色
			角色就是系统用户的身份，权限认证时会通过用户找到其对应的角色，再动态初始化模块图标
			--查询:
				使用一对多关联映射
			--新增:
				打开新增页:根据模块表中全部数据，动态初始化页面上的checkbox
				新增保存:将角色名存入role_info，将勾选的模块存入role_module
				问题: TODO 直接回车保存，提交未执行校验？？
			--修改:
				打开修改页:根据模块表初始化checkbox；根据中间表设置默认勾选；根据角色表设置角色名
				修改保存:修改角色名；根据角色id删除中间表数据；重新插入中间表数据
		b.管理员
			管理员是系统用户，用来创建及修改用户，使用这些用户登录系统
			--查询:
				使用一对多关联映射，增加查询条件
			--密码重置:
				根据选中的checkbox，得到对应的id值
				密码重置后，页面不需要显示密码，不刷新页面，所以使用异步请求访问controller
				问题: TODO 全选checkbox时，将第一行标题也存入ids，sql出现问题？？
					$("td input:checked");// 排除第一行tr
			--新增:
				打开新增页:根据角色表中全部数据，动态初始化页面上的checkbox
				新增保存:将管理员信息存入admin_info，将勾选的角色存入admin_role
			--修改:
				打开修改页:根据角色表初始化checkbox；根据中间表设置默认勾选；根据管理员表设置管理员信息
				修改保存:修改管理员信息；根据管理员id删除中间表数据；重新插入中间表数据
				
	3)权限认证
		a.登陆
			使用系统用户登录系统
			1)打开登陆页
			2)登陆校验 异步请求提交验证
			3)打开系统主页
			4)登陆检查 CheckLoginInterceptor.java
				在访问系统中除登陆外的任何一个功能时，需要检查用户是否成功登陆，否则踢回登陆页
				由于该业务是通用的，可以使用拦截器处理 (两种方式:session/cookie)
		b.客户端认证
			登陆成功后，需要系统自动判断当前用户的角色，进而动态初始化模块图标
			1)动态初始化模块
				// 保存用户权限至session，权限认证用
				List<Module> modules = adminDao.findModulesByAdmin(admin.getAdmin_id());
				session.setAttribute("allModules", modules);
				页面动态初始化模块${allModules}
			2)点亮当前访问模块 CurrentModuleInterceptor.java
				用拦截器判断用户当前正在访问的模块，再将该模块id保存至session供页面使用
		c.服务端认证
			为了防止用户直接输入url访问模块功能，需要在服务端统一验证用户是否具有当前url的访问权限
			1)用户在访问任何一个模块时，应当判断其是否有该模块的访问权限，若没有权限将其踢至nopower页面
			2)由于该业务是通用的，可以使用拦截器处理
				DBUtil-->Dao(值传递，不用AOP)
				登录检查获取session-->主逻辑(用拦截器，不用AOP)
				事务处理、日志记录、异常处理等(与主逻辑间无值传递，用AOP)