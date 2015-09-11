
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
object createStandalone extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](_display_(/*1.2*/views/*1.7*/.html.mainTheme/*1.22*/ {_display_(Seq[Any](format.raw/*1.24*/("""
  """),format.raw/*2.3*/("""<script type="text/javascript">
      exports = """),format.raw/*3.17*/("""{"""),format.raw/*3.18*/("""}"""),format.raw/*3.19*/(""";
      submitModelURL = function(modelURL) """),format.raw/*4.43*/("""{"""),format.raw/*4.44*/("""
          """),format.raw/*5.11*/("""var submissionURL = location.protocol + "//" + location.host + "/assets/modelslib/" + encodeURI(modelURL);
          $("#url-input").val(submissionURL);
          $("td[name=nlogo-url-form]").submit();
      """),format.raw/*8.7*/("""}"""),format.raw/*8.8*/(""";

      bindTextFileChooserToFileInput = function(textFileChooser, fileInput) """),format.raw/*10.77*/("""{"""),format.raw/*10.78*/("""
        """),format.raw/*11.9*/("""textFileChooser.on("click", function(e) """),format.raw/*11.49*/("""{"""),format.raw/*11.50*/(""" """),format.raw/*11.51*/("""fileInput.click() """),format.raw/*11.69*/("""}"""),format.raw/*11.70*/(""");
        fileInput.on("change", function(e) """),format.raw/*12.44*/("""{"""),format.raw/*12.45*/("""
            """),format.raw/*13.13*/("""var fileName = e.currentTarget.files[0].name;
            textFileChooser.val(fileName);
        """),format.raw/*15.9*/("""}"""),format.raw/*15.10*/(""");
      """),format.raw/*16.7*/("""}"""),format.raw/*16.8*/(""";

      $(document).ready(function() """),format.raw/*18.36*/("""{"""),format.raw/*18.37*/("""
          """),format.raw/*19.11*/("""var modelList = $('.model-list');
          exports.bindModelChooser(modelList, submitModelURL);
          bindTextFileChooserToFileInput($("#choose-file"), $("#file-input"));
      """),format.raw/*22.7*/("""}"""),format.raw/*22.8*/(""")
  </script>

  <link rel="stylesheet" href='"""),_display_(/*25.33*/routes/*25.39*/.Assets.at("lib/chosen/chosen.css")),format.raw/*25.74*/("""' />
  <script type="text/javascript" src='"""),_display_(/*26.40*/routes/*26.46*/.Assets.at("lib/chosen/chosen.jquery.js")),format.raw/*26.87*/("""'></script>
  <script type="text/javascript" src='"""),_display_(/*27.40*/routes/*27.46*/.Assets.at("javascripts/models.js")),format.raw/*27.81*/("""'></script>

  <h3>Upload a Model</h3>
  <form name="nlogo-file-form" enctype="multipart/form-data" action="/save-nlogo" method="POST">
      <input id="choose-file" type="text" class="rounded text_input_margin" placeholder="Select a file..."/>
      <input type="submit" value="Submit" class="btn">
      <input id="file-input" type="file"  name="model" size="0" class="invisible" />
  </form>

  <br>
  <h4>-OR-</h4>

  <h3>Find a Built-In Model</h3>

  <form id="nlogo-url-form" name="nlogo-url-form" action="/save-url" method="POST">
      <div class="model-list tortoise-model-list"></div>
      <input id="url-input" placeholder="Model URL" type="text" name="model" class="rounded text_input_margin">
      <input type="submit" value="Submit" class="btn">
  </form>

  <script type="text/javascript">
    if (window.location.hash) """),format.raw/*48.31*/("""{"""),format.raw/*48.32*/("""
      """),format.raw/*49.7*/("""$('#url-input').val(window.location.hash.substring(1));
      $("#nlogo-url-form").submit();
    """),format.raw/*51.5*/("""}"""),format.raw/*51.6*/("""
  """),format.raw/*52.3*/("""</script>

""")))}),format.raw/*54.2*/("""
"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jun 06 15:48:04 UTC 2015
                  SOURCE: /home/ubuntu/TFIA/Galapagos/app/views/createStandalone.scala.html
                  HASH: 19673aa15583695bcc2f6980a68377a3591cae19
                  MATRIX: 591->1|603->6|626->21|665->23|694->26|769->74|797->75|825->76|896->120|924->121|962->132|1196->340|1223->341|1330->420|1359->421|1395->430|1463->470|1492->471|1521->472|1567->490|1596->491|1670->537|1699->538|1740->551|1864->648|1893->649|1929->658|1957->659|2023->697|2052->698|2091->709|2300->891|2328->892|2402->939|2417->945|2473->980|2544->1024|2559->1030|2621->1071|2699->1122|2714->1128|2770->1163|3635->2000|3664->2001|3698->2008|3822->2105|3850->2106|3880->2109|3922->2121
                  LINES: 22->1|22->1|22->1|22->1|23->2|24->3|24->3|24->3|25->4|25->4|26->5|29->8|29->8|31->10|31->10|32->11|32->11|32->11|32->11|32->11|32->11|33->12|33->12|34->13|36->15|36->15|37->16|37->16|39->18|39->18|40->19|43->22|43->22|46->25|46->25|46->25|47->26|47->26|47->26|48->27|48->27|48->27|69->48|69->48|70->49|72->51|72->51|73->52|75->54
                  -- GENERATED --
              */
          