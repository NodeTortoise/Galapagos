
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
object mainTheme extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content: Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

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
"""))}
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jun 06 15:48:04 UTC 2015
                  SOURCE: /home/ubuntu/TFIA/Galapagos/app/views/mainTheme.scala.html
                  HASH: 29430ee50733fbeb5f4b0d98bd4ad3387c1ed595
                  MATRIX: 507->1|610->16|638->18|801->155|815->161|889->214|967->266|981->272|1038->309|1117->361|1132->367|1201->414|1285->471|1300->477|1353->509|1401->530|1416->536|1471->570|2020->1092|2048->1099|2087->1110
                  LINES: 19->1|22->1|24->3|29->8|29->8|29->8|30->9|30->9|30->9|31->10|31->10|31->10|32->11|32->11|32->11|33->12|33->12|33->12|50->29|50->29|51->30
                  -- GENERATED --
              */
          