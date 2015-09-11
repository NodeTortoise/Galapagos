
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
object tortoise extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

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
      <div id="model-container"></div>
  </body>
  <script src='"""),_display_(/*25.17*/routes/*25.23*/.Assets.at("lib/jquery/jquery.js")),format.raw/*25.57*/("""' type="text/javascript"></script>
  <script type="text/javascript" src='"""),_display_(/*26.40*/routes/*26.46*/.Assets.at("lib/chosen/chosen.jquery.js")),format.raw/*26.87*/("""'></script>
  <script src=""""),_display_(/*27.17*/routes/*27.23*/.Assets.at("lib/markdown-js/markdown.js")),format.raw/*27.64*/(""""></script>
  <script src=""""),_display_(/*28.17*/routes/*28.23*/.Assets.at("lib/highcharts/adapters/standalone-framework.js")),format.raw/*28.84*/(""""></script>
  <script src=""""),_display_(/*29.17*/routes/*29.23*/.Assets.at("lib/highcharts/highcharts.js")),format.raw/*29.65*/(""""></script>
  <script src=""""),_display_(/*30.17*/routes/*30.23*/.Assets.at("lib/highcharts/modules/exporting.js")),format.raw/*30.72*/(""""></script>
  <script src=""""),_display_(/*31.17*/routes/*31.23*/.Assets.at("lib/ractive/ractive.js")),format.raw/*31.59*/(""""></script>
  <script src=""""),_display_(/*32.17*/routes/*32.23*/.Assets.at("lib/codemirror/lib/codemirror.js")),format.raw/*32.69*/(""""></script>
  <script src=""""),_display_(/*33.17*/routes/*33.23*/.Assets.at("lib/codemirror/addon/mode/simple.js")),format.raw/*33.72*/(""""></script>
  <script>
    var exports = """),format.raw/*35.19*/("""{"""),format.raw/*35.20*/("""}"""),format.raw/*35.21*/(""";
  </script>
  <script type="text/javascript" src='"""),_display_(/*37.40*/routes/*37.46*/.Assets.at("javascripts/models.js")),format.raw/*37.81*/("""'></script>
  <script src=""""),_display_(/*38.17*/routes/*38.23*/.Local.engine),format.raw/*38.36*/(""""></script>
  <script src=""""),_display_(/*39.17*/routes/*39.23*/.Assets.at("javascripts/TortoiseJS/agent/colors.js")),format.raw/*39.75*/(""""></script>
  <script src=""""),_display_(/*40.17*/routes/*40.23*/.Assets.at("javascripts/TortoiseJS/agent/drawshape.js")),format.raw/*40.78*/(""""></script>
  <script src=""""),_display_(/*41.17*/routes/*41.23*/.Assets.at("javascripts/TortoiseJS/agent/defaultshapes.js")),format.raw/*41.82*/(""""></script>
  <script src=""""),_display_(/*42.17*/routes/*42.23*/.Assets.at("javascripts/TortoiseJS/agent/linkdrawer.js")),format.raw/*42.79*/(""""></script>
  <script src=""""),_display_(/*43.17*/routes/*43.23*/.Assets.at("javascripts/TortoiseJS/agent/view.js")),format.raw/*43.73*/(""""></script>
  <script src=""""),_display_(/*44.17*/routes/*44.23*/.Assets.at("javascripts/TortoiseJS/agent/editor.js")),format.raw/*44.75*/(""""></script>
  <script src=""""),_display_(/*45.17*/routes/*45.23*/.Assets.at("javascripts/TortoiseJS/agent/info.js")),format.raw/*45.73*/(""""></script>
  <script src=""""),_display_(/*46.17*/routes/*46.23*/.Assets.at("javascripts/TortoiseJS/agent/output.js")),format.raw/*46.75*/(""""></script>
  <script src=""""),_display_(/*47.17*/routes/*47.23*/.Assets.at("javascripts/TortoiseJS/agent/console.js")),format.raw/*47.76*/(""""></script>
  <script src=""""),_display_(/*48.17*/routes/*48.23*/.Assets.at("javascripts/TortoiseJS/agent/widgets.js")),format.raw/*48.76*/(""""></script>
  <script src=""""),_display_(/*49.17*/routes/*49.23*/.Assets.at("javascripts/TortoiseJS/control/session-lite.js")),format.raw/*49.83*/(""""></script>
  <script src=""""),_display_(/*50.17*/routes/*50.23*/.Assets.at("javascripts/plot/highchartsops.js")),format.raw/*50.70*/(""""></script>
  <script>
    var modelContainer = document.querySelector('#model-container');
    var session        = null; // initialized in callback;
    var urlPrefix      = location.protocol + '//' + location.host + '/assets/modelslib/';
    var url            = urlPrefix + 'Sample%20Models/Biology/Wolf%20Sheep%20Predation.nlogo';
    if (window.location.hash) """),format.raw/*56.31*/("""{"""),format.raw/*56.32*/("""
      """),format.raw/*57.7*/("""url = window.location.hash.substring(1);
    """),format.raw/*58.5*/("""}"""),format.raw/*58.6*/("""

    """),format.raw/*60.5*/("""document.querySelector('#model-file-input').addEventListener('change', function (event) """),format.raw/*60.93*/("""{"""),format.raw/*60.94*/("""
      """),format.raw/*61.7*/("""var reader = new FileReader();
      reader.onload = function (e) """),format.raw/*62.36*/("""{"""),format.raw/*62.37*/("""
        """),format.raw/*63.9*/("""openNlogo(e.target.result);
      """),format.raw/*64.7*/("""}"""),format.raw/*64.8*/(""";
      reader.readAsText(this.files[0]);
    """),format.raw/*66.5*/("""}"""),format.raw/*66.6*/(""");

    function pickModel(url) """),format.raw/*68.29*/("""{"""),format.raw/*68.30*/("""
      """),format.raw/*69.7*/("""var encoded = encodeURI(urlPrefix + url);
      openURL(encoded);
    """),format.raw/*71.5*/("""}"""),format.raw/*71.6*/("""

    """),format.raw/*73.5*/("""function openURL(url) """),format.raw/*73.27*/("""{"""),format.raw/*73.28*/("""
      """),format.raw/*74.7*/("""window.location.hash = url;
      if (decodeURI(url) === url) """),format.raw/*75.35*/("""{"""),format.raw/*75.36*/("""
        """),format.raw/*76.9*/("""url = encodeURI(url);
      """),format.raw/*77.7*/("""}"""),format.raw/*77.8*/("""
      """),format.raw/*78.7*/("""if (session) """),format.raw/*78.20*/("""{"""),format.raw/*78.21*/("""
        """),format.raw/*79.9*/("""session.teardown();
      """),format.raw/*80.7*/("""}"""),format.raw/*80.8*/("""
      """),format.raw/*81.7*/("""Tortoise.fromURL(url, modelContainer, function (res) """),format.raw/*81.60*/("""{"""),format.raw/*81.61*/("""
        """),format.raw/*82.9*/("""session = res;
        session.startLoop();
      """),format.raw/*84.7*/("""}"""),format.raw/*84.8*/(""");
    """),format.raw/*85.5*/("""}"""),format.raw/*85.6*/("""

    """),format.raw/*87.5*/("""function openNlogo(nlogoContents) """),format.raw/*87.39*/("""{"""),format.raw/*87.40*/("""
      """),format.raw/*88.7*/("""window.location.hash = "";
      if (session) """),format.raw/*89.20*/("""{"""),format.raw/*89.21*/("""
        """),format.raw/*90.9*/("""session.teardown();
      """),format.raw/*91.7*/("""}"""),format.raw/*91.8*/("""
      """),format.raw/*92.7*/("""Tortoise.fromNlogo(nlogoContents, modelContainer, function (res) """),format.raw/*92.72*/("""{"""),format.raw/*92.73*/("""
        """),format.raw/*93.9*/("""session = res;
        session.startLoop();
      """),format.raw/*95.7*/("""}"""),format.raw/*95.8*/(""");
    """),format.raw/*96.5*/("""}"""),format.raw/*96.6*/("""

    """),format.raw/*98.5*/("""exports.bindModelChooser($('.model-list'), pickModel);

    openURL(url);
  </script>
</html>
"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Jun 06 15:48:04 UTC 2015
                  SOURCE: /home/ubuntu/TFIA/Galapagos/app/views/tortoise.scala.html
                  HASH: 349f62ce3daafbcc6802e1429826e071814083fc
                  MATRIX: 583->0|690->81|704->87|759->122|824->161|838->167|905->214|970->253|984->259|1041->296|1104->333|1118->339|1175->376|1240->415|1254->421|1311->458|1376->497|1390->503|1454->547|1996->1062|2011->1068|2066->1102|2167->1176|2182->1182|2244->1223|2299->1251|2314->1257|2376->1298|2431->1326|2446->1332|2528->1393|2583->1421|2598->1427|2661->1469|2716->1497|2731->1503|2801->1552|2856->1580|2871->1586|2928->1622|2983->1650|2998->1656|3065->1702|3120->1730|3135->1736|3205->1785|3274->1826|3303->1827|3332->1828|3412->1881|3427->1887|3483->1922|3538->1950|3553->1956|3587->1969|3642->1997|3657->2003|3730->2055|3785->2083|3800->2089|3876->2144|3931->2172|3946->2178|4026->2237|4081->2265|4096->2271|4173->2327|4228->2355|4243->2361|4314->2411|4369->2439|4384->2445|4457->2497|4512->2525|4527->2531|4598->2581|4653->2609|4668->2615|4741->2667|4796->2695|4811->2701|4885->2754|4940->2782|4955->2788|5029->2841|5084->2869|5099->2875|5180->2935|5235->2963|5250->2969|5318->3016|5712->3382|5741->3383|5775->3390|5847->3435|5875->3436|5908->3442|6024->3530|6053->3531|6087->3538|6181->3604|6210->3605|6246->3614|6307->3648|6335->3649|6408->3695|6436->3696|6496->3728|6525->3729|6559->3736|6656->3806|6684->3807|6717->3813|6767->3835|6796->3836|6830->3843|6920->3905|6949->3906|6985->3915|7040->3943|7068->3944|7102->3951|7143->3964|7172->3965|7208->3974|7261->4000|7289->4001|7323->4008|7404->4061|7433->4062|7469->4071|7546->4121|7574->4122|7608->4129|7636->4130|7669->4136|7731->4170|7760->4171|7794->4178|7868->4224|7897->4225|7933->4234|7986->4260|8014->4261|8048->4268|8141->4333|8170->4334|8206->4343|8283->4393|8311->4394|8345->4401|8373->4402|8406->4408
                  LINES: 22->1|25->4|25->4|25->4|26->5|26->5|26->5|27->6|27->6|27->6|28->7|28->7|28->7|29->8|29->8|29->8|30->9|30->9|30->9|46->25|46->25|46->25|47->26|47->26|47->26|48->27|48->27|48->27|49->28|49->28|49->28|50->29|50->29|50->29|51->30|51->30|51->30|52->31|52->31|52->31|53->32|53->32|53->32|54->33|54->33|54->33|56->35|56->35|56->35|58->37|58->37|58->37|59->38|59->38|59->38|60->39|60->39|60->39|61->40|61->40|61->40|62->41|62->41|62->41|63->42|63->42|63->42|64->43|64->43|64->43|65->44|65->44|65->44|66->45|66->45|66->45|67->46|67->46|67->46|68->47|68->47|68->47|69->48|69->48|69->48|70->49|70->49|70->49|71->50|71->50|71->50|77->56|77->56|78->57|79->58|79->58|81->60|81->60|81->60|82->61|83->62|83->62|84->63|85->64|85->64|87->66|87->66|89->68|89->68|90->69|92->71|92->71|94->73|94->73|94->73|95->74|96->75|96->75|97->76|98->77|98->77|99->78|99->78|99->78|100->79|101->80|101->80|102->81|102->81|102->81|103->82|105->84|105->84|106->85|106->85|108->87|108->87|108->87|109->88|110->89|110->89|111->90|112->91|112->91|113->92|113->92|113->92|114->93|116->95|116->95|117->96|117->96|119->98
                  -- GENERATED --
              */
          