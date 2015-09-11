(function() {
  var TortoiseSession, ajax, attachEditor, createSession, reifyElement;

  reifyElement = function(elem) {
    if (typeof elem === "string") {
      return document.querySelector(elem);
    } else {
      return elem;
    }
  };

  window.tortoise = function(containers, socketURL) {
    var code, elem, refinedContainers, session, srcURL;
    elem = reifyElement(containers.view_container || '.netlogo-model');
    if (socketURL == null) {
      socketURL = elem.dataset.url;
    }
    srcURL = elem.dataset.src;
    code = elem.textContent;
    elem.textContent = '';
    refinedContainers = {
      code_container: containers.code_container || '.netlogo-code',
      info_container: containers.info_container || '.netlogo-model-info',
      view_container: elem
    };
    session = createSession(refinedContainers, socketURL);
    if (srcURL != null) {
      session.openURL(srcURL);
    } else if (code.trim()) {
      editor.setValue(code);
      editor.clearSelection();
      editor.getSelection().moveCursorFileStart();
    }
    return session;
  };

  createSession = function(containers, socketURL) {
    var codeElem, connection, container, controller, editor, elem, session;
    elem = containers.view_container;
    if (elem.querySelector('div.view-container') == null) {
      container = document.createElement('div');
      container.classList.add('view-container');
      elem.appendChild(container);
      elem.appendChild(document.createElement('div'));
    }
    codeElem = reifyElement(containers.code_container);
    editor = attachEditor(codeElem);
    controller = new AgentStreamController(container);
    connection = connect(socketURL);
    session = new TortoiseSession(connection, controller, editor, reifyElement(containers.info_container));
    return session;
  };

  attachEditor = function(elem) {
    var editor, editorElem;
    editorElem = document.createElement('div');
    editorElem.style.height = "450px";
    elem.appendChild(editorElem);
    editor = ace.edit(editorElem);
    editor.setTheme('ace/theme/netlogo-classic');
    editor.getSession().setMode('ace/mode/netlogo');
    editor.setFontSize('11px');
    editor.renderer.setShowGutter(false);
    editor.setShowPrintMargin(false);
    return editor;
  };

  TortoiseSession = (function() {
    function TortoiseSession(connection, controller, editor, _infoContainer) {
      var compileTimeout;
      this.connection = connection;
      this.controller = controller;
      this.editor = editor;
      this._infoContainer = _infoContainer;
      this.connection.on('update', (function(_this) {
        return function(msg) {
          return _this.update(JSON.parse(msg.message));
        };
      })(this));
      this.connection.on('js', (function(_this) {
        return function(msg) {
          return _this.runJSCommand(msg.message);
        };
      })(this));
      this.connection.on('model_update', (function(_this) {
        return function(msg) {
          return _this.evalJSModel(msg.message.code, msg.message.info);
        };
      })(this));
      compileTimeout = -1;
      this.editor.session.on('change', (function(_this) {
        return function() {
          clearTimeout(compileTimeout);
          return compileTimeout = setTimeout((function() {
            return _this.recompile();
          }), 500);
        };
      })(this));
      this.run('compile', '');
    }

    TortoiseSession.prototype.update = function(modelUpdate) {
      var update, _i, _len;
      if (modelUpdate instanceof Array) {
        for (_i = 0, _len = modelUpdate.length; _i < _len; _i++) {
          update = modelUpdate[_i];
          this.controller.update(update);
        }
      } else {
        this.controller.update(modelUpdate);
      }
      return this.controller.repaint();
    };

    TortoiseSession.prototype.evalJSModel = function(code, info) {
      this.setInfo(info);
      eval.call(window, code);
      return this.update(Updater.collectUpdates());
    };

    TortoiseSession.prototype.runJSCommand = function(js) {
      (new Function(js)).call(window, js);
      return this.update(Updater.collectUpdates());
    };

    TortoiseSession.prototype.run = function(agentType, cmd) {
      return this.connection.send({
        agentType: agentType,
        cmd: cmd
      });
    };

    TortoiseSession.prototype.openURL = function(nlogoURL) {
      return ajax(nlogoURL, (function(_this) {
        return function(nlogoContents) {
          return _this.open(nlogoContents);
        };
      })(this));
    };

    TortoiseSession.prototype.open = function(nlogoContents) {
      var code, delim, endOfCode, info, tail, tailTail;
      this.run('open', nlogoContents);
      delim = '@#$#@#$#@';
      if (this.editor != null) {
        endOfCode = nlogoContents.indexOf(delim);
        if (endOfCode >= 0) {
          code = nlogoContents.substring(0, endOfCode);
        }
        this.editor.setValue(code);
        this.editor.clearSelection();
        tail = nlogoContents.substring(endOfCode + delim.length + 1);
        tailTail = tail.substring(tail.indexOf(delim) + delim.length + 1);
        info = tailTail.substring(0, tailTail.indexOf(delim));
        return this.setInfo(info);
      }
    };

    TortoiseSession.prototype.setInfo = function(info) {
      var html;
      html = info.trim() !== "" ? markdown.toHTML(info) : "<span style='font-size: 20px;'>No info available.</span>";
      return this._infoContainer.innerHTML = html;
    };

    TortoiseSession.prototype.recompile = function() {
      console.log('Sending recompile request');
      return this.run('compile', this.editor.getValue());
    };

    return TortoiseSession;

  })();

  ajax = function(url, callback) {
    var req;
    req = new XMLHttpRequest();
    req.onreadystatechange = function() {
      if (req.readyState === req.DONE) {
        return callback(req.responseText);
      }
    };
    req.open('GET', url);
    return req.send();
  };

}).call(this);

//# sourceMappingURL=tortoise.js.map
