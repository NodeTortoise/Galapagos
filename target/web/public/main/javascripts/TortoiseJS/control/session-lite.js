(function() {
  var DEFAULT_REDRAW_DELAY, DEFAULT_UPDATE_DELAY, FAST_UPDATE_EXP, MAX_REDRAW_DELAY, MAX_UPDATE_DELAY, MAX_UPDATE_TIME, REDRAW_EXP, SLOW_UPDATE_EXP, browserCompileCallback, globalEval, now, _ref,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  DEFAULT_UPDATE_DELAY = 1000 / 60;

  MAX_UPDATE_DELAY = 1000;

  FAST_UPDATE_EXP = 0.5;

  SLOW_UPDATE_EXP = 4;

  MAX_UPDATE_TIME = 100;

  DEFAULT_REDRAW_DELAY = 1000 / 30;

  MAX_REDRAW_DELAY = 1000;

  REDRAW_EXP = 2;

  window.SessionLite = (function() {
    function SessionLite(widgetController) {
      this.widgetController = widgetController;
      this.eventLoop = __bind(this.eventLoop, this);
      this._eventLoopTimeout = -1;
      this._lastRedraw = 0;
      this._lastUpdate = 0;
      this.widgetController.ractive.on('editor.recompile', (function(_this) {
        return function(event) {
          return _this.recompile();
        };
      })(this));
      this.widgetController.ractive.on('exportnlogo', (function(_this) {
        return function(event) {
          return _this.exportnlogo(event);
        };
      })(this));
      this.widgetController.ractive.on('console.run', (function(_this) {
        return function(code) {
          return _this.run(code);
        };
      })(this));
      this.drawEveryFrame = false;
    }

    SessionLite.prototype.startLoop = function() {
      this.widgetController.updateWidgets();
      if (procedures.startup != null) {
        Call(procedures.startup);
      }
      return requestAnimationFrame(this.eventLoop);
    };

    SessionLite.prototype.updateDelay = function() {
      var speed, speedFactor;
      speed = this.widgetController.speed();
      if (speed > 0) {
        speedFactor = Math.pow(Math.abs(speed), FAST_UPDATE_EXP);
        return DEFAULT_UPDATE_DELAY * (1 - speedFactor);
      } else {
        speedFactor = Math.pow(Math.abs(speed), SLOW_UPDATE_EXP);
        return MAX_UPDATE_DELAY * speedFactor + DEFAULT_UPDATE_DELAY * (1 - speedFactor);
      }
    };

    SessionLite.prototype.redrawDelay = function() {
      var speed, speedFactor;
      speed = this.widgetController.speed();
      if (speed > 0) {
        speedFactor = Math.pow(Math.abs(this.widgetController.speed()), REDRAW_EXP);
        return MAX_REDRAW_DELAY * speedFactor + DEFAULT_REDRAW_DELAY * (1 - speedFactor);
      } else {
        return DEFAULT_REDRAW_DELAY;
      }
    };

    SessionLite.prototype.eventLoop = function(timestamp) {
      var i, maxNumUpdates, updatesDeadline, _i;
      this._eventLoopTimeout = requestAnimationFrame(this.eventLoop);
      updatesDeadline = Math.min(this._lastRedraw + this.redrawDelay(), now() + MAX_UPDATE_TIME);
      maxNumUpdates = this.drawEveryFrame ? 1 : (now() - this._lastUpdate) / this.updateDelay();
      for (i = _i = 1; _i <= maxNumUpdates; i = _i += 1) {
        this._lastUpdate = now();
        this.widgetController.runForevers();
        if (now() >= updatesDeadline) {
          break;
        }
      }
      if (i > maxNumUpdates || now() - this._lastRedraw > this.redrawDelay() || this.drawEveryFrame) {
        this._lastRedraw = now();
        this.widgetController.redraw();
      }
      return this.widgetController.updateWidgets();
    };

    SessionLite.prototype.teardown = function() {
      this.widgetController.teardown();
      return cancelAnimationFrame(this._eventLoopTimeout);
    };

    SessionLite.prototype.recompile = function() {
      world.clearAll();
      this.widgetController.redraw();
      return codeCompile(this.widgetController.code(), [], [], this.widgetController.widgets, function(res) {
        if (res.model.success) {
          return globalEval(res.model.result);
        } else {
          return alert(res.model.result.map(function(err) {
            return err.message;
          }).join('\n'));
        }
      });
    };

    SessionLite.prototype.exportnlogo = function() {
      var exportBlob, exportRequest, exportedNLogo, filename;
      filename = window.prompt('Filename:', this.widgetController.ractive.get('filename'));
      if (filename != null) {
        exportRequest = {
          info: this.widgetController.ractive.get('info'),
          code: this.widgetController.ractive.get('code'),
          widgets: this.widgetController.widgets,
          turtleShapes: turtleShapes,
          linkShapes: linkShapes
        };
        exportedNLogo = (new BrowserCompiler()).exportNlogo(exportRequest);
        if (exportedNLogo.success) {
          exportBlob = new Blob([exportedNLogo.result], {
            type: "text/plain:charset=utf-8"
          });
          return saveAs(exportBlob, filename);
        } else {
          return alert(exportedNLogo.result.map(function(err) {
            return err.message;
          }).join('\n'));
        }
      }
    };

    SessionLite.prototype.makeForm = function(method, path, data) {
      var field, form, name, value;
      form = document.createElement('form');
      form.setAttribute('method', method);
      form.setAttribute('action', path);
      for (name in data) {
        value = data[name];
        field = document.createElement('input');
        field.setAttribute('type', 'hidden');
        field.setAttribute('name', name);
        field.setAttribute('value', value);
        form.appendChild(field);
      }
      return form;
    };

    SessionLite.prototype.run = function(code) {
      return codeCompile(this.widgetController.code(), [code], [], this.widgetController.widgets, function(res) {
        var result, success;
        success = res.commands[0].success;
        result = res.commands[0].result;
        if (success) {
          return new Function(result)();
        } else {
          return alert(result.map(function(err) {
            return err.message;
          }).join('\n'));
        }
      });
    };

    return SessionLite;

  })();

  window.Tortoise = {
    normalizedFileName: function(path) {
      var pathComponents;
      pathComponents = path.split(/\/|\\/);
      return decodeURI(pathComponents[pathComponents.length - 1]);
    },
    fromNlogo: function(nlogo, container, path, callback) {
      return nlogoCompile(nlogo, [], [], [], browserCompileCallback(container, callback, this.normalizedFileName(path)));
    },
    fromURL: function(url, container, callback) {
      var req;
      req = new XMLHttpRequest();
      req.open('GET', url);
      req.onreadystatechange = (function(_this) {
        return function() {
          if (req.readyState === req.DONE) {
            return nlogoCompile(req.responseText, [], [], [], browserCompileCallback(container, callback, _this.normalizedFileName(url)));
          }
        };
      })(this);
      return req.send("");
    },
    fromCompiledModel: function(container, widgetString, code, info, compiledSource, readOnly, filename) {
      var widgetController, widgets;
      if (compiledSource == null) {
        compiledSource = "";
      }
      if (readOnly == null) {
        readOnly = false;
      }
      if (filename == null) {
        filename = "export.nlogo";
      }
      widgets = globalEval(widgetString);
      widgetController = bindWidgets(container, widgets, code, info, readOnly, filename);
      if (window.modelConfig == null) {
        window.modelConfig = {};
      }
      modelConfig.plotOps = widgetController.plotOps;
      modelConfig.mouse = widgetController.mouse;
      modelConfig.print = {
        write: widgetController.write
      };
      modelConfig.output = widgetController.output;
      globalEval(compiledSource);
      return new SessionLite(widgetController);
    }
  };

  window.AgentModel = tortoise_require('agentmodel');

  browserCompileCallback = function(container, callback, filename) {
    return function(res) {
      if (res.model.success) {
        return callback(Tortoise.fromCompiledModel(container, res.widgets, res.code, res.info, res.model.result, false, filename));
      } else {
        return container.innerHTML = res.model.result.map(function(err) {
          return err.message;
        }).join('<br/>');
      }
    };
  };

  window.nlogoCompile = function(model, commands, reporters, widgets, onFulfilled) {
    return onFulfilled((new BrowserCompiler()).fromNlogo(model, commands));
  };

  window.codeCompile = function(code, commands, reporters, widgets, onFulfilled) {
    var compileParams;
    compileParams = {
      code: code,
      widgets: widgets,
      commands: commands,
      reporters: reporters,
      turtleShapes: typeof turtleShapes !== "undefined" && turtleShapes !== null ? turtleShapes : [],
      linkShapes: typeof linkShapes !== "undefined" && linkShapes !== null ? linkShapes : []
    };
    return onFulfilled((new BrowserCompiler()).fromModel(compileParams));
  };

  window.serverNlogoCompile = function(model, commands, reporters, widgets, onFulfilled) {
    var compileCallback, compileParams;
    compileParams = {
      model: model,
      commands: JSON.stringify(commands),
      reporters: JSON.stringify(reporters)
    };
    compileCallback = function(res) {
      return onFulfilled(JSON.parse(res));
    };
    return ajax('/compile-nlogo', compileParams, compileCallback);
  };

  window.serverCodeCompile = function(code, commands, reporters, widgets, onFulfilled) {
    var compileCallback, compileParams;
    compileParams = {
      code: code,
      widgets: JSON.stringify(widgets),
      commands: JSON.stringify(commands),
      reporters: JSON.stringify(reporters),
      turtleShapes: JSON.stringify(typeof turtleShapes !== "undefined" && turtleShapes !== null ? turtleShapes : []),
      linkShapes: JSON.stringify(typeof linkShapes !== "undefined" && linkShapes !== null ? linkShapes : [])
    };
    compileCallback = function(res) {
      return onFulfilled(JSON.parse(res));
    };
    return ajax('/compile-code', compileParams, compileCallback);
  };

  window.ajax = function(url, params, callback) {
    var key, paramPairs, req, value;
    paramPairs = (function() {
      var _results;
      _results = [];
      for (key in params) {
        value = params[key];
        _results.push(encodeURIComponent(key) + '=' + encodeURIComponent(value));
      }
      return _results;
    })();
    req = new XMLHttpRequest();
    req.open('POST', url);
    req.onreadystatechange = function() {
      if (req.readyState === req.DONE) {
        return callback(req.responseText);
      }
    };
    req.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    return req.send(paramPairs.join('&'));
  };

  globalEval = eval;

  now = (_ref = typeof performance !== "undefined" && performance !== null ? performance.now.bind(performance) : void 0) != null ? _ref : Date.now.bind(Date);

}).call(this);

//# sourceMappingURL=session-lite.js.map
