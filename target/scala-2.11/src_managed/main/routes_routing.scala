// @SOURCE:/home/ubuntu/TFIA/Galapagos/conf/routes
// @HASH:84845f9cb981c4757f5df2040c7e0fd46977e1f3
// @DATE:Sat Jun 06 15:48:03 UTC 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ General""", Routes.prefix + """"""))
        

// @LINE:7
private[this] lazy val controllers_Application_modelList1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("model/list.json"))))
private[this] lazy val controllers_Application_modelList1_invoker = createInvoker(
controllers.Application.modelList,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "modelList", Nil,"GET", """""", Routes.prefix + """model/list.json"""))
        

// @LINE:8
private[this] lazy val controllers_Application_model2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("model/"),DynamicPart("modelPath", """.*\.nlogo""",false))))
private[this] lazy val controllers_Application_model2_invoker = createInvoker(
controllers.Application.model(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "model", Seq(classOf[String]),"GET", """""", Routes.prefix + """model/$modelPath<.*\.nlogo>"""))
        

// @LINE:11
private[this] lazy val controllers_Local_createStandaloneTortoise3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("create-standalone"))))
private[this] lazy val controllers_Local_createStandaloneTortoise3_invoker = createInvoker(
controllers.Local.createStandaloneTortoise,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "createStandaloneTortoise", Nil,"GET", """ Local (Tortoise)""", Routes.prefix + """create-standalone"""))
        

// @LINE:12
private[this] lazy val controllers_Local_tortoise4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tortoise"))))
private[this] lazy val controllers_Local_tortoise4_invoker = createInvoker(
controllers.Local.tortoise,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "tortoise", Nil,"GET", """""", Routes.prefix + """tortoise"""))
        

// @LINE:15
private[this] lazy val controllers_CompilerService_compileURL5_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("compile-url"))))
private[this] lazy val controllers_CompilerService_compileURL5_invoker = createInvoker(
controllers.CompilerService.compileURL,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileURL", Nil,"POST", """ Compiler Service""", Routes.prefix + """compile-url"""))
        

// @LINE:16
private[this] lazy val controllers_CompilerService_compileURL6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("compile-url"))))
private[this] lazy val controllers_CompilerService_compileURL6_invoker = createInvoker(
controllers.CompilerService.compileURL,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileURL", Nil,"GET", """""", Routes.prefix + """compile-url"""))
        

// @LINE:17
private[this] lazy val controllers_CompilerService_compileCode7_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("compile-code"))))
private[this] lazy val controllers_CompilerService_compileCode7_invoker = createInvoker(
controllers.CompilerService.compileCode,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileCode", Nil,"POST", """""", Routes.prefix + """compile-code"""))
        

// @LINE:18
private[this] lazy val controllers_CompilerService_compileCode8_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("compile-code"))))
private[this] lazy val controllers_CompilerService_compileCode8_invoker = createInvoker(
controllers.CompilerService.compileCode,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileCode", Nil,"GET", """""", Routes.prefix + """compile-code"""))
        

// @LINE:19
private[this] lazy val controllers_CompilerService_compileNlogo9_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("compile-nlogo"))))
private[this] lazy val controllers_CompilerService_compileNlogo9_invoker = createInvoker(
controllers.CompilerService.compileNlogo,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileNlogo", Nil,"POST", """""", Routes.prefix + """compile-nlogo"""))
        

// @LINE:20
private[this] lazy val controllers_CompilerService_compileNlogo10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("compile-nlogo"))))
private[this] lazy val controllers_CompilerService_compileNlogo10_invoker = createInvoker(
controllers.CompilerService.compileNlogo,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileNlogo", Nil,"GET", """""", Routes.prefix + """compile-nlogo"""))
        

// @LINE:21
private[this] lazy val controllers_CompilerService_saveURL11_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("save-url"))))
private[this] lazy val controllers_CompilerService_saveURL11_invoker = createInvoker(
controllers.CompilerService.saveURL,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveURL", Nil,"POST", """""", Routes.prefix + """save-url"""))
        

// @LINE:22
private[this] lazy val controllers_CompilerService_saveURL12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("save-url"))))
private[this] lazy val controllers_CompilerService_saveURL12_invoker = createInvoker(
controllers.CompilerService.saveURL,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveURL", Nil,"GET", """""", Routes.prefix + """save-url"""))
        

// @LINE:23
private[this] lazy val controllers_CompilerService_saveCode13_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("save-code"))))
private[this] lazy val controllers_CompilerService_saveCode13_invoker = createInvoker(
controllers.CompilerService.saveCode,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveCode", Nil,"POST", """""", Routes.prefix + """save-code"""))
        

// @LINE:24
private[this] lazy val controllers_CompilerService_saveCode14_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("save-code"))))
private[this] lazy val controllers_CompilerService_saveCode14_invoker = createInvoker(
controllers.CompilerService.saveCode,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveCode", Nil,"GET", """""", Routes.prefix + """save-code"""))
        

// @LINE:25
private[this] lazy val controllers_CompilerService_saveNlogo15_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("save-nlogo"))))
private[this] lazy val controllers_CompilerService_saveNlogo15_invoker = createInvoker(
controllers.CompilerService.saveNlogo,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveNlogo", Nil,"POST", """""", Routes.prefix + """save-nlogo"""))
        

// @LINE:26
private[this] lazy val controllers_CompilerService_saveNlogo16_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("save-nlogo"))))
private[this] lazy val controllers_CompilerService_saveNlogo16_invoker = createInvoker(
controllers.CompilerService.saveNlogo,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveNlogo", Nil,"GET", """""", Routes.prefix + """save-nlogo"""))
        

// @LINE:28
private[this] lazy val controllers_CompilerService_modelStatuses17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("model/statuses.json"))))
private[this] lazy val controllers_CompilerService_modelStatuses17_invoker = createInvoker(
controllers.CompilerService.modelStatuses,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "modelStatuses", Nil,"GET", """""", Routes.prefix + """model/statuses.json"""))
        

// @LINE:30
private[this] lazy val controllers_Local_engine18_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("netlogo-engine.js"))))
private[this] lazy val controllers_Local_engine18_invoker = createInvoker(
controllers.Local.engine,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "engine", Nil,"GET", """""", Routes.prefix + """netlogo-engine.js"""))
        

// @LINE:31
private[this] lazy val controllers_Local_agentModel19_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("netlogo-agentmodel.js"))))
private[this] lazy val controllers_Local_agentModel19_invoker = createInvoker(
controllers.Local.agentModel,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "agentModel", Nil,"GET", """""", Routes.prefix + """netlogo-agentmodel.js"""))
        

// @LINE:34
private[this] lazy val controllers_Assets_at20_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at20_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """model/list.json""","""controllers.Application.modelList"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """model/$modelPath<.*\.nlogo>""","""controllers.Application.model(modelPath:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """create-standalone""","""controllers.Local.createStandaloneTortoise"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tortoise""","""controllers.Local.tortoise"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """compile-url""","""controllers.CompilerService.compileURL"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """compile-url""","""controllers.CompilerService.compileURL"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """compile-code""","""controllers.CompilerService.compileCode"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """compile-code""","""controllers.CompilerService.compileCode"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """compile-nlogo""","""controllers.CompilerService.compileNlogo"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """compile-nlogo""","""controllers.CompilerService.compileNlogo"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """save-url""","""controllers.CompilerService.saveURL"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """save-url""","""controllers.CompilerService.saveURL"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """save-code""","""controllers.CompilerService.saveCode"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """save-code""","""controllers.CompilerService.saveCode"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """save-nlogo""","""controllers.CompilerService.saveNlogo"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """save-nlogo""","""controllers.CompilerService.saveNlogo"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """model/statuses.json""","""controllers.CompilerService.modelStatuses"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """netlogo-engine.js""","""controllers.Local.engine"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """netlogo-agentmodel.js""","""controllers.Local.agentModel"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index)
   }
}
        

// @LINE:7
case controllers_Application_modelList1_route(params) => {
   call { 
        controllers_Application_modelList1_invoker.call(controllers.Application.modelList)
   }
}
        

// @LINE:8
case controllers_Application_model2_route(params) => {
   call(params.fromPath[String]("modelPath", None)) { (modelPath) =>
        controllers_Application_model2_invoker.call(controllers.Application.model(modelPath))
   }
}
        

// @LINE:11
case controllers_Local_createStandaloneTortoise3_route(params) => {
   call { 
        controllers_Local_createStandaloneTortoise3_invoker.call(controllers.Local.createStandaloneTortoise)
   }
}
        

// @LINE:12
case controllers_Local_tortoise4_route(params) => {
   call { 
        controllers_Local_tortoise4_invoker.call(controllers.Local.tortoise)
   }
}
        

// @LINE:15
case controllers_CompilerService_compileURL5_route(params) => {
   call { 
        controllers_CompilerService_compileURL5_invoker.call(controllers.CompilerService.compileURL)
   }
}
        

// @LINE:16
case controllers_CompilerService_compileURL6_route(params) => {
   call { 
        controllers_CompilerService_compileURL6_invoker.call(controllers.CompilerService.compileURL)
   }
}
        

// @LINE:17
case controllers_CompilerService_compileCode7_route(params) => {
   call { 
        controllers_CompilerService_compileCode7_invoker.call(controllers.CompilerService.compileCode)
   }
}
        

// @LINE:18
case controllers_CompilerService_compileCode8_route(params) => {
   call { 
        controllers_CompilerService_compileCode8_invoker.call(controllers.CompilerService.compileCode)
   }
}
        

// @LINE:19
case controllers_CompilerService_compileNlogo9_route(params) => {
   call { 
        controllers_CompilerService_compileNlogo9_invoker.call(controllers.CompilerService.compileNlogo)
   }
}
        

// @LINE:20
case controllers_CompilerService_compileNlogo10_route(params) => {
   call { 
        controllers_CompilerService_compileNlogo10_invoker.call(controllers.CompilerService.compileNlogo)
   }
}
        

// @LINE:21
case controllers_CompilerService_saveURL11_route(params) => {
   call { 
        controllers_CompilerService_saveURL11_invoker.call(controllers.CompilerService.saveURL)
   }
}
        

// @LINE:22
case controllers_CompilerService_saveURL12_route(params) => {
   call { 
        controllers_CompilerService_saveURL12_invoker.call(controllers.CompilerService.saveURL)
   }
}
        

// @LINE:23
case controllers_CompilerService_saveCode13_route(params) => {
   call { 
        controllers_CompilerService_saveCode13_invoker.call(controllers.CompilerService.saveCode)
   }
}
        

// @LINE:24
case controllers_CompilerService_saveCode14_route(params) => {
   call { 
        controllers_CompilerService_saveCode14_invoker.call(controllers.CompilerService.saveCode)
   }
}
        

// @LINE:25
case controllers_CompilerService_saveNlogo15_route(params) => {
   call { 
        controllers_CompilerService_saveNlogo15_invoker.call(controllers.CompilerService.saveNlogo)
   }
}
        

// @LINE:26
case controllers_CompilerService_saveNlogo16_route(params) => {
   call { 
        controllers_CompilerService_saveNlogo16_invoker.call(controllers.CompilerService.saveNlogo)
   }
}
        

// @LINE:28
case controllers_CompilerService_modelStatuses17_route(params) => {
   call { 
        controllers_CompilerService_modelStatuses17_invoker.call(controllers.CompilerService.modelStatuses)
   }
}
        

// @LINE:30
case controllers_Local_engine18_route(params) => {
   call { 
        controllers_Local_engine18_invoker.call(controllers.Local.engine)
   }
}
        

// @LINE:31
case controllers_Local_agentModel19_route(params) => {
   call { 
        controllers_Local_agentModel19_invoker.call(controllers.Local.agentModel)
   }
}
        

// @LINE:34
case controllers_Assets_at20_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at20_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     