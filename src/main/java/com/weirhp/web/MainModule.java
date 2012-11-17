package com.weirhp.web;

import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

import com.weirhp.web.module.IndexModule;

@Modules(value = { IndexModule.class }, packages = { "com.weirhp" }, scanPackage = true)
@IocBy(type = ComboIocProvider.class, args = { "*org.nutz.ioc.loader.json.JsonLoader", "config/",
        "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.weirhp" })
@SetupBy(MvcSetup.class)
@Localization("msg")
@Fail("json")
@Filters(@By(type = SysActionFilter.class, args = "/login"))
// 全局的Shiro注解过滤器
public class MainModule {
}
