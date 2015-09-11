
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object standaloneTortoise extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template6[String,String,String,String,String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(libsJs: String, js: String, css: String, widgets: String, nlogoCode: String, info: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {
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
      <title>NodeTortoise</title>
  </head>
  <body>
    <div id="model-container" style="float: left"></div>
    <div id="users" style="float: left; margin-left: 25px; height: 200px; border: solid 1px black; width: 250px; padding: 10px"></div>
    <style>
      """),_display_(/*20.8*/Html(css)),format.raw/*20.17*/("""
    """),format.raw/*21.5*/("""</style>
    <script src="/socket.io/socket.io.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/_Controller.js"></script>
    <script type="text/javascript">
      """),_display_(/*26.8*/Html(js)),format.raw/*26.16*/("""
      """),format.raw/*27.7*/("""_Controller.create();
      var session =
        (function () """),format.raw/*29.22*/("""{"""),format.raw/*29.23*/("""
          """),format.raw/*30.11*/("""var widgets = """),_display_(/*30.26*/Html(widgets)),format.raw/*30.39*/(""";
          var code    = """"),_display_(/*31.27*/clean(nlogoCode)),format.raw/*31.43*/("""";
          var info    = """"),_display_(/*32.27*/clean(info)),format.raw/*32.38*/("""";
          return Tortoise.fromCompiledModel('#model-container', widgets, code, info, '', true);
        """),format.raw/*34.9*/("""}"""),format.raw/*34.10*/(""")();
      """),_display_(/*35.8*/Html(libsJs)),format.raw/*35.20*/("""
      """),format.raw/*36.7*/("""session.startLoop()
    </script>
  </body>
</html>
"""))}
  }

  def render(libsJs:String,js:String,css:String,widgets:String,nlogoCode:String,info:String): play.twirl.api.HtmlFormat.Appendable = apply(libsJs,js,css,widgets,nlogoCode,info)

  def f:((String,String,String,String,String,String) => play.twirl.api.HtmlFormat.Appendable) = (libsJs,js,css,widgets,nlogoCode,info) => apply(libsJs,js,css,widgets,nlogoCode,info)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jun 06 15:48:04 UTC 2015
                  SOURCE: /home/ubuntu/TFIA/Galapagos/app/views/standaloneTortoise.scala.html
                  HASH: 9b3df4a3958332f0a3d0083b8d7078b98f8bbf90
                  MATRIX: 553->1|715->97|727->102|907->92|938->252|969->256|1283->544|1313->553|1346->559|1621->808|1650->816|1685->824|1778->889|1807->890|1847->902|1889->917|1923->930|1979->959|2016->975|2073->1005|2105->1016|2241->1125|2270->1126|2309->1139|2342->1151|2377->1159
                  LINES: 19->1|21->3|21->3|29->1|31->10|33->12|41->20|41->20|42->21|47->26|47->26|48->27|50->29|50->29|51->30|51->30|51->30|52->31|52->31|53->32|53->32|55->34|55->34|56->35|56->35|57->36
                  -- GENERATED --
              */
          