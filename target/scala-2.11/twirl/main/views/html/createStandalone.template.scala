
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object createStandalone_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class createStandalone extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


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
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object createStandalone extends createStandalone_Scope0.createStandalone
              /*
                  -- GENERATED --
                  DATE: Tue Sep 15 06:06:04 UTC 2015
                  SOURCE: /home/ubuntu/Galapagos/app/views/createStandalone.scala.html
                  HASH: c247151723c06e3e6ed3adf06b62ea50d6f0ea80
                  MATRIX: 631->1|643->6|666->21|705->23|734->26|809->74|837->75|865->76|936->120|964->121|1002->132|1236->340|1263->341|1370->420|1399->421|1435->430|1503->470|1532->471|1561->472|1607->490|1636->491|1710->537|1739->538|1780->551|1904->648|1933->649|1969->658|1997->659|2063->697|2092->698|2131->709|2340->891|2368->892|2442->939|2457->945|2513->980|2584->1024|2599->1030|2661->1071|2739->1122|2754->1128|2810->1163|3675->2000|3704->2001|3738->2008|3862->2105|3890->2106|3920->2109|3962->2121
                  LINES: 25->1|25->1|25->1|25->1|26->2|27->3|27->3|27->3|28->4|28->4|29->5|32->8|32->8|34->10|34->10|35->11|35->11|35->11|35->11|35->11|35->11|36->12|36->12|37->13|39->15|39->15|40->16|40->16|42->18|42->18|43->19|46->22|46->22|49->25|49->25|49->25|50->26|50->26|50->26|51->27|51->27|51->27|72->48|72->48|73->49|75->51|75->51|76->52|78->54
                  -- GENERATED --
              */
          