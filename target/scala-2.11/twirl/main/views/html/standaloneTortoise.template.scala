
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object standaloneTortoise_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class standaloneTortoise extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template6[String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(libsJs: String, js: String, css: String, widgets: String, nlogoCode: String, info: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {

def /*3.2*/clean/*3.7*/(text: String) = {{
  Html(text.map {
    case '\n' => "\\n"
    case '"'  => "\\\""
    case '\\' => "\\\\"
    case x    => x
  }.mkString)
}};
Seq[Any](format.raw/*1.93*/("""

"""),format.raw/*10.2*/("""

"""),format.raw/*12.1*/("""<html>
  <head>
  </head>
  <body>
    <div id="model-container"></div>
    <style>
      """),_display_(/*18.8*/Html(css)),format.raw/*18.17*/("""
    """),format.raw/*19.5*/("""</style>
    <script type="text/javascript">
      """),_display_(/*21.8*/Html(js)),format.raw/*21.16*/("""
      """),format.raw/*22.7*/("""var session =
        (function () """),format.raw/*23.22*/("""{"""),format.raw/*23.23*/("""
          """),format.raw/*24.11*/("""var widgets = """),_display_(/*24.26*/Html(widgets)),format.raw/*24.39*/(""";
          var code    = """"),_display_(/*25.27*/clean(nlogoCode)),format.raw/*25.43*/("""";
          var info    = """"),_display_(/*26.27*/clean(info)),format.raw/*26.38*/("""";
          return Tortoise.fromCompiledModel('#model-container', widgets, code, info, '', true);
        """),format.raw/*28.9*/("""}"""),format.raw/*28.10*/(""")();
      """),_display_(/*29.8*/Html(libsJs)),format.raw/*29.20*/("""
      """),format.raw/*30.7*/("""session.startLoop()
    </script>
  </body>
</html>
"""))
      }
    }
  }

  def render(libsJs:String,js:String,css:String,widgets:String,nlogoCode:String,info:String): play.twirl.api.HtmlFormat.Appendable = apply(libsJs,js,css,widgets,nlogoCode,info)

  def f:((String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (libsJs,js,css,widgets,nlogoCode,info) => apply(libsJs,js,css,widgets,nlogoCode,info)

  def ref: this.type = this

}


}

/**/
object standaloneTortoise extends standaloneTortoise_Scope0.standaloneTortoise
              /*
                  -- GENERATED --
                  DATE: Tue Sep 15 06:06:04 UTC 2015
                  SOURCE: /home/ubuntu/Galapagos/app/views/standaloneTortoise.scala.html
                  HASH: 26b3d15cc3e0cc10c001e0c66a4d36cb16e77eb8
                  MATRIX: 588->1|757->95|769->100|942->92|971->243|1000->245|1117->336|1147->345|1179->350|1257->402|1286->410|1320->417|1383->452|1412->453|1451->464|1493->479|1527->492|1582->520|1619->536|1675->565|1707->576|1841->683|1870->684|1908->696|1941->708|1975->715
                  LINES: 20->1|24->3|24->3|32->1|34->10|36->12|42->18|42->18|43->19|45->21|45->21|46->22|47->23|47->23|48->24|48->24|48->24|49->25|49->25|50->26|50->26|52->28|52->28|53->29|53->29|54->30
                  -- GENERATED --
              */
          