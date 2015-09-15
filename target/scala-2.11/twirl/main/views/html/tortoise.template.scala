
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object tortoise_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class tortoise extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<html>
  <head>
    <title>NetLogo Web</title>
    <link rel="stylesheet" href='"""),_display_(/*4.35*/routes/*4.41*/.Assets.at("lib/chosen/chosen.css")),format.raw/*4.76*/("""' />
    <link rel="stylesheet" href='"""),_display_(/*5.35*/routes/*5.41*/.Assets.at("lib/codemirror/lib/codemirror.css")),format.raw/*5.88*/("""' />
    <link rel="stylesheet" href='"""),_display_(/*6.35*/routes/*6.41*/.Assets.at("stylesheets/classes.css")),format.raw/*6.78*/("""'>
    <link rel="stylesheet" href='"""),_display_(/*7.35*/routes/*7.41*/.Assets.at("stylesheets/widgets.css")),format.raw/*7.78*/("""' />
    <link rel="stylesheet" href='"""),_display_(/*8.35*/routes/*8.41*/.Assets.at("stylesheets/classic.css")),format.raw/*8.78*/("""' />
    <link rel="stylesheet" href='"""),_display_(/*9.35*/routes/*9.41*/.Assets.at("stylesheets/netlogo-syntax.css")),format.raw/*9.85*/("""' />
  </head>
  <body>
    <div class="dynamic-row" style="margin-bottom: 20px; max-width: 900px;">
      <div style="width: 320px;">
        <h3>Find a Built-In Model</h3>
        <div class="model-list tortoise-model-list" style="width: 300px"></div>
      </div>
      <h3 style="width: 30px;"> or </h3>
      <div style="">
        <h3>Upload a Model</h3>
        <input id="model-file-input" type="file" name="model" />
      </div>
    </div>
    <div id="model-container" class="model-container"></div>
  </body>
  <script src='"""),_display_(/*25.17*/routes/*25.23*/.Assets.at("lib/jquery/jquery.js")),format.raw/*25.57*/("""' type="text/javascript"></script>
  <script src='"""),_display_(/*26.17*/routes/*26.23*/.Assets.at("lib/chosen/chosen.jquery.js")),format.raw/*26.64*/("""' type="text/javascript"></script>
  <script src='"""),_display_(/*27.17*/routes/*27.23*/.Assets.at("lib/filesaver.js/FileSaver.js")),format.raw/*27.66*/("""' type="text/javascript"></script>
  <script src=""""),_display_(/*28.17*/routes/*28.23*/.Assets.at("lib/markdown-js/markdown.js")),format.raw/*28.64*/(""""></script>
  <script src=""""),_display_(/*29.17*/routes/*29.23*/.Assets.at("lib/highcharts/adapters/standalone-framework.js")),format.raw/*29.84*/(""""></script>
  <script src=""""),_display_(/*30.17*/routes/*30.23*/.Assets.at("lib/highcharts/highcharts.js")),format.raw/*30.65*/(""""></script>
  <script src=""""),_display_(/*31.17*/routes/*31.23*/.Assets.at("lib/highcharts/modules/exporting.js")),format.raw/*31.72*/(""""></script>
  <script src=""""),_display_(/*32.17*/routes/*32.23*/.Assets.at("lib/ractive/ractive.js")),format.raw/*32.59*/(""""></script>
  <script src=""""),_display_(/*33.17*/routes/*33.23*/.Assets.at("lib/codemirror/lib/codemirror.js")),format.raw/*33.69*/(""""></script>
  <script src=""""),_display_(/*34.17*/routes/*34.23*/.Assets.at("lib/codemirror/addon/mode/simple.js")),format.raw/*34.72*/(""""></script>
  <script>
    var exports = """),format.raw/*36.19*/("""{"""),format.raw/*36.20*/("""}"""),format.raw/*36.21*/(""";
  </script>
  <script type="text/javascript" src='"""),_display_(/*38.40*/routes/*38.46*/.Assets.at("javascripts/models.js")),format.raw/*38.81*/("""'></script>
  <script src=""""),_display_(/*39.17*/routes/*39.23*/.Local.engine),format.raw/*39.36*/(""""></script>
  <script src=""""),_display_(/*40.17*/routes/*40.23*/.Assets.at("javascripts/TortoiseJS/agent/colors.js")),format.raw/*40.75*/(""""></script>
  <script src=""""),_display_(/*41.17*/routes/*41.23*/.Assets.at("javascripts/TortoiseJS/agent/drawshape.js")),format.raw/*41.78*/(""""></script>
  <script src=""""),_display_(/*42.17*/routes/*42.23*/.Assets.at("javascripts/TortoiseJS/agent/defaultshapes.js")),format.raw/*42.82*/(""""></script>
  <script src=""""),_display_(/*43.17*/routes/*43.23*/.Assets.at("javascripts/TortoiseJS/agent/linkdrawer.js")),format.raw/*43.79*/(""""></script>
  <script src=""""),_display_(/*44.17*/routes/*44.23*/.Assets.at("javascripts/TortoiseJS/agent/view.js")),format.raw/*44.73*/(""""></script>
  <script src=""""),_display_(/*45.17*/routes/*45.23*/.Assets.at("javascripts/TortoiseJS/agent/editor.js")),format.raw/*45.75*/(""""></script>
  <script src=""""),_display_(/*46.17*/routes/*46.23*/.Assets.at("javascripts/TortoiseJS/agent/info.js")),format.raw/*46.73*/(""""></script>
  <script src=""""),_display_(/*47.17*/routes/*47.23*/.Assets.at("javascripts/TortoiseJS/agent/output.js")),format.raw/*47.75*/(""""></script>
  <script src=""""),_display_(/*48.17*/routes/*48.23*/.Assets.at("javascripts/TortoiseJS/agent/console.js")),format.raw/*48.76*/(""""></script>
  <script src=""""),_display_(/*49.17*/routes/*49.23*/.Assets.at("javascripts/TortoiseJS/agent/widgets.js")),format.raw/*49.76*/(""""></script>
  <script src=""""),_display_(/*50.17*/routes/*50.23*/.CompilerService.netLogoWeb),format.raw/*50.50*/(""""></script>
  <script src=""""),_display_(/*51.17*/routes/*51.23*/.Assets.at("javascripts/TortoiseJS/control/session-lite.js")),format.raw/*51.83*/(""""></script>
  <script src=""""),_display_(/*52.17*/routes/*52.23*/.Assets.at("javascripts/plot/highchartsops.js")),format.raw/*52.70*/(""""></script>
  <script>
    var modelContainer = document.querySelector('#model-container');
    var session        = null; // initialized in callback;
    var urlPrefix      = location.protocol + '//' + location.host + '/assets/modelslib/';
    var url            = urlPrefix + 'Sample%20Models/Biology/Wolf%20Sheep%20Predation.nlogo';
    if (window.location.hash) """),format.raw/*58.31*/("""{"""),format.raw/*58.32*/("""
      """),format.raw/*59.7*/("""url = window.location.hash.substring(1);
    """),format.raw/*60.5*/("""}"""),format.raw/*60.6*/("""

    """),format.raw/*62.5*/("""document.querySelector('#model-file-input').addEventListener('change', function (event) """),format.raw/*62.93*/("""{"""),format.raw/*62.94*/("""
      """),format.raw/*63.7*/("""var reader = new FileReader();
      reader.onload = function (e) """),format.raw/*64.36*/("""{"""),format.raw/*64.37*/("""
        """),format.raw/*65.9*/("""openNlogo(e.target.result);
      """),format.raw/*66.7*/("""}"""),format.raw/*66.8*/(""";
      reader.readAsText(this.files[0]);
    """),format.raw/*68.5*/("""}"""),format.raw/*68.6*/(""");

    function pickModel(url) """),format.raw/*70.29*/("""{"""),format.raw/*70.30*/("""
      """),format.raw/*71.7*/("""var encoded = encodeURI(urlPrefix + url);
      openURL(encoded);
    """),format.raw/*73.5*/("""}"""),format.raw/*73.6*/("""

    """),format.raw/*75.5*/("""function openURL(url) """),format.raw/*75.27*/("""{"""),format.raw/*75.28*/("""
      """),format.raw/*76.7*/("""window.location.hash = url;
      if (decodeURI(url) === url) """),format.raw/*77.35*/("""{"""),format.raw/*77.36*/("""
        """),format.raw/*78.9*/("""url = encodeURI(url);
      """),format.raw/*79.7*/("""}"""),format.raw/*79.8*/("""
      """),format.raw/*80.7*/("""if (session) """),format.raw/*80.20*/("""{"""),format.raw/*80.21*/("""
        """),format.raw/*81.9*/("""session.teardown();
      """),format.raw/*82.7*/("""}"""),format.raw/*82.8*/("""
      """),format.raw/*83.7*/("""Tortoise.fromURL(url, modelContainer, function (res) """),format.raw/*83.60*/("""{"""),format.raw/*83.61*/("""
        """),format.raw/*84.9*/("""session = res;
        session.startLoop();
      """),format.raw/*86.7*/("""}"""),format.raw/*86.8*/(""");
    """),format.raw/*87.5*/("""}"""),format.raw/*87.6*/("""

    """),format.raw/*89.5*/("""function openNlogo(nlogoContents) """),format.raw/*89.39*/("""{"""),format.raw/*89.40*/("""
      """),format.raw/*90.7*/("""window.location.hash = "";
      if (session) """),format.raw/*91.20*/("""{"""),format.raw/*91.21*/("""
        """),format.raw/*92.9*/("""session.teardown();
      """),format.raw/*93.7*/("""}"""),format.raw/*93.8*/("""
      """),format.raw/*94.7*/("""filePath = $("#model-file-input")[0].value;
      Tortoise.fromNlogo(nlogoContents, modelContainer, filePath, function (res) """),format.raw/*95.82*/("""{"""),format.raw/*95.83*/("""
        """),format.raw/*96.9*/("""session = res;
        session.startLoop();
      """),format.raw/*98.7*/("""}"""),format.raw/*98.8*/(""");
    """),format.raw/*99.5*/("""}"""),format.raw/*99.6*/("""

    """),format.raw/*101.5*/("""exports.bindModelChooser($('.model-list'), pickModel);

    openURL(url);
  </script>
</html>
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
object tortoise extends tortoise_Scope0.tortoise
              /*
                  -- GENERATED --
                  DATE: Tue Sep 15 06:06:04 UTC 2015
                  SOURCE: /home/ubuntu/Galapagos/app/views/tortoise.scala.html
                  HASH: fea0ed343bd982672892e9ab1e944ed7c1030a46
                  MATRIX: 615->0|722->81|736->87|791->122|856->161|870->167|937->214|1002->253|1016->259|1073->296|1136->333|1150->339|1207->376|1272->415|1286->421|1343->458|1408->497|1422->503|1486->547|2050->1084|2065->1090|2120->1124|2198->1175|2213->1181|2275->1222|2353->1273|2368->1279|2432->1322|2510->1373|2525->1379|2587->1420|2642->1448|2657->1454|2739->1515|2794->1543|2809->1549|2872->1591|2927->1619|2942->1625|3012->1674|3067->1702|3082->1708|3139->1744|3194->1772|3209->1778|3276->1824|3331->1852|3346->1858|3416->1907|3485->1948|3514->1949|3543->1950|3623->2003|3638->2009|3694->2044|3749->2072|3764->2078|3798->2091|3853->2119|3868->2125|3941->2177|3996->2205|4011->2211|4087->2266|4142->2294|4157->2300|4237->2359|4292->2387|4307->2393|4384->2449|4439->2477|4454->2483|4525->2533|4580->2561|4595->2567|4668->2619|4723->2647|4738->2653|4809->2703|4864->2731|4879->2737|4952->2789|5007->2817|5022->2823|5096->2876|5151->2904|5166->2910|5240->2963|5295->2991|5310->2997|5358->3024|5413->3052|5428->3058|5509->3118|5564->3146|5579->3152|5647->3199|6041->3565|6070->3566|6104->3573|6176->3618|6204->3619|6237->3625|6353->3713|6382->3714|6416->3721|6510->3787|6539->3788|6575->3797|6636->3831|6664->3832|6737->3878|6765->3879|6825->3911|6854->3912|6888->3919|6985->3989|7013->3990|7046->3996|7096->4018|7125->4019|7159->4026|7249->4088|7278->4089|7314->4098|7369->4126|7397->4127|7431->4134|7472->4147|7501->4148|7537->4157|7590->4183|7618->4184|7652->4191|7733->4244|7762->4245|7798->4254|7875->4304|7903->4305|7937->4312|7965->4313|7998->4319|8060->4353|8089->4354|8123->4361|8197->4407|8226->4408|8262->4417|8315->4443|8343->4444|8377->4451|8530->4576|8559->4577|8595->4586|8672->4636|8700->4637|8734->4644|8762->4645|8796->4651
                  LINES: 25->1|28->4|28->4|28->4|29->5|29->5|29->5|30->6|30->6|30->6|31->7|31->7|31->7|32->8|32->8|32->8|33->9|33->9|33->9|49->25|49->25|49->25|50->26|50->26|50->26|51->27|51->27|51->27|52->28|52->28|52->28|53->29|53->29|53->29|54->30|54->30|54->30|55->31|55->31|55->31|56->32|56->32|56->32|57->33|57->33|57->33|58->34|58->34|58->34|60->36|60->36|60->36|62->38|62->38|62->38|63->39|63->39|63->39|64->40|64->40|64->40|65->41|65->41|65->41|66->42|66->42|66->42|67->43|67->43|67->43|68->44|68->44|68->44|69->45|69->45|69->45|70->46|70->46|70->46|71->47|71->47|71->47|72->48|72->48|72->48|73->49|73->49|73->49|74->50|74->50|74->50|75->51|75->51|75->51|76->52|76->52|76->52|82->58|82->58|83->59|84->60|84->60|86->62|86->62|86->62|87->63|88->64|88->64|89->65|90->66|90->66|92->68|92->68|94->70|94->70|95->71|97->73|97->73|99->75|99->75|99->75|100->76|101->77|101->77|102->78|103->79|103->79|104->80|104->80|104->80|105->81|106->82|106->82|107->83|107->83|107->83|108->84|110->86|110->86|111->87|111->87|113->89|113->89|113->89|114->90|115->91|115->91|116->92|117->93|117->93|118->94|119->95|119->95|120->96|122->98|122->98|123->99|123->99|125->101
                  -- GENERATED --
              */
          