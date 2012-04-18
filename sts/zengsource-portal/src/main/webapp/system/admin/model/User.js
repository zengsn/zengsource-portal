/**
 * 
 */
Ext.define('JXP.system.model.User', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id',
		mapping : 'id'
	}, {
		name: 'username',
		mapping: 'username'
	}, {
		name: 'nickname',
		mapping: 'nickname'
	}, {
		name: 'realname',
		mapping: 'realname'
	}, {
		name: 'realnameShare',
		mapping: 'realnameShare'
	}, {
		name: 'sex',
		mapping: 'sex'
	}, {
		name: 'birthday',
		mapping: 'birthday'
	}, {
		name: 'location',
		mapping: 'location'
	}, {
		name: 'sexShare',
		mapping: 'sexShare'
	}, {
		name: 'birthdayShare',
		mapping: 'birthdayShare'
	}, {
		name: 'locationShare',
		mapping: 'locationShare'
	}, {
		name: 'introduction',
		mapping: 'introduction'
	}, {
		name: 'status',
		type   : 'int', 
		mapping: 'status'
	}, {
		name: 'email',
		mapping: 'email'
	}, {
		name: 'blog',
		mapping: 'blog'
	}, {
		name: 'qq',
		mapping: 'qq'
	}, {
		name: 'weibo',
		mapping: 'weibo'
	}, {
		name: 'msn',
		mapping: 'msn'
	}, {
		name: 'mobile',
		mapping: 'mobile'
	}, {
		name: 'emailShare',
		mapping: 'emailShare'
	}, {
		name: 'blogShare',
		mapping: 'blogShare'
	}, {
		name: 'qqShare',
		mapping: 'qqShare'
	}, {
		name: 'weiboShare',
		mapping: 'weiboShare'
	}, {
		name: 'msnShare',
		mapping: 'msnShare'
	}, {
		name: 'mobileShare',
		mapping: 'mobileShare'
	}, {
		name: 'lastLoginTime',
		mapping: 'lastLoginTime'
	}, {
		name: 'createdTime',
		mapping: 'createdTime'
	}]
});