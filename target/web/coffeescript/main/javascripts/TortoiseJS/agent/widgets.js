(function() {
  var createPlotOps, fillOutWidgets, isValidValue, partials, template;

  window.bindWidgets = function(container, widgets, code, info, readOnly, filename) {
    var controller, model, mouse, output, outputWidget, plotOps, ractive, updateUICallback, viewController, viewModel, w, write;
    if (typeof container === 'string') {
      container = document.querySelector(container);
    }
    controller = null;
    updateUICallback = function() {
      controller.redraw();
      return controller.updateWidgets();
    };
    fillOutWidgets(widgets, updateUICallback);
    model = {
      widgets: widgets,
      speed: 0.0,
      ticks: "",
      width: Math.max.apply(Math, (function() {
        var _i, _len, _results;
        _results = [];
        for (_i = 0, _len = widgets.length; _i < _len; _i++) {
          w = widgets[_i];
          _results.push(w.right);
        }
        return _results;
      })()),
      height: Math.max.apply(Math, (function() {
        var _i, _len, _results;
        _results = [];
        for (_i = 0, _len = widgets.length; _i < _len; _i++) {
          w = widgets[_i];
          _results.push(w.bottom);
        }
        return _results;
      })()),
      code: code,
      info: info,
      readOnly: readOnly,
      exportForm: false,
      filename: filename,
      consoleOutput: '',
      outputWidgetOutput: '',
      markdown: markdown.toHTML
    };
    ractive = new Ractive({
      el: container,
      template: template,
      partials: partials,
      components: {
        editor: EditorWidget,
        console: ConsoleWidget,
        outputArea: OutputArea,
        infotab: InfoTabWidget
      },
      magic: true,
      data: model
    });
    viewModel = widgets.filter(function(w) {
      return w.type === 'view';
    })[0];
    viewController = new AgentStreamController(container.querySelector('.netlogo-view-container'), viewModel.fontSize);
    outputWidget = widgets.filter(function(w) {
      return w.type === 'output';
    })[0];
    plotOps = createPlotOps(container, widgets);
    mouse = {
      peekIsDown: function() {
        return viewController.mouseDown;
      },
      peekIsInside: function() {
        return viewController.mouseInside;
      },
      peekX: viewController.mouseXcor,
      peekY: viewController.mouseYcor
    };
    write = function(str) {
      return model.consoleOutput += str;
    };
    output = {
      write: function(str) {
        return model.outputWidgetOutput += str;
      },
      clear: function() {
        return model.outputWidgetOutput = "";
      }
    };
    ractive.observe('widgets.*.currentValue', function(newVal, oldVal, keyPath, widgetNum) {
      var widget;
      widget = widgets[widgetNum];
      if ((typeof world !== "undefined" && world !== null) && newVal !== oldVal && isValidValue(widget, newVal)) {
        return world.observer.setGlobal(widget.varName, newVal);
      }
    });
    ractive.on('activateButton', function(event) {
      return event.context.run();
    });
    return controller = new WidgetController(ractive, model, widgets, viewController, plotOps, mouse, write, output);
  };

  window.WidgetController = (function() {
    function WidgetController(ractive, model, widgets, viewController, plotOps, mouse, write, output) {
      this.ractive = ractive;
      this.model = model;
      this.widgets = widgets;
      this.viewController = viewController;
      this.plotOps = plotOps;
      this.mouse = mouse;
      this.write = write;
      this.output = output;
    }

    WidgetController.prototype.runForevers = function() {
      var widget, _i, _len, _ref, _results;
      _ref = this.widgets;
      _results = [];
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        widget = _ref[_i];
        if (widget.type === 'button' && widget.forever && widget.running) {
          _results.push(widget.run());
        } else {
          _results.push(void 0);
        }
      }
      return _results;
    };

    WidgetController.prototype.updateWidgets = function() {
      var err, i, isInvalidNumber, maxValue, minValue, stepValue, widget, _i, _len, _ref;
      _ref = this.widgets;
      for (i = _i = 0, _len = _ref.length; _i < _len; i = ++_i) {
        widget = _ref[i];
        if (widget.currentValue != null) {
          if (widget.varName != null) {
            widget.currentValue = world.observer.getGlobal(widget.varName);
          } else if (widget.reporter != null) {
            try {
              widget.currentValue = widget.reporter();
              isInvalidNumber = function(n) {
                return isNaN(n) || (n === (void 0) || n === null || n === Infinity || n === (-Infinity));
              };
              if (typeof widget.currentValue === "number" && isInvalidNumber(widget.currentValue)) {
                widget.currentValue = 'N/A';
              }
            } catch (_error) {
              err = _error;
              widget.currentValue = 'N/A';
            }
          }
          if ((widget.precision != null) && typeof widget.currentValue === 'number' && isFinite(widget.currentValue)) {
            widget.currentValue = NLMath.precision(widget.currentValue, widget.precision);
          }
        }
        if (widget['type'] === 'slider') {
          maxValue = widget.getMax();
          stepValue = widget.getStep();
          minValue = widget.getMin();
          if (widget.maxValue !== maxValue || widget.stepValue !== stepValue || widget.minValue !== minValue) {
            widget.maxValue = maxValue;
            widget.stepValue = stepValue;
            widget.minValue = minValue - 0.000001;
            widget.minValue = minValue;
          }
        }
      }
      try {
        return this.model.ticks = Math.floor(world.ticker.tickCount());
      } catch (_error) {
        err = _error;
      }
    };

    WidgetController.prototype.speed = function() {
      return this.model.speed;
    };

    WidgetController.prototype.redraw = function() {
      if (Updater.hasUpdates()) {
        return this.viewController.update(Updater.collectUpdates());
      }
    };

    WidgetController.prototype.teardown = function() {
      return this.ractive.teardown();
    };

    WidgetController.prototype.code = function() {
      return this.ractive.get('code');
    };

    return WidgetController;

  })();

  fillOutWidgets = function(widgets, updateUICallback) {
    var plotCount, task, widget, _i, _len, _results;
    plotCount = 0;
    _results = [];
    for (_i = 0, _len = widgets.length; _i < _len; _i++) {
      widget = widgets[_i];
      if (widget.varName != null) {
        widget.varName = widget.varName.toLowerCase();
      }
      switch (widget['type']) {
        case "switch":
          _results.push(widget.currentValue = widget.on);
          break;
        case "slider":
          widget.currentValue = widget["default"];
          widget.getMin = new Function("return " + widget.compiledMin.result);
          widget.getMax = new Function("return " + widget.compiledMax.result);
          widget.getStep = new Function("return " + widget.compiledStep.result);
          widget.minValue = widget["default"];
          widget.maxValue = widget["default"] + 1;
          _results.push(widget.stepValue = 1);
          break;
        case "inputBox":
          _results.push(widget.currentValue = widget.value);
          break;
        case "button":
          if (widget.forever) {
            widget.running = false;
          }
          if (widget.compiledSource.success) {
            task = new Function(widget.compiledSource.result);
            _results.push((function(task) {
              return widget.run = widget.forever ? task : function() {
                task();
                return updateUICallback();
              };
            })(task));
          } else {
            _results.push(widget.run = function() {
              var res;
              return alert("Button failed to compile with:\n" + ((function() {
                var _j, _len1, _ref, _results1;
                _ref = widget.compiledSource.result;
                _results1 = [];
                for (_j = 0, _len1 = _ref.length; _j < _len1; _j++) {
                  res = _ref[_j];
                  _results1.push(res.message);
                }
                return _results1;
              })()).join('\n'));
            });
          }
          break;
        case "chooser":
          _results.push(widget.currentValue = widget.choices[widget.currentChoice]);
          break;
        case "monitor":
          widget.reporter = new Function("return " + widget.compiledSource.result);
          _results.push(widget.currentValue = "");
          break;
        case "plot":
          _results.push(widget.plotNumber = plotCount++);
          break;
        default:
          _results.push(void 0);
      }
    }
    return _results;
  };

  createPlotOps = function(container, widgets) {
    var plotOps, widget, _i, _len;
    plotOps = {};
    for (_i = 0, _len = widgets.length; _i < _len; _i++) {
      widget = widgets[_i];
      if (widget.type === "plot") {
        plotOps[widget.display] = new HighchartsOps(container.querySelector(".netlogo-plot-" + widget.plotNumber));
      }
    }
    return plotOps;
  };

  isValidValue = function(widget, value) {
    switch (widget.type) {
      case 'slider':
        return !isNaN(value);
      case 'inputBox':
        return !(widget.boxtype === 'Number' && isNaN(value));
      default:
        return true;
    }
  };

  template = "<div class=\"netlogo-model\" style=\"width: {{width}}px;\">\n  <div class=\"netlogo-header\">\n    <label class=\"netlogo-widget netlogo-speed-slider\">\n      <span class=\"netlogo-label\">speed</span>\n      <input type=\"range\" min=-1 max=1 step=0.01 value={{speed}} />\n    </label>\n\n    <div class=\"netlogo-subheader\">\n      {{# !readOnly }}\n      <button class=\"netlogo-export-button\" style=\"margin-bottom: 10px;\" id=\"export-button\" on-click=\"exportnlogo\">Export to NetLogo...</button>\n      {{/}}\n      <div class=\"netlogo-powered-by\">\n        <a href=\"http://ccl.northwestern.edu/netlogo/\">\n          <img style=\"vertical-align: middle;\" alt=\"NetLogo\" src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAUCAYAAACNiR0NAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAANcSURBVHjarJRdaFxFFMd/M/dj7252uxubKms+bGprVyIVbNMWWqkQqtLUSpQWfSiV+oVFTcE3DeiDgvoiUSiCYLH2oVoLtQ+iaaIWWtE2FKGkkSrkq5svN+sm7ma/7p3x4W42lEbjQw8MM8yc87/nzPnNFVprbqWJXyMyXuMqx1Ni6N3ny3cX8tOHNLoBUMvESoFI2Xbs4zeO1lzREpSrMSNS1zkBDv6uo1/noz1H7mpvS4SjprAl2AZYEqzKbEowBAgBAkjPKX2599JjT7R0bj412D0JYNplPSBD1G2SmR/e6u1ikEHG2vYiGxoJmxAyIGSCI8GpCItKimtvl2JtfGujDNkX6epuAhCjNeAZxM1ocPy2Qh4toGQ5DLU+ysiuA2S3P0KgJkjAgEAlQylAA64CG/jlUk6//ng4cNWmLK0yOPNMnG99Rs9LQINVKrD+wmke7upg55PrWP3eYcwrlykpKCkoelDy/HVegQhoABNAepbACwjOt72gZkJhypX70YDWEEklue+rbnYc2MiGp1upPfYReiJJUUG58gFXu4udch1wHcjFIgy0HyIjb2yvBpT2F6t+6+f+D15lW8c9JDo7iPSdgVIRLUqL2AyHDQAOf9hfbqxvMF98eT3RuTS1avHyl+Stcphe2chP9+4k/t3RbXVl3W+Ws17FY56/w3VcbO/koS/eZLoAqrQMxADZMTYOfwpwoWjL4+bCYcgssMqGOzPD6CIkZ/3SxTJ0ayFIN6/BnBrZb2XdE1JUgkJWkfrUNRJnPyc16zsbgPyXIUJBpvc+y89nk/S8/4nek3NPGeBWMwzGvhUPnP6RubRLwfODlqqx3LSCyee2MnlwMwA2RwgO5qouVcHmksUdJweYyi8hZkrUjgT5t/ejNq0jBsSqNWsKyT9uFtxw7Bs585d3g46KOeT2bWHmtd14KyP+5mzqpsYU3OyioACMhGiqPTMocsrHId9cy9BLDzKxq8X3ctMwlV6yKSHL4fr4dd0DeQBTBUgUkvpE1kVPbqkX117ZzuSaFf4zyfz5n9A4lk0yNU7vyb7jTy1kmFGipejKvh6h9n0W995ZPTu227hqmCz33xXgFV1v9NzI96NfjndWt7XWCB/7BSICFWL+j3lAofpCtfYFb6X9MwCJZ07mUsXRGwAAAABJRU5ErkJggg==\"/>\n          <span style=\"font-size: 16px;\">powered by NetLogo</span>\n        </a>\n      </div>\n    </div>\n  </div>\n\n  <div style=\"position: relative; width: {{width}}px; height: {{height}}px\"\n       class=\"netlogo-widget-container\">\n    {{#widgets}}\n      {{# type === 'view'               }} {{>view         }} {{/}}\n      {{# type === 'textBox'            }} {{>textBox      }} {{/}}\n      {{# type === 'switch'             }} {{>switcher     }} {{/}}\n      {{# type === 'button'  && !forever}} {{>button       }} {{/}}\n      {{# type === 'button'  &&  forever}} {{>foreverButton}} {{/}}\n      {{# type === 'slider'             }} {{>slider       }} {{/}}\n      {{# type === 'chooser'            }} {{>chooser      }} {{/}}\n      {{# type === 'monitor'            }} {{>monitor      }} {{/}}\n      {{# type === 'inputBox'           }} {{>inputBox     }} {{/}}\n      {{# type === 'plot'               }} {{>plot         }} {{/}}\n      {{# type === 'output'             }} {{>output       }} {{/}}\n    {{/}}\n  </div>\n\n  <div class=\"netlogo-tabs\">\n    {{# !readOnly }}\n    <label class=\"netlogo-console-tab netlogo-tab netlogo-command {{#showConsole}}netlogo-active{{/}}\">\n      <input type=\"checkbox\" checked=\"{{showConsole}}\" />\n      Command Center\n    </label>\n    {{/}}\n    <label class=\"netlogo-code-tab netlogo-tab netlogo-command {{#showCode}}netlogo-active{{/}}\">\n      <input type=\"checkbox\" checked=\"{{ showCode }}\" />\n      NetLogo Code\n    </label>\n    <label class=\"netlogo-info-tab netlogo-tab netlogo-command {{#showInfo}}netlogo-active{{/}}\">\n      <input type=\"checkbox\" checked=\"{{ showInfo }}\" />\n      Model Info\n    </label>\n  </div>\n  <div class=\"netlogo-model-text\">\n    {{# !readOnly }}\n    {{#showConsole}}\n      <console output=\"{{consoleOutput}}\"/>\n    {{/}}\n    {{/}}\n    {{#showCode}}\n      <editor code='{{code}}' readOnly='{{readOnly}}' />\n    {{/}}\n    {{#showInfo}}\n      <infotab rawText='{{info}}' editing='false' />\n    {{/}}\n  </div>\n</div>";

  partials = {
    view: "<div class=\"netlogo-widget netlogo-view-container\" style=\"{{>dimensions}}\">\n  <div class=\"netlogo-widget netlogo-tick-counter\">\n    {{# showTickCounter}}\n      {{tickCounterLabel}}: <span>{{ticks}}</span>\n    {{/}}\n  </div>\n</div>",
    textBox: "<pre class=\"netlogo-widget netlogo-text-box\" style=\"{{>dimensions}} font-size: {{fontSize}}px;\">{{ display }}</pre>",
    switcher: "<label class=\"netlogo-widget netlogo-switcher netlogo-input\" style=\"{{>dimensions}}\">\n  <input type=\"checkbox\" checked={{ currentValue }} />\n  <span class=\"netlogo-label\">{{ display }}</span>\n</label>",
    slider: "<label class=\"netlogo-widget netlogo-slider netlogo-input\" style=\"{{>dimensions}}\">\n  <input type=\"range\"\n         max=\"{{maxValue}}\" min=\"{{minValue}}\" step=\"{{step}}\" value=\"{{currentValue}}\" />\n  <div class=\"netlogo-slider-label\">\n    <span class=\"netlogo-label\">{{display}}</span>\n    <span class=\"netlogo-slider-value\">\n      <input type=\"number\"\n             style=\"width: {{currentValue.toString().length + 2.5}}ch\"\n             min={{minValue}} max={{maxValue}} value={{currentValue}} step={{step}} />\n      {{#units}}{{units}}{{/}}\n    </span>\n  </div>\n</label>",
    button: "<button class=\"netlogo-widget netlogo-button netlogo-command\"\n       type=\"button\"\n       style=\"{{>dimensions}}\"\n       on-click=\"activateButton\">\n  <span>{{display || source}}</span>\n</button>",
    foreverButton: "<label class=\"netlogo-widget netlogo-button netlogo-forever-button {{#running}}netlogo-active{{/}} netlogo-command\"\n       style=\"{{>dimensions}}\">\n  <input type=\"checkbox\" checked={{ running }} />\n  <span class=\"netlogo-label\">{{display || source}}</span>\n</label>",
    chooser: "<label class=\"netlogo-widget netlogo-chooser netlogo-input\" style=\"{{>dimensions}}\">\n  <span class=\"netlogo-label\">{{display}}</span>\n  <select class=\"netlogo-chooser-select\" value=\"{{currentValue}}\">\n  {{#choices}}\n    <option class=\"netlogo-chooser-option\" value=\"{{.}}\">{{>literal}}</option>\n  {{/}}\n  </select>\n</label>",
    monitor: "<div class=\"netlogo-widget netlogo-monitor netlogo-output\" style=\"{{>dimensions}} font-size: {{fontSize}}px;\">\n  <div class=\"netlogo-label\">{{display || source}}</div>\n  <output class=\"netlogo-value\">{{currentValue}}</output>\n</div>",
    inputBox: "<label class=\"netlogo-widget netlogo-input-box netlogo-input\" style=\"{{>dimensions}}\">\n  <div class=\"netlogo-label\">{{varName}}</div>\n  {{# boxtype === 'Number'}}<input type=\"number\" value=\"{{currentValue}}\" />{{/}}\n  {{# boxtype === 'String'}}<input type=\"text\" value=\"{{currentValue}}\" />{{/}}\n  {{# boxtype === 'String (reporter)'}}<input type=\"text\" value=\"{{currentValue}}\" />{{/}}\n  {{# boxtype === 'String (commands)'}}<input type=\"text\" value=\"{{currentValue}}\" />{{/}}\n  <!-- TODO: Fix color input. It'd be nice to use html5s color input. -->\n  {{# boxtype === 'Color'}}<input type=\"color\" value=\"{{currentValue}}\" />{{/}}\n</label>",
    plot: "<div class=\"netlogo-widget netlogo-plot netlogo-plot-{{plotNumber}} netlogo-output\"\n     style=\"{{>dimensions}}\"></div>",
    output: "<div class=\"netlogo-widget netlogo-output netlogo-output-widget\" style=\"{{>dimensions}}\">\n  <outputArea output=\"{{outputWidgetOutput}}\"/>\n</div>",
    literal: "{{# typeof . === \"string\"}}{{.}}{{/}}\n{{# typeof . === \"number\"}}{{.}}{{/}}\n{{# typeof . === \"object\"}}\n  [{{#.}}\n    {{>literal}}\n  {{/}}]\n{{/}}",
    dimensions: "position: absolute;\nleft: {{ left }}px; top: {{ top }}px;\nwidth: {{ right - left }}px; height: {{ bottom - top }}px;"
  };

}).call(this);

//# sourceMappingURL=widgets.js.map
