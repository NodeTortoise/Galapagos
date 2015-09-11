(function() {
  var DEFAULT_REDRAW_DELAY, DEFAULT_UPDATE_DELAY, FAST_UPDATE_EXP, MAX_REDRAW_DELAY, MAX_UPDATE_DELAY, MAX_UPDATE_TIME, REDRAW_EXP, SLOW_UPDATE_EXP, globalEval, makeCompileCallback, now, _ref,
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
      this.widgetController.ractive.on('console.run', (function(_this) {
        return function(code) {
          return _this.run(code);
        };
      })(this));
    }

    SessionLite.prototype.startLoop = function() {
      this.widgetController.updateWidgets();
      if (typeof startup !== "undefined" && startup !== null) {
        Call(startup);
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
      maxNumUpdates = (now() - this._lastUpdate) / this.updateDelay();
      for (i = _i = 1; _i <= maxNumUpdates; i = _i += 1) {
        this._lastUpdate = now();
        this.widgetController.runForevers();
        if (now() >= updatesDeadline) {
          break;
        }
      }
      if (i > maxNumUpdates || now() - this._lastRedraw > this.redrawDelay()) {
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
      return compile('code', this.widgetController.code(), [], [], this.widgetController.widgets, function(res) {
        if (res.model.success) {
          return globalEval(res.model.result);
        } else {
          return alert(res.model.result.map(function(err) {
            return err.message;
          }).join('\n'));
        }
      });
    };

    SessionLite.prototype.run = function(code) {
      return compile('code', this.widgetController.code(), [code], [], this.widgetController.widgets, function(res) {
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
      }, function(err) {
        return alert(err);
      });
    };

    return SessionLite;

  })();

  window.Tortoise = {
    fromNlogo: function(nlogo, container, callback) {
      return compile("nlogo", nlogo, [], [], [], makeCompileCallback(container, callback));
    },
    fromURL: function(url, container, callback) {
      return compile("url", url, [], [], [], makeCompileCallback(container, callback));
    },
    fromCompiledModel: function(container, widgets, code, info, compiledSource, readOnly) {
      var widgetController;
      if (compiledSource == null) {
        compiledSource = "";
      }
      if (readOnly == null) {
        readOnly = false;
      }
      widgetController = bindWidgets(container, widgets, code, info, readOnly);
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

  makeCompileCallback = function(container, callback) {
    return function(res) {
      if (res.model.success) {
        return callback(Tortoise.fromCompiledModel(container, res.widgets, res.code, res.info, res.model.result));
      } else {
        return container.innerHTML = res.model.result.map(function(err) {
          return err.message;
        }).join('<br/>');
      }
    };
  };

  window.compile = function(source, model, commands, reporters, widgets, onFulfilled, onRejected) {
    var compileCallback, compileParams;
    if (onRejected == null) {
      onRejected = function(s) {
        throw s;
      };
    }
    compileParams = {
      model: model,
      widgets: JSON.stringify(widgets),
      commands: JSON.stringify(commands),
      reporters: JSON.stringify(reporters)
    };
    compileCallback = function(res) {
      return onFulfilled(JSON.parse(res));
    };
    return ajax('/compile-' + source, compileParams, compileCallback);
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
