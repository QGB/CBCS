package com.baoxiu.web.config;

/**
 * @author mapuqi
 * @version created at 2012-12-13 下午12:29:37 description 配置业务请求编码�? 其他业务相关信息
 */

public class ConfigServer {

	// 访问主页
	public static String WEB_HOST = "http://10.0.23.39:8080/web";
	// 访问图片
	public static String WEB_IMAGE = WEB_HOST + "/image";

	public static final int TODAYDISH = 1;// 今日菜谱
	public static final int DISHDETAIL = 2;// 菜谱详情
	public static final int CATEGORY = 3;// 分类
	public static final int CATEGORYDETAIL = 4;// 分类详情
	public static final int ACTIVITY = 5;// 活动介绍
	public static final int ACTIVITYDETAIL = 6;// 活动详情
	public static final int LOGIN = 7;// 会员登录
	public static final int SELECTED = 8;// 已点菜单
	public static final int UPDATESELECTED = 9;// 更新已点菜单
	public static final int INSERTSELECTED = 10;// 插入已点菜单
	public static final int COLLECT = 11;// 查询收藏表
	public static final int INSERTCOLLECT = 12;// 插入收藏信息
	public static final int DELETECOLLECT = 13;// 删除收藏信息
	public static final int ORDER = 14;// 查询所有订单
	public static final int ORDERDETAIL = 15;// 查询某订单点菜信息
	public static final int UPDATEGRADE = 16;// 更新评分
	public static final int INSERTORDER = 17;// 提交订单
	public static final int DELETESELECTED = 18;// 清空已点菜单
	public static final int TABLE=19;// 选桌
	public static final int HUNT=20;// 搜索
	public static final int HUNT_ALL=21;// 所有菜名

	public static final String confailed = "-1";// 网络连接失败code

	public static final String TODAYDISH_SERER = "/TodayDishServlet";// 今日菜谱
	public static final String DISHDETAIL_SERER = "/DishDetailServlet";// 菜谱详情
	public static final String CATEGORY_SERER = "/CategoryServlet";// 分类
	public static final String CATEGORYDETAIL_SERER = "/CategoryDetailServlet";// 分类
	public static final String ACTIVITY_SERER = "/ActivityServlet";// 活动介绍
	public static final String ACTIVITYDETAIL_SERER = "/ActivityDetailServlet";// 活动详情
	public static final String LOGIN_SERER = "/LoginServlet";// 会员登录
	public static final String SELECTED_SERER = "/SelectedServlet";// 已点菜单
	public static final String UPDATESELECTED_SERER = "/UpdateSelectedServlet";// 更新已点菜单
	public static final String INSERTSELECTED_SERER = "/InsertSelectedServlet";// 插入已点菜单
	public static final String COLLECT_SERER = "/CollectListServlet";// 查询收藏表
	public static final String INSERTCOLLECT_SERER = "/InsertCollectServlet";// 插入收藏信息
	public static final String DELETECOLLECT_SERER = "/DeleteCollectServlet";// 删除收藏信息
	public static final String ORDER_SERER = "/OrderServlet";// 查询所有订单
	public static final String ORDERDETAIL_SERER = "/OrderDetailServlet";// 查询某订单点菜信息
	public static final String UPDATEGRADE_SERER = "/UpdateGradeServlet";// 更新评分
	public static final String INSERTORDER_SERER = "/InsertOrderServlet";// 提交订单
	public static final String DELETESELECTED_SERER = "/DeleteSelectedServlet";// 清空已点菜单
	public static final String TABLE_SERER = "/TableServlet";// 选桌
	public static final String HUNT_SERER = "/HuntServlet";// 搜索
	public static final String HUNTALL_SERER = "/HuntAllServlet";// 所有菜名
}
