(function() {
  var Connection;

  window.connect = function(socketURL) {
    return new Connection(new WebSocket(socketURL));
  };

  Connection = (function() {
    function Connection(socket) {
      this.socket = socket;
      this.listeners = {
        'all': []
      };
      this.outbox = [];
      this.socket.onmessage = (function(_this) {
        return function(event) {
          return _this.dispatch(JSON.parse(event.data));
        };
      })(this);
      this.socket.onopen = (function(_this) {
        return function() {
          var msg, _i, _len, _ref, _results;
          _ref = _this.outbox;
          _results = [];
          for (_i = 0, _len = _ref.length; _i < _len; _i++) {
            msg = _ref[_i];
            _results.push(_this.send(msg));
          }
          return _results;
        };
      })(this);
    }

    Connection.prototype.send = function(message) {
      if (this.socket.readyState === this.socket.OPEN) {
        return this.socket.send(JSON.stringify(message));
      } else {
        return this.outbox.push(message);
      }
    };

    Connection.prototype.dispatch = function(msg) {
      var listener, _i, _j, _len, _len1, _ref, _ref1, _results;
      _ref = this.listeners['all'];
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        listener = _ref[_i];
        listener(msg);
      }
      if (this.listeners[msg.kind] == null) {
        this.listeners[msg.kind] = [];
      }
      _ref1 = this.listeners[msg.kind];
      _results = [];
      for (_j = 0, _len1 = _ref1.length; _j < _len1; _j++) {
        listener = _ref1[_j];
        _results.push(listener(msg));
      }
      return _results;
    };

    Connection.prototype.on = function(kind, listener) {
      if (this.listeners[kind] == null) {
        this.listeners[kind] = [];
      }
      return this.listeners[kind].push(listener);
    };

    return Connection;

  })();

}).call(this);

//# sourceMappingURL=connection.js.map
