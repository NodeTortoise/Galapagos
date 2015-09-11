// @SOURCE:/home/ubuntu/TFIA/Galapagos/conf/routes
// @HASH:84845f9cb981c4757f5df2040c7e0fd46977e1f3
// @DATE:Sat Jun 06 15:48:03 UTC 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:34
// @LINE:31
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:31
// @LINE:30
// @LINE:12
// @LINE:11
class ReverseLocal {


// @LINE:31
def agentModel(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "netlogo-agentmodel.js")
}
                        

// @LINE:30
def engine(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "netlogo-engine.js")
}
                        

// @LINE:12
def tortoise(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tortoise")
}
                        

// @LINE:11
def createStandaloneTortoise(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "create-standalone")
}
                        

}
                          

// @LINE:34
class ReverseAssets {


// @LINE:34
def at(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
class ReverseCompilerService {


// @LINE:22
// @LINE:21
def saveURL(): Call = {
   () match {
// @LINE:21
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "save-url")
                                         
   }
}
                                                

// @LINE:24
// @LINE:23
def saveCode(): Call = {
   () match {
// @LINE:23
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "save-code")
                                         
   }
}
                                                

// @LINE:26
// @LINE:25
def saveNlogo(): Call = {
   () match {
// @LINE:25
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "save-nlogo")
                                         
   }
}
                                                

// @LINE:18
// @LINE:17
def compileCode(): Call = {
   () match {
// @LINE:17
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "compile-code")
                                         
   }
}
                                                

// @LINE:20
// @LINE:19
def compileNlogo(): Call = {
   () match {
// @LINE:19
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "compile-nlogo")
                                         
   }
}
                                                

// @LINE:28
def modelStatuses(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "model/statuses.json")
}
                        

// @LINE:16
// @LINE:15
def compileURL(): Call = {
   () match {
// @LINE:15
case ()  =>
  import ReverseRouteContext.empty
  Call("POST", _prefix + { _defaultPrefix } + "compile-url")
                                         
   }
}
                                                

}
                          

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:8
def model(modelPath:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "model/" + implicitly[PathBindable[String]].unbind("modelPath", modelPath))
}
                        

// @LINE:7
def modelList(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "model/list.json")
}
                        

// @LINE:6
def index(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

}
                          
}
                  


// @LINE:34
// @LINE:31
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:31
// @LINE:30
// @LINE:12
// @LINE:11
class ReverseLocal {


// @LINE:31
def agentModel : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Local.agentModel",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "netlogo-agentmodel.js"})
      }
   """
)
                        

// @LINE:30
def engine : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Local.engine",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "netlogo-engine.js"})
      }
   """
)
                        

// @LINE:12
def tortoise : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Local.tortoise",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tortoise"})
      }
   """
)
                        

// @LINE:11
def createStandaloneTortoise : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Local.createStandaloneTortoise",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "create-standalone"})
      }
   """
)
                        

}
              

// @LINE:34
class ReverseAssets {


// @LINE:34
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
class ReverseCompilerService {


// @LINE:22
// @LINE:21
def saveURL : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.saveURL",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "save-url"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "save-url"})
      }
      }
   """
)
                        

// @LINE:24
// @LINE:23
def saveCode : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.saveCode",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "save-code"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "save-code"})
      }
      }
   """
)
                        

// @LINE:26
// @LINE:25
def saveNlogo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.saveNlogo",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "save-nlogo"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "save-nlogo"})
      }
      }
   """
)
                        

// @LINE:18
// @LINE:17
def compileCode : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.compileCode",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "compile-code"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "compile-code"})
      }
      }
   """
)
                        

// @LINE:20
// @LINE:19
def compileNlogo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.compileNlogo",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "compile-nlogo"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "compile-nlogo"})
      }
      }
   """
)
                        

// @LINE:28
def modelStatuses : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.modelStatuses",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "model/statuses.json"})
      }
   """
)
                        

// @LINE:16
// @LINE:15
def compileURL : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.CompilerService.compileURL",
   """
      function() {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "compile-url"})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "compile-url"})
      }
      }
   """
)
                        

}
              

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:8
def model : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.model",
   """
      function(modelPath) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "model/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("modelPath", modelPath)})
      }
   """
)
                        

// @LINE:7
def modelList : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.modelList",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "model/list.json"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

}
              
}
        


// @LINE:34
// @LINE:31
// @LINE:30
// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:31
// @LINE:30
// @LINE:12
// @LINE:11
class ReverseLocal {


// @LINE:31
def agentModel(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Local.agentModel(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "agentModel", Seq(), "GET", """""", _prefix + """netlogo-agentmodel.js""")
)
                      

// @LINE:30
def engine(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Local.engine(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "engine", Seq(), "GET", """""", _prefix + """netlogo-engine.js""")
)
                      

// @LINE:12
def tortoise(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Local.tortoise(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "tortoise", Seq(), "GET", """""", _prefix + """tortoise""")
)
                      

// @LINE:11
def createStandaloneTortoise(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Local.createStandaloneTortoise(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Local", "createStandaloneTortoise", Seq(), "GET", """ Local (Tortoise)""", _prefix + """create-standalone""")
)
                      

}
                          

// @LINE:34
class ReverseAssets {


// @LINE:34
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:28
// @LINE:26
// @LINE:25
// @LINE:24
// @LINE:23
// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:15
class ReverseCompilerService {


// @LINE:21
def saveURL(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.saveURL(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveURL", Seq(), "POST", """""", _prefix + """save-url""")
)
                      

// @LINE:23
def saveCode(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.saveCode(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveCode", Seq(), "POST", """""", _prefix + """save-code""")
)
                      

// @LINE:25
def saveNlogo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.saveNlogo(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "saveNlogo", Seq(), "POST", """""", _prefix + """save-nlogo""")
)
                      

// @LINE:17
def compileCode(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.compileCode(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileCode", Seq(), "POST", """""", _prefix + """compile-code""")
)
                      

// @LINE:19
def compileNlogo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.compileNlogo(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileNlogo", Seq(), "POST", """""", _prefix + """compile-nlogo""")
)
                      

// @LINE:28
def modelStatuses(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.modelStatuses(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "modelStatuses", Seq(), "GET", """""", _prefix + """model/statuses.json""")
)
                      

// @LINE:15
def compileURL(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.CompilerService.compileURL(), HandlerDef(this.getClass.getClassLoader, "", "controllers.CompilerService", "compileURL", Seq(), "POST", """ Compiler Service""", _prefix + """compile-url""")
)
                      

}
                          

// @LINE:8
// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:8
def model(modelPath:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.model(modelPath), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "model", Seq(classOf[String]), "GET", """""", _prefix + """model/$modelPath<.*\.nlogo>""")
)
                      

// @LINE:7
def modelList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.modelList(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "modelList", Seq(), "GET", """""", _prefix + """model/list.json""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Seq(), "GET", """ General""", _prefix + """""")
)
                      

}
                          
}
        
    