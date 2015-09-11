(function() {
  window.OutputArea = Ractive.extend({
    data: {
      output: ''
    },
    isolated: true,
    oninit: function() {
      return this.observe('output', function() {
        return this.update('output').then((function(_this) {
          return function() {
            var outputElem;
            outputElem = _this.find('.netlogo-output-area');
            return outputElem != null ? outputElem.scrollTop = outputElem.scrollHeight : void 0;
          };
        })(this));
      });
    },
    template: "<pre class='netlogo-output-area'>{{output}}</pre>"
  });

}).call(this);

//# sourceMappingURL=output.js.map
