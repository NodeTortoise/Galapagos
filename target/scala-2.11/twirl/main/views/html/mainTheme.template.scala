
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object mainTheme_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class mainTheme extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.17*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>

<html class="not_full_height">
  <head>
    <title>NetLogo Web</title>
    <link rel="stylesheet" media="screen" href='"""),_display_(/*8.50*/routes/*8.56*/.Assets.at("stylesheets/managed/bootstrap-1.4.0.css")),format.raw/*8.109*/("""'>
    <link rel="stylesheet" media="screen" href='"""),_display_(/*9.50*/routes/*9.56*/.Assets.at("stylesheets/classes.css")),format.raw/*9.93*/("""'>
    <link rel="stylesheet" media="screen" href='"""),_display_(/*10.50*/routes/*10.56*/.Assets.at("stylesheets/element-overrides.css")),format.raw/*10.103*/("""'>
    <link rel="shortcut icon" type="image/png" href='"""),_display_(/*11.55*/routes/*11.61*/.Assets.at("images/favicon.png")),format.raw/*11.93*/("""'>
    <script src='"""),_display_(/*12.19*/routes/*12.25*/.Assets.at("lib/jquery/jquery.js")),format.raw/*12.59*/("""' type="text/javascript"></script>
  </head>
  <body class="not_full_height">

    <div class="topbar">
      <div class="fill">
        <div class="core_content_box container">
          <a class="brand" href="/">NetLogo Web</a>
        </div>
      </div>
    </div>

    <div class="core_content_box container not_full_height">

      <div class="content" style="background-color: #347999; height: auto;">
        <table id="chat" class="full_height" style="table-layout: fixed; width: 100%">
          <tr>
          """),_display_(/*29.12*/content),format.raw/*29.19*/("""
          """),format.raw/*30.11*/("""</tr>
        </table>
      </div>

    </div>

  </body>
</html>
"""))
      }
    }
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}


}

/**/
object mainTheme extends mainTheme_Scope0.mainTheme
              /*
                  -- GENERATED --
                  DATE: Tue Sep 15 06:06:04 UTC 2015
                  SOURCE: /home/ubuntu/Galapagos/app/views/mainTheme.scala.html
                  HASH: 6635dea18f0c5c29811ae561c85bb4b1a07b7e41
                  MATRIX: 533->1|643->16|671->18|834->155|848->161|922->214|1000->266|1014->272|1071->309|1150->361|1165->367|1234->414|1318->471|1333->477|1386->509|1434->530|1449->536|1504->570|2053->1092|2081->1099|2120->1110
                  LINES: 20->1|25->1|27->3|32->8|32->8|32->8|33->9|33->9|33->9|34->10|34->10|34->10|35->11|35->11|35->11|36->12|36->12|36->12|53->29|53->29|54->30
                  -- GENERATED --
              */
          