(function() {
  var DOWN_KEY, ENTER_KEY, TAB_KEY, UP_KEY;

  window.ConsoleWidget = Ractive.extend({
    data: {
      input: '',
      agentTypes: ['observer', 'turtles', 'patches', 'links'],
      agentTypeIndex: 0,
      history: [],
      historyIndex: 0,
      workingEntry: {},
      output: ''
    },
    isolated: true,
    computed: {
      agentType: {
        get: function() {
          return this.get('agentTypes')[this.get('agentTypeIndex')];
        },
        set: function(val) {
          var index;
          index = this.get('agentTypes').indexOf(val);
          if (index >= 0) {
            return this.set('agentTypeIndex', index);
          }
        }
      }
    },
    components: {
      outputArea: OutputArea
    },
    oninit: function() {
      var changeAgentType, moveInHistory;
      changeAgentType = (function(_this) {
        return function() {
          return _this.set('agentTypeIndex', (_this.get('agentTypeIndex') + 1) % _this.get('agentTypes').length);
        };
      })(this);
      moveInHistory = (function(_this) {
        return function(index) {
          var entry, newIndex;
          newIndex = _this.get('historyIndex') + index;
          if (newIndex < 0) {
            newIndex = 0;
          } else if (newIndex > _this.get('history').length) {
            newIndex = _this.get('history').length;
          }
          if (_this.get('historyIndex') === _this.get('history').length) {
            _this.set('workingEntry', {
              agentType: _this.get('agentType'),
              input: _this.get('input')
            });
          }
          if (newIndex === _this.get('history').length) {
            _this.set(_this.get('workingEntry'));
          } else {
            entry = _this.get('history')[newIndex];
            _this.set(entry);
          }
          return _this.set('historyIndex', newIndex);
        };
      })(this);
      this.on('change-mode', function(event) {
        switch (event.original.which) {
          case TAB_KEY:
            changeAgentType();
            return false;
          case UP_KEY:
            moveInHistory(-1);
            return false;
          case DOWN_KEY:
            moveInHistory(1);
            return false;
          default:
            return true;
        }
      });
      return this.on('check-run', function(event) {
        var agentType, history, input, lastEntry;
        if (event.original.which === ENTER_KEY) {
          input = this.get('input');
          agentType = this.get('agentType');
          this.set('output', "" + (this.get('output')) + agentType + "> " + input + "\n");
          history = this.get('history');
          lastEntry = history.length > 0 ? history[history.length - 1] : {
            agentType: '',
            input: ''
          };
          if (lastEntry.input !== input || lastEntry.agentType !== agentType) {
            history.push({
              agentType: agentType,
              input: input
            });
          }
          this.set('historyIndex', history.length);
          if (agentType !== 'observer') {
            input = "ask " + agentType + " [ " + input + " ]";
          }
          this.fire('run', input);
          this.set('input', '');
          return this.set('workingEntry', {});
        }
      });
    },
    template: "<div class='netlogo-command-center netlogo-widget'>\n  <outputArea output='{{output}}'/>\n\n  <div class='netlogo-command-center-input'>\n    <label>\n      <select value=\"{{agentType}}\">\n      {{#agentTypes}}\n        <option value=\"{{.}}\">{{.}}</option>\n      {{/}}\n      </select>\n    </label>\n    <input type='text'\n           on-keypress='check-run'\n           on-keydown='change-mode'\n           value='{{input}}' />\n  </div>\n</div>"
  });

  ENTER_KEY = 13;

  TAB_KEY = 9;

  UP_KEY = 38;

  DOWN_KEY = 40;

}).call(this);

//# sourceMappingURL=console.js.map
