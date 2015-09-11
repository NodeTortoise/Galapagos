(function() {
  var InfoTabEditor;

  InfoTabEditor = Ractive.extend({
    onrender: function() {
      window.infoTabEditor = CodeMirror(this.find('.netlogo-info-editor'), {
        value: this.get('rawText'),
        tabsize: 2,
        mode: 'markdown',
        theme: 'netlogo-default',
        editing: this.get('editing'),
        lineWrapping: true
      });
      return infoTabEditor.on('change', (function(_this) {
        return function() {
          return _this.set('rawText', infoTabEditor.getValue());
        };
      })(this));
    },
    template: "<div class='netlogo-info-editor'></div>"
  });

  window.InfoTabWidget = Ractive.extend({
    components: {
      infoeditor: InfoTabEditor
    },
    template: "<div class='netlogo-info'>\n  <label class='netlogo-toggle-edit-mode'>\n    <input type='checkbox' checked='{{editing}}'>\n    Edit Mode\n  </label>\n  {{# !editing }}\n    <div class='netlogo-info-markdown'>{{{markdown(rawText)}}}</div>\n  {{ else }}\n    <infoeditor rawText='{{rawText}}' />\n  {{ / }}\n</div>"
  });

}).call(this);

//# sourceMappingURL=info.js.map
